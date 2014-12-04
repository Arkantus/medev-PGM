/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.util.ArrayList;

/**
 *
 * @author rlebail
 */
public class PGMImage {
    
    public final static int greyScale = 255;
    public final static int lineLength = 70;
    
    private int l;
    private int h;
    private ArrayList<Integer> pixelArray;

    public PGMImage(int largeur, int hauteur){
        l=largeur;
        h=hauteur;
        pixelArray = new ArrayList<>();
        for(int i=0;i<l*h;i++) {pixelArray.add(200);}
        
    }
    
   public PGMImage(PGMImage I){
        l = I.getL();
        h = I.getH();
        pixelArray = I.getPixelArray();
    } 
       
    
    /**
     * @return the l
     */
    public int getL() {
        return l;
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    /**
     * @return the pixelArray
     */
    public ArrayList<Integer> getPixelArray() {
        return pixelArray;
    }
    
    public void setPixelArray(ArrayList a) {
        pixelArray=(ArrayList<Integer>)a.clone();
    }
    
    public void fillImage() {
     
        for(int i=0; i<pixelArray.size(); i++){
            
            pixelArray.set(i, Math.round( (float) i*greyScale/pixelArray.size() ));
        }
        
    }
    
    public PGMImage diff(PGMImage I){
    
    int diffVal = 0;
        
    if(!(l==I.getL() && h==I.getH())) {
        System.out.println("Les images n'ont pas la même taille");
        return I;
    }
        
    ArrayList<Integer> dataArray = I.getPixelArray();
    ArrayList<Integer> resArray = new ArrayList<>();
    
    for(int i=0; i<pixelArray.size(); i++){
       
       diffVal = pixelArray.get(i)-dataArray.get(i)>0?(pixelArray.get(i)-dataArray.get(i)):0;
       resArray.set(i, diffVal);
    }
    
    PGMImage delta = new PGMImage(l,h);
    delta.setPixelArray(resArray);
    
    return delta;           
    }
    
    public PGMImage seuil(int s){
       ArrayList<Integer> resArray = new ArrayList<>();
       int diffVal = 0;
       
       for(int i=0; i<pixelArray.size(); i++){
           diffVal =  pixelArray.get(i)>s?255:0;
           resArray.set(i,diffVal);
       } 
       
    PGMImage seuild = new PGMImage(l,h);
    seuild.setPixelArray(resArray);
    
    return seuild;   
    }
    
    public PGMImage seuil(){
       return this.seuil(127);
    }
    
    public PGMImage generateHistogram() {
        
       ArrayList<Integer> freqArrays = new ArrayList<>();
       int maxFreq = 0;
       
       for(int i=0; i<l*h; i++){
           freqArrays.add(0);
       } 
       
       for(int i=0; i<l*h; i++){           
           freqArrays.set(pixelArray.get(i),1+freqArrays.get(pixelArray.get(i)));
           
           if(freqArrays.get(pixelArray.get(i))>maxFreq){
               maxFreq=freqArrays.get(pixelArray.get(i));
           }
       } 
       
        //System.out.println(maxFreq);
        
       ArrayList<Integer> histPixelArray = new ArrayList<>();
       
       for(int i=0;i<256*maxFreq;i++){
           histPixelArray.add(0);
       }
       
       
       for(int i=0; i<=256; i++){
           for(int j=0; j<maxFreq; j++){
             
               
            if(j<freqArrays.get(i))
            {histPixelArray.set(maxFreq*i+j,0);}
            else
            {histPixelArray.set(maxFreq*i+j,255);}
            
           }
       }
        
       PGMImage histImage = new PGMImage(maxFreq,256); 
       histImage.setPixelArray(histPixelArray);
       
       return histImage;        
    }
    
    public void resizeH(int hauteur)
    { 
        
        int oldHauteur = h;
        int oldLargeur = l;
        ArrayList<Integer> oldArray = (ArrayList<Integer>)pixelArray.clone();
        
        ArrayList<Integer> newArray = new ArrayList<>();
        
        int deltaH = oldHauteur-hauteur;

        if(deltaH==0){return;}
        if(deltaH>0){ //réduction
        ArrayList<Integer> delIndicesArray = new ArrayList<>();    
        
        for(int i=0;i<deltaH;i++){
            delIndicesArray.add(Math.round((float) i*oldHauteur/deltaH));
        }    
        System.out.println(deltaH);
        
        for(int i=0;i<oldHauteur*oldLargeur;i++){
           if(!delIndicesArray.contains(i%oldLargeur)){
               newArray.add(oldArray.get(i));
           } 
        setH(hauteur);
            
        }
            
            
        }
        else{
            
            
            
            
        }
        System.out.println(newArray.size());
        setPixelArray(newArray);
    }
    
    public void resizeL(int largeur)
    {
       int oldHauteur = h;
        int oldLargeur = l;
        ArrayList<Integer> oldArray = (ArrayList<Integer>)pixelArray.clone();
        
        ArrayList<Integer> newArray = new ArrayList<>();
        
        int deltaL = oldLargeur-largeur;

        if(deltaL==0){return;}
        if(deltaL>0){ //réduction
        ArrayList<Integer> delIndicesArray = new ArrayList<>();    
            
        for(int i=0;i<deltaL;i++){
            delIndicesArray.add(Math.round((float) i*oldLargeur/deltaL));
        }    
        
        for(int i=0;i<oldHauteur*oldLargeur;i++){
           if(!delIndicesArray.contains((int)i/oldLargeur)){
               newArray.add(oldArray.get(i));
           } 
        setL(largeur);
            
        }
            
            
        }
        else{
            
            
            
            
        }
        System.out.println(newArray.size());
        setPixelArray(newArray);

    }       
            
    public void resize(int largeur, int hauteur){
    
        resizeH(hauteur);
        resizeL(largeur);
                
    }

    /**
     * @param l the l to set
     */
    public void setL(int l) {
        this.l = l;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }
}
