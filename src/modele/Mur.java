package modele;

public class Mur extends ElementStatic {
    private String image = "Images/PNG/Retina/Blocks/block_02.png";

    /**
     * Constructeur de la class Mur, elle étend la class ElementStatic,
     * Le constructeur prend en paramètre les coordonnées x et y.
     * 
     * @param x
     * @param y
     */
    public Mur(int x, int y) {
        super(x, y, '#');
    }

    /**
     * Une méthode permettant de retourner le chemin menant à l'image de la classe
     * Mur.
     * 
     * @return Strng
     */
    public String getImage() {
        return image;
    }

}
