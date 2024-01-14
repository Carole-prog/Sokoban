package modele;

public class Caisse extends ElementDep {
    private String image = "Images/PNG/Retina/Crates/crate_02.png";

    /**
     * Constructeur de la classe Caisse qui étend une classe Element Dep, et prend
     * deux paramètre x et y.
     * 
     * @param x
     * @param y
     */
    public Caisse(int x, int y) {
        super(x, y, '$');
    }

    /**
     * Cette Méthode permet de retourner le chemin menant à l'image de la caisse.
     * 
     * @return String
     */
    public String getImage() {
        if (OnDestinaton()) {
            setImage("Images/PNG/Retina/Crates/crate_27.png");
            return image;
        }
        setImage("Images/PNG/Retina/Crates/crate_02.png");
        return image;
    }

    /**
     * Cette Méthode permet de modifier le chemin de l'image de la Caisse, avec un
     * nouveau chemin de type String
     * 
     * @param nouv_image
     */
    public void setImage(String nouv_image) {
        this.image = nouv_image;
    }

    /**
     * La méthode OnDestination permet de savoir si la caisse a changer de symbole
     * 
     * @return vrai ou faux, si elle a changer de symbole, si oui elle est sur une
     *         destination.
     */
    public boolean OnDestinaton() {
        return getSymbole() == '*';
    }

}
