import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

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
private char lastMove= ' ';
private Timer ai;
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

        ActionListener appelleAi = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (!intelligenceArtificielle())
                {
                    repaint();
                    affichageLaby.repaint();

                    int reponse= JOptionPane.showConfirmDialog(fenetreJeu,"L'intelligence artificielle ne peut résoudre le problème. Voulez-vous rejouez?","message important",JOptionPane.YES_NO_OPTION);
                    if (reponse==0) //oui
                    {
                        reset(args,fenetreJeu);
                    }

                    if (reponse==1)
                    {
                        // break;
                        //fenetreJeu.setVisible(false);
                        // fenetreJeu.dispose();
                        System.exit(0);

                    }
                }

                repaint();
                affichageLaby.repaint();
            }
        };

        this.ai = new Timer(5,appelleAi);//cree timer qui appelle l'ia

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
                repaint();
                affichageLaby.repaint();
            }

        });

        JButton boutonIA = new JButton("intelligence artificielle");

        boutonIA.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               ai.start();
            }

        });

        panneauDroit.add(mursVisible,BorderLayout.SOUTH);

        panneauDroit.add(boutonIA,BorderLayout.EAST);


        this.add(panneauDroit,BorderLayout.EAST);

        //this.requestFocus();

        this.setFocusable(true);

        this.requestFocusInWindow();

        this.addKeyListener(this);


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

        minuterie = new Timer(delai,rendreMursInvisible);
        minuterie.start();
    }





    public void finDejeu(Labyrinthe labyrinthe)
    {

        if(labyrinthe.getPerso().getviesRestantes()!=0)
        {
           double positionXPerso= labyrinthe.getPerso().getPositionXPersonnage();

            double positionYPerso= labyrinthe.getPerso().getPositionYPersonnage();

           if ((positionXPerso-1.5==labyrinthe.getSortieX())&&(positionYPerso==labyrinthe.getSortieY()+0.5))
          //  System.out.println(positionYPerso==labyrinthe.getSortieY()+0.5);
           // if((positionXPerso>labyrinthe.getSortieX())&&(positionYPerso==labyrinthe.getSortieY()-0.5))
           // if((positionXPerso>labyrinthe.getSortieX())&&(positionYPerso==labyrinthe.getSortieY()+0.5))
            {
                ai.stop();
                int reponse= JOptionPane.showConfirmDialog(fenetreJeu,"vous avez gagné. Voulez-vous rejouer","message important",JOptionPane.YES_NO_OPTION);
                if (reponse==0) //oui
                {
                    reset(args,fenetreJeu);
                }

                if (reponse==1)
                {
                        // break;
                    //fenetreJeu.setVisible(false);
                   // fenetreJeu.dispose();
                    System.exit(0);

                }

               // return true;//a gagne et veut pas continuer
            }
            //return false;//pas fin de jeu
         }

        if (labyrinthe.getPerso().getviesRestantes()==0)
        {
            ai.stop();
            int reponse = JOptionPane.showConfirmDialog(fenetreJeu, "vous avez perdu. Voulez-vous rejouer", "message important", JOptionPane.YES_NO_OPTION);

            if (reponse == 0) //oui
            {
                reset(args, fenetreJeu);
            }

            if (reponse == 1)
            {

                //fenetreJeu.setVisible(false);
                // fenetreJeu.dispose();
                System.exit(0);

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
    //    System.out.println("keyTyped");
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
        fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetreJeu.setVisible(true);

    }

    /**
     *
     * @return: un boolean indiquant si l'ia a fini ou si elle est bloqué
     */
    public boolean intelligenceArtificielle()
    {
        double posXperso = labyrinthe.getPerso().getPositionXPersonnage();
        double posYperso = labyrinthe.getPerso().getPositionYPersonnage();




        //while(!((posXperso-.5==labyrinthe.getSortieX())&&(posYperso-.5==labyrinthe.getSortieY())))//tant que atteint pas la sortie
       // {

            posXperso = labyrinthe.getPerso().getPositionXPersonnage();
            posYperso = labyrinthe.getPerso().getPositionYPersonnage();

            boolean murDroite = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - .5), true, false)) != null;
            boolean murDroiteLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + 1.5), (int) (posYperso - .5), true, false)) != null;
            boolean murDroiteLoinEnbas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - 1.5), true, false)) != null;



            //           boolean murBas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - .5), false, false)) != null;
            boolean murBas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso + .5), false, false)) != null;
            boolean murBasLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso + 1.5), false, false)) != null;


            boolean murGauche = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), true, false)) != null;//true s'il y a un muret
            boolean murGaucheLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - 1.5), true, false)) != null;


            boolean murHaut = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), false, false)) != null;//true s'il y a un muret
            boolean murHautLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - 1.5), false, false)) != null;//true s'il y a un muret


            boolean aDroite = (posXperso == (labyrinthe.getL() - .5));//est sur le bord droit
            boolean enBas = (posYperso + .5) == (labyrinthe.getH());
            boolean aGauche = posXperso == 0.5;
            boolean enHaut = posYperso == 0.5;
            boolean enBasLoin = (posYperso + 1.5) == (labyrinthe.getH());

            boolean aDroiteLoin =(posXperso == (labyrinthe.getL() - 1.5));

            boolean haut = murHaut || enHaut;

            boolean bas = murBas || enBas;

            boolean gauche = murGauche || aGauche;

            boolean droit = murDroite || aDroite;

            boolean basLoin= murBasLoin || enBasLoin;


            //char preLastMove=' ';
            int delai= 3;

            //try
            //{

            if ((posXperso + .5 == labyrinthe.getSortieX()) && (posYperso == labyrinthe.getSortieY() + 0.5))
            {
                /*setTextVie(labyrinthe.deplace('D'));
                finDejeu(labyrinthe);
                lastMove = 'D';
                repaint();
                affichageLaby.repaint();
                TimeUnit.SECONDS.sleep(delai);*/
                this.lastMove = 'D';
                deplacePanel(delai,'D');

                lastMove = 'D';
                deplacePanel(delai,'D');
               /* setTextVie(labyrinthe.deplace('D'));
                finDejeu(labyrinthe);
                lastMove = 'D';
                repaint();
                affichageLaby.repaint();
                TimeUnit.SECONDS.sleep(delai);*/
            }


            if (gauche)
            {

                // System.out.println("gauche");
                if (droit && bas && haut)//bloque
                {
                    System.out.println("gauche bas droit haut");
                    //  System.out.println("bloque");
                    return false;
                }

                else if (bas && haut)
                {
                    System.out.println("gauche bas haut");
                    lastMove = 'D';
                    deplacePanel(delai,'D');
                    /*setTextVie(labyrinthe.deplace('D'));
                    finDejeu(labyrinthe);
                    lastMove = 'D';
                    repaint();
                    affichageLaby.repaint();
                    TimeUnit.SECONDS.sleep(delai);*/
                }
                else if (bas && droit)
                {
                    System.out.println("gauche bas droit");
                    lastMove = 'H';
                    deplacePanel(delai,'H');
                    /*setTextVie(labyrinthe.deplace('H'));
                    finDejeu(labyrinthe);
                    repaint();
                    affichageLaby.repaint();
                    lastMove = 'H';
                    TimeUnit.SECONDS.sleep(delai);*/

                }

                else if (bas)//peut pas aller a droite ni en bas
                {
                    System.out.println("gauche bas ");

                    if (lastMove != 'D') {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                        /*  setTextVie(labyrinthe.deplace('D'));
                        finDejeu(labyrinthe);
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/


                    } else if (lastMove != 'H') {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                        /* setTextVie(labyrinthe.deplace('H'));
                        finDejeu(labyrinthe);
                        lastMove = 'H';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/


                    }
                } else if (droit)//peut pas aller a droite ni a gauche
                {
                    System.out.println("gauche  droit");

                    if (lastMove == 'H') {
                        if (!murBasLoin && !murBas) {
                            lastMove = 'B';
                            deplacePanel(delai,'B');
                            /*setTextVie(labyrinthe.deplace('B'));
                            finDejeu(labyrinthe);
                            lastMove = 'B';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/


                        } else if (!haut) {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                            /*setTextVie(labyrinthe.deplace('H'));
                            finDejeu(labyrinthe);
                            lastMove = 'H';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/

                        }

                    }
                    else if (lastMove == 'B')
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                        /* setTextVie(labyrinthe.deplace('H'));
                        finDejeu(labyrinthe);
                        lastMove = 'H';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                    else if (!bas) {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                        /*setTextVie(labyrinthe.deplace('B'));
                        finDejeu(labyrinthe);
                        lastMove = 'B';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                    else if (!haut) {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                        /*setTextVie(labyrinthe.deplace('H'));
                        finDejeu(labyrinthe);
                        lastMove = 'H';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }


                } //fin si droit

                else if (haut)//peut pas aller a droite ni en haut
                {
                    System.out.println("gauche haut");
                    if (lastMove == 'G') {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                        /* setTextVie(labyrinthe.deplace('B'));
                        finDejeu(labyrinthe);
                        lastMove = 'B';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/


                    } else if (lastMove == 'H') {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                        /*setTextVie(labyrinthe.deplace('D'));
                        finDejeu(labyrinthe);
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                    else if ((lastMove == 'B') && (bas) && (!droit)) {
                        lastMove = 'G';
                        deplacePanel(delai,'G');
                        /*setTextVie(labyrinthe.deplace('G'));
                        finDejeu(labyrinthe);
                        lastMove = 'G';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/


                    } else if (!bas) {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                        /*setTextVie(labyrinthe.deplace('B'));
                        finDejeu(labyrinthe);
                        lastMove = 'B';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    } else if (!droit) {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                        /*setTextVie(labyrinthe.deplace('D'));
                        finDejeu(labyrinthe);
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                }

                else
                {
                    System.out.println("gauche");
                       /* if ((lastMove=='G')||(lastMove=='H'))
                        {
                            labyrinthe.deplace('B');
                            finDejeu(labyrinthe);
                            lastMove='B';
                            repaint();
                        }

                        if ((lastMove=='B')||(lastMove=='H'))
                        {
                            labyrinthe.deplace('G');
                            finDejeu(labyrinthe);
                            lastMove='G';
                            repaint();
                        }

                        if((lastMove=='B')|| (lastMove=='G'))
                        {
                            labyrinthe.deplace('H');
                            finDejeu(labyrinthe);
                            lastMove='H';
                            repaint();
                        }*/

                    //  System.out.println("else");

                    if (lastMove == 'H')
                    {
                        System.out.println("last move=='H'");
                       if (!bas&&basLoin&&murDroiteLoinEnbas&&!droit)
                       {
                           lastMove='D';
                           deplacePanel(delai,'D');
                       }

                       if (!bas&&basLoin&&!droit)
                       {
                           lastMove='D';
                           deplacePanel(delai,'D');

                       }
                        else if (!droit)
                        {
                            lastMove = 'D';
                            deplacePanel(delai,'D');
                            /*setTextVie(labyrinthe.deplace('D'));
                            finDejeu(labyrinthe);
                            lastMove = 'D';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/

                        }
                        else if (!haut)
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                            /* setTextVie(labyrinthe.deplace('H'));
                            finDejeu(labyrinthe);
                            lastMove = 'H';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/

                        }
                    }



                       /*if (murBasLoin)
                       {
                           setTextVie(labyrinthe.deplace('D'));
                           finDejeu(labyrinthe);
                           lastMove='D';
                           repaint();
                           affichageLaby.repaint();

                       }*/

                    else if (!bas)
                    {
                        if (lastMove != 'H' && (murBasLoin || (posYperso + 1.5) == (labyrinthe.getH())))
                        {
                            lastMove = 'B';
                            deplacePanel(delai,'B');
                            /*setTextVie(labyrinthe.deplace('B'));
                            finDejeu(labyrinthe);
                            lastMove = 'B';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/
                        }

                        else if (lastMove!='H')
                        {
                            lastMove='B';
                            deplacePanel(delai,'B');
                        }

                    }

                    else if (!droit)
                    {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                       /* setTextVie(labyrinthe.deplace('D'));
                        finDejeu(labyrinthe);
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                    else if (!haut)
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                        /*setTextVie(labyrinthe.deplace('H'));
                        finDejeu(labyrinthe);
                        lastMove = 'H';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }


                }

            }
            else//pas d'obtacle a gauche
            {
                // boolean gaucheSortie= (posXperso==(labyrinthe.getSortieX()-0.5)) && (posYperso==(labyrinthe.getSortieY()+0.5));//est a cote de la sortie

                //   if (gaucheSortie && pasADroite)
                if (bas)//obstacle ou limite du bas vers le bas
                {

                    if (droit)
                    {
                        System.out.println("bas droit");

                        if (!haut)
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                            /*setTextVie(labyrinthe.deplace('H'));
                            finDejeu(labyrinthe);
                            lastMove = 'H';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/


                        }

                        else if (!gauche)//murs a droite en bas et en haut
                        {
                            lastMove = 'G';
                            deplacePanel(delai,'G');
                            /*setTextVie(labyrinthe.deplace('G'));
                            finDejeu(labyrinthe);
                            lastMove = 'G';
                            repaint();
                            affichageLaby.repaint();
                            TimeUnit.SECONDS.sleep(delai);*/


                        }

                    }

                   else if((lastMove=='G')&&(aDroiteLoin||murDroiteLoin))
                    {
                        if (!haut)
                        {
                            lastMove='H';
                            deplacePanel(delai,'H');
                        }

                        else
                        {//gauche libre puisque arrive ici seulement si gauche est faux
                            lastMove='G';
                            deplacePanel(delai,'G');
                        }
                    }
                    else if (!droit)
                    {
                        System.out.println("bas");
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                        /*
                        setTextVie(labyrinthe.deplace('D'));
                        finDejeu(labyrinthe);
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/
                    }

                }

                else if (!droit)
                {

                    if ((!bas) &&(lastMove!='H'))
                    {
                        System.out.println("rien a droit ni a gauche et en bas last move!='H'");
                        System.out.println("lastmove= "+lastMove);
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                        /*setTextVie(labyrinthe.deplace('B'));
                        lastMove = 'B';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                    else
                    {
                        System.out.println("rien a droite ni a gauche");
                        lastMove = 'D';
                        deplacePanel(delai,'D');

                        /*setTextVie(labyrinthe.deplace('D'));
                        lastMove = 'D';
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/

                    }

                }
                else if (droit)
                {
                    if (!haut)
                    {
                        System.out.println(" droit bas");
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }

                    if (bas)
                    {
                        System.out.println(" droit bas");
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                        /*setTextVie(labyrinthe.deplace('H'));
                        finDejeu(labyrinthe);

                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/
                    }
                    else
                    {

                        System.out.println(" droit");
                        if (lastMove!='H')
                        {
                            lastMove = 'B';
                            deplacePanel(delai, 'B');
                        /*setTextVie(labyrinthe.deplace('B'));
                        finDejeu(labyrinthe);
                        repaint();
                        affichageLaby.repaint();
                        TimeUnit.SECONDS.sleep(delai);*/
                        }
                        else if(haut&&(lastMove=='H')&& basLoin)
                        {
                            lastMove='G';
                            deplacePanel(delai,'G');
                        }

                    }
                }
            }
        //}
             /*catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }*/

       // }
        return true;
    }


    public void deplacePanel(int delai, char deplacement)
    {
        try
        {
            setTextVie(labyrinthe.deplace(deplacement));
            finDejeu(labyrinthe);
            repaint();
            affichageLaby.repaint();
            TimeUnit.SECONDS.sleep(delai);
        }
        catch (InterruptedException ex)
        {

            Thread.currentThread().interrupt();
        }
    }
}
