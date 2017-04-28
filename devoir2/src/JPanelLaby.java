import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jeremycoulombe on 17-04-01.
 */
public class JPanelLaby extends JPanel implements KeyListener
{
private Labyrinthe labyrinthe;
private AffichageLaby affichageLaby;
private JLabel texteVie;
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

        JPanel panneauDroit=new JPanel();
        panneauDroit.setLayout(new BorderLayout());

       JLabel indicationsTouches = new JLabel("droite: d; gauche: g ou s; haut: h ou e; bas: b ou x");

       JLabel texteVie= new JLabel("Il vous reste "+labyrinthe.getPerso().getviesRestantes()+" vies");

      this.texteVie=texteVie;
      JPanel panneauDroitHaut= new JPanel();

      panneauDroitHaut.setLayout(new BorderLayout());

      panneauDroitHaut.add(indicationsTouches,BorderLayout.NORTH);

      panneauDroitHaut.add(texteVie,BorderLayout.CENTER);


      panneauDroit.add(panneauDroitHaut,BorderLayout.NORTH);

       //cree les boutons pour les deplacements
        JButton haut = new JButton("haut");

        haut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('H');
            }

        });

        JButton bas = new JButton("bas");

        bas.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('B');
            }

        });

        JButton droit = new JButton("droit");

        droit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('D');
            }

        });

        JButton gauche = new JButton("gauche");

        gauche.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('H');
            }

        });



        JPanel panneauDroitDroit= new JPanel();//cree sous-panneau du sous-panneau du JPanel principal qui contiendra les boutons

        panneauDroitDroit.setLayout(new BorderLayout());//met comme Layout border layout

        panneauDroitDroit.add(haut,BorderLayout.NORTH);//ajoute boutons au panneau
        panneauDroitDroit.add(bas,BorderLayout.SOUTH);
        panneauDroitDroit.add(gauche,BorderLayout.EAST);
        panneauDroitDroit.add(droit,BorderLayout.WEST);

        panneauDroit.add(panneauDroitDroit,BorderLayout.WEST);//ajoute panneau avec boutons aux sous-panneaux droit du Jpanel principal

       JButton mursVisible = new JButton("rendre murs visibles");

        mursVisible.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.getListe().tousVisible();
                labyrinthe.intelligenceArtificielle();
            }

        });

        JButton boutonIA = new JButton("intelligence artificielle");

        mursVisible.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.intelligenceArtificielle();
            }

        });

        panneauDroit.add(mursVisible,BorderLayout.SOUTH);

        panneauDroit.add(boutonIA,BorderLayout.EAST);


        this.add(panneauDroit,BorderLayout.EAST);


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

    public void setTextVie()
    {
        this.texteVie.setText("Il vous reste "+this.labyrinthe.getPerso().getviesRestantes()+" vies");
    }

    public AffichageLaby getAffichageLaby()
    {
        return this.affichageLaby;
    }


}
