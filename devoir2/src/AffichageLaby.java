import javax.swing.*;
import java.awt.*;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class AffichageLaby extends JComponent
{
    Labyrinthe labyrinthe;

    public AffichageLaby(Labyrinthe labyrinthe)
    {
        this.labyrinthe=labyrinthe;
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);//comme dans l'exemple en classe

        Graphics2D graphics2D=(Graphics2D)g;//cast pour avoir plus de methodes

       // System.out.println("rendu affichage");

        int largeur = getWidth();//obtient largeur disponible

        int hauteur=getHeight();//obtient hauteur disponible

       // int tailleMurHorizontal= (largeur/100)*labyrinthe.getL();

         int tailleMurHorizontal= (int)((largeur/labyrinthe.getL())*.75);


        //int tailleMurVertical= (hauteur/100)*labyrinthe.getH();
        int tailleMurVertical= (int)((hauteur/labyrinthe.getH())*.75);




        int longBordSup= labyrinthe.getL()*tailleMurHorizontal;

        int longBordGauche=labyrinthe.getH()*tailleMurVertical;

       graphics2D.drawRect(0,0,largeur,hauteur);//met rectangle pour enlever ce qui etait la avant
        //coordonne a verifier
      //  graphics2D.drawRect(0,0,longBordSup,longBordGauche);//met rectangle pour enlever ce qui etait la avant


        //devrait faire cote gauche puis haut et bas puis cote droit avec sortie

        g.drawLine(2, 2, 2, longBordGauche+1);
        //fait cote gauche

        g.drawLine(2,2,longBordSup+1,2);
        //fait haut

        g.drawLine(2,longBordGauche+1,longBordSup+1,longBordGauche+1);
        //fait bas

        //g.drawLine(longBordSup-1,0,longBordSup-1,labyrinthe.getSortieY()*tailleMurVertical-1);
        g.drawLine(longBordSup+1,2,longBordSup+1,labyrinthe.getSortieY()*tailleMurVertical);

        //fait cote droit jusqu a sortie

        if (labyrinthe.getSortieY()!= hauteur)
        { //si sortie pas en bas a droite alors continue 1 case plus loin
            g.drawLine(longBordSup+1, labyrinthe.getSortieY()*tailleMurVertical + tailleMurVertical, longBordSup+1,longBordGauche+1 );
        }

        for (int i=0;i<labyrinthe.getH();i++)//hauteur y
        {
            for(int j=0; j< labyrinthe.getL();j++)//largeur x
            {
                boolean murVertical= labyrinthe.getListe().chercheMuret(new Muret(j,i,true,false))!=null;
                boolean murHorizontal= labyrinthe.getListe().chercheMuret(new Muret(j,i,false,false))!=null;

                //System.out.println("evalue condition");
                if (murVertical || murHorizontal )
                {
                  //  boolean enBordure= i==0 || j==labyrinthe.getL()-1 || j==0 || i== labyrinthe.getH()-1;
                    //System.out.println("condition non nulle vrai");

                 //   if(!enBordure)
                  //  {
                     //   System.out.println("pas en bordure");

                        int x2=j*tailleMurHorizontal+tailleMurHorizontal;
                        int y2=i*tailleMurVertical+tailleMurVertical;

                        if(murVertical)
                        {
                            if (j!=labyrinthe.getSortieX()) //veut pas couper la sortie
                            {
                                g.drawLine(j * tailleMurHorizontal - 1, i * tailleMurVertical - 1, j * tailleMurHorizontal - 1, y2 - 1);
                            }
                        }

                        if(murHorizontal)
                        {
                            g.drawLine(j * tailleMurHorizontal-1,i * tailleMurVertical-1,x2-1,i * tailleMurVertical-1);
                        }

                   // }

                }
            }
        }

        int x1= (int)(labyrinthe.getPerso().getPositionXPersonnage())*tailleMurHorizontal;
        int y1= (int)(labyrinthe.getPerso().getPositionYPersonnage())*tailleMurVertical;

        labyrinthe.getPerso().dessine(g,x1,y1,x1+tailleMurHorizontal,y1+tailleMurVertical);



    }

}
