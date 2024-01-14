package modele;

import java.io.*;
import java.util.*;

public class Lecture {
    private String nom_fichier;
    private List<String> map = new ArrayList<>();

    private int nbrLignes = 0;

    /**
     * Constructeur de la classe Lecture qui prend en paramètre le chemin du fichier
     * texte.
     * 
     * @param nom
     */
    public Lecture(String nom) {
        nom_fichier = nom;
    }

    public String getNomFichier() {
        return nom_fichier;
    }

    /**
     * Cette Méthode permet de récupéré la map entrée en paramètre et de la
     * sauvegarder dans une liste de String
     */
    public List<String> createlist() {
        try {
            File fichier = new File(this.nom_fichier);
            BufferedReader fich = new BufferedReader(new FileReader(fichier));
            String line;
            while ((line = fich.readLine()) != null) {
                map.add(line);
                nbrLignes++;
            }
            fich.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Cette Méthode permet d'afficher la map sur le terminal.
     */
    public String toString() {
        for (String line : map) {
            System.out.println(line);
        }
        return " ";
    }

    /**
     * Cette méthode permet de savoir quel nombre de ligne contient ce fichier.
     * 
     * @return int
     */
    public int getNbrLignes() {
        return nbrLignes;
    }

    /**
     * Cette méthode permet de savoir quel nombre de colonnes contient ce fichier,
     * ici le nombre de caractère sur une ligne.
     * 
     * @return int;
     */
    public int getTaillelignes() {
        for (String ligne : map) {
            return ligne.length();
        }
        return 0;
    }
}
