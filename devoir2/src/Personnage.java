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

    /**
     *
     * @param g Graphics
     * @param x1 coordonne en x du haut a gauche de la boite dans laquelle je veux faire mon personnage
     * @param y1 coordonne en y du haut a gauche de la boite dans laquelle je veux faire mon personnage
     * @param x2 coordonne en x du bas a droite de la boite dans laquelle je veux faire mon personnage
     * @param y2 coordonne en y du bas a droite de la boite dans laquelle je veux faire mon personnage
     */
    public void dessine(Graphics g, int x1, int y1, int x2, int y2)
    {
        g.fillOval(x1,y1,x2-x1,y2-y1);
    }
}



