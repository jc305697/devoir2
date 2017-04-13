import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by jeremycoulombe on 17-04-01.
 */


/////Note: cette version de jeuLaby est concue pour être executée en console uniquement. Ne pas executer sur la console de IntelliJ
public class JeuLaby
{
    public static void main (String[] args2)
    { //RETIRER LE 2 DE ARGS POUR MÉTHODE CMD
        int alertChanged = 1;                   /////changer alertChanged à 0 POUR MÉTHODE CMD
        String[] args = new String[5];/////////AJOUTÉ POUR JOUABILITÉ DANS CONSOLE INTELLIJ, SUPPRIMER LE 2 DE ARGS
        while(true)
        {//Si les paramètres sont incorrects, on recommence la saisie de données pour le jeu lui-même

            if(alertChanged!=0)
            { //Si la personne désire changer les paramètres
                Scanner snap = new Scanner(System.in);
                System.out.println("Hauteur?");

                args[0] = snap.nextLine();
                System.out.println("Largeur?");
                args[1] = snap.nextLine();
                System.out.println("Densité des murets?");
                args[2] = snap.nextLine();
                System.out.println("Durée de la visibilité?");
                args[3] = snap.nextLine();
                System.out.println("Nombre de vies??");
                args[4] = snap.nextLine();
            }


            if (args.length == 5)
            {//Bon nombre de paramètres
                //Ce qui ensuit est un gros paquet de conditions, pour la validité des arguments, à modifier pour limites supérieures...

                if ((Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 50)
                        || (Integer.parseInt(args[1]) < 1 || Integer.parseInt(args[1]) > 50)
                        || (Double.parseDouble(args[2]) > 1 || Double.parseDouble(args[2]) < 0)
                        || (Integer.parseInt(args[3]) < 0) || (Integer.parseInt(args[4]) < 1))
                {
                    System.out.println("Paramètres incorrects...");
                    System.out.println("Utilisation: java Laby <hauteur (entre 1 et 50)> <largeur(entre 1 et 50)> <densite(entre 1 exclus et 0 inclus)> <duree visible (+ que 0)> <nb vies(+ que 0)>");
                    System.out.println("Exemple: java Laby 10 20 0.20 10 5");
                } //FIN IF SI PARAMÈTRES ONT BORNES NON RESPECTÉES

                else
                {

                    while(true) {


                        int hauteur = Integer.parseInt(args[0]);
                        int largeur = Integer.parseInt(args[1]);
                        double densite = Double.parseDouble(args[2]);
                        int visibiliteTimed = Integer.parseInt(args[3]);
                        int viesRestantes = Integer.parseInt(args[4]);
                        Labyrinthe laby  = new Labyrinthe(largeur, hauteur, densite, visibiliteTimed, viesRestantes);
                        laby.toString();
                        System.out.println(laby);

                        JFrame fenetreJeu= new JFrame("Labyrinthe");//cree fenetre de jeu


                        JPanelLaby panelLaby=new JPanelLaby(laby);

                        fenetreJeu.add(panelLaby);

                        Dimension dimensionEcran = Toolkit.getDefaultToolkit().getScreenSize();
                        int hauteurAffichage = (int)(dimensionEcran.height*0.25);//hauteur de la fenetre sera le 1/4 de l ecran au debut

                        int largeurAffichage = (int)(dimensionEcran.width*0.25);//largeur de la fenetre sera le 1/4 de l ecran au debut

                        fenetreJeu.setSize(largeurAffichage,hauteurAffichage);//specifie la taille au depart de la fentre de jeu

                        fenetreJeu.setVisible(true);

                        while (true)
                        {

                            boolean gagne = panelLaby.boucleDeJeu(laby);

                            if (gagne==false)
                            {
                                //mettre fenetre de dialogue qui dit que la personne a perdu et demander si veut jouer encore...
                                //JoptionPane.showMessageDialog;
                                //if(veutplus jouer ) fait break
                                //si veut jouer encore appele fonction qui reset le affichage jlaby
                            }

                            if (gagne)
                            {
                                //mettre fenetre de dialogue qui dit que la personne a gagner et demander si veut jouer encore...
                                //JoptionPane.showMessageDialog;
                                //if(veutplus jouer ) fait break
                                //si veut jouer encore appele fonction qui reset le affichage jlaby et les composants
                            }


                        }



                        while (true)
                        {
                            System.out.println("veuillez entrez une direction");
                            Scanner snap = new Scanner(System.in);
                            String deplacement = snap.nextLine();
                            //if(deplacement != "d" ||deplacement != "g"||deplacement != "h"||deplacement != "b"){

                            if(deplacement.length()==1)
                            {
                                char direction= deplacement.charAt(0);//CharAt permet de faire un cast vers char

                                if (direction!='D' || direction!='G' || direction!='H'|| direction!='B')
                                {
                                    System.out.println("Commande inconnue... S'il vous plâit entrer 'D', 'G', 'H' ou encore 'B'");
                                }

                                else
                                {//Si touche est correcte...
                                    laby.deplace(direction);
                                    laby.toString(); //Reprint de la laby
                                }
                            }

                            if (deplacement.length()!=1)
                            {
                                System.out.println("longueur de commande illégale veuillez recommencez");
                            }

                            //Partie du jeu, appeler fonctions.


                            if (viesRestantes == 0)
                            {
                                break;
                            }
                        }//BOUCLE WHILE POUR LA PARTIE JEU, BREAK==PASSER À L'INTERFACE UTIL


                        if (viesRestantes == 0)
                        {
                            while(true)
                            {
                                System.out.println("Vous êtes mort, il ne vous restait plus de vies! Réessayer? (Oui/Non):");
                                Scanner scan = new Scanner(System.in);
                                if (scan.equals("Oui"))
                                {
                                    System.out.println("Parfait, nous allons réinitialiser le jeu pour vous! Mêmes arguments?");//Lets continue the while!
                                    //Faut avoir qqch pour changer les paramètres? Qqch plus interactif?
                                    while (true)
                                    {
                                        if (scan.nextLine().equals("Non"))
                                        {
                                            alertChanged = 1;
                                            break;
                                        }

                                        else if (scan.nextLine().equals("Oui"))
                                        {
                                            break;
                                            //Do nothing, while(true) gonna take care of dis
                                        }

                                        else
                                        {
                                            System.out.println("Désolé, la réponse n'était pas claire! Voulez-vous réinitialiser les arguments? (Oui/Non)");
                                        }
                                    }

                                }

                                else if (scan.equals("Non"))
                                {
                                    Runtime.getRuntime().exit(0);//Sortie de la console, status=0 signifie que tout va bien
                                    //Le programme achève ici? Commande permettant de shutdown l'app?
                                }

                                else
                                {
                                    System.out.println("Désolé, la réponse n'était pas claire! Voulez-vous sortir du jeu? (Oui/Non)");
                                }
                            }
                        }
                    }
                }//ELSE SI TOUT EST CORRECT DANS LES BORNES DES PARAMÈTRES --- BOUCLE DE JEU

            }//IF DE IF (ARGS LENGTH==5)

            else
            {
                System.out.println("Nombre de paramètres incorrect: il faut 5 paramètres exactement");
                System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
            }//ELSE DE IF (ARGS LENGTH ==5)
        }//Boucle while(true), afin d'executer l'interface lui-même

    }//MAIN
}//JEU LABY
