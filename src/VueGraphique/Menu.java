package VueGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import modele.*;

public class Menu {
    private HashMap<String, Lecture> niveaux = new HashMap<>();
    private HashMap<Class<? extends Element>, ImageIcon> images = new HashMap<>();

    /** Constructeur et VueGraphique du Menu pour accéder aux Sokoban */
    public Menu() {
        // On initialise notre Hashmap Niveaux
        niveaux.put("map/map1.txt", new Lecture("map/map1.txt"));
        niveaux.put("map/map2.txt", new Lecture("map/map2.txt"));
        niveaux.put("map/map3.txt", new Lecture("map/map3.txt"));

        // On initalise notre Hashmap images pour permettre d'accéder plus facilement à
        // l'image de la caisse au lieu de la rappeler 36x -> ça aide niveau mémoire
        images.put(Caisse.class,
                resizeImage(new ImageIcon(VueSokoban.class.getResource("/Images/PNG/Retina/Crates/crate_03.png"))));
        images.put(Vide.class, resizeImage(new ImageIcon("")));

        // Créer une nouvelle JFrame
        JFrame frame = new JFrame("MENU SOKOBAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Lecture fichier = new Lecture("map/mapMenu.txt");
        Carte MenuCard = new Carte(fichier);
        System.out.println(MenuCard);
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

        JPanel panel2 = new JPanel(new GridLayout(1, 4));
        JButton buttonMap1 = new JButton("Niveau 1");
        buttonMap1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenue sur le Niveau 1 !");
                frame.dispose();
                VueSokoban vue = new VueSokoban(niveaux.get("map/map1.txt"));
                vue.AfficheJeu();
            }
        });

        JButton buttonMap2 = new JButton("Niveau 2");
        buttonMap2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenue sur le Niveau 2 !");
                frame.dispose();
                VueSokoban vue = new VueSokoban(niveaux.get("map/map2.txt"));
                vue.AfficheJeu();
            }
        });

        JButton buttonMap3 = new JButton("Niveau 3");
        buttonMap3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenue sur le Niveau 3 !");
                frame.dispose();
                VueSokoban vue = new VueSokoban(niveaux.get("map/map3.txt"));
                vue.AfficheJeu();
            }
        });

        JButton buttonquit = new JButton("Quitter");
        buttonquit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Merci d'avoir jouer !");
                System.exit(0);
            }
        });

        panel2.add(buttonMap1);
        panel2.add(buttonMap2);
        panel2.add(buttonMap3);
        panel2.add(buttonquit);
        frame.add(panel2, BorderLayout.SOUTH);

        // -----------------------------------
        JMenuBar menuBar = new JMenuBar();

        JMenu jeuMenu = new JMenu("Jeu");
        menuBar.add(jeuMenu);

        JMenuItem quitMenuItem = new JMenuItem("Quitter");
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Merci d'avoir jouer !");
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

        // Définir la taille de la fenêtre et l'afficher
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Méthode qui permet de redimenssionner les images.
     * 
     * @param image
     * @return ImageIcon
     */
    private ImageIcon resizeImage(ImageIcon image) {
        Image nouv_image = image.getImage();
        Image resizImage = nouv_image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizeImageIcon = new ImageIcon(resizImage);
        return resizeImageIcon;
    }

}
