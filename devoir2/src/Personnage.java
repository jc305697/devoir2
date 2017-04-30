/**
 * Created by jeremycoulombe on 17-04-01.
 */
import java.awt.*;
public class Personnage
{
    //////SET ET GET POUR POSITIONS
    private double positionX;

    private double positionY;

    private int viesRestantes;

    public Personnage (double posX, double posY, int nbVies)
    {
        this.positionX=posX;
        this.positionY=posY;
        this.viesRestantes=nbVies;
    }

    public double getPositionXPersonnage()
    {
        return positionX;
    }

    public void setPositionXPersonnage(double newPosX)
    {
        positionX = newPosX;
    }


    public double getPositionYPersonnage()
    {
        return positionY;
    }

    public void setPositionYPersonnage(double newPosY)
    {
        positionY = newPosY;
    }
    /////////////////////////////////////////////////////////


    //////SET ET GET POUR NBR DE VIES

    public int getviesRestantes()
    {
        return viesRestantes;
    }

    public void setViesRestantes(int nouveauViesRestantes)
    {
        viesRestantes = nouveauViesRestantes;
    }
    /////////////////////////////////////////////////////////


    //////MÉTHODE POUR DESSINER LE PERSONNAGE DANS LE GRAPH
    public void dessine(Graphics g, int x1, int y1, int x2, int y2)
    {
        //TO DO:
        /*une méthode void dessine(Graphics g, int x1, int y1, int x2, int y2)
        qui sera appelée en lui passant un contexte graphique,
        ainsi que les dimensions (en pixels) de la boite dans
        laquelle dessiner le personnage.
        Cette méthode sera appelée par la méthode paintComponent de
        votre AffichageLaby. Vous pouvez par exemple commencer
        par représenter votre personnage par un simple cercle ou ovale
        (et plus tard faire quelque chose de plus joli). */

       // Graphics2D graphics2D= (Graphics2D)g;
        g.fillOval(x1,y1,x2-x1,y2-y1);
    }
}



