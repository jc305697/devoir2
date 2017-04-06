/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class Labyrinthe
{
    int  l;//largeur
    int h; //hauteur
    Personnage perso;
    ListeMuret liste;
    int sortieX;
    int sortieY;

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

        double posAleatPersoX= (double)Math.round(Math.random())+0.5;
        double posAleatPersoY= (double)Math.round(Math.random())+0.5;

        this.perso.setPositionXPersonnage(posAleatPersoX);
        this.perso.setPositionYPersonnage(posAleatPersoY);

        int posAleatSortieX=0;//position en x ou sera la sortie initialise a 0 pour eviter probleme
        int posAleatSortieY=0;//position en y ou sera la sortie

        double nbAleat= Math.random();

        if (nbAleat<0.25)
        {
            posAleatSortieX=0;
            posAleatSortieY=(int)(Math.random()*(h-1));
        }

        if ((nbAleat<0.50)&&(nbAleat>0.25))
        {
            posAleatSortieX=l-1;
            posAleatSortieY=(int)(Math.random()*(h-1));
        }

        if ((nbAleat<0.75)&&(nbAleat>0.50))
        {
            posAleatSortieX=(int)(Math.random()*(l-1));
            posAleatSortieY=0;
        }

        if ((nbAleat<1)&&(nbAleat>.75))
        {
            posAleatSortieX=(int)(Math.random()*(l-1));
            posAleatSortieY=h-1;
        }

        this.sortieX=posAleatSortieX;

        this.sortieY=posAleatSortieY;



    }
}
