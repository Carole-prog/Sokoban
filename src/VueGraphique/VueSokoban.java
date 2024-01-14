package VueGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;

import modele.*;

public class VueSokoban {
    private Lecture fichier;
    private int larg_icon = 50;
    private int long_icon = 50;
    private Carte MenuCard;
    private long debut;
    private HashMap<Class<? extends Element>, ImageIcon> images = new HashMap<>();
    private HashMap<String, Lecture> niveaux = new HashMap<>();

    /**
     * Constructeur de la class VueSokoban qui prend en paramètre une class Lecture.
     * 
     * @param nouv_fichier
     */
    public VueSokoban(Lecture nouv_fichier) {
        this.fichier = nouv_fichier;
        this.MenuCard = new Carte(fichier);
        initImage();
    }

    /** Méthode qui permet d'initialiser les HashMap */
    private void initImage() {
        images.put(Caisse.class, resizeImage(
                combinaisonImage(VueSokoban.class.getResource("Images/PNG/Retina/Ground/ground_04.png"),
                        VueSokoban.class.getResource("/Images/PNG/Retina/Crates/crate_02.png"))));
        images.put(Destination.class,
                resizeImage(new ImageIcon(VueSokoban.class.getResource("/Images/PNG/Retina/Ground/ground_01.png"))));
        images.put(Mur.class,
                resizeImage(new ImageIcon(VueSokoban.class.getResource("/Images/PNG/Retina/Blocks/block_02.png"))));
        images.put(Sol.class,
                resizeImage(new ImageIcon(VueSokoban.class.getResource("/Images/PNG/Retina/Ground/ground_04.png"))));
        images.put(Joueur.class, resizeImage(
                combinaisonImage(VueSokoban.class.getResource("/Images/PNG/Retina/Ground/ground_04.png"),
                        VueSokoban.class.getResource("/Images/PNG/Retina/Player/player_21.png"))));
        images.put(Vide.class, new ImageIcon(""));

        niveaux.put("map/map1.txt", new Lecture("map/map1.txt"));
        niveaux.put("map/map2.txt", new Lecture("map/map2.txt"));
        niveaux.put("map/map3.txt", new Lecture("map/map3.txt"));

    }

    /**
     * Permet de fusionner deux images selon leur chemin
     * 
     * @param url
     * @param url2
     * @return ImageIcon
     */
    public ImageIcon combinaisonImage(URL url, URL url2) {
        // Charger les deux images à superposer
        ImageIcon Iconimage1 = new ImageIcon(url);
        ImageIcon Iconimage2 = new ImageIcon(url2);

        // Créer un nouveau BufferedImage de la même taille que l'image 1
        BufferedImage combinedImage = new BufferedImage(Iconimage1.getIconWidth(), Iconimage1.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Dessiner l'image 1 sur le BufferedImage
        Graphics g = combinedImage.getGraphics();
        g.drawImage(Iconimage1.getImage(), 0, 0, null);
        g.drawImage(Iconimage2.getImage(), 0, 0, null);

        // Créer un nouvel ImageIcon à partir du BufferedImage combiné
        ImageIcon combinedIcon = new ImageIcon(combinedImage);

        return combinedIcon;
    }

    /** Affiche le jeu */
    public void AfficheJeu() {
        debut = System.currentTimeMillis();
        JFrame frame = new JFrame("SOKOBAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int lignes = fichier.getNbrLignes();
        int colonnes = fichier.getTaillelignes();
        Element[][] plateau = MenuCard.getCarte();

        JPanel element = new JPanel(new GridLayout(lignes, colonnes));

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                Element e = plateau[i][j];
                ImageIcon image = images.get(e.getClass());
                JLabel label = new JLabel(image);
                element.add(label);
            }
        }

        frame.add(element);

        // ---------------------------------
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                GestionCoup(e, plateau, element, lignes, colonnes, frame);
            }
        };
        frame.addKeyListener(keyAdapter);

        // --------------------------------------------

        JMenuBar menuBar = new JMenuBar();

        JMenu jeuMenu = new JMenu("Jeu");
        menuBar.add(jeuMenu);

        JMenuItem retryItem = new JMenuItem("Recommencer");
        retryItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Voulez-vous recommencer ?");
                String key = choix_niveau();
                frame.dispose();
                lancerNouveauJeu(niveaux.get(key));
            }
        });
        jeuMenu.add(retryItem);

        JMenuItem quitMenuItem = new JMenuItem("Quitter");
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        jeuMenu.add(quitMenuItem);

        JMenu aideMenu = new JMenu("Aide");
        menuBar.add(aideMenu);

        JMenuItem propos = new JMenuItem("A propos..");
        propos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Vous ne pouvez pas déplacer une caisse sur les murs. \n Vous pouvez tirer une seule caisse à la fois. \n Chaque coup est un mouvement réfléchi, vous ne pouvez pas revenir en arrière. \n ");
            }
        });
        aideMenu.add(propos);

        JMenuItem commande = new JMenuItem("Commandes");
        commande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Pour vous déplacez, vous pouvez utiliser les flèches de votre ordinateur et/ou les touches ZQSD");
            }
        });
        aideMenu.add(commande);

        JMenuItem credits = new JMenuItem("Crédits");
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Jeu du Sokoban 2022-2023.\n Fait par Carole Mackowiak");
            }
        });
        aideMenu.add(credits);

        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Méthode qui permet de savoir dans quel niveau on se trouve.
     * 
     * @return String
     */
    public String choix_niveau() {
        String key = "";
        for (Map.Entry<String, Lecture> entry : niveaux.entrySet()) {
            System.out.println(entry.getValue());
            if (entry.getKey() == fichier.getNomFichier()) {
                key = entry.getKey();
                break;
            }
        }
        return key;
    }

    /**
     * Méthode qui permet de vérifier chaque touche appuyer, met à jour l'image du
     * joueur par rapport à son déplacement.
     * 
     * @param e
     * @param plateau
     * @param element
     * @param lignes
     * @param colonnes
     * @param frame
     */
    public void GestionCoup(KeyEvent e, Element[][] plateau, JPanel element, int lignes, int colonnes, JFrame frame) {
        int keyCode = e.getKeyCode();
        Joueur joueur = MenuCard.getJoueur();
        Deplacement dep = null;
        switch (keyCode) {
            case KeyEvent.VK_Q:
                dep = Deplacement.GAUCHE;
                joueur.setImage("/Images/PNG/Retina/Player/player_12.png");
                break;
            case KeyEvent.VK_D:
                dep = Deplacement.DROITE;
                joueur.setImage("/Images/PNG/Retina/Player/player_09.png");
                break;
            case KeyEvent.VK_Z:
                dep = Deplacement.HAUT;
                joueur.setImage("/Images/PNG/Retina/Player/player_24.png");
                break;
            case KeyEvent.VK_S:
                dep = Deplacement.BAS;
                joueur.setImage("/Images/PNG/Retina/Player/player_21.png");
                break;
            case KeyEvent.VK_LEFT:
                dep = Deplacement.GAUCHE;
                joueur.setImage("/Images/PNG/Retina/Player/player_12.png");
                break;
            case KeyEvent.VK_RIGHT:
                dep = Deplacement.DROITE;
                joueur.setImage("/Images/PNG/Retina/Player/player_09.png");
                break;
            case KeyEvent.VK_DOWN:
                dep = Deplacement.BAS;
                joueur.setImage("/Images/PNG/Retina/Player/player_21.png");
                break;
            case KeyEvent.VK_UP:
                dep = Deplacement.HAUT;
                joueur.setImage("/Images/PNG/Retina/Player/player_24.png");
                break;
            default:
                break;
        }
        if (dep != null) {
            MenuCard.deplacerJoueur(dep);
            miseAJourAffichage(plateau, element);
            dialogue_win(element, plateau, lignes, colonnes, frame);

        }

    };

    /**
     * Méthode qui met à jour l'affichage du GridLayout du sokoban
     * 
     * @param plateau
     * @param element
     */
    private void miseAJourAffichage(Element[][] plateau, JPanel element) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                Component component = element.getComponent(i * plateau[i].length + j);
                if (plateau[i][j] instanceof Caisse) {

                    ImageIcon image = resizeImage(
                            combinaisonImage(VueSokoban.class.getResource("Images/PNG/Retina/Ground/ground_04.png"),
                                    VueSokoban.class.getResource(((Caisse) plateau[i][j]).getImage())));
                    ((JLabel) component).setIcon(image);

                } else if (plateau[i][j] instanceof Joueur) {

                    if (((Joueur) plateau[i][j]).OnDestinaton()) {
                        ImageIcon image = resizeImage(combinaisonImage(
                                VueSokoban.class.getResource("/Images/PNG/Retina/Ground/ground_01.png"),
                                VueSokoban.class.getResource(((Joueur) plateau[i][j]).getImage())));
                        ((JLabel) component).setIcon(image);
                    } else {
                        ImageIcon image = resizeImage(
                                combinaisonImage(VueSokoban.class.getResource("Images/PNG/Retina/Ground/ground_04.png"),
                                        VueSokoban.class.getResource(((Joueur) plateau[i][j]).getImage())));
                        ((JLabel) component).setIcon(image);
                    }
                } else {
                    ImageIcon image = images.get(plateau[i][j].getClass());
                    ((JLabel) component).setIcon(image);
                }
            }
        }
    }

    /**
     * Méthode qui envoie une pop up demandant à l'utilisateur si il veut changer de
     * niveau ou quitter l'application.
     * 
     * @param element
     * @param plateau
     * @param lignes
     * @param colonnes
     * @param frame
     */
    public void dialogue_win(JPanel element, Element[][] plateau, int lignes, int colonnes, JFrame frame) {
        if (!MenuCard.partiefini()) {
            long fin = System.currentTimeMillis();
            long tempsEcoulee = fin - debut;
            double resultat = tempsEcoulee / 1000;

            for (Component component : element.getComponents()) {
                component.setEnabled(false);
            }

            String[] lesTextes;
            if (choix_niveau() == "map/map1.txt") {
                lesTextes = new String[] { "Rejouez", "Niveau 2", "Niveau 3", "Quitter" };

            } else if (choix_niveau() == "map/map2.txt") {
                lesTextes = new String[] { "Niveau 1", "Rejouez", "Niveau 3", "Quitter" };

            } else {
                lesTextes = new String[] { "Niveau 1", "Niveau 2", "Rejouez", "Quitter" };
            }
            int retour = JOptionPane.showOptionDialog(frame,
                    "Bravo ! Vous avez gagné en " + resultat + " s ! Voulez-vous passer au prochain niveau ?",
                    "Victoire !", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, lesTextes,
                    lesTextes[0]);

            switch (retour) {
                case 0:
                    lancerNouveauJeu(niveaux.get("map/map1.txt"));
                    frame.dispose();
                    break;
                case 1:
                    lancerNouveauJeu(niveaux.get("map/map2.txt"));
                    frame.dispose();
                    break;
                case 2:
                    lancerNouveauJeu(niveaux.get("map/map3.txt"));
                    frame.dispose();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Permet de relancer le jeu.
     * 
     * @param fichier2
     */
    private void lancerNouveauJeu(Lecture fichier2) {
        VueSokoban nouveauJeu = new VueSokoban(fichier2);
        nouveauJeu.AfficheJeu();
    }

    /**
     * Méthode qui permet de resize une image à la longueur et largueur pour éviter
     * des débordements.
     * 
     * @param image
     * @return ImageIcon
     */
    private ImageIcon resizeImage(ImageIcon image) {
        Image nouv_image = image.getImage();
        Image resizImage = nouv_image.getScaledInstance(long_icon, larg_icon, Image.SCALE_SMOOTH);
        ImageIcon resizeImageIcon = new ImageIcon(resizImage);
        return resizeImageIcon;
    }

}
