/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JeuLaby
{
    public void main (String[] args){
        if (args.length == 5) {//Bon nombre de paramètres

            if ((args[0] < 1 || args[0] > 50) || (args[1] < 1 || args[1] > 50) || (args[2] > 1 || args[2] < 0) || (args[3] < 0) || (args[4] < 1)) {
                System.out.println("Paramètres incorrects...");
                System.out.println("Utilisation: java Laby <hauteur> <largeur> <densite> <duree visible> <nb vies>");
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
