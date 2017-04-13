import javax.swing.*;
import java.awt.*;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JPanelLaby extends JPanel
{

    //mettre differents boutons
    public JPanelLaby(Labyrinthe labyrinthe)
    {

        AffichageLaby affichageLaby= new AffichageLaby(labyrinthe);
        //cree affichageLaby qui sera afficher au Centre

        this.setLayout(new BorderLayout());//mets le Borderlayout comme layout

        this.add(affichageLaby,BorderLayout.CENTER);//mets affichageLaby au centre

    }


    public boolean boucleDeJeu(Labyrinthe labyrinthe)
    {

        while (labyrinthe.getPerso().getviesRestantes()!=0)
        {
            double positionXPerso= labyrinthe.getPerso().getPositionXPersonnage();

            double positionYPerso= labyrinthe.getPerso().getPositionYPersonnage();

            if ((positionXPerso==labyrinthe.getSortieX()-0.5)&&(positionYPerso==labyrinthe.getSortieY()-0.5))
            {
                return true;
            }


        }
        return false;
    }
}
