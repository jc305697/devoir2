/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class ListeMuret
{
    NoeudMuret premierNoeud;
    int size;

    /**
     * constructeur de la liste
     */
    public ListeMuret()
    {
        premierNoeud=null;
        size=0;
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
     * rend tous les murs invisible
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
            System.out.println("retourne null");
            return null;

        }

        NoeudMuret noeud = premierNoeud;

        while ((noeud.noeudSuivant != null)&&(noeud.element.equals(m)==false))//continue tant que prochain noeud n'est pas = à null
        {
            noeud = noeud.noeudSuivant;
        }

       // if (noeud.element.equals(m))

        if (noeud!=null)
        {

           /* if (noeud.element.equals(m))//methode equals redefini dans muret
            {
                System.out.println("retourne muret");
                return noeud.element;
            }*/
            System.out.println("retourne muret");

            return noeud.element;
        }
        System.out.println("retourne null");

        return null;//si rendu ici alors pas dans la liste

    }

}
