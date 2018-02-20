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

/**
 *
 * @author Asus
 */
public class Naive_Bayes {
    
    File file;
    File testfile;
    
    public Naive_Bayes(File full, File test) {
        this.file = full;
        this.testfile = test;
    }
    
    public void textOku() throws FileNotFoundException, IOException {

        String line;
        int i = 1;
        int sutun=0,zero=0,one=0;

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            String parts[] = line.split(" ");
            sutun = parts.length;
            switch (parts[4]) {
                case "0":
                    zero++;
                    break;
                case "1":
                    one++;
                    break;
                default:
                    System.out.println("HATAAAAAAAAAAAAAAAAAAAAAAAA");
                    break;
            }
        }
        
        double[][] zeroMatris = new double[zero][sutun];
        double[][] oneMatris = new double[one][sutun];
        
        br.close();
        fr.close();
        
        FileReader fr2 = new FileReader(file);
        BufferedReader br2 = new BufferedReader(fr2);

        one = 0;
        zero = 0;
        
        while ((line = br2.readLine()) != null) {
            String parts[] = line.split(" ");
            
                if (parts[4].equals("0")) {
                    for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    zeroMatris[zero][j] = m;
                    
                    }
                    zero++;
                }
                else if(parts[4].equals("1")){
                    for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    oneMatris[one][j] = m;
                    
                    }
                    one++;
                }
                else{
                    System.out.println("HATAAAAAAAAAAAAAAAAAAAAA");
                }
            }
        

        br2.close();
        fr2.close();
        
        testAnaliz(zeroMatris, oneMatris);
        
        }
        
        private void testAnaliz(double[][] zero, double[][] one) throws IOException{
            double[][] zeroTablo = new double[2][4];
            double[][] oneTablo = new double[2][4];
            String line;
            int sutun=0,satir=0;
            double dogru =0, full =0;
            double sonuc1=1,sonuc0=1,gerceksonuc=1;
            
            FileReader fr = new FileReader(testfile);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                String parts[] = line.split(" ");
                sutun = parts.length;
                satir++;
            }
            
            int[][] testMatris = new int[satir][sutun];

            br.close();
            fr.close();
            
            FileReader fr2 = new FileReader(testfile);
            BufferedReader br2 = new BufferedReader(fr2);
            
            satir = 0;
            
            while ((line = br2.readLine()) != null) {
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[satir][j] = m;
                }
                satir++;
            }
            
            fr2.close();
            br2.close();
            
            for(int i = 0;i<satir;i++){
                int k = 0;
                double deger;
                    System.out.println((i+1)+". test kaydı sonucu :\t");
                    for (int j = 0; j < sutun-1; j++) {
                            deger = 0;
                            k=0;
                            while(k != zero.length){
                                if(zero[k][j] == testMatris[i][j]){
                                   deger++; 
                                }
                                k++;
                            }
                            if((deger/zero.length)==0){
                        
                                sonuc0 = sonuc0 * ((deger+1)/(zero.length+1));
                                zeroTablo[0][j] = ((deger+1)/(zero.length+1));
                                zeroTablo[1][j] = 1-((deger+1)/(zero.length+1));
                                System.out.println("Değer 0'dı. Laplace Correction Uygulandı.");
                                
                            }
                            else{
                                
                                sonuc0 = sonuc0 * (deger/zero.length);
                                zeroTablo[0][j] = (deger / zero.length);
                                zeroTablo[1][j] = 1 - (deger / zero.length);
                                
                            }
                            
                            
                            k=0;
                            deger=0;
                            
                            while (k != one.length) {
                            if (one[k][j] == testMatris[i][j]) {
                                deger++;
                                }
                            k++;
                            }
                            if((deger/one.length)==0){
                                
                                sonuc1 = sonuc1 * ((deger+1)/(one.length+1));
                                oneTablo[0][j] = ((deger + 1) / (one.length + 1));
                                oneTablo[1][j] = 1 - ((deger + 1) / (one.length + 1));
                                System.out.println("Değer 0'dı. Laplace Correction Uygulandı.");
                                
                            }
                            
                            else{
                                
                                sonuc1 = sonuc1 * (deger / one.length);
                                oneTablo[0][j] = (deger/one.length);
                                oneTablo[1][j] = 1-(deger/one.length);
                                
                            }
                            
                        
                    }
                    double test2 = zero.length+one.length;
                    double test1 = (zero.length / test2);
                    sonuc0 = sonuc0 * test1;
                    test2 = zero.length+one.length;
                    test1 = (one.length / test2);
                    sonuc1 = sonuc1 * test1;
                    
                    if(sonuc1>sonuc0){
                        gerceksonuc = 1;
                    }
                    else{
                        gerceksonuc = 0;
                    }
                    
                    System.out.println("Olasılık Tablosu\n");
                    
                    for(int g = 0;g<4;g++){
                        System.out.println(zeroTablo[0][g] +"\t"+zeroTablo[1][g]);
                        System.out.println(oneTablo[0][g] +"\t"+oneTablo[1][g]);
                    }
                    
                    System.out.println("Eğitim Verisine Göre \n 0'ın oranı"+sonuc0+"\t 1'in oranı"+sonuc1);
                    System.out.println("Testteki Sonuç:"+testMatris[i][4]);
                    if(gerceksonuc == testMatris[i][4]){
                        full++;
                        dogru++;
                        System.out.println("Sonuç Doğru!");
                    }
                    else{
                        full++;
                        System.out.println("Sonuç Yanlış!");
                    }
                    
            }
            dogru = dogru/full;
            System.out.println("Sınıflandırma Başarısı : "+dogru);
            
        }
        
        
}
