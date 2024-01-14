package modele;

public enum Deplacement {
    HAUT(-1, 0), BAS(1, 0), GAUCHE(0, -1), DROITE(0, 1);

    private int incrementX;
    private int incrementy;

    /**
     * Constructeur de la classe Deplacement qui permet de créer des instances de
     * cette énumération en leur associant 2 valeurs, incX et incY
     * 
     * @param incX
     * @param incY
     */
    private Deplacement(int incX, int incY) {
        this.incrementX = incX;
        this.incrementy = incY;
    }

    /**
     * Cette méthode permet de récupéré la coordonnées X du déplacement voulu.
     * 
     * @return int
     */
    public int getX() {
        return incrementX;
    }

    /**
     * Cettte méthode permet de récupéré la coordonnées Y du déplacement voulu.
     * 
     * @return int;
     */
    public int getY() {
        return incrementy;
    }
}
