/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JeuLaby
{
    public void main (String[] args){
        if (args.length == 5) {//Bon nombre de paramètres
            //Ce qui ensuit est un gros paquet de conditions, pour la validité des arguments, à modifier pour limites supérieures...
            if ((Integer.parseInt(args[0])< 1 || Integer.parseInt(args[0]) > 50) || (Integer.parseInt(args[1]) < 1 || Integer.parseInt(args[1]) > 50) || (Integer.parseInt(args[2]) > 1 || Integer.parseInt(args[2]) < 0) || (Integer.parseInt(args[3]) < 0) || (Integer.parseInt(args[4]) < 1))
            {
                System.out.println("Paramètres incorrects...");
                System.out.println("Utilisation: java Laby <hauteur (entre 1 et 50)> <largeur(entre 1 et 50)> <densite(entre 1 exclus et 0 inclus)> <duree visible (+ que 0)> <nb vies(+ que 0)>");
                System.out.println("Exemple: java Laby 10 20 0.20 10 5");
            }
            else {
                //Isérer ici le jeu, en passant les paramètres en args
            }

        }//IF DE IF (ARGS LENGTH==5)

        else{
            System.out.println("Nombre de paramètres incorrect: il faut 5 paramètres exactement");
            System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
        }//ELSE DE IF (ARGS LENGTH ==5)



    }//MAIN
}//JEU LABY
