/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class Labyrinthe
{
    private int  l;//largeur
    private int h; //hauteur
    private Personnage perso;
    private ListeMuret liste;
    private int sortieX;
    private int sortieY;

    public Labyrinthe(int l,int h,double densiteLaby, double delai, int nbVie)
    {
        this.l=l;
        this.h=h;

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
        this.perso.setViesRestantes(nbVie);

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

    public String toString()
    {
        String resultat="";
        for (double i=0;i<this.h;i=i+0.5)//hauteur "y"
        {
            for (double j=0;j<this.l;j=j+0.5)//largeur "x"
            {
                if ((j-0.5==(int)j)&&(i-0.5==(int)i))//si j ai 2 chiffres avec .5
                {
                    boolean memeX= this.perso.getPositionXPersonnage()==j;
                    boolean memeY= this.perso.getPositionYPersonnage()==i;
                    if (memeX&&memeY)
                    {
                      //  this.perso.dessine();
                    }
                }


                if (!((j-0.5==(int)j)&&(i-0.5==(int)i)))// au moins 1 chiffre avec 0.5
                {
                    Muret muretPosHoriz = new Muret((int) j, (int) i, false, true);
                    Muret muretPosVert = new Muret((int) j, (int) i, true, true);
                    if (this.liste.chercheMuret(muretPosHoriz) != null) {
                        //System.out.print("---");
                        resultat+="---";
                    }

                   /* if (this.liste.chercheMuret(muretPosVert) != null) probleme il faut ajouter le contenu des cases apres avant de mettre les autres de marcations de case
                    {
                        for (int k = 0; k < 2; k++)
                        {
                           // System.out.print("|\n");
                          //  resultat+="|\n";
                        }

                        //System.out.print("|");

                    }*/
                }
            }
        }
        return resultat;
    }

    public boolean deplace(char direction)
    {
        double posXPerso= this.perso.getPositionXPersonnage();
        double posYPerso= this.perso.getPositionYPersonnage();
        switch (direction)
        {
            case 'D':
                Muret muretDVert1= new Muret((int)(posXPerso+0.5),(int)posYPerso,true,true);
                Muret muretDVert2= new Muret((int)(posXPerso+0.5),(int)(posYPerso+1),true,true);

                Muret muretDHori1= new Muret((int)(posXPerso+0.5),(int)posYPerso,false,true);
                Muret muretDHori2= new Muret((int)(posXPerso-0.5),(int)posYPerso,false,true);

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
               if ((this.liste.chercheMuret(muretDVert1)!=null)||(this.liste.chercheMuret(muretDVert2)!=null))
               {

                    if (this.liste.chercheMuret(muretDVert1)!=null)
                    {
                        this.liste.chercheMuret(muretDVert1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretDVert2)!=null)
                    {
                        this.liste.chercheMuret(muretDVert2).setVisible(true);
                    }

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;

            case 'G':
                Muret muretGVert1= new Muret((int)(posXPerso-0.5),(int)posYPerso,true,true);
                Muret muretGVert2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+1),true,true);

                /*Muret muretGHori1= new Muret((int)(posXPerso-0.5),(int)posYPerso,false,true);
                Muret muretGHori2= new Muret((int)(posXPerso-1.5),(int)posYPerso,false,true);

                if ((this.liste.chercheMuret(muretGHori1)!=null) || (this.liste.chercheMuret(muretGHori2)!=null)
                        ||(this.liste.chercheMuret(muretGVert1)!=null)||(this.liste.chercheMuret(muretGVert2)!=null) )*/
                if ((this.liste.chercheMuret(muretGVert1)!=null)||(this.liste.chercheMuret(muretGVert2)!=null))
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

                    if (this.liste.chercheMuret(muretGVert2)!=null)
                    {
                        this.liste.chercheMuret(muretGVert2).setVisible(true);
                    }
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);
                    return false;
                }

                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);
                return true;

            case 'H':
                /*Muret muretHVert1= new Muret((int)(posXPerso-0.5),(int)posYPerso,true,true);
                Muret muretHVert2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+1),true,true);*/

                Muret muretHHori1= new Muret((int)(posXPerso+0.5),(int)(posYPerso-0.5),false,true);
                Muret muretHHori2= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),false,true);

               /* if ((this.liste.chercheMuret(muretHHori1)!=null) || (this.liste.chercheMuret(muretHHori2)!=null)
                        ||(this.liste.chercheMuret(muretHVert1)!=null)||(this.liste.chercheMuret(muretHVert2)!=null) ) */
               if ((this.liste.chercheMuret(muretHHori1)!=null) || (this.liste.chercheMuret(muretHHori2)!=null))
               {
                    if (this.liste.chercheMuret(muretHHori1)!=null)
                    {
                        this.liste.chercheMuret(muretHHori1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretHHori2)!=null)
                    {
                        this.liste.chercheMuret(muretHHori2).setVisible(true);
                    }

                    /*if (this.liste.chercheMuret(muretHVert1)!=null)
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

                Muret muretBHori1= new Muret((int)(posXPerso+0.5),(int)(posYPerso+0.5),false,true);
                Muret muretBHori2= new Muret((int)(posXPerso-0.5),(int)(posYPerso+0.5),false,true);

                if ((this.liste.chercheMuret(muretBHori1)!=null) || (this.liste.chercheMuret(muretBHori2)!=null))
                {
                    if (this.liste.chercheMuret(muretBHori1)!=null)
                    {
                        this.liste.chercheMuret(muretBHori1).setVisible(true);
                    }

                    if (this.liste.chercheMuret(muretBHori2)!=null)
                    {
                        this.liste.chercheMuret(muretBHori2).setVisible(true);
                    }

                    /*if (this.liste.chercheMuret(muretHVert1)!=null)
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
