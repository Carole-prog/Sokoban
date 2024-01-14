package VueTexte;

import java.io.IOException;

//Es ce que c'est pour lire les caractères qu'on va mettre dans le menu pour lire les caractères ?!? bonne question ptêt regarder le TP
public class Outils {

    public static char lireCaractere() {
        int rep = ' ';
        int buf;
        try {
            rep = System.in.read();
            buf = rep;
            while (buf != '\n')
                buf = System.in.read();
        } catch (IOException e) {
        }
        ;
        return (char) rep;
    }

}