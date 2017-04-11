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

        //int nbMurs= 2*(l+h);

       // for (int i=0;i<nbMurs;i++)
        for (int i=0;i<h;i++)//hauteur commence a 0 genre de y
        {
            for (int j=0;j<l;j++)//largeur commence a 0 genre de x
            {
                boolean hautOuBas= (i==0) || (i==h-1);

                //if (hautOuBas)
                //{
                    if (i==0)//je suis sur la ligne en haut ajoute les contours
                    {
                        if (((j == 0) || (j == l - 1)))//je suis soit a gauche completement ou a droite completement
                        {
                            liste.ajoutMuret(new Muret(j, 0, true, true));

                        }
                        else
                        {
                            liste.ajoutMuret(new Muret(j, 0, false, true));
                        }
                    }

                    if(i==h-1)//je suis sur la ligne du bas
                    {
                        liste.ajoutMuret(new Muret(j, 0, false, true));
                    }
               // }
                if (!hautOuBas)//je ne suis ni sur la ligne du haut ou sur la ligne du bas
                {
                    if ((Math.random()<densiteLaby)&&(j!=l-1))//muret horizontal et pas completement a droite
                    {
                        Muret nouveauMuret=new Muret(j,i,false,true);
                        if (!liste.equals(nouveauMuret))//n'est pas dans la liste
                        {
                            liste.ajoutMuret(nouveauMuret);//ajoute a la liste
                        }
                    }

                    if ((Math.random()<densiteLaby)&&(i!=h-1))//je ne suis pas sur la derniere ligne
                    {
                        Muret nouveauMuret=new Muret(j,i,false,true);
                        if (!liste.equals(nouveauMuret))//n'est pas dans la liste
                        {
                            liste.ajoutMuret(nouveauMuret);//ajoute a la liste
                        }
                    }


                }

            }
        }
        //this.perso.setViesRestantes(nbVie);


        //perso doit etre colle sur mur le gauche
        double posAleatPersoX= 0.5;
        double posAleatPersoY= (double)Math.round(Math.random()*(h-1))+0.5;

        this.perso.setPositionXPersonnage(posAleatPersoX);
        this.perso.setPositionYPersonnage(posAleatPersoY);

        int posAleatSortieX;//position en x ou sera la sortie
        int posAleatSortieY;//position en y ou sera la sortie

        double nbAleat= Math.random();

        /*if (nbAleat<0.25)
        {
            posAleatSortieX=0;
            posAleatSortieY=(int)(Math.random()*(h-1));
        }*/

       //sortie doit etre sur le mur droit
            posAleatSortieX=l-1;
            posAleatSortieY=(int)(Math.random()*(h-1));

        /*
        if ((nbAleat<0.75)&&(nbAleat>0.50))
        {
            posAleatSortieX=(int)(Math.random()*(l-1));
            posAleatSortieY=0;
        }

        if ((nbAleat<1)&&(nbAleat>.75))
        {
            posAleatSortieX=(int)(Math.random()*(l-1));
            posAleatSortieY=h-1;
        }*/

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

    public String toString() {

        String bordureSup = "- - - "; //On va accumuler cette unité
        String bordureAcc = ""; //Accumulateur, la longueur dépend de la largeur du laby demandé
        for(int h=0; h<l; h++){ //Crée les lignes de bordure sup et inf. Besoin seulement que ce soit répété deux fois.
            bordureAcc += bordureSup;
        }

        String resultat=""; //Accumulateur pour tout la grille.
        resultat +=bordureAcc; //Première ligne horizontale ajoutée, on va en ajouter une autre à la fin

        for(double i=0; i<this.h; i++)
        { //AXE Y, META-LIGNES

            //les lignes horizontales qu'on change à chaque tour. Chaque tout de i permet de créer une méta-ligne qui sera ajoutée à résultat.

            int flagCharTrouveEtMis = 0; //Permet de déterminer si un personnage était à la ligne. C'est plus tard utilisé pour créer la méta-ligne

            String murLigne = "|"; //Mini-ligne, crée une ligne de murs verticaux uniquement, sans personnage
            String murPersLigne = "|"; //Ligne avec barres verticales AVEC personnage
            String murLigneHorizo = "|";//Ligne horizontale de murs horizontaux composés de tirets "-"

            for(double j=0; j<this.l; j++)
            { //AXE X, META-COLONNES

                Muret muretPosVert2 = new Muret((int) j, (int) i, true, true);
                Muret muretPosHorizo2 = new Muret((int) j, (int) i, false, true);

                if(this.liste.chercheMuret(muretPosVert2) !=null)
                {
                    murLigne+="    |";
                }

                else
                {
                    murLigne+="     ";
                }

                if(this.liste.chercheMuret(muretPosHorizo2) !=null)
                {
                    murLigneHorizo+="- - - ";
                }

                else
                {
                    murLigneHorizo+="     ";
                }
                if(this.perso.getPositionXPersonnage()==i+0.5)
                { //Si dans la ligne y a un personnage, procédure spéciale

                    flagCharTrouveEtMis++;

                    if(this.perso.getPositionYPersonnage()==j+0.5)
                    { //Si le perso est exactement à cette position, on dessine juste le perso avec espaces

                        Muret muretPosVert = new Muret((int) j, (int) i, true, true);

                        if(this.liste.chercheMuret(muretPosVert)!=null)
                        {//S'il y a un mur à cette position...
                            murPersLigne +=" c  |";//Avec mur
                        }

                        else
                        {
                            murPersLigne+= " c  "; //Sans mur
                        }
                    }

                    else
                    {
                        Muret muretPosVert = new Muret((int) j, (int) i, true, true);

                        if(this.liste.chercheMuret(muretPosVert)!=null)
                        {
                            murPersLigne+= "   |";
                        }

                        else
                        {
                            murPersLigne +="    ";
                        }

                    }
                }
            }

            if (flagCharTrouveEtMis!=0)
            { //À la méta-ligne donnée, si flagCharTrouvéEtMis n'est pas égal à 0, on va créer une méta-ligne
                resultat+=murLigne+ "|\n"; //Ligne de murs simples, car le personnage est au centre
                resultat+=murPersLigne+ "|\n"; //Ligne de murs CONTENANT le personnage
                resultat+=murLigne+ "|\n"; //Ligne de murs simples
                resultat+=murLigneHorizo+ "|\n"; //Ligne de murets horizontaux
            }

            else
            {                               //Aucun perso, alors on se contente de répéter trois fois la même ligne
                resultat+=murLigne+ "| \n";       //Ligne de murs simple
                resultat+=murLigne+ "| \n";       //Bis
                resultat+=murLigne+ "| \n";       //Bis!
                resultat+=murLigneHorizo+ "| \n"; //Murets horizontaux
            }
            //Une fois la meta-ligne faite, on passe à la meta-ligne suivante, avec incrémentation de i, dans sa boucle for
        }

        resultat+=bordureAcc + "\n"; //On ajoute ici une bordure horizontale inférieure ("-----------.....------")

        return resultat; //Et le résultat, la grille, est retournée, pour impressiond ans le jeu!
    }

    public boolean deplace(char direction)
    {
        double posXPerso= this.perso.getPositionXPersonnage();

        double posYPerso= this.perso.getPositionYPersonnage();

        switch (direction)
        {
            case 'D':
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

                    if (this.liste.chercheMuret(muretDVert1)!=null)
                    {
                        this.liste.chercheMuret(muretDVert1).setVisible(true);
                    }

                    /*if (this.liste.chercheMuret(muretDVert2)!=null)
                    {
                        this.liste.chercheMuret(muretDVert2).setVisible(true);
                    }*/

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;

            case 'G':
                Muret muretGVert1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),true,true);
                //Muret muretGVert2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+1),true,true);

                /*Muret muretGHori1= new Muret((int)(posXPerso-0.5),(int)posYPerso,false,true);
                Muret muretGHori2= new Muret((int)(posXPerso-1.5),(int)posYPerso,false,true);

                if ((this.liste.chercheMuret(muretGHori1)!=null) || (this.liste.chercheMuret(muretGHori2)!=null)
                        ||(this.liste.chercheMuret(muretGVert1)!=null)||(this.liste.chercheMuret(muretGVert2)!=null) )*/
               // if ((this.liste.chercheMuret(muretGVert1)!=null)||(this.liste.chercheMuret(muretGVert2)!=null))
                if (this.liste.chercheMuret(muretGVert1)!=null)
                {
                   /* if (this.liste.chercheMuret(muretGHori1)!=null)
                    {
                        this.liste.chercheMuret(muretGHori1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretGHori2)!=null)
                    {
                        this.liste.chercheMuret(muretGHori2).setVisible(true);
                    }*/

                    if (this.liste.chercheMuret(muretGVert1)!=null)
                    {
                        this.liste.chercheMuret(muretGVert1).setVisible(true);
                    }

                   /* if (this.liste.chercheMuret(muretGVert2)!=null)
                    {
                        this.liste.chercheMuret(muretGVert2).setVisible(true);
                    }*/
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;

            case 'H':
                /*Muret muretHVert1= new Muret((int)(posXPerso-0.5),(int)posYPerso,true,true);
                Muret muretHVert2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+1),true,true);*/

                Muret muretHHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),false,true);
                //Muret muretHHori2= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),false,true);

               /* if ((this.liste.chercheMuret(muretHHori1)!=null) || (this.liste.chercheMuret(muretHHori2)!=null)
                        ||(this.liste.chercheMuret(muretHVert1)!=null)||(this.liste.chercheMuret(muretHVert2)!=null) ) */
               //if ((this.liste.chercheMuret(muretHHori1)!=null) || (this.liste.chercheMuret(muretHHori2)!=null))
                if (this.liste.chercheMuret(muretHHori1)!=null)
               {
                    if (this.liste.chercheMuret(muretHHori1)!=null)
                    {
                        this.liste.chercheMuret(muretHHori1).setVisible(true);
                    }

                    /*if (this.liste.chercheMuret(muretHHori2)!=null)
                    {
                        this.liste.chercheMuret(muretHHori2).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretHVert1)!=null)
                    {
                        this.liste.chercheMuret(muretHVert1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretHVert2)!=null)
                    {
                        this.liste.chercheMuret(muretHVert2).setVisible(true);
                    }*/

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;

            case 'B':
                /*Muret muretBVert1= new Muret((int)(posXPerso-0.5),(int)posYPerso,true,true);
                Muret muretBVert2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+1),true,true);*/

                Muret muretBHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso+0.5),false,true);
              //  Muret muretBHori2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+0.5),false,true);

                //if ((this.liste.chercheMuret(muretBHori1)!=null) || (this.liste.chercheMuret(muretBHori2)!=null))
                if (this.liste.chercheMuret(muretBHori1)!=null)
                {
                    if (this.liste.chercheMuret(muretBHori1)!=null)
                    {
                        this.liste.chercheMuret(muretBHori1).setVisible(true);
                    }

                  /*  if (this.liste.chercheMuret(muretBHori2)!=null)
                    {
                        this.liste.chercheMuret(muretBHori2).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretHVert1)!=null)
                    {
                        this.liste.chercheMuret(muretHVert1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretHVert2)!=null)
                    {
                        this.liste.chercheMuret(muretHVert2).setVisible(true);
                    }*/

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;
        }
        return false;
    }
}
