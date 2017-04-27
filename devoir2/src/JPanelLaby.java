import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JPanelLaby extends JPanel implements KeyListener
{
private Labyrinthe labyrinthe;
private AffichageLaby affichageLaby;
    //mettre differents boutons
    public JPanelLaby(Labyrinthe labyrinthe)
    {

        AffichageLaby affichageLaby= new AffichageLaby(labyrinthe);
        //cree affichageLaby qui sera afficher au Centre
        this.labyrinthe=labyrinthe;
        this.affichageLaby=affichageLaby;
        this.setLayout(new BorderLayout());//mets le Borderlayout comme layout
        this.requestFocus();
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

    public  void keyPressed(KeyEvent e)
    {
        System.out.println("1");

        switch (e.getKeyChar())
        {
            case('d'):
                labyrinthe.deplace('D');
                //this.affichageLaby.repaint();
                System.out.println("d");
                break;
            case ('g'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("g");
                break;
            case('s'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("s");
                break;
            case('h'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("h");
                break;
            case('e'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("e");
                break;
            case ('b'):
                labyrinthe.deplace('B');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("b");
                break;
            case('x'):
                labyrinthe.deplace('x');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("x");
                break;
             default:
                 System.out.println("1");

        }
    }

    public void keyReleased(KeyEvent e)
    {
        System.out.println("2");

        switch (e.getKeyChar())
        {
            case('d'):
                labyrinthe.deplace('D');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("d");
                break;
            case ('g'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("g");
                break;
            case('s'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("s");
                break;
            case('h'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("h");
                break;
            case('e'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("e");
                break;
            case ('b'):
                labyrinthe.deplace('B');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("b");
                break;
            case('x'):
                labyrinthe.deplace('x');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("x");
                break;
            default:
                System.out.println("2");

        }
    }

    public void keyTyped(KeyEvent e)
    {
        System.out.println("3");

        switch (e.getKeyChar())
        {
            case('d'):
                labyrinthe.deplace('D');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("d");
                break;
            case ('g'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("g");
                break;
            case('s'):
                labyrinthe.deplace('G');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("s");
                break;
            case('h'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("h");
                break;
            case('e'):
                labyrinthe.deplace('H');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("e");
                break;
            case ('b'):
                labyrinthe.deplace('B');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("b");
                break;
            case('x'):
                labyrinthe.deplace('x');
                //this.repaint();
                //this.affichageLaby.repaint();
                System.out.println("x");
                break;
            default:
                System.out.println("3");

        }
    }
}
