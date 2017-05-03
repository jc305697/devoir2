/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class Labyrinthe
{

    private int  l;//largeur en termes de cases
    private int h; //hauteur en termes de cases
    private Personnage perso;
    private ListeMuret liste;
    private int sortieX;
    private int sortieY;

    public Labyrinthe(int l,int h,double densiteLaby, double delai, int nbVie)
    {
        this.l=l;
        this.h=h;
        this.liste=new ListeMuret();
        this.perso=new Personnage(0,0,nbVie);




         int nbMurs=0;
     for (int i=0;i<h;i++)//hauteur commence a 0 genre de y
        {
            for (int j=0;j<l;j++)//largeur commence a 0 genre de x
            {
                boolean contour= (i==0) || (i==h-1) || (j==l-1) || (j==0);



                    if (Math.random()<densiteLaby)//nb aleatoire < densitelaby   et pas completement a droite
                    {
                        Muret nouveauMuret=new Muret(j,i,false,true);//cree muret horizontal en j,i


                        if (liste.chercheMuret(nouveauMuret)==null)//le muret n'est pas dans la liste
                        {

                            nbMurs++;

                            liste.ajoutMuret(nouveauMuret);//ajoute a la liste

                        }
                    }

                    if (Math.random()<densiteLaby)//nb aleatoire < densitelaby  et je ne suis pas sur la derniere ligne
                    {
                        Muret nouveauMuret1=new Muret(j,i,true,true);//cree mur vertical
                        if (liste.chercheMuret(nouveauMuret1)==null)//n'est pas dans la liste
                        {
                            nbMurs++;

                            liste.ajoutMuret(nouveauMuret1);//ajoute a la liste


                        }
                    }


            }
        }


        //perso doit etre colle sur mur le gauche
        double posAleatPersoX= 0.5;
        double posAleatPersoY= (double)(Math.round(Math.random()*(h-1)))+0.5;

        this.perso.setPositionXPersonnage(posAleatPersoX);
        this.perso.setPositionYPersonnage(posAleatPersoY);

        int posAleatSortieX;//position en x ou sera la sortie
        int posAleatSortieY;//position en y ou sera la sortie

        double nbAleat= Math.random();


       //sortie doit etre sur le mur droit
            posAleatSortieX=l-1;
            posAleatSortieY=(int)(Math.random()*(h-1));


        this.sortieX=posAleatSortieX;

        this.sortieY=posAleatSortieY;

    }

    public int getL()
    {
        return l;
    }

    public int getH()
    {
        return h;
    }

    public int getSortieX()
    {
        return sortieX;
    }

    public int getSortieY()
    {
        return sortieY;
    }

    public Personnage getPerso()
    {
        return perso;
    }

    public ListeMuret getListe()
    {
        return liste;
    }


    public String toString() {
        String ultimateAccumulator = "";
        String bordureSup = "______"; //On va accumuler cette unité
        String bordureAcc = ""; //Accumulateur, la longueur dépend de la largeur du laby demandé
        for(int h=0; h<l; h++){ //Crée les lignes de bordure sup et inf. Besoin seulement que ce soit répété deux fois.
            bordureAcc += bordureSup;
        }
        String resultat=""; //Accumulateur pour tout la grille.
        resultat +=bordureAcc + "\n"; //Première ligne horizontale ajoutée, on va en ajouter une autre à la fin
        boolean flagCharTrouveEtMis = false;
        for(double j=0; j<this.l; j++)
        { //AXE Y, META-LIGNES

            String murLigne = "|"; //Mini-ligne, crée une ligne de murs verticaux uniquement, sans personnage
            String murPersLigne = "|"; //Ligne avec barres verticales AVEC personnage
            String murLigneHorizo = "|";//Ligne horizontale de murs horizontaux composés de tirets "-"

            for(double i=0; i<this.h; i++)
            { //AXE X, META-COLONNES

                Muret muretPosVert2 = new Muret((int) j, (int) i, true, true);
                Muret muretPosHorizo2 = new Muret((int) j, (int) i, false, true);

                if(i<this.h-1){

                    if(this.liste.chercheMuret(muretPosVert2) !=null)
                    {
                        murLigne+="     |";
                    }
                    else if(this.liste.chercheMuret(muretPosVert2) ==null){
                        murLigne+="      ";
                    }
                }
                else{
                    if((this.sortieX==i&& this.sortieY==j)) {
                        murLigne += "     out ";
                    }
                    else{
                        murLigne+="     |";
                    }
                }


                if(j<this.l-1) {
                    if ((this.liste.chercheMuret(muretPosHorizo2) != null) && (this.liste.chercheMuret(muretPosVert2) != null)) {
                        murLigneHorizo += "_____|";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) != null && this.liste.chercheMuret(muretPosVert2) == null) {
                        murLigneHorizo += "______";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) == null && this.liste.chercheMuret(muretPosVert2) == null) {
                        murLigneHorizo += "      ";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) == null && this.liste.chercheMuret(muretPosVert2) != null) {
                        murLigneHorizo += "      |";
                    }
                }
                else{
                    if ((this.liste.chercheMuret(muretPosVert2) != null)) {
                        murLigneHorizo += "_____|";
                    }
                    else if((this.sortieX==i&& this.sortieY==j)) {
                        murLigne += "______ ";
                    }
                    else{
                        murLigneHorizo += "______";
                    }
                }

                if(this.perso.getPositionXPersonnage()==i+0.5)
                { //Si dans la ligne y a un personnage, procédure spéciale



                    if(this.perso.getPositionYPersonnage()==j+0.5)
                    { //Si le perso est exactement à cette position, on dessine juste le perso avec espaces

                        Muret muretPosVert = new Muret((int) j, (int) i, true, true);
                        flagCharTrouveEtMis=true;
                        if(this.liste.chercheMuret(muretPosVert)!=null)
                        {//S'il y a un mur à cette position...
                            murPersLigne +="  c  |";//Avec mur
                        }

                        else
                        {
                            murPersLigne+= "  c  "; //Sans mur
                        }
                    }

                }
            }

            if (flagCharTrouveEtMis==true)
            { //À la méta-ligne donnée, si flagCharTrouvéEtMis n'est pas égal à 0, on va créer une méta-ligne
                resultat+=murLigne+ "\n"; //Ligne de murs simples, car le personnage est au centre
                resultat+=murPersLigne+ "\n"; //Ligne de murs CONTENANT le personnage
                resultat+=murLigneHorizo+ "\n"; //Ligne de murets horizontaux
                flagCharTrouveEtMis = false;
            }

            else if(flagCharTrouveEtMis==false)
            {                               //Aucun perso, alors on se contente de répéter trois fois la même ligne
                resultat+=murLigne+ " \n";       //Ligne de murs simple
                resultat+=murLigne+ " \n";       //Bis
                resultat+=murLigneHorizo+ " \n"; //Murets horizontaux
            }
            //Une fois la meta-ligne faite, on passe à la meta-ligne suivante, avec incrémentation de i, dans sa boucle for
        }

        resultat+= "\n"; //On ajoute ici une bordure horizontale inférieure ("-----------.....------")

        return resultat; //Et le résultat, la grille, est retournée, pour impressiond ans le jeu!
    }

    public boolean deplace(char direction)
    {
        double posXPerso= this.perso.getPositionXPersonnage();

        double posYPerso= this.perso.getPositionYPersonnage();

        switch (direction)
        {
            case 'D':
                System.out.println("D");

                Muret muretDVert1= new Muret((int)(posXPerso+0.5),(int)(posYPerso-0.5),true,true);
              //  Muret muretDVert2= new Muret((int)(posXPerso+0.5),(int)(posYPerso+1),true,true);

              //  Muret muretDHori1= new Muret((int)(posXPerso+0.5),(int)posYPerso,false,true);
               // Muret muretDHori2= new Muret((int)(posXPerso-0.5),(int)posYPerso,false,true);

               /* if ((this.liste.chercheMuret(muretDHori1)!=null) || (this.liste.chercheMuret(muretDHori2)!=null)
                        ||(this.liste.chercheMuret(muretDVert1)!=null)||(this.liste.chercheMuret(muretDVert2)!=null) )                {
                    if (this.liste.chercheMuret(muretDHori1)!=null)
                    {
                        this.liste.chercheMuret(muretDHori1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretDHori2)!=null)
                    {
                        this.liste.chercheMuret(muretDHori2).setVisible(true);
                    }*/
               //if ((this.liste.chercheMuret(muretDVert1)!=null)||(this.liste.chercheMuret(muretDVert2)!=null))

                if (this.liste.chercheMuret(muretDVert1)!=null)
                {

                    this.liste.chercheMuret(muretDVert1).setVisible(true);


                    /*if (this.liste.chercheMuret(muretDVert2)!=null)
                    {
                        this.liste.chercheMuret(muretDVert2).setVisible(true);
                    }*/

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }




                if ((this.perso.getPositionXPersonnage()+.5==l) && (this.perso.getPositionYPersonnage()-.5!=sortieY))
                {//n'est pas la sortie, mais est sur le cote droit et va a droite donc fonce dans le mur
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }


                //pas a droite et pas de mur a droite
                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);

                //this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()+1);
                return true;

            case 'G':
                System.out.println("G");

                Muret muretGVert1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),true,true);


                if (this.liste.chercheMuret(muretGVert1)!=null)
                {

                    if (this.liste.chercheMuret(muretGVert1)!=null)
                    {
                        this.liste.chercheMuret(muretGVert1).setVisible(true);//rend mur visible
                    }

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                if (this.perso.getPositionXPersonnage()-.5==0)//si a extreme gauche
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                //sinon deplacement valide
                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()-1);

                //this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()+5);
                return true;


            case 'H':
                System.out.println("H");

                Muret muretHHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),false,true);


                if (this.liste.chercheMuret(muretHHori1)!=null)
               {


                        this.liste.chercheMuret(muretHHori1).setVisible(true);

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                if (this.perso.getPositionYPersonnage()-.5==0)
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }
                 //this.set
                this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()-1);

                return true;

            case 'B':

                System.out.println("B");
                Muret muretBHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso+0.5),false,true);


                if (this.liste.chercheMuret(muretBHori1)!=null)
                {
                    this.liste.chercheMuret(muretBHori1).setVisible(true);

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);

                    return false;
                }

                if (this.perso.getPositionYPersonnage()+.5==h)
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()+1);

                return true;
        }

        return false;
    }



}
