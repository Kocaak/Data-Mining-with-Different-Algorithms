/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veri_madenciligi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Apriori {
    
    File file;
    int destek;
    HashMap<Integer,String[]> Dataset = new HashMap<>();
    List<String[]> Tarama = new ArrayList<String[]>();
    double[] Frekans = {0,0,0,0,0,0};
    Iterator<Integer> keySetIterator;
    int satirSayisi = 1;

    public Apriori(int k, File full) {
        destek = k;
        file = full;
    }

     public void textOku() {
        
         String line = null;
         
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line=br.readLine()) != null){
                String[] parts = line.split(" ");
                Dataset.put(satirSayisi,parts);
                satirSayisi++;
            }
            
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        keySetIterator = Dataset.keySet().iterator();
        
        DestekBul();
    }
     
     private void DestekBul(){
         
         for(int i = 1; i<=Dataset.size();i++){
             if(Arrays.toString(Dataset.get(i)).contains("1")){
                 
                 Frekans[0]++;
                 
             }
             if (Arrays.toString(Dataset.get(i)).contains("2")) {

                 Frekans[1]++;

             }
             if (Arrays.toString(Dataset.get(i)).contains("3")) {

                 Frekans[2]++;

             }
             if (Arrays.toString(Dataset.get(i)).contains("4")) {

                 Frekans[3]++;

             }
             if (Arrays.toString(Dataset.get(i)).contains("5")) {

                 Frekans[4]++;

             }
             if (Arrays.toString(Dataset.get(i)).contains("6")) {

                 Frekans[5]++;

             }
         }
         
         //System.out.println(veriSayisi[0] +""+ veriSayisi[1] +""+ veriSayisi[2] +""+ veriSayisi[3]);
         BirinciTarama();
         
     }
     
     private void BirinciTarama(){
         int m = 1;
         for(int i = 0; i<Frekans.length;i++){
             double ilkyuzde = 100*(Frekans[i] /(double) Frekans.length);
             if(ilkyuzde >= destek){
                 for(int j = i+1;j<Frekans.length;j++){
                     double ikinciyuzde = 100*(Frekans[j] / (double) Frekans.length);
                     if (ikinciyuzde >= destek) {
                         while(!(Dataset.get(m) == null)){ //hata verirse for size yapacağım
                             String[] karsilastirma = {String.valueOf(i+1),String.valueOf(j+1)};
                             if ((Arrays.toString(Dataset.get(m)).contains(String.valueOf(i+1))) && (Arrays.toString(Dataset.get(m)).contains(String.valueOf(j)))) {
                                    Tarama.add(karsilastirma);
                                    break;
                             }
                             
                             m++;
                             
                         }
                         m=1;
                         
                     }
                 }
             }
         }
         
         System.out.println("GİRİLEN DESTEĞİN ÜZERİNDEKİ KÜMELENMİŞ GRUPLAR");
         int x = 0;
         for(int i = 0;i<Tarama.size();i++){
             for(int j = 1;j<=Dataset.size();j++){
                 String[] parts1 = Arrays.toString(Tarama.get(i)).split(",");
                 parts1[0]=parts1[0].replace("[", "");
                 parts1[1]=parts1[1].replace("]", "");
                     if (Arrays.toString(Dataset.get(j)).contains(parts1[0]) &&  Arrays.toString(Dataset.get(j)).contains(parts1[1])){
                         x++;
                        }

             }
             double yuzde = 100*((double) x / (double) Frekans.length);
             if(yuzde >= destek){
                 System.out.println(Arrays.toString(Tarama.get(i)) +"----------"+ yuzde);
             }
             x=0;
         }
         
         
     }
    
}
