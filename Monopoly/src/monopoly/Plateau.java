package monopoly;

import java.util.*;


public class Plateau {
    
    private ArrayList<Case> listeCases;
    private LinkedList<Joueur> listeJoueurs;
    private Boolean partieTerminee;
  
    // CONSTRUCTEURS
    public Plateau () {
        this.listeCases=initPlateau();
        this.listeJoueurs= new LinkedList<Joueur>();
        partieTerminee=false;
        
    }
    public Plateau (ArrayList<Case> l1, LinkedList<Joueur> l2) {
        this.listeCases=l1;
        this.listeJoueurs=l2;
        partieTerminee=false;
    }
    
    public void ajoutCase(Case c) {
        listeCases.add(c.getPosition(),c);
    }

    
    public ArrayList<Case> initPlateau(){
        ArrayList<Case> l=new ArrayList<Case>(40);
        l.add(1, new Rue("rue de la distilerie",1,8000, 800));
        l.add(3, new Rue("rue du pilori", 3, 10000, 800));
        l.add(6, new Rue("rue Jeanne D'Arc",6,11000, 800));
        l.add(8, new Rue("rue Duguay Trouin", 8, 11000, 800));
        l.add(9, new Rue("route de la Joneliere", 9, 12000, 800));
        l.add(11, new Rue("rue de l'église", 11, 13000, 900));
        l.add(13,  new Rue("Cours des 50 otages", 13, 13000, 900));
        l.add(14, new Rue("rue Paul Bellamy", 14, 14000, 900));
        l.add(16,  new Rue("rue du crébillon", 16, 15000, 900));
        l.add(18, new Rue("rue du Général De Gaulle", 18, 15000, 900));
        l.add(19, new Rue("rue de Budapest", 19, 16000, 900));
        l.add(21, new Rue("rue de Feltre", 21, 17000, 1000));
        l.add(23, new Rue("Avenue Matignon", 23, 17000, 1000));
        l.add(24, new Rue("rue des tilleuls", 24, 18000, 1000));
        l.add(26, new Rue("route du rhum", 26, 19000, 1000));
        l.add(27, new Rue("rue des 3 rois mages", 27, 19000, 1000));
        l.add(29, new Rue("rue Charlemagne", 29, 20000, 1000));
        l.add(31, new Rue("rue du calvaire", 31, 21000, 1500));
        l.add(32, new Rue("rue Jean De La Fontaine", 32, 21000, 1500));
        l.add(34, new Rue("rue Dominique Strauss-Kahn", 34, 22000, 1500));
        l.add(37,  new Rue("rue du lapin nain", 37, 25000, 1500));
        l.add(39, new Rue("rue de la chenille carnivore", 39, 27000, 1500));
        l.add(12, new Utilitaire("Electric Company",12,15000));
        l.add(26, new Utilitaire("Water Works",26,15000));
        l.add(5,new Gare("Gare Montparnasse",5,20000));
        l.add(15,new Gare("Gare de Lyon",15,20000));
        l.add(25,new Gare("Gare du Nord",25,20000));
        l.add(35,new Gare("Gare Saint Lazare",35,20000));
        l.add(2,new CaisseCommunautaire("Caisse de communaute",2));
        l.add(17,new CaisseCommunautaire("Caisse de communaute",17));
        l.add(33,new CaisseCommunautaire("Caisse de communaute",33));
        l.add(7,new Chance("Chance",7));
        l.add(22,new Chance("Chance",22));
        l.add(36,new Chance("Chance",36));
        l.add(4, new IncomeTax());
        l.add(38, new LuxuryTax());
        
        return l;
    }

    //GETTERS & SETTERS
    public ArrayList<Case> getListeCases() {
        return listeCases;
    }
    public void setListeCases(ArrayList<Case> listeCases) {
        this.listeCases = listeCases;
    }
    public LinkedList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }
    public void setListeJoueurs(LinkedList<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }  
    
    //
    public int nbGares(Joueur j) {
        int nbGares=0;
        for (Case c : listeCases) {
            if ((c.getClass().getSimpleName().equals("Gare")) && (((CaseAchetable)c).getProprietaire()==j)) {
                nbGares++;
            }
        }
        return nbGares;
    }
    
    public void affiche() {
        for (Case c : listeCases) {
            System.out.println(c.toString()+"\n");
        }
        
        //TODO afficher les joueurs
        for (Joueur j : listeJoueurs) {
            System.out.println(j.toString()+"\n");
        }
    }
    
    public Case avance(Case c, int d) {
        if (c.getPosition()+d<40){
        return listeCases.get(c.getPosition()+d);
        }
        else { 
        return listeCases.get(c.getPosition()+d-40);
        }
        
    }
    
    public Boolean finDePartie(){
        // la partie est terminée lorqu'il n'y a plus qu'un joueur
        if (listeJoueurs.size()==1){
            partieTerminee=true;
        }
        return (partieTerminee);
    }
    
    public void partie(){
        while(!finDePartie()){
            for(Joueur j : listeJoueurs){
                try{j.tourDeJeu();}
                
                //si le joueur est en défaut de paiement, on le supprime de la liste de joueur
                catch (NoMoreMoneyException n){
                    listeJoueurs.remove(j);
                    System.out.println( j.getNom() +" est un loooooooooser(une chiasse)");
                }
            }
            };
            }
            //si le joueur est en défaut de paiement, on le supprime de la liste de joueur
            
        }
    }

}
