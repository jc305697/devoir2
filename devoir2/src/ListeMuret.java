/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class ListeMuret
{
    NoeudMuret premierNeud;
    int size;

    /**
     * constructeur de la liste
     */
    public ListeMuret()
    {
        premierNeud=null;
        size=0;
    }


    /**
     *
     * @param nouveauMuret: ajoute le nouveauMuret au début en créant un nouveau 1er noeud
     */
    public void ajoutMuret(Muret nouveauMuret)
    {
        NoeudMuret nouveauPremierNoeud=new NoeudMuret(nouveauMuret,null);

        nouveauPremierNoeud.noeudSuivant=premierNeud;

        size++;
    }

    /**
     * rend tous les murs invisible
     */
   public void tousInvisible()
    {
       if(premierNeud!=null)//si premier noeud n'est pas nulle
       {
           NoeudMuret noeud= premierNeud;

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
        if(premierNeud!=null)//si premier noeud n'est pas nulle
        {
            NoeudMuret noeud= premierNeud;

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

        if (premierNeud == null)//si premier noeud n'est pas nulle
        {
            return null;
        }

        NoeudMuret noeud = premierNeud;

        while ((noeud.noeudSuivant != null)&&(noeud.element.equals(m)))//continue tant que prochain noeud n'est pas = à null
        {
            noeud = noeud.noeudSuivant;
        }

       // if (noeud.element.equals(m))
        if (noeud!=null)
        {

            if (noeud.element.equals(m))//methode equals redefini dans muret
            {
                return noeud.element;
            }
        }

        return null;//si rendu ici alors pas dans la liste

    }

}
