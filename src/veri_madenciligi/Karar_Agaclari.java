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
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class Karar_Agaclari {
    
    File file;
    File testfile;

    public Karar_Agaclari(File full, File test) {
        this.file = full;
        this.testfile = test;
    }
    
    public int satirSayisiniBul() throws FileNotFoundException, IOException{
        
        String line;
        int i=1;
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        while((line = br.readLine()) != null){
            i++;
        }
        
        return i;
    }
    
    public int sutunSayisiniBul() throws FileNotFoundException, IOException{
        
        String line;
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        line = br.readLine();
        
        String parts[] = line.split("\t");
        
        return parts.length;
    }
    
    public void textOku() throws FileNotFoundException, IOException{
        
        String line;
        int i = 0;

        int satir = satirSayisiniBul();
        int sutun = sutunSayisiniBul();
        
        int[][] Matris = new int[satir][sutun];
        
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        while((line = br.readLine()) != null){
            String parts[] = line.split("\t");
            for(int j = 0;j<parts.length;j++){
                int m = Integer.parseInt(parts[j]);
                Matris[i][j] = m;
            }
            i++;
        }
        
        br.close();
        fr.close();
        
        entropiHesapla(Matris,sutun,satir);
    }
    
    public int[] birinciIcin(int Matris[][],int sutun,int satir){
        
        int[] birinciSutunDegiskenSayisi = {0,0,0,0,0,0,0,0,0,0,0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][0]){
                    case 1: birinciSutunDegiskenSayisi[0]++;
                            break;
                    case 2: birinciSutunDegiskenSayisi[1]++;
                            break;
                    case 3: birinciSutunDegiskenSayisi[2]++;
                            break;
                    case 4: birinciSutunDegiskenSayisi[3]++;
                            break;
                    case 5: birinciSutunDegiskenSayisi[4]++;
                            break;
                    case 6: birinciSutunDegiskenSayisi[5]++;
                            break;
                    case 7: birinciSutunDegiskenSayisi[6]++;
                            break;
                    case 8: birinciSutunDegiskenSayisi[7]++;
                            break;
                    case 9: birinciSutunDegiskenSayisi[8]++;
                            break;
                    case 10: birinciSutunDegiskenSayisi[9]++;
                            break;
                    case 11: birinciSutunDegiskenSayisi[10]++;
                            break;
                    case 12: birinciSutunDegiskenSayisi[11]++;
                            break;
                }
            }
        
        return birinciSutunDegiskenSayisi;
    }
    
    public int[] ikinciIcin(int Matris[][],int sutun,int satir){
        
        int[] birinciSutunDegiskenSayisi = {0,0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][1]){
                    case 1: birinciSutunDegiskenSayisi[0]++;
                            break;
                    case 2: birinciSutunDegiskenSayisi[1]++;
                            break;
                    case 3: birinciSutunDegiskenSayisi[2]++;
                            break;
                }
            }
        
        return birinciSutunDegiskenSayisi;
    }
    
    public int[] ucuncuIcin(int[][] Matris, int sutun, int satir) {
        
        int[] birinciSutunDegiskenSayisi = {0,0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][2]){
                    case 1: birinciSutunDegiskenSayisi[0]++;
                            break;
                    case 2: birinciSutunDegiskenSayisi[1]++;
                            break;
                    case 3: birinciSutunDegiskenSayisi[2]++;
                            break;
                }
            }
        
        return birinciSutunDegiskenSayisi;
    }

    public int[] dorduncuIcin(int[][] Matris, int sutun, int satir) {
        int[] birinciSutunDegiskenSayisi = {0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][3]){
                    case 1: birinciSutunDegiskenSayisi[0]++;
                            break;
                    case 2: birinciSutunDegiskenSayisi[1]++;
                            break;
                }
            }
        
        return birinciSutunDegiskenSayisi;
    }

    public int[] besinciIcin(int[][] Matris, int sutun, int satir) {
        int[] birinciSutunDegiskenSayisi = {0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][4]){
                    case 1: birinciSutunDegiskenSayisi[0]++;
                            break;
                    case 2: birinciSutunDegiskenSayisi[1]++;
                            break;
                }
            }
        
        return birinciSutunDegiskenSayisi;
    }
    
    public int[] sonucunEntropisi(int[][] Matris, int sutun, int satir){
        
        int[] sonucDegiskenSayisi = {0,0};
        
            for(int j=0; j<satir; j++){
                switch(Matris[j][5]){
                    case 1: sonucDegiskenSayisi[0]++;
                            break;
                    case 2: sonucDegiskenSayisi[1]++;
                            break;
                }
            }
        
        return sonucDegiskenSayisi;
        
    }
    
    public void entropiHesapla(int Matris[][],int sutun,int satir) throws IOException{
        
        int[] sonuc = sonucunEntropisi(Matris, sutun, satir);
        int[] sutundakiDegiskenSayisi = sutundakiDegisken(Matris, sutun, satir);
        //int[] sutunDegiskenSayisi = sutundakiDegisken(Matris, sutun, satir);
        double[] entropi = {0,0,0,0,0,0};
        double denek=0;
        
        for(int i=0; i<2; i++){
            double bolum = (double)sonuc[i]/(double)satir;
            denek = denek + (bolum*(Math.log(bolum) / Math.log(2)));
        }
        
        denek = -denek;  
        System.out.println(denek);
        entropi[5] = denek;
        
        //-----------------------------------------------------------
        
        int[] birinciSutun = birinciIcin(Matris, sutun, satir);
        //int[] sutunDegiskenSayisi = sutundakiDegisken(Matris, sutun, satir);
        
        for(int i=0; i<12; i++){
            double bolum = (double)birinciSutun[i]/(double)satir;
            denek = denek + (bolum*(Math.log(bolum) / Math.log(2)));
        }
        
        denek = -denek;  
        System.out.println(denek);
        entropi[0] = denek;
        
        //-----------------------------------------------------------
        
        int[] ikinciSutun = ikinciIcin(Matris, sutun, satir);
        denek=0;
        for(int i=0; i<3; i++){
            double bolum = (double)ikinciSutun[i]/(double)satir;
            denek = denek + (bolum*(Math.log(bolum) / Math.log(2)));
        }
        
        denek = -denek;  
        System.out.println(denek);
        entropi[1] = denek;
        
        //-----------------------------------------------------------
        int[] ucuncuSutun = ucuncuIcin(Matris, sutun, satir);
        denek = 0;
        for (int i = 0; i < 3; i++) {
            double bolum = (double) ucuncuSutun[i] / (double) satir;
            denek = denek + (bolum * (Math.log(bolum) / Math.log(2)));
        }

        denek = -denek;
        System.out.println(denek);
        entropi[2] = denek;
        
        //-----------------------------------------------------------
        int[] dorduncuSutun = dorduncuIcin(Matris, sutun, satir);
        denek = 0;
        for (int i = 0; i < 2; i++) {
            double bolum = (double) dorduncuSutun[i] / (double) satir;
            denek = denek + (bolum * (Math.log(bolum) / Math.log(2)));
        }

        denek = -denek;
        System.out.println(denek);
        entropi[3] = denek;
        
        //-----------------------------------------------------------
        int[] besinciSutun = besinciIcin(Matris, sutun, satir);
        denek = 0;
        for (int i = 0; i < 2; i++) {
            double bolum = (double) besinciSutun[i] / (double) satir;
            denek = denek + (bolum * (Math.log(bolum) / Math.log(2)));
        }

        denek = -denek;
        System.out.println(denek);
        entropi[4] = denek;
        
        //-----------------------------------------------------------
        
        int yes=0;
        int no=0;
        
            for(int j = 0; j<satir; j++){
                if(Matris[j][0]==1){
                    if(Matris[j][5]==1){ //1 "no"
                        no++;
                        }
                    
                    else{
                        yes++;
                        }
                    
                    }
                }
            
            System.out.println(yes);
            System.out.println(no);
            
            }
        


   
    public int[] sutundakiDegisken(int Matris[][],int sutun,int satir) throws FileNotFoundException, IOException{
        
        int[] degisken = new int[sutun];
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int m=0;
        
        for(int i = 0; i<sutun; i++){
            while((line=br.readLine()) != null){
                
                String[] parts = line.split("\t");
                
                if(Integer.parseInt(parts[i])>m){
                    
                     m=Integer.parseInt(parts[i]);
                    
                } 
            }
            degisken[i]=m;
        }
            return degisken;
        
    }
    
}