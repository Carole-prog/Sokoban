package modele;

import java.util.*;

public class Carte {
    private Lecture fichier;
    private Element[][] carte;
    private List<Caisse> lesCaisses = new ArrayList<>();

    /**
     * Constructeur de la class Carte qui initialise la carte et le fichier(
     * map?.txt )
     * 
     * @param nouv_fichier
     */
    public Carte(Lecture nouv_fichier) {
        this.fichier = nouv_fichier;
        this.carte = create_Carte();
    }

    /**
     * Méthode permettant de modifier les Elements de la carte
     * 
     * @param nouv_carte
     */
    public void setCarte(Element[][] nouv_carte) {
        this.carte = nouv_carte;
    }

    /**
     * Cette Méthode permet de retourner un tableau d'éléments
     * 
     * @return Element[][] -> un tableau d'éléments ( Mur, Vide, Destination,
     *         Caisse, ...)
     */
    public Element[][] create_Carte() {

        List<String> map = fichier.createlist();
        Element[][] carte = new Element[fichier.getNbrLignes()][fichier.getTaillelignes()];

        for (int i = 0; i < map.size(); i++) {
            String ligne = map.get(i);

            for (int j = 0; j < ligne.length(); j++) {

                char symbole = ligne.charAt(j);

                if (symbole == '@') {
                    carte[i][j] = new Joueur(i, j, this);

                } else if (symbole == '$') {
                    carte[i][j] = new Caisse(i, j);

                } else if (symbole == '/') {
                    carte[i][j] = new Vide(i, j);

                } else if (symbole == '#') {
                    carte[i][j] = new Mur(i, j);

                } else if (symbole == '.') {
                    carte[i][j] = new Destination(i, j);

                } else if (symbole == ' ') {
                    carte[i][j] = new Sol(i, j);
                }
            }
        }
        return carte;
    }

    /**
     * Méthode permettant de récupéré la carte sous forme de matrice
     * 
     * @return Element[][]
     */
    public Element[][] getCarte() {
        return carte;
    }

    /**
     * Méthode permettant de déplacer le joueur dans différent cas de figure sur la
     * carte
     * 
     * @param deplacement
     */
    public void deplacerJoueur(Deplacement deplacement) {
        Joueur joueur = getJoueur();
        int ancien_x = joueur.getX();
        int ancien_y = joueur.getY();

        if (joueur.peutdep(deplacement)) {
            int nouv_x = ancien_x + deplacement.getX();
            int nouv_y = ancien_y + deplacement.getY();

            if (estCaisse(nouv_x, nouv_y)) {
                mettreajourCaisse(joueur, deplacement, nouv_x, nouv_y, ancien_x, ancien_y);

            } else if (estSol(nouv_x, nouv_y)) {

                if (joueur.OnDestinaton()) {

                    joueur.setSymbole('@');
                    carte[ancien_x][ancien_y] = new Destination(ancien_x, ancien_y);
                } else {
                    carte[ancien_x][ancien_y] = new Sol(ancien_x, ancien_y);
                }
                carte[nouv_x][nouv_y] = joueur;
                joueur.SetXY(nouv_x, nouv_y);

            } else if (estDestination(nouv_x, nouv_y)) {

                if (joueur.OnDestinaton()) {
                    carte[ancien_x][ancien_y] = new Destination(ancien_x, ancien_y);

                } else {

                    joueur.setSymbole('+');
                    carte[ancien_x][ancien_y] = new Sol(ancien_x, ancien_y);
                }

                carte[nouv_x][nouv_y] = joueur;
                joueur.SetXY(nouv_x, nouv_y);

            } else {
                return;
            }

        }
    }

    /**
     * Méthode permettant de déplacer la caisse et le joueur dans différent cas de
     * figure sur le plateau
     * 
     * @param joueur
     * @param deplacement
     * @param nouv_x
     * @param nouv_y
     * @param ancien_x
     * @param ancien_y
     */
    public void mettreajourCaisse(Joueur joueur, Deplacement deplacement, int nouv_x, int nouv_y, int ancien_x,
            int ancien_y) {
        int nouv_x_caisse = nouv_x + deplacement.getX();
        int nouv_y_caisse = nouv_y + deplacement.getY();
        Caisse caisse = getCaisse(nouv_x, nouv_y);
        if (estSol(nouv_x_caisse, nouv_y_caisse)) {

            if (caisse.OnDestinaton()) {

                caisse.setSymbole('$');
                joueur.setSymbole('+');

            } else if (joueur.OnDestinaton()) {

                joueur.setSymbole('@');
                carte[ancien_x][ancien_y] = new Destination(ancien_x, ancien_y);

            } else {
                carte[ancien_x][ancien_y] = new Sol(ancien_x, ancien_y);
            }
            carte[nouv_x_caisse][nouv_y_caisse] = caisse;
            carte[nouv_x][nouv_y] = joueur;
            caisse.SetXY(nouv_x_caisse, nouv_y_caisse);
            joueur.SetXY(nouv_x, nouv_y);

        } else if (estDestination(nouv_x_caisse, nouv_y_caisse)) {

            if (caisse.OnDestinaton() && joueur.OnDestinaton()) {

                carte[ancien_x][ancien_y] = new Destination(ancien_x, ancien_y);

            } else if (caisse.OnDestinaton()) {

                joueur.setSymbole('+');
                carte[ancien_x][ancien_y] = new Sol(ancien_x, ancien_y);

            } else if (joueur.OnDestinaton()) {

                caisse.setSymbole('*');
                joueur.setSymbole('@');
                carte[ancien_x][ancien_y] = new Destination(ancien_x, ancien_y);

            } else {

                caisse.setSymbole('*');
                carte[ancien_x][ancien_y] = new Sol(ancien_x, ancien_y);
            }
            carte[nouv_x_caisse][nouv_y_caisse] = caisse;
            carte[nouv_x][nouv_y] = joueur;
            caisse.SetXY(nouv_x_caisse, nouv_y_caisse);
            joueur.SetXY(nouv_x, nouv_y);

        } else {
            return;
        }
        misejourCaisse();
    }

    /** Méthode permettant de mettre à jour : lesCaisses */
    public void misejourCaisse() {
        lesCaisses.clear();
        if (lesCaisses.isEmpty()) {
            for (int i = 0; i < carte.length; i++) {
                for (int j = 0; j < carte[0].length; j++) {
                    if (carte[i][j] instanceof Caisse) {
                        lesCaisses.add((Caisse) carte[i][j]);
                    }
                }
            }
        }
    }

    /**
     * Méthode permmettant de savoir de quel nature est l'objet aux coordonnées x et
     * y, renvoie vrai si c'est un Mur
     * 
     * @param x
     * @param y
     * @return true, false
     */
    public boolean estMur(int x, int y) {
        if (carte[x][y] instanceof Mur) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de savoir de quel nature est l'objet aux coordonnées x et
     * y, renvoie vrai si c'est un Sol
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean estSol(int x, int y) {
        if (carte[x][y] instanceof Sol) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permmettant de savoir de quel nature est l'objet aux coordonnées x et
     * y, renvoie vrai si c'est une Caisse
     * 
     * @param x
     * @param y
     * @return true, false
     */
    public boolean estCaisse(int x, int y) {
        if (carte[x][y] instanceof Caisse) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permmettant de savoir de quel nature est l'objet aux coordonnées x et
     * y, renvoie vrai si c'est le Vide
     * 
     * @param x
     * @param y
     * @return true, false
     */
    public boolean estVide(int x, int y) {
        if (carte[x][y] instanceof Vide) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permmettant de savoir de quel nature est l'objet aux coordonnées x et
     * y, renvoie vrai si c'est une Destination
     * 
     * @param x
     * @param y
     * @return true, false
     */
    public boolean estDestination(int x, int y) {
        if (carte[x][y] instanceof Destination) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de savoir où se trouve le joueur sur la carte
     * 
     * @return un objet Joueur
     */
    public Joueur getJoueur() {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                if (carte[i][j] instanceof Joueur) {
                    return (Joueur) carte[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Une méthode permettant de savoir quel caisse, on utilise grâce aux
     * coordonnées,
     * 
     * @param x
     * @param y
     * @return un objet Caisse
     */
    public Caisse getCaisse(int x, int y) {
        if (carte[x][y] instanceof Caisse) {
            return (Caisse) carte[x][y];
        }
        return null;
    }

    /**
     * Méthode qui permet de savoir si la partie est fini ou non
     * 
     * @return true ou false
     */
    public boolean partiefini() {
        if (lesCaisses.isEmpty()) {
            return true;
        }
        int nbrSurDestination = 0;
        for (Caisse caisse : lesCaisses) {
            if (caisse.OnDestinaton()) {
                nbrSurDestination++;
                if (nbrSurDestination == lesCaisses.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Méthode qui permet de réinitialiser la carte */
    public void recommencer() {
        lesCaisses.clear();
        Element[][] nouv_carte = create_Carte();
        this.carte = nouv_carte;
    }

    /** Méthode qui permet d'afficher ma carte sur le Terminal */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                sb.append(carte[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
