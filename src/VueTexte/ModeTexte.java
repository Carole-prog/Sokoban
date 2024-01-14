package VueTexte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import modele.Carte;
import modele.Deplacement;
import modele.Lecture;

public class ModeTexte {
    private static char numero = ' ';
    private HashMap<Character, String> map = new HashMap<>();
    private HashMap<Character, Deplacement> cmd = new HashMap<>();

    /**
     * Constructeur Mode Texte qui prend en paramètre le choix de l'utilisateur lors
     * de la séléction de niveau et initialise les différents dictionnaires
     * Et lance la partie.
     * 
     * @param c
     * @throws IOException
     */
    public ModeTexte(char c) throws IOException {
        numero = c;
        map.put('1', "map/map1.txt");
        map.put('2', "map/map2.txt");
        map.put('3', "map/map3.txt");

        cmd.put('z', Deplacement.HAUT);
        cmd.put('s', Deplacement.BAS);
        cmd.put('d', Deplacement.DROITE);
        cmd.put('q', Deplacement.GAUCHE);
        partie();
    }

    /**
     * Méthode permettant de crée notre Carte ou l'utilisateur pourra jouer.
     * 
     * @return Carte
     * @throws NoSuchElementException
     */
    public Carte choice() throws NoSuchElementException {
        for (Map.Entry<Character, String> entry : map.entrySet()) {
            if (entry.getKey() == numero) {
                Lecture mappy = new Lecture(entry.getValue());
                return new Carte(mappy);
            }
        }
        throw new NoSuchElementException(
                "Aucun élément correspondant à la clé " + numero + " n'a été trouvé dans notre dictionnaire!");
    }

    /**
     * Affiche la fin de la partie.
     * 
     * @throws IOException
     */
    public void win() throws IOException {
        System.out.println("==================================================");
        System.out.println("||                                              ||");
        System.out.println("||          BRAVO ! Vous avez gagné !           ||");
        System.out.println("||                                              ||");
        System.out.println("==================================================");
        System.out.println("|| Pour revenir au menu :                       ||");
        System.out.println("|| Tapez 'r' :                                  ||");
        System.out.println("==================================================");
        char userInput;
        do {
            userInput = Outils.lireCaractere();
        } while (userInput != 'r');

        if (userInput == 'r') {
            new MenuTexte();
        }

    }

    /**
     * Permet de lancer la partie.
     * 
     * @throws IOException
     */
    public void partie() throws IOException {
        Carte card = choice();
        System.out.println(card);
        while (card.partiefini()) {
            char input = Outils.lireCaractere();
            Deplacement deplacement = cmd.get(input);
            if (deplacement != null) {
                card.deplacerJoueur(deplacement);
                System.out.println(card);
            } else {
                System.out.println("Commande non reconnue!");
            }
        }
        win();
    }

}
