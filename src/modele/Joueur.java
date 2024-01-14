package modele;

public class Joueur extends ElementDep {

    private Carte carte;
    private String image = "Images/PNG/Retina/Player/player_21.png";

    /**
     * Constructeur de la class Joueur, elle étend la class ElementDep.
     * Elle prend en paramètre x , y les coordonnées et la carte sur laquelle il se
     * trouve.
     * 
     * @param x
     * @param y
     * @param carte
     */
    public Joueur(int x, int y, Carte carte) {
        super(x, y, '@');
        this.carte = carte;
    }

    /**
     * Cette méthode permet de récupéré le chemin menant à l'image attribué au
     * Joueur.
     * 
     * @return String
     */
    public String getImage() {
        return image;
    }

    /**
     * Cette Méthode permet de modifier le chemin menant à l'image du Joueur.
     * 
     * @param nouv_image
     */
    public void setImage(String nouv_image) {
        this.image = nouv_image;
    }

    /**
     * Cette méthode permet de savoir si le joueur peut ou non se déplacer sur la
     * carte, renvoie vrai ou faux.
     * 
     * @param deplacement
     * @return boolean
     */
    public boolean peutdep(Deplacement deplacement) {
        if (!carte.partiefini()) {
            return false;
        }
        int nouv_x = getX() + deplacement.getX();
        int nouv_y = getY() + deplacement.getY();

        if (carte.estDestination(nouv_x, nouv_y)) {
            return true;
        } else if (carte.estCaisse(nouv_x, nouv_y)
                && carte.estSol(nouv_x + deplacement.getX(), nouv_y + deplacement.getY())) {
            return true;
        } else if (carte.estCaisse(nouv_x, nouv_y)
                && carte.estDestination(nouv_x + deplacement.getX(), nouv_y + deplacement.getY())) {
            return true;
        } else if (carte.estSol(nouv_x, nouv_y)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cette méthode permet de savoir si le Joueur est sur une Destination ou non.
     * 
     * @return boolean
     */
    public boolean OnDestinaton() {
        return getSymbole() == '+';
    }

}
