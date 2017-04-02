import javax.swing.*;
import java.awt.*;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JPanelLaby extends JPanel
{
    public JPanelLaby(int largeur,int hauteur,double densiteLaby,double delai, int nbVie)
    {
        AffichageLaby affichageLaby= new AffichageLaby(new Labyrinthe(largeur,hauteur,densiteLaby,delai,nbVie));
        //cree affichageLaby qui sera afficher au Centre

        setLayout(new BorderLayout());//mets le Borderlayout comme layout

        add(affichageLaby,BorderLayout.CENTER);//mets affichageLaby au centre

    }
}
