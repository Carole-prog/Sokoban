package modele;

public class ElementDep extends Element {

    private char symbole;

    /**
     * Constructeur ElementDep qui étend Element, et représente un élément
     * déplaçable.
     * Elle prend en paramètre x et y les coordonnées de l'élément ainsi qu'un
     * symbole ( char ) qui représente l'élément.
     * 
     * @param x
     * @param y
     * @param symbole
     */
    public ElementDep(int x, int y, char symbole) {
        super(x, y);
        this.symbole = symbole;
    }

    /**
     * Méthode qui permet de renvoyer le symbole de l'élément déplaçable.
     * 
     * @return char.
     */
    public char getSymbole() {
        return symbole;
    }

    /**
     * Méthode qui permet de changer le symbole de l'élément.
     * 
     * @param c
     */
    public void setSymbole(char c) {
        this.symbole = c;
    }

    /**
     * Méthode qui permet d'afficher le symbole ( char ) en String sur le terminal;
     */
    public String toString() {
        return String.valueOf(symbole);
    }
}