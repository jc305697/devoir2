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
        super.paintComponent(g);//comme dans l'exemple en clasee

        Graphics2D graphics2D=(Graphics2D)g;//cast pour avoir plus de methodes

        int largeur = getWidth();//obtient largeur disponible

        int hauteur=getHeight();//obtient hauteur disponible

        int tailleMurHorizontal= largeur/100*labyrinthe.getL();

        int tailleMurVertical= hauteur/100*labyrinthe.getH();


        graphics2D.drawRect(0,0,largeur,hauteur);//met rectangle pour enlever ce qui etait la avant
        //coordonne a verifier

        //devrait faire cote gauche puis haut et bas puis cote droit avec sortie

        g.drawLine(0, 0, 0, hauteur);
        //fait cote gauche

        g.drawLine(0,0,largeur,0);
        //fait haut

        g.drawLine(0,hauteur,largeur,hauteur);
        //fait bas

        g.drawLine(largeur,0,largeur,labyrinthe.getSortieY());
        //fait cote droit jusqu a sortie

        if (labyrinthe.getSortieY()!= hauteur)
        { //si sortie pas en bas a droite alors continue 1 case plus loin
            g.drawLine(largeur, labyrinthe.getSortieY() + tailleMurVertical, largeur,hauteur );
        }

        for (int i=0;i<labyrinthe.getH();i++)//hauteur y
        {
            for(int j=0; j< labyrinthe.getL();j++)//largeur x
            {
                boolean murVertical= labyrinthe.getListe().chercheMuret(new Muret(j,i,true,false))!=null;
                boolean murHorizontal= labyrinthe.getListe().chercheMuret(new Muret(j,i,false,false))!=null;

                if (murVertical || murHorizontal )
                {
                    boolean enBordure= i==0 || j==labyrinthe.getL()-1 || j==0 || i== labyrinthe.getH()-1;

                    if(!enBordure)
                    {
                        int x2=j*tailleMurHorizontal+tailleMurHorizontal;
                        int y2=i*tailleMurVertical+tailleMurVertical;

                        if(murVertical)
                        {
                            g.drawLine(j * tailleMurHorizontal, i * tailleMurVertical, j * tailleMurHorizontal, y2);
                        }

                        if(murHorizontal)
                        {
                            g.drawLine(j * tailleMurHorizontal,i * tailleMurVertical,x2,i * tailleMurVertical);
                        }

                    }

                }
            }
        }

        int x1= (int)(labyrinthe.getPerso().getPositionXPersonnage())*tailleMurHorizontal;
        int y1= (int)(labyrinthe.getPerso().getPositionYPersonnage())*tailleMurVertical;

        labyrinthe.getPerso().dessine(g,x1,y1,x1+tailleMurHorizontal,y1+tailleMurVertical);



    }

}
