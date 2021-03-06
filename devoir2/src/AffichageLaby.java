import javax.swing.*;
import java.awt.*;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
//Jérémy Coulombe et Stephanie Guay-Vachon

public class AffichageLaby extends JComponent
{
    Labyrinthe labyrinthe;

    public AffichageLaby(Labyrinthe labyrinthe)
    {
        this.labyrinthe=labyrinthe;
    }

    /**
     * @param g: graphics donné par le système
     */
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);//comme dans l'exemple en classe

        Graphics2D graphics2D=(Graphics2D)g;//cast pour avoir plus de methodes


        int largeur = getWidth();//obtient largeur d'affichage disponible

        int hauteur= getHeight();//obtient hauteur d'affichage disponible


        int tailleMurHorizontal= (int)((largeur/labyrinthe.getL())*.75);//regle de 3 pour adapter les mesures a la taille d'affichage pour les murs horizontal


        int tailleMurVertical= (int)((hauteur/labyrinthe.getH())*.75);//regle de 3 pour adapter les mesures a la taille d'affichage pour les murs vertical




        int longBordSup= (int)(labyrinthe.getL()*tailleMurHorizontal);//calcule la longueur de la bordure supérieure meme long. pour bordure du bas

        int longBordGauche= (int)(labyrinthe.getH()*tailleMurVertical);//calcule la longueur de la bordure gauche meme long. pour bord. du bas

       graphics2D.drawRect(0,0,largeur,hauteur);//met rectangle pour enlever ce qui etait la avant


        //fait cote gauche

        g.drawLine(3, 3, 3, longBordGauche+2);

        //fait haut

        g.drawLine(3,3,longBordSup+2,3);

        //fait bas
        g.drawLine(3,longBordGauche+2,longBordSup+2,longBordGauche+2);

        //fait cote droit jusqu a sortie
        g.drawLine(longBordSup+2,3,longBordSup+2,labyrinthe.getSortieY()*tailleMurVertical+2);


        if (labyrinthe.getSortieY()!= hauteur)
        { //si sortie pas en bas a droite alors continue 1 taille de mur vertical plus loin
            g.drawLine(longBordSup+2, labyrinthe.getSortieY()*tailleMurVertical + tailleMurVertical+2, longBordSup+2,longBordGauche+2 );

        }

        for (int i=0;i<labyrinthe.getH();i++)//hauteur y
        {
            for(int j=0; j< labyrinthe.getL();j++)//largeur x
            {
                boolean murVertical= labyrinthe.getListe().chercheMuret(new Muret(j,i,true,false))!=null;

                boolean murHorizontal= labyrinthe.getListe().chercheMuret(new Muret(j,i,false,false))!=null;

                boolean murHorizontalVisible=false;//doit initialiser pour éviter erreur de compilation
                boolean murVerticalVisible=false;

                if (murVertical) //s'il y a un mur
                {
                     murVerticalVisible = labyrinthe.getListe().chercheMuret(new Muret(j, i, true, false)).getVisible();
                    //murVerticalVisible=boolean illustrant si mur visible ou pas
                }

                if (murHorizontal)
                {
                 murHorizontalVisible = labyrinthe.getListe().chercheMuret(new Muret(j, i, false, false)).getVisible();
                    //murVerticalVisible=boolean illustrant si mur visible ou pas
                }

                if ((murVertical && murVerticalVisible)|| (murHorizontal && murHorizontalVisible))
                {
                    int x2=j*tailleMurHorizontal+tailleMurHorizontal;//X jusqu'où je veux dessiner mon mur

                    int y2=i*tailleMurVertical+tailleMurVertical;//Y jusqu'où je veux dessiner mon mur

                        if(murVertical)
                        {
                            if (j!=labyrinthe.getSortieX()) //veut pas couper la sortie
                            {
                                  g.drawLine(j*tailleMurHorizontal+3,i*tailleMurVertical+3,j*tailleMurHorizontal+3,y2+3);
                                //trace une ligne vertical et additionne 3  pour compenser pour le decalage du dessin du contour
                            }
                        }

                        if(murHorizontal)
                        {
                            g.drawLine(j * tailleMurHorizontal+3,i * tailleMurVertical+3,x2+3,i * tailleMurVertical+3);
                            //trace une ligne horizontal de la longueur d'un mur horizontal
                        }


                }
            }
        }

        int x1= (int)(labyrinthe.getPerso().getPositionXPersonnage())*tailleMurHorizontal+4;//position en x du personnage dans l'affichage ajoute 3 pour compenser
        int y1= (int)(labyrinthe.getPerso().getPositionYPersonnage())*tailleMurVertical+4;//position en y du personnage dans l'affichage ajoute 3 pour compenser

        labyrinthe.getPerso().dessine(g,x1,y1,x1+tailleMurHorizontal-3,y1+tailleMurVertical-3);//dessine le personnage


    }

}
