/**
 * Created by jeremycoulombe on 17-04-01.
 */
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
