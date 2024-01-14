package modele;

public class Sol extends ElementStatic {
    private String image = "Images/PNG/Retina/Ground/ground_04.png";

    /**
     * Constructeur de la class Sol étend la classe ElementStatic et prend en
     * paramètre les coordonnées x et y.
     * 
     * @param x
     * @param y
     */
    public Sol(int x, int y) {
        super(x, y, ' ');
    }

    /**
     * Cette méthode permet de récupéré le chemin de l'image de la class Sol
     * 
     * @return String
     */
    public String getImage() {
        return image;
    }

}
