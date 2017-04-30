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
private boolean timerBoolean;
private Timer minuterie;
    //mettre differents boutons
    public JPanelLaby(Labyrinthe labyrinthe,int visibilite)
    {

        this.timerBoolean=false;
        AffichageLaby affichageLaby= new AffichageLaby(labyrinthe);
        //cree affichageLaby qui sera afficher au Centre
        this.labyrinthe=labyrinthe;
        this.affichageLaby=affichageLaby;
        this.setLayout(new BorderLayout());//mets le Borderlayout comme layout
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
                repaint();
            }

        });

        JButton bas = new JButton("bas");

        bas.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('B');
                repaint();
            }

        });

        JButton droit = new JButton("droit");

        droit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('D');
                repaint();
            }

        });

        JButton gauche = new JButton("gauche");

        gauche.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('G');
                repaint();
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


        this.requestFocus();

        Action versHaut =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('H');
                repaint();
                //affichageLaby.repaint();

            }
        };

        String stringHaut= "vers Haut";
        this.getInputMap().put(KeyStroke.getKeyStroke("h"),stringHaut);
        this.getInputMap().put(KeyStroke.getKeyStroke("e"),stringHaut);


        this.getActionMap().put(stringHaut,versHaut);

        Action versBas =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('B');
                repaint();
                //affichageLaby.repaint();

            }
        };

        String stringBas = "vers Bas";
        this.getInputMap().put(KeyStroke.getKeyStroke("b"),stringBas);
        this.getInputMap().put(KeyStroke.getKeyStroke("x"),stringBas);


        this.getActionMap().put(stringBas,versHaut);

        Action versGauche =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('G');
                repaint();
                // affichageLaby.repaint();
            }
        };

        String stringGauche = "vers Gauche";
        this.getInputMap().put(KeyStroke.getKeyStroke("g"),stringGauche);
        this.getInputMap().put(KeyStroke.getKeyStroke("s"),stringGauche);


        this.getActionMap().put(stringGauche,versHaut);

        Action versDroite =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.deplace('D');
                repaint();
                //affichageLaby.repaint();

            }
        };

        String stringDroite = "vers Droite";
        this.getInputMap().put(KeyStroke.getKeyStroke("d"),stringDroite);

        this.getActionMap().put(stringDroite,versHaut);

        int indicateurTimer;

        //rend invisible les murs après un certain temps
        ActionListener rendreMursInvisible = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("timer");
                    labyrinthe.getListe().tousInvisible();
                    repaint();
                    affichageLaby.repaint();

                    getMinuterie().stop();//arrete le timer
            }
        };

        int delai= visibilite*1000;//converti en millisecondes
        Timer timer = new Timer(delai,rendreMursInvisible);
        timer.start();
        this.minuterie=timer;
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


    }

    public void keyTyped(KeyEvent e)
    {
        System.out.println("3");

    }

    public void setTextVie()
    {
        this.texteVie.setText("Il vous reste "+this.labyrinthe.getPerso().getviesRestantes()+" vies");
    }

    public AffichageLaby getAffichageLaby()
    {
        return this.affichageLaby;
    }

    public Labyrinthe getLabyrinthe()
    {
        return this.labyrinthe;
    }

    public Timer getMinuterie()
    {
        return this.minuterie;
    }

}
