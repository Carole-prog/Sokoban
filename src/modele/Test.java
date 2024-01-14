package modele;

/**
 * Cette Class Test permet juste de savoir si la classe Lecture marche
 * correctement
 */
public class Test {
    public static void main(String[] args) {
        Lecture lecteur = new Lecture("map.txt");
        lecteur.createlist();

        // Affichage du nombre de lignes
        System.out.println("Nombre de lignes : " + lecteur.getNbrLignes());

        // Affichage de la taille de chaque ligne
        System.out.println("Taille de la ligne : " + lecteur.getTaillelignes());

        // Affichage du contenu de l'ArrayList
        System.out.println(lecteur.toString());
    }

}
