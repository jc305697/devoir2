/**
 * Created by jeremycoulombe on 17-04-01.
 */
//Jérémy Coulombe et Stephanie Guay-Vachon
public class Labyrinthe
{

    private int  l;//largeur en termes de cases
    private int h; //hauteur en termes de cases
    private Personnage perso;
    private ListeMuret liste;
    private int sortieX;
    private int sortieY;

    public Labyrinthe(int l,int h,double densiteLaby, double delai, int nbVie)
    {
        this.l=l;
        this.h=h;
        this.liste=new ListeMuret();
        this.perso=new Personnage(0,0,nbVie);




         int nbMurs=0;
     for (int i=0;i<h;i++)//hauteur commence a 0 genre de y
        {
            for (int j=0;j<l;j++)//largeur commence a 0 genre de x
            {
                    if (Math.random()<densiteLaby)//nb aleatoire < densitelaby   et pas completement a droite
                    {
                        Muret nouveauMuret=new Muret(j,i,false,true);//cree muret horizontal en j,i

                        if (liste.chercheMuret(nouveauMuret)==null)//le muret n'est pas dans la liste
                        {
                            nbMurs++;
                            liste.ajoutMuret(nouveauMuret);//ajoute a la liste
                        }
                    }

                    if (Math.random()<densiteLaby)//nb aleatoire < densitelaby  et je ne suis pas sur la derniere ligne
                    {
                        Muret nouveauMuret1=new Muret(j,i,true,true);//cree mur vertical
                        if (liste.chercheMuret(nouveauMuret1)==null)//n'est pas dans la liste
                        {
                            nbMurs++;
                            liste.ajoutMuret(nouveauMuret1);//ajoute a la liste
                        }
                    }
            }
        }

        //perso doit etre colle sur mur le gauche
        double posAleatPersoX= 0.5;
        double posAleatPersoY= (double)(Math.round(Math.random()*(h-1)))+0.5;

        this.perso.setPositionXPersonnage(posAleatPersoX);
        this.perso.setPositionYPersonnage(posAleatPersoY);

        int posAleatSortieX;//position en x ou sera la sortie
        int posAleatSortieY;//position en y ou sera la sortie

        double nbAleat= Math.random();


       //sortie doit etre sur le mur droit
            posAleatSortieX=l-1;
            posAleatSortieY=(int)(Math.random()*(h-1));


        this.sortieX=posAleatSortieX;

        this.sortieY=posAleatSortieY;

    }

    public int getL()
    {
        return l;
    }

    public int getH()
    {
        return h;
    }

    public int getSortieX()
    {
        return sortieX;
    }

    public int getSortieY()
    {
        return sortieY;
    }

    public Personnage getPerso()
    {
        return perso;
    }

    public ListeMuret getListe()
    {
        return liste;
    }

    /**
     *
     * @return string representant le labyrithe
     */
    public String toString() {
        String bordureSup = "______"; //On va accumuler cette unité
        String bordureAcc = ""; //Accumulateur, la longueur dépend de la largeur du laby demandé
        for(int h=0; h<l; h++){ //Crée les lignes de bordure sup et inf. Besoin seulement que ce soit répété deux fois.
            bordureAcc += bordureSup;
        }
        String resultat=""; //Accumulateur pour tout la grille.
        resultat +=bordureAcc + "\n"; //Première ligne horizontale ajoutée, on va en ajouter une autre à la fin
        boolean flagCharTrouveEtMis = false;// Je n'ai pas trouvé mon personnage
        for(double j=0; j<this.h; j++)
        { //AXE Y, META-LIGNES

            String murLigne = "|"; //Mini-ligne, crée une ligne de murs verticaux uniquement, sans personnage
            String murPersLigne = "|"; //Ligne avec barres verticales AVEC personnage
            String murLigneHorizo = "|";//Ligne horizontale de murs horizontaux composés de tirets "-"

            for(double i=0; i<this.l; i++)
            { //AXE X, META-COLONNES
                Muret muretPosVert2 = new Muret((int) i, (int) j, true, true);
                Muret muretPosHorizo2 = new Muret((int) i, (int) j, false, true);


                if(i<this.h-1)
                {

                    if(this.liste.chercheMuret(muretPosVert2) !=null)//j'ai un muret vertical à cette position
                    {
                        murLigne+="     |";
                    }
                    else if(this.liste.chercheMuret(muretPosVert2) ==null)//il n'y pas de muret à cette position
                    {
                        murLigne+="      ";//espace + pas de colonne à l'emplacement de la colonne
                    }
                }
                else{
                    if((this.sortieX==i && this.sortieY==j)) // i et j sont à l'emplacement de la sortie
                    {
                        murLigne += "     out ";
                    }
                    else{
                        murLigne+="     |";//sinon ajoute bordure droite
                    }
                }


                if(j<this.l-1)
                {
                    if ((this.liste.chercheMuret(muretPosHorizo2) != null) && (this.liste.chercheMuret(muretPosVert2) != null)) {//j'ai un mur vertical et horizontal
                        murLigneHorizo += "_____|";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) != null && this.liste.chercheMuret(muretPosVert2) == null) {//j'ai un mur horizontal seulement
                        murLigneHorizo += "______";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) == null && this.liste.chercheMuret(muretPosVert2) == null) {//je n'ai aucun mur
                        murLigneHorizo += "      ";
                    } else if (this.liste.chercheMuret(muretPosHorizo2) == null && this.liste.chercheMuret(muretPosVert2) != null) {//j'ai un mur vertical seulement
                        murLigneHorizo += "     |";
                    }
                }
                else
                {
                    if((this.sortieX==i&& this.sortieY==j)) { //si sortie en bas a droite
                        murLigneHorizo += "______";
                    }
                    if(j>this.l-1 && (this.sortieY==j)){// si il n'y a pas de sortie en bas dans le coin
                        murLigneHorizo += "_____|";
                    }
                  /*  else
                    {
                            // si il n'y a pas de sortie en bas dans le coin
                                murLigneHorizo += "_____|";
                    }*/

                }

                if(this.perso.getPositionYPersonnage()==j+0.5)
                { //Si dans la ligne il y a un personnage, procédure spéciale
                    if(this.perso.getPositionXPersonnage()==i+0.5)
                    { //Si le perso est exactement à cette position, on dessine juste le perso avec espaces
                        flagCharTrouveEtMis=true;
                        if(this.liste.chercheMuret(muretPosVert2)!=null)
                        {//S'il y a un mur à cette position...
                            murPersLigne +="  c  |";//Avec mur
                        }

                        else
                        {
                            murPersLigne+= "   c  "; //Sans mur
                        }
                    }
                    else//pas de personnage à ce x
                    {
                        if(i<this.h-1){

                            if(this.liste.chercheMuret(muretPosVert2) !=null)
                            {
                                murPersLigne+="     |";// s'il y a un mur vertical
                            }
                            else if(this.liste.chercheMuret(muretPosVert2) ==null){
                                murPersLigne+="      "; // s'il n'y a pas de mur vertical
                            }
                        }
                        else{
                            if((this.sortieX==i&& this.sortieY==j)) {
                                murPersLigne += "     out ";//il y a une sortie à cette position
                            }
                            else{
                                murPersLigne+="     |";//sinon ajoute bordure droite
                            }
                        }
                    }

                }
            }

            if (flagCharTrouveEtMis==true)//si personnage trouvé et ajouté à la string
            { //À la méta-ligne donnée, si flagCharTrouvéEtMis n'est pas égal à 0, on va créer une méta-ligne
                resultat+=murLigne+ "\n"; //Ligne de murs simples, car le personnage est au centre
                resultat+=murPersLigne+ "\n"; //Ligne de murs CONTENANT le personnage
                resultat+=murLigneHorizo+ "\n"; //Ligne de murets horizontaux
                flagCharTrouveEtMis = false;
            }

            else if(flagCharTrouveEtMis==false)
            {                               //Aucun perso, alors on se contente de répéter trois fois la même ligne
                resultat+=murLigne+ " \n";       //Ligne de murs simple
                resultat+=murLigne+ " \n";       //Bis
                resultat+=murLigneHorizo+ " \n"; //Murets horizontaux
            }
            //Une fois la meta-ligne faite, on passe à la meta-ligne suivante, avec incrémentation de i, dans sa boucle for
        }

        resultat+= "\n"; //ajoute saut de ligne

        return resultat; //Et le résultat, la grille, est retournée, pour impression dans le jeu!
    }

    /**
     * @param direction caractère représentant la direction dans laquelle le personnage se déplace
     * @return boolean qui est = à true si le personnage n'entre pas en collision avec un mur ou une bordure et false s'il rentre en collision avec l'un ou l'autre
     */
    public boolean deplace(char direction)
    {
        double posXPerso= this.perso.getPositionXPersonnage();

        double posYPerso= this.perso.getPositionYPersonnage();

        switch (direction)
        {
            case 'D':

                Muret muretDVert1= new Muret((int)(posXPerso+0.5),(int)(posYPerso-0.5),true,true);

                if (this.liste.chercheMuret(muretDVert1)!=null)//il y a un mur
                {

                    this.liste.chercheMuret(muretDVert1).setVisible(true);//met le mur visible

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }




                if ((this.perso.getPositionXPersonnage()+.5==l) && (this.perso.getPositionYPersonnage()-.5!=sortieY))
                {//n'est pas la sortie, mais est sur le cote droit et va a droite donc fonce dans le mur
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }


                //pas a droite et pas de mur a droite
                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()+1);//met a jour la position du personnage

                return true;

            case 'G':

                Muret muretGVert1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),true,true);


                if (this.liste.chercheMuret(muretGVert1)!=null)//il y a un mur
                {
                    this.liste.chercheMuret(muretGVert1).setVisible(true);//rend mur visible

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }

                if (this.perso.getPositionXPersonnage()-.5==0)//si a extreme gauche
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }

                //sinon deplacement valide
                this.perso.setPositionXPersonnage(this.perso.getPositionXPersonnage()-1);//met a jour la position du personnage

                return true;


            case 'H':

                Muret muretHHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso-0.5),false,true);

                if (this.liste.chercheMuret(muretHHori1)!=null)//il y a un mur
               {
                   this.liste.chercheMuret(muretHHori1).setVisible(true);//rend mur visible

                   this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                   return false;
                }

                if (this.perso.getPositionYPersonnage()-.5==0)//sur le bord de la bordure en haut
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }

                this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()-1);//met a jour la position du personnage

                return true;

            case 'B':

                Muret muretBHori1= new Muret((int)(posXPerso-0.5),(int)(posYPerso+0.5),false,true);

                if (this.liste.chercheMuret(muretBHori1)!=null)//il y a un mur
                {
                    this.liste.chercheMuret(muretBHori1).setVisible(true);//rend mur visible

                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }

                if (this.perso.getPositionYPersonnage()+.5==h)//sur le bord de la bordure en bas
                {
                    this.perso.setViesRestantes(this.perso.getviesRestantes()-1);//enleve 1 vie
                    return false;
                }

                this.perso.setPositionYPersonnage(this.perso.getPositionYPersonnage()+1);//met a jour la position du personnage

                return true;
        }

        return false;//necessairement false
    }



}
