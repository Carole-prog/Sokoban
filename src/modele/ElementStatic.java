package modele;

public class ElementStatic extends Element {
    private char symbole;

    /**
     * Constructeur de la class Element Static qui étend la class Element, et
     * représente un élément statique.
     * Ce constructeur prend en paramètre x, y les coordonées et le symbole pour la
     * partie textuel.
     * 
     * @param x
     * @param y
     * @param symbole
     */
    public ElementStatic(int x, int y, char symbole) {
        super(x, y);
        this.symbole = symbole;
    }

    /**
     * Méthode permettant de récupéré le symbole de l'élément
     * 
     * @return char
     */
    public char getSymbole() {
        return symbole;
    }

    /**
     * Méthode qui permet d'afficher une String de notre symbole pour l'afficher sur
     * le terminal
     * 
     * @return String
     */
    public String toString() {
        return String.valueOf(symbole);
    }
}
