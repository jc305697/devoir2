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
//private boolean timerBoolean;
private Timer minuterie;
private JFrame fenetreJeu;
private String[] args;
private char lastMove= ' ';//represente dernier mouvement de l'intelligence artificielle
private Timer ai;//controle le temps en seconde entre les appels de l'intellignence artificielle
    public JPanelLaby(Labyrinthe labyrinthe,int visibilite,String[] args,JFrame fenetreJeu)
    {
        //je mets les  differents boutons et l'affichage du labyrinthe
        //this.timerBoolean=false;//je n'ai pas encore activé le timer

        AffichageLaby affichageLaby= new AffichageLaby(labyrinthe);//cree affichageLaby qui sera afficher au Centre

        this.labyrinthe=labyrinthe;//

        this.args=args;//tableau des arguments qui avait été passé à la ligne de commande
        this.fenetreJeu=fenetreJeu;//JFrame provenant de JeuLaby correspondant à l'affichage graphique du jeu

        this.affichageLaby=affichageLaby;//garde référence vers affichageLaby

        this.setLayout(new BorderLayout());//mets le Borderlayout comme layout

        this.add(affichageLaby,BorderLayout.CENTER);//mets affichageLaby au centre

        JPanel panneauDroit=new JPanel();//crée un JPanel pour contenir mes différents boutons et JLabel

        panneauDroit.setLayout(new BorderLayout());

       JLabel indicationsTouches = new JLabel("droite: d; gauche: g ou s; haut: h ou e; bas: b ou x");

       JLabel texteVie= new JLabel("Il vous reste "+labyrinthe.getPerso().getviesRestantes()+" vies");


       this.texteVie=texteVie;//garde références afin de remplacer le texte au fur et à mesure


      JPanel panneauDroitHaut= new JPanel();//crée un sous-panneau pour mettre les JLabels qui donnent des indications

      panneauDroitHaut.setLayout(new BorderLayout());

      panneauDroitHaut.add(indicationsTouches,BorderLayout.NORTH);//ajoute les éléments

      panneauDroitHaut.add(texteVie,BorderLayout.CENTER);


      panneauDroit.add(panneauDroitHaut,BorderLayout.NORTH);//ajoute sous-panneau au panneau

        ActionListener appelleAi = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if (!intelligenceArtificielle())//si intelligence artificielle retourne false donc intelligence artificielle peut pas trouver un chemin
                {
                    repaint();
                    ai.stop();//arrête l'ai

                    int reponse= JOptionPane.showConfirmDialog(fenetreJeu,"L'intelligence artificielle ne peut résoudre le problème. Voulez-vous rejouez?","message important",JOptionPane.YES_NO_OPTION);

                    if (reponse==0) //oui
                    {
                        reset(args,fenetreJeu);
                    }

                    if (reponse==1)//non
                    {
                        System.exit(0);//ferme la fenêtre et termine le processus
                    }
                }

                repaint();//affiche nouveau labyrinthe avec ancien paramètre
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

       JButton mursVisible = new JButton("rendre murs visibles");//bouton pour rendre les murs visibles

        mursVisible.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                labyrinthe.getListe().tousVisible();
                repaint();
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


        this.add(panneauDroit,BorderLayout.EAST);//ajoute panneau droit au JPanelLaby

        this.setFocusable(true);

        this.requestFocusInWindow();

        this.addKeyListener(this);//ajoute un keyListener pour les touches du clavier


        //rend invisible les murs après un certain temps
        ActionListener rendreMursInvisible = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    labyrinthe.getListe().tousInvisible();
                    repaint();

                    getMinuterie().stop();//arrete le timer
            }
        };

        int delai= visibilite*1000;//converti en millisecondes

        minuterie = new Timer(delai,rendreMursInvisible);//rend les murs invisibles apres un certain délai en millisecondes
        minuterie.start();
    }





    public void finDejeu(Labyrinthe labyrinthe)
    {

        if(labyrinthe.getPerso().getviesRestantes()!=0)//si nbs de vies restantes n'est pas égal à 0
        {
           double positionXPerso= labyrinthe.getPerso().getPositionXPersonnage();

           double positionYPerso= labyrinthe.getPerso().getPositionYPersonnage();

           if ((positionXPerso-1.5==labyrinthe.getSortieX())&&(positionYPerso==labyrinthe.getSortieY()+0.5))//sorti par la sortie du labyrinthe
            {
                ai.stop();
                int reponse= JOptionPane.showConfirmDialog(fenetreJeu,"vous avez gagné. Voulez-vous rejouer","message important",JOptionPane.YES_NO_OPTION);
                if (reponse==0) //oui
                {
                    reset(args,fenetreJeu);
                }

                if (reponse==1)//non
                {
                    System.exit(0);//arrête le processus et ferme la fenêtre
                }
            }
         }

        if (labyrinthe.getPerso().getviesRestantes()==0)
        {
            ai.stop();
            int reponse = JOptionPane.showConfirmDialog(fenetreJeu, "vous avez perdu. Voulez-vous rejouer", "message important", JOptionPane.YES_NO_OPTION);

            if (reponse == 0) //oui
            {
                reset(args, fenetreJeu);
            }

            if (reponse == 1)//non
            {
                System.exit(0);//arrête le processus et ferme la fenêtre

            }
        }
    }

    public  void keyPressed(KeyEvent e)//méthode pour keyListener
    {

    }

    public void keyReleased(KeyEvent e)//méthode pour keyListener
    {

    }

    public void keyTyped(KeyEvent e)//méthode pour keyListener
    {
        switch (e.getKeyChar()) {//touche tapé au clavier et appele fonction deplace avec direction correspondante puis affiche les changements et évalue si fin du jeu
            case ('d'):
                this.setTextVie(labyrinthe.deplace('D'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('g'):
                this.setTextVie(labyrinthe.deplace('G'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('s'):
                this.setTextVie(labyrinthe.deplace('G'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('h'):
                this.setTextVie(labyrinthe.deplace('H'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('e'):
                this.setTextVie( labyrinthe.deplace('H'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('b'):
                this.setTextVie(labyrinthe.deplace('B'));
                 this.repaint();
                this.finDejeu(labyrinthe);
                break;
            case ('x'):
                this.setTextVie( labyrinthe.deplace('x'));
                this.repaint();
                this.finDejeu(labyrinthe);
                break;
            default:
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


        posXperso = labyrinthe.getPerso().getPositionXPersonnage();
        posYperso = labyrinthe.getPerso().getPositionYPersonnage();

        boolean murDroite = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - .5), true, false)) != null;
        boolean murDroiteLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + 1.5), (int) (posYperso - .5), true, false)) != null;
        boolean murDroiteLoinEnbas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso + .5), (int) (posYperso - 1.5), true, false)) != null;
        //Pour chaque élément de ce paquet: on cherche s'il y a un mur à droite immédiatement, plus loin, et en bas aussi


        boolean murBas = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso + .5), false, false)) != null;
        boolean murBasLoin = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso + 1.5), false, false)) != null;

        boolean murGauche = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), true, false)) != null;//true s'il y a un muret

        boolean murHaut = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso - .5), false, false)) != null;//true s'il y a un muret
        ////Pour ces trois paquets, c'est le même principe

        boolean aDroite = (posXperso == (labyrinthe.getL() - .5));//Si sur le bord droit
        boolean enBas = (posYperso + .5) == (labyrinthe.getH());//Si sur le bord en bas
        boolean aGauche = posXperso == 0.5;     //Si au bord  en bas
        boolean enHaut = posYperso == 0.5;      //Si au bord en haut
        boolean enBasLoin = (posYperso + 1.5) == (labyrinthe.getH()); //Si une case du bord en bas

        boolean aDroiteLoin =(posXperso == (labyrinthe.getL() - 1.5));//bloque 1 case vers la droite par la bordure droite

        boolean haut = murHaut || enHaut;//bloqué vers le haut

        boolean bas = murBas || enBas;//bloqué vers le bas

        boolean gauche = murGauche || aGauche;//bloqué à gauche

        boolean droit = murDroite || aDroite;//pas pas aller à droite sans perdre une vie donc bloqué à droite

        boolean basLoin= murBasLoin || enBasLoin;//vrai si bloqué 1 case plus loin en direction du bas pourra pas aller vers le bas rendu à cette case


        int delai= 1; //Pour pouvoir visualiser le mouvement de l'AI

        if ((posXperso + .5 == labyrinthe.getL()) && (posYperso - 0.5 == labyrinthe.getSortieY() ))//je suis a côté de la sortie
        {
            this.lastMove = 'D';
            deplacePanel(delai,'D');
        }


        else if ((posXperso + 1.5 == labyrinthe.getL()) && (posYperso - 0.5 == labyrinthe.getSortieY() ))//je suis 1 case à côté de la sortie
        {
            this.lastMove = 'D';
            deplacePanel(delai,'D');
        }


            if (gauche)//mur a gauche
            {
                if (droit && bas && haut)// je suis bloque
                {
                    return false;
                }

                else if (bas && haut) // peut pas aller a gauche en bas et en haut
                {
                    lastMove = 'D';
                    deplacePanel(delai,'D');
                }
                else if (bas && droit)//peut pas aller à gauche en bas et à droite
                {
                    lastMove = 'H';
                    deplacePanel(delai,'H');
                }

                else if (bas)//peut pas aller à gauche ou en bas
                {
                    if (lastMove != 'D')
                    {
                        boolean murHautLoinDroit = labyrinthe.getListe().chercheMuret(new Muret((int) (posXperso - .5), (int) (posYperso + 1.5), false, false)) != null;//true s'il y a un muret

                        if ( aDroiteLoin || murDroiteLoin )
                        {
                            //sait que haut pas vrai donc
                            lastMove='H';
                            deplacePanel(delai,'H');
                        }
                        else if ((murDroiteLoin || aDroiteLoin ) && (!haut) && (murHautLoinDroit))//j'ai un mur a droite  ou la bourdure doite dans plus d'une case vers la droite
                        {
                            lastMove = 'H';
                            deplacePanel(delai, 'H');
                        }
                        else
                        {
                            lastMove = 'D';
                            deplacePanel(delai, 'D');
                        }
                    }//fin si dernier mouvemnt !=D

                    else if (lastMove != 'H')
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }
                }
                else if (droit)//peut pas aller a droite ni a gauche
                {
                    if (lastMove == 'H')
                    {
                        if (!haut)//S'il y a rien en haut, on continue en haut
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');

                        }
                        else if (!basLoin && !murBas)//pas de mur en bas 1 case plus loin, ou ne sera  pas a la bordure du bas 1 case plus loin
                        {
                            lastMove = 'B';
                            deplacePanel(delai,'B');

                        }
                        else //S'il y a un mur en haut et à droite/gauche, on va vers le bas
                        {
                            lastMove='B';
                            deplacePanel(delai,'B');
                        }
                    }

                    else if (lastMove == 'B')
                    {
                        if (!basLoin)//S'il y a rien au loin en bas, on continue en bas
                        {
                            lastMove='B';
                            deplacePanel(delai,'B');
                        }


                        else if(murDroiteLoinEnbas && !aDroite)//descend seulement si il n'y a pas de mur en bas à droite  et n'est pas sur la bordure droite
                        {
                            lastMove='B';
                            deplacePanel(delai,'B');
                        }
                        else if (!droit)//S'il y a pas de mur à droite, alors on va vers la droite
                        {
                            lastMove='D';
                            deplacePanel(delai,'D');
                        }


                        else //S'il y a un mur en bas, gauche, droite, on va vers le haut
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                        }
                    }

                    else if (!bas) //rien vers le bas
                    {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                    }

                    else if (!haut) //rien vers le haut
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }


                } ////FIN SI IL Y A MUR À DROITE

                else if (haut)//peut pas aller a droite ni en haut
                {
                    if ((lastMove == 'G') && (!bas)) //rien vers le bas
                    {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                    }

                    else if ((lastMove == 'H') && !droit)//rien a droite
                    {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                    }

                    else if ((lastMove == 'B') && (bas) && (!droit))// bloque vers le bas et rien a droite
                    {
                        lastMove = 'G';
                        deplacePanel(delai,'G');
                    }

                    else if (!bas)//rien vers le bas
                    {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                    }
                    else if (!droit)//rien a droite
                    {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                    }
                }

                else//Ni à droite ni en haut
                {
                    if (lastMove == 'H')//si dernier mouvement == H
                    {
                       if (!bas && basLoin && murDroiteLoinEnbas && !droit)//rien vers le bas mais  bloqué par quelque chose une case vers le bas et par mur droit
                       {                                                    //de la prochaine case vers le bas et pas bloqué à droite
                           lastMove='D';
                           deplacePanel(delai,'D');
                       }
                       if (!bas && basLoin && !droit)//rien vers le bas et bloqué par quelque chose une case vers le bas et rien vers la droite
                       {
                           lastMove='D';
                           deplacePanel(delai,'D');

                       }
                        else if (!droit)//rien vers la droite
                        {
                            lastMove = 'D';
                            deplacePanel(delai,'D');

                        }
                        else if (!haut)//rien vers le haut
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                        }
                    }
                    else if ((aDroiteLoin || murDroiteLoin || droit || murDroite) && (murBasLoin || basLoin || bas || murBas))// pris dans une boite
                    {
                        if (!haut)//rien ver le haut
                        {
                            lastMove='H';
                            deplacePanel(delai,'H');
                        }
                    }
                    else if (!bas)//rien en bas
                    {
                        if (lastMove != 'H' && (murBasLoin || (posYperso + 1.5) == (labyrinthe.getH())))// bloqué par mur 1 case plus loin vers le bas ou par bordure du bas
                        {
                            lastMove = 'B';
                            deplacePanel(delai,'B');
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
                    }
                    else if (!haut)
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }
                }
            }

            else//pas d'obtacle a gauche
            {
                if (bas)//obstacle ou limite du bas vers le bas
                {
                    if (droit)//bloqué à droite
                    {
                        if (!haut)//pas bloqué en haut
                        {
                            lastMove = 'H';
                            deplacePanel(delai,'H');
                        }
                        else if (!gauche)//murs a droite en bas et en haut
                        {
                            lastMove = 'G';
                            deplacePanel(delai,'G');
                        }
                    }
                   else if((lastMove=='G') && (aDroiteLoin||murDroiteLoin))//dernier mouvement vers la gauche et bloqué 1 case plus loin vers la droite
                    {
                        if (!haut)//rien vers le haut
                        {
                            lastMove='H';
                            deplacePanel(delai,'H');
                        }

                        else
                        {//gauche libre puisque arrive ici si et seulement si gauche est faux
                            lastMove='G';
                            deplacePanel(delai,'G');
                        }
                    }

                    else if (!droit)
                    {
                        lastMove = 'D';
                        deplacePanel(delai,'D');
                    }

                }
                else if (!droit)
                {
                    if ((aDroiteLoin || murDroiteLoin || droit || murDroite) && ((murBasLoin || basLoin || bas || murBas)||(murHaut || enHaut)))// pris dans une boite
                    {
                        if (!haut)
                        {
                        lastMove='H';
                        deplacePanel(delai,'H');
                        }

                     else  if (!gauche)
                        {
                            lastMove='G';
                            deplacePanel(delai,'G');
                        }

                    }

                    else if ((!bas) &&(lastMove!='H'))
                    {
                        lastMove = 'B';
                        deplacePanel(delai,'B');
                    }

                    else //S'il y a un mur en bas, et que le dernier mouvement n'était pas vers le haut
                    {
                        if ((lastMove=='H')&&(basLoin || murBasLoin) && (murDroiteLoin|| aDroiteLoin) && (!haut)) //S'il y a un mur en bas loin ou près, et à droite loin ou près et y a rien en haut
                        {
                            lastMove='H';
                            deplacePanel(delai,'H');
                        }
                        else
                        {
                            lastMove = 'D';
                            deplacePanel(delai, 'D');
                        }
                    }
                }

                else if (droit)//bloqué à droite
                {
                    if (bas) //Droite et en bas, donc on va en haut
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }

                    else if (!basLoin && (lastMove!='H'))// afin d'éviter de monter et descendre
                    {
                        lastMove='B';
                        deplacePanel(delai,'B');
                    }
                    else if (!haut)//Droite et en bas, va vers le haut puisque rien vers le haut
                    {
                        lastMove = 'H';
                        deplacePanel(delai,'H');
                    }

                    else//si les autres sont faux
                    {
                        if (lastMove!='H')
                        {
                            lastMove = 'B';
                            deplacePanel(delai, 'B');
                        }
                        else if(haut && (lastMove=='H') && basLoin)//bloqué en haut et en bas loin il ya un mur
                        {
                            lastMove='G';
                            deplacePanel(delai,'G');
                        }
                        else
                        {
                           lastMove='G';
                           deplacePanel(delai,'G');
                        }
                    }
                }
            }
        return true;
    }


    public void deplacePanel(int delai, char deplacement)
    {
        try
        {
            setTextVie(labyrinthe.deplace(deplacement));//utilise boolean donné par deplacement comme paramètre à setTexteVie
            repaint();//pour afficher les modifications
            finDejeu(labyrinthe);//pour déterminer si c'est la fin du jeu
           TimeUnit.SECONDS.sleep(delai);//stop le processus pour un certain delai en secondes
       }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
