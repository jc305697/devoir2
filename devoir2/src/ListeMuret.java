/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class ListeMuret
{
    private NoeudMuret premierNoeud;
   private int size;

    /**
     * constructeur de la liste
     */
    public ListeMuret()
    {
        premierNoeud=null;
        size=0;
    }

    public int getSize()
    {
        return size;
    }

    public NoeudMuret getPremierNoeud()
    {
        return premierNoeud;
    }




    /**
     *
     * @param nouveauMuret: ajoute le nouveauMuret au début en créant un nouveau 1er noeud
     */
    public void ajoutMuret(Muret nouveauMuret)
    {
        NoeudMuret nouveauPremierNoeud=new NoeudMuret(nouveauMuret,null);

        nouveauPremierNoeud.noeudSuivant=premierNoeud;

        premierNoeud=nouveauPremierNoeud;
        size++;
    }

    /**
     * rend tous les murs de la liste invisible
     */
   public void tousInvisible()
    {
       if(premierNoeud!=null)//si premier noeud n'est pas nulle
       {
           NoeudMuret noeud= premierNoeud;

           while (noeud.noeudSuivant!=null)//continue tant que prochain noeud n'est pas = à null
           {
               noeud.element.setVisible(false);
               noeud=noeud.noeudSuivant;
           }
           noeud.element.setVisible(false);

       }
    }

    /**
     * rend tous les murs de la liste visible
     */
    public void tousVisible()
    {
        if(premierNoeud!=null)//si premier noeud n'est pas nulle
        {
            NoeudMuret noeud= premierNoeud;

            while (noeud.noeudSuivant!=null)//continue tant que prochain noeud n'est pas = à null
            {
                noeud.element.setVisible(true);
                noeud=noeud.noeudSuivant;
            }

            noeud.element.setVisible(true);

        }
    }

    /**
     *
     * @param m: muret que je cherche dans ma liste chainee
     * @return: retourne reference vers muret
     */
    public Muret chercheMuret(Muret m)
    {

        if (premierNoeud == null)//si premier noeud n'est pas nulle
        {
          //  System.out.println("retourne null");
            //System.out.println("premierNoeud==null");
            return null;
        }

        NoeudMuret noeud = premierNoeud;

        while ((noeud.noeudSuivant != null)&&(noeud.element.equals(m)==false))//continue tant que prochain noeud n'est pas = à null
        {
            noeud = noeud.noeudSuivant;
        }

        if (noeud.element.equals(m))//j'ai trouvé mon élément
        {

            return noeud.element;
        }

        return null;//si rendu ici alors pas dans la liste puisque rendu a la fin de la liste

    }

}
