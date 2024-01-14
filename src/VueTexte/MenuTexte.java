package VueTexte;

import java.io.IOException;

public class MenuTexte {
    /**
     * Constructuer de la class MenuTexte qui affiche un menu pour l'utilisateur et
     * exécute la méthode correspondante en fonctiond de l'entrée de l'utilisateur
     * 
     * @throws IOException
     */
    public MenuTexte() throws IOException {
        System.out.println("==========================================");
        System.out.println("|| Bienvenue dans le jeu du Sokoban !   ||");
        System.out.println("||                                      ||");
        System.out.println("||       1. Commencer la partie         ||");
        System.out.println("||       2. Règles du jeu               ||");
        System.out.println("||       3. Commandes du jeu            ||");
        System.out.println("||       4. Signification des Symboles  ||");
        System.out.println("||       5. Quitter                     ||");
        System.out.println("||                                      ||");
        System.out.println("|| Par Carole Mackowiak                 ||");
        System.out.println("==========================================");
        switch (Outils.lireCaractere()) {
            case ('1'):
                selectionMenu();
                break;
            case ('2'):
                regle();
                break;
            case ('3'):
                commande();
                break;
            case ('4'):
                symbole();
                break;
            case ('5'):
                System.out.println("Merci d'avoir jouer au Sokoban !");
                System.exit(0);
                break;
            default:
                System.out.println("Je n'ai pas compris votre demande");
                break;
        }
    }

    /**
     * Affiche un Menu de selection des différents niveau
     * 
     * @throws IOException
     */
    public void selectionMenu() throws IOException {
        System.out.println("======================================");
        System.out.println("|| Selections de Niveaux :          ||");
        System.out.println("||   1. Map Numéro 1 ( Facile )     ||");
        System.out.println("||   2. Map Numéro 2 ( Medium )     ||");
        System.out.println("||   3. Map Numéro 3 ( Difficile )  ||");
        System.out.println("======================================");
        switch (Outils.lireCaractere()) {
            case ('1'):
                new ModeTexte('1');
                break;
            case ('2'):
                new ModeTexte('2');
                break;
            case ('3'):
                new ModeTexte('3');
                break;
            case ('r'):
                new MenuTexte();
                break;
            default:
                break;
        }
    }

    /**
     * Affiche les commandes du jeu sur le terminal
     * 
     * @throws IOException
     */
    public void commande() throws IOException {
        System.out.println("===============================");
        System.out.println("|| Pour déplacez le joueur : ||");
        System.out.println("|| Avancez : z               ||");
        System.out.println("|| Reculez : s               ||");
        System.out.println("|| Allez à Droite : d        ||");
        System.out.println("|| Allez à Gauche : g        ||");
        System.out.println("||                           ||");
        System.out.println("|| Pour revenir au menu :    ||");
        System.out.println("|| Entrez 'r'                ||");
        System.out.println("===============================");
        if (Outils.lireCaractere() == 'r') {
            new MenuTexte();
        }

    }

    /**
     * Affiche les règles du jeu sur le terminal.
     * 
     * @throws IOException
     */
    public void regle() throws IOException {
        System.out.println("========================================================================");
        System.out.println("|| Votre but est de déplacer toutes les caisses sur les destinations !||");
        System.out.println("||                                                                    ||");
        System.out.println("|| Pour revenir au menu :                                             ||");
        System.out.println("|| Entrez 'r'                                                         ||");
        System.out.println("========================================================================");
        if (Outils.lireCaractere() == 'r') {
            new MenuTexte();
        }
    }

    /**
     * Affiche la signification du jeu.
     * 
     * @throws IOException
     */
    public void symbole() throws IOException {
        System.out.println("===================================================================");
        System.out.println("|| Vous rencontrerez différents symboles au cours de vos parties. ||");
        System.out.println("|| En voici la liste :                                            ||");
        System.out.println("||                                                                ||");
        System.out.println("|| # : mur                                                        ||");
        System.out.println("|| / : vide                                                       ||");
        System.out.println("||  : sol                                                         ||");
        System.out.println("|| . : destination                                                ||");
        System.out.println("|| @ : joueur sur sol                                             ||");
        System.out.println("|| $ : caisse sur sol                                             ||");
        System.out.println("|| + : joueur sur destination                                     ||");
        System.out.println("|| * : caisse sur destination                                     ||");
        System.out.println("||                                                                ||");
        System.out.println("|| Pour revenir au menu :                                         ||");
        System.out.println("|| Entrez 'r'                                                     ||");
        System.out.println("====================================================================");
        if (Outils.lireCaractere() == 'r') {
            new MenuTexte();
        }
    }

}
