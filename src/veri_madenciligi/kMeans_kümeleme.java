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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class kMeans_kümeleme {
    
    File file;
    int kparametresi;
    
    public kMeans_kümeleme(File full,int kparametresi) {
        this.file = full;
        this.kparametresi = kparametresi;
    }
    
    public int satirSayisiniBul() throws FileNotFoundException, IOException{

        String line;
        int i = 0;

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            i++;
        }

        br.close();
        fr.close();
        
        return i;
    }
    
    public int sutunSayisiniBul() throws FileNotFoundException, IOException {

        String line;

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        line = br.readLine();

        String parts[] = line.split(" ");

        br.close();
        fr.close();
        
        return parts.length;
    }
    
    public void textOku(){
        
        int satir=0,sutun=0,i=0;
        String line;
        List<Integer> list = new ArrayList<>();
        List<Integer> asillist = new ArrayList<>();
        double oklid = 0;
        
        try {
            satir = satirSayisiniBul();
            sutun = sutunSayisiniBul();
        } catch (IOException ex) {
            System.out.println("Textler açılmıyor");
        }
        
        int[][] Matris = new int[satir][sutun];
        double[] enYakinUc = new double[kparametresi-1];
        
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Textler açılmıyor");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        for (int j = 0; j < kparametresi-1; j++) {
            enYakinUc[j] = 999;
        }
        
        int j = 0;
        
        
        for(i=0;i<satir;i++){
                if(!asillist.contains(Matris[i][j])){
                    asillist.add(Matris[i][j]);
                    for(int m = i+1;m<satir;m++){
                        if(!asillist.contains(Matris[m][j])){
                          for(int v=1;v<=sutun-1;v++){
                              
                              oklid = oklid + (Math.pow(Math.abs(Matris[i][v] - Matris[m][v]), 2));
                              oklid = (double) Math.sqrt(oklid);
                              
                          }  

                          
                          for(int l = 0;l<enYakinUc.length;l++){
                              if(oklid < enYakinUc[l]){
                                  enYakinUc[l] = oklid;
                                  list.add(Matris[m][j]);
                                  break;
                              }
                          }
                          
                       }
  
                    }
                    
                    asillist.add(list.get(list.size() - 1));
                    list.remove(list.size() - 1);
                    asillist.add(list.get(list.size() - 1));
                    list.clear();

                    for (int y = 0; y < kparametresi - 1; y++) {
                        enYakinUc[y] = 999;
                    }
                    
                }
                
        }
        
        ekranaYazdir(asillist,satir);
        
        
    }

    private void ekranaYazdir(List<Integer> asillist,int satir) {
        
        int tab = 0;
        
        for(int i = 0; i<asillist.size(); i++){
                
                System.out.print(asillist.get(i)+"-");
                tab++;
                
                if(tab==kparametresi){
                    System.out.print("\n");
                    tab=0;
                }
                
        }
        
    }

}
