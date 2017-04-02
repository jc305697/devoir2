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

        graphics2D.drawRect(0,0,largeur,hauteur);//met rectangle pour enlever ce qui etait la avant
        //coordonne a verifier

    }

}
