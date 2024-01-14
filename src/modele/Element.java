package modele;

public abstract class Element {
    private int x;
    private int y;

    /**
     * Constructeur de la class Element, nous avons deux paramètre x et y, qui
     * représente les coordonnées de l'élément.
     * 
     * @param x
     * @param y
     */
    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode qui renvoie la coordonée x de l'élément.
     * 
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Méthode qui renvoie la coordonnée y de l'élément.
     * 
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode qui permet de changer les coordonnées x et y de l'élément avec les
     * nouvelles valeurs passés en paramètre.
     * 
     * @param nouv_x
     * @param nouv_y
     */
    public void SetXY(int nouv_x, int nouv_y) {
        this.x = nouv_x;
        this.y = nouv_y;
    }
}