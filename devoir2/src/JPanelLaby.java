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
private JFrame fenetreJeu;
private String[] args;
    //mettre differents boutons
    public JPanelLaby(Labyrinthe labyrinthe,int visibilite,String[] args,JFrame fenetreJeu)
    {

        this.timerBoolean=false;
        AffichageLaby affichageLaby= new AffichageLaby(labyrinthe);
        //cree affichageLaby qui sera afficher au Centre
        this.labyrinthe=labyrinthe;

        this.args=args;
        this.fenetreJeu=fenetreJeu;

        this.affichageLaby=affichageLaby;
        //reset(args,fenetreJeu);

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
               setTextVie(labyrinthe.deplace('H'));
                repaint();
                finDejeu(labyrinthe);
            }

        });

        JButton bas = new JButton("bas");

        bas.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setTextVie(labyrinthe.deplace('B'));
                repaint();
                finDejeu(labyrinthe);
            }

        });

        JButton droit = new JButton("droit");

        droit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setTextVie(labyrinthe.deplace('D'));
                repaint();
                finDejeu(labyrinthe);
            }

        });

        JButton gauche = new JButton("gauche");

        gauche.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setTextVie(labyrinthe.deplace('G'));
                repaint();
                finDejeu(labyrinthe);
            }

        });



        JPanel panneauDroitDroit= new JPanel();//cree sous-panneau du sous-panneau du JPanel principal qui contiendra les boutons

        panneauDroitDroit.setLayout(new BorderLayout());//met comme Layout border layout

        panneauDroitDroit.add(haut,BorderLayout.NORTH);//ajoute boutons au panneau
        panneauDroitDroit.add(bas,BorderLayout.SOUTH);
        panneauDroitDroit.add(gauche,BorderLayout.WEST);
        panneauDroitDroit.add(droit,BorderLayout.EAST);

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
                intelligenceArtificielle();
            }

        });

        panneauDroit.add(mursVisible,BorderLayout.SOUTH);

        panneauDroit.add(boutonIA,BorderLayout.EAST);


        this.add(panneauDroit,BorderLayout.EAST);

        //this.requestFocus();

        this.setFocusable(true);

        this.requestFocusInWindow();

        this.addKeyListener(this);

      /*  Action versHaut =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("passe dans action haut");
                labyrinthe.deplace('H');
                repaint();
                affichageLaby.repaint();

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
                System.out.println("passe dans action bas");
                labyrinthe.deplace('B');
                repaint();
                affichageLaby.repaint();

            }
        };

        String stringBas = "vers Bas";
        this.getInputMap().put(KeyStroke.getKeyStroke("b"),stringBas);
        this.getInputMap().put(KeyStroke.getKeyStroke("x"),stringBas);


        this.getActionMap().put(stringBas,versBas);

        Action versGauche =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("passe dans action gauche");
                labyrinthe.deplace('G');
                repaint();
                affichageLaby.repaint();
            }
        };

        String stringGauche = "vers Gauche";
        this.getInputMap().put(KeyStroke.getKeyStroke("g"),stringGauche);
        this.getInputMap().put(KeyStroke.getKeyStroke("s"),stringGauche);


        this.getActionMap().put(stringGauche,versGauche);

        Action versDroite =new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("passe dans action droit");
                labyrinthe.deplace('D');
                repaint();
                affichageLaby.repaint();


            }
        };

        String stringDroite = "vers Droite";
        this.getInputMap().put(KeyStroke.getKeyStroke("d"),stringDroite);

        this.getActionMap().put(stringDroite,versDroite);*/

        //int indicateurTimer;

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


    public void finDejeu(Labyrinthe labyrinthe)
    {

        if(labyrinthe.getPerso().getviesRestantes()!=0)
        {
           double positionXPerso= labyrinthe.getPerso().getPositionXPersonnage();

            double positionYPerso= labyrinthe.getPerso().getPositionYPersonnage();

            if ((positionXPerso==labyrinthe.getSortieX()-0.5)&&(positionYPerso==labyrinthe.getSortieY()-0.5))
            {
                int reponse= JOptionPane.showConfirmDialog(fenetreJeu,"vous avez gagné. Voulez-vous rejouer","message important",JOptionPane.YES_NO_OPTION);
                if (reponse==0) //oui
                {
                    reset(args,fenetreJeu);
                }

                if (reponse==1)
                {
                        // break;
                    fenetreJeu.dispose();

                }

               // return true;//a gagne et veut pas continuer
            }
            //return false;//pas fin de jeu
         }

        if (labyrinthe.getPerso().getviesRestantes()==0)
        {
            int reponse = JOptionPane.showConfirmDialog(fenetreJeu, "vous avez perdu. Voulez-vous rejouer", "message important", JOptionPane.YES_NO_OPTION);

            if (reponse == 0) //oui
            {
                reset(args, fenetreJeu);
            }

            if (reponse == 1)
            {
                //break;
                fenetreJeu.dispose();

            }
        }
        //return true;// nb de vie =0 et veut pas continuer
    }

    public  void keyPressed(KeyEvent e)
    {
       // System.out.println("1");

    }

    public void keyReleased(KeyEvent e)
    {
       // System.out.println("2");


    }

    public void keyTyped(KeyEvent e)
    {
        System.out.println("keyTyped");
        switch (e.getKeyChar()) {
            case ('d'):
                this.setTextVie(labyrinthe.deplace('D'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
                // System.out.println("d");
                break;
            case ('g'):
                this.setTextVie(labyrinthe.deplace('G'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
               // System.out.println("g");
                break;
            case ('s'):
                this.setTextVie(labyrinthe.deplace('G'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
               // System.out.println("s");
                break;
            case ('h'):
                this.setTextVie(labyrinthe.deplace('H'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
              //  System.out.println("h");
                break;
            case ('e'):
                this.setTextVie( labyrinthe.deplace('H'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
                //System.out.println("e");
                break;
            case ('b'):
                this.setTextVie(labyrinthe.deplace('B'));
                 this.repaint();
                 this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
              //  System.out.println("b");
                break;
            case ('x'):
                this.setTextVie( labyrinthe.deplace('x'));
                this.repaint();
                this.affichageLaby.repaint();
                this.finDejeu(labyrinthe);
                //System.out.println("x");
                break;
            default:
                //System.out.println("1");
                break;
        }
    }

    public void setTextVie(boolean valeurDeplacement)
    {
        if (valeurDeplacement==false)
        {
            System.out.println("setText");
            this.texteVie.setText("Il vous reste "+ this.labyrinthe.getPerso().getviesRestantes()+ " vies");
        }
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

    public static void reset(String[] args,JFrame fenetreJeu)
    {
        int hauteur = Integer.parseInt(args[0]);
        int largeur = Integer.parseInt(args[1]);
        double densite = Double.parseDouble(args[2]);
        int visibiliteTimed = Integer.parseInt(args[3]);
        int viesRestantes = Integer.parseInt(args[4]);

        Labyrinthe laby = new Labyrinthe(largeur, hauteur, densite, visibiliteTimed, viesRestantes);

        JPanelLaby panelLaby = new JPanelLaby(laby,visibiliteTimed,args,fenetreJeu);

        fenetreJeu.setContentPane(panelLaby);

    }
    public boolean intelligenceArtificielle()
    {
        double positionXPerso = labyrinthe.getPerso().getPositionXPersonnage();
        double positionYPerso = labyrinthe.getPerso().getPositionYPersonnage();


        while(!((positionXPerso==labyrinthe.getSortieX()-0.5)&&(positionYPerso==labyrinthe.getSortieY()-0.5)))//tant que atteint pas la sortie
        {
            double posXperso = labyrinthe.getPerso().getPositionXPersonnage();
            double posYperso = labyrinthe.getPerso().getPositionYPersonnage();

            boolean murDroite = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - .5), true, false)) != null;
            boolean murBas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - .5), false, false)) != null;


            boolean murGauche = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), true, false)) != null;//true s'il y a un muret
            boolean murHaut = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), false, false)) != null;//true s'il y a un muret

            if (murDroite)
            {
                if (murGauche && murBas && murHaut)//bloque
                {
                    return false;
                }

                if (murBas && murHaut) {
                    labyrinthe.deplace('G');
                }

                if (murBas && murGauche) {
                    labyrinthe.deplace('H');
                }


            }

            else {
                labyrinthe.deplace('D');
            }
        }
        return true;


    }

}
