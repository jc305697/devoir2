/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class Muret
{
    private int x;
    private int y;
    private boolean vertical;//false=horizontal true=vertical
    private boolean visible;

    public Muret(int x,int y,boolean vertical,boolean visible)
    {
      this.x=x;
      this.y=y;
      this.vertical=vertical;
      this.visible=visible;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }
    /**
     * redefinition de la methode equals
     * @param obj: variable de type Object
     * @return un boolean soit true si les coordonnes en x, y et l'orientation sont egales false sinon
     */
    public boolean equals(Object obj)
    {
       if (obj instanceof Muret) //si peut voir obj comme muret
       {
           Muret referenceObj= (Muret)obj;//stocke cast de reference pour simplifier le code

           boolean coordX = this.x == referenceObj.x;
           boolean coordY= this.y==referenceObj.y;
           boolean compVert= this.vertical==referenceObj.vertical;
           if (coordX && coordY && compVert)
           {
               return true;//si les coordonnes en x, y et l'orientation sont = alors true
           }
       }

       return false;//si arrive ici alors necessairement false

    }

    /**
     *
     * @param nouveauVisible: met ce boolean comme valeur de l'attribut visible de l'objet
     */
    public void setVisible(boolean nouveauVisible)
    {
        this.visible=nouveauVisible;
    }

    /**
     * @return la valeur de l'attribut visible de l'objet
     */
    public boolean getVisible()
    {
        return this.visible;
    }



}
