package modele;

public class Vide extends ElementStatic {
    private String image = "";

    /**
     * Constructeur de la classe Vide qui étend la classe appelé ElementStatic, elle
     * prend en paramètre deux coordonnées x et y.
     * 
     * @param x
     * @param y
     */
    public Vide(int x, int y) {
        super(x, y, '/');
    }

    /**
     * Méthode permettant de récupéré le chemin de l'image de la classe Vide
     * 
     * @return String.
     */
    public String getImage() {
        return image;
    }
}
