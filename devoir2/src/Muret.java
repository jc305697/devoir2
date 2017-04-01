/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class Muret
{
    private int x;
    private int y;
    private boolean orientation;
    private boolean visible;

    public Muret(int x,int y,boolean orientation,boolean visible)
    {
      this.x=x;
      this.y=y;
      this.orientation=orientation;
      this.visible=visible;
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
           boolean compOrient= this.orientation==referenceObj.orientation;
           if (coordX && coordY && compOrient)
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




}
