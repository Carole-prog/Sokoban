package modele;

public class Destination extends ElementStatic {
    private String image = "Images/PNG/Retina/Ground/ground_01.png";

    /**
     * Constructeur de la classe Destination étend la classe Element Static, et
     * prend en paramètre, deux coordonnées x et y.
     * 
     * @param x
     * @param y
     */
    public Destination(int x, int y) {
        super(x, y, '.');
    }

    /**
     * Méthode qui permet de récupéré le chemin de l'image pour la partie graphique
     * du Sokoban
     * 
     * @return String
     */
    public String getImage() {
        return image;
    }
}
