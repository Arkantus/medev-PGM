package monopoly;

import java.util.LinkedList;

/**
 *
 * @author bchevill
 */
public class Joueur {
    private String nom;
    private int position;
    private int fortune;
    private LinkedList<CaseAchetable> propriete;
    private boolean enPrison;
    private int jourEnPrison;
    private Plateau plateauJeu;
    private int nbUtilitaires;
   
   Joueur(){
    nom = new String();
    position=0;
    fortune=100000;
    propriete=null;
    enPrison=false;
    jourEnPrison=0;
    plateauJeu=new Plateau();
    
   } 
   
   public String getNom(){
       return this.nom;
   }
   public int getPosition(){
       return this.position;
   }
   public int getFortune(){
       return this.fortune;
   }
   public LinkedList<CaseAchetable> getPropriete(){
        return this.propriete;
   }
   public boolean getEnPrison(){
       return this.enPrison;
   }
   public int getJourEnPrison(){
       return this.jourEnPrison;
   }
   public Plateau getPlateau(){
       return this.plateauJeu;
   }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setFortune(int fortune) {
        this.fortune = fortune;
    }

    public void setPropriete(LinkedList<CaseAchetable> propriete) {
        this.propriete = propriete;
    }

    public void setEnPrison(boolean enPrison) {
        this.enPrison = enPrison;
    }

    public void setJourEnPrison(int jourEnPrison) {
        this.jourEnPrison = jourEnPrison;
    }
    public void setPlateau(Plateau p){
        this.plateauJeu=p;
    }
    public void ajouterJourEnPrison(){
        this.jourEnPrison=jourEnPrison+1;
    }
    public void paiement(int somme){
        
    }
    public void paiementJoueur(Joueur j1,int somme){
        this.fortune-=somme;
        j1.fortune+=somme;
    }
    public void paiementBanque(int somme){
        
    }
    public void acheter(CaseAchetable case){
        this.propriete.add(case);
        paiementBanque(case.getPrix());
        case.setProprietaire(this);
        
    }
    public static int lanceLeDe(){
        return ((int)Math.floor(Math.random()*6))+1;
    }
}