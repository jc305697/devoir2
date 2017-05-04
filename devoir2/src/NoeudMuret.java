/**
 * Created by jeremycoulombe on 17-04-01.
 */
//Jérémy Coulombe et Stephanie Guay-Vachon


public class NoeudMuret
{
    Muret element;
    NoeudMuret noeudSuivant;

    public NoeudMuret(Muret element, NoeudMuret noeudSuivant)
    {
        this.element=element;
        this.noeudSuivant=noeudSuivant;
    }
}

