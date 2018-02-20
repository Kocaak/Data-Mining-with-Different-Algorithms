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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class kNN {
    
    File file;
    File testfile;
    int kparametresi;
    
    public kNN(String k,File full,File test){
        kparametresi = Integer.parseInt(k);
        file = full;
        testfile = test;
    }
    
    
    public int satirSayisiniBul(File aranacak) throws FileNotFoundException, IOException{

        String line;
        int i = 0;

        FileReader fr = new FileReader(aranacak);
        BufferedReader br = new BufferedReader(fr);

        while ((line = br.readLine()) != null) {
            i++;
        }

        return i;
    }
    
    public void ManhattanveTekrar(){
        try {

            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);

            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0, testi = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();

            System.out.println("\nEğitim Dosyasında " + i + " Kadar Veri Vardı ve İşlendi.");

            double[] enYakinUc = new double[kparametresi];
            int[] enYakinUcunSonucu = new int[kparametresi];

            for (int j = 0; j < kparametresi; j++) {
                enYakinUc[j] = 999;
            }

            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0, yanlis = 0;

            BufferedReader tbr = new BufferedReader(new FileReader(testfile));

            while ((line = tbr.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();

            System.out.println("\nTest Dosyasında " + testsatir + " Kadar Veri Var.");

            for (int j = 0; j < testsatir; j++) {
                for (int k = 0; k < fullsatir; k++) {

                    double manhattan = (double) (Math.abs(testMatris[j][0] - Matris[k][0])) + (double) Math.abs(testMatris[j][1] - Matris[k][1]);

                    for (int m = 0; m < kparametresi; m++) {
                        if (manhattan < enYakinUc[m]) {
                            enYakinUc[m] = manhattan;
                            enYakinUcunSonucu[m] = Matris[k][2];
                            break;
                        }
                        
                    }

                }
                int ucunsonucu = Mod(enYakinUcunSonucu);
                System.out.println("\n" + (j + 1) + ".eleman için Eğitimdeki Sonuç: " + ucunsonucu);
                if (ucunsonucu == testMatris[j][2]) {

                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;

                } else {
                    System.out.println("\n Olması gereken sonuc : " + ucunsonucu + " \t Olan sonuc: " + testMatris[j][2]);
                    yanlis++;
                }

            }

            System.out.println("\n\n\n" + (testsatir - 1) + "adetlik eğitim verisinde " + dogru + " adet doğru, " + yanlis + " adet yanlis cikmistir.");
            double yuzde = (double) ((dogru) * 100) / (dogru + yanlis);
            System.out.println("\n Başarı Oranı %" + yuzde);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ManhattanveAgirlik(){
        try {

            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);

            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0, testi = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();

            System.out.println("\nEğitim Dosyasında " + i + " Kadar Veri Vardı ve İşlendi.");

            double enyakin = 999;
            int enYakinSonucu = -1;

            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0, yanlis = 0;

            BufferedReader tbr = new BufferedReader(new FileReader(testfile));

            while ((line = tbr.readLine()) != null) {
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();

            System.out.println("\nTest Dosyasında " + testsatir + " Kadar Veri Var.");

            for (int j = 0; j < testsatir; j++) {
                for (int k = 0; k < fullsatir; k++) {

                    double manhattan = (double) (Math.abs(testMatris[j][0] - Matris[k][0])) + (double) Math.abs(testMatris[j][1] - Matris[k][1]);

                        if (manhattan < enyakin) {
                            enyakin = manhattan;
                            enYakinSonucu = Matris[k][2];
                        }
                        

                }
                System.out.println("\n" + (j + 1) + ".eleman için Eğitimdeki Sonuç: " + enYakinSonucu);
                if (enYakinSonucu == testMatris[j][2]) {

                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;

                } else {
                    System.out.println("\n Olması gereken sonuc : " + enYakinSonucu + " \t Olan sonuc: " + testMatris[j][2]);
                    yanlis++;
                }

            }

            System.out.println("\n\n\n" + (testsatir - 1) + "adetlik eğitim verisinde " + dogru + " adet doğru, " + yanlis + " adet yanlis cikmistir.");
            double yuzde = (double) ((dogru) * 100) / (dogru + yanlis);
            System.out.println("\n Başarı Oranı %" + yuzde);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MinkowskiveTekrar() {
        try {

            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);

            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0, testi = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();

            System.out.println("\nEğitim Dosyasında " + i + " Kadar Veri Vardı ve İşlendi.");

            double[] enYakinUc = new double[kparametresi];
            int[] enYakinUcunSonucu = new int[kparametresi];

            for (int j = 0; j < kparametresi; j++) {
                enYakinUc[j] = 999;
            }

            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0, yanlis = 0;

            BufferedReader tbr = new BufferedReader(new FileReader(testfile));

            while ((line = tbr.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();

            System.out.println("\nTest Dosyasında " + testsatir + " Kadar Veri Var.");

            for (int j = 0; j < testsatir; j++) {
                for (int k = 0; k < fullsatir; k++) {

                    double minkowski = (double) (Math.abs(testMatris[j][0] - Matris[k][0])) + (double) Math.abs(testMatris[j][1] - Matris[k][1]);

                    for (int m = 0; m < kparametresi; m++) {
                        if (minkowski < enYakinUc[m]) {
                            enYakinUc[m] = minkowski;
                            enYakinUcunSonucu[m] = Matris[k][2];
                            break;
                        }
                    }

                }
                int ucunsonucu = Mod(enYakinUcunSonucu);
                System.out.println("\n" + (j + 1) + ".eleman için Eğitimdeki Sonuç: " + ucunsonucu);
                if (ucunsonucu == testMatris[j][2]) {

                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;

                } else {
                    System.out.println("\n Olması gereken sonuc : " + ucunsonucu + " \t Olan sonuc: " + testMatris[j][2]);
                    yanlis++;
                }

            }

            System.out.println("\n\n\n" + (testsatir - 1) + "adetlik eğitim verisinde " + dogru + " adet doğru, " + yanlis + " adet yanlis cikmistir.");
            double yuzde = (double) ((dogru) * 100) / (dogru + yanlis);
            System.out.println("\n Başarı Oranı %" + yuzde);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MinkowskiveAgirlik(){
        try {

            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);

            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0, testi = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();

            System.out.println("\nEğitim Dosyasında " + i + " Kadar Veri Vardı ve İşlendi.");

            double enyakin = 999;
            int enYakinSonucu = -1;

            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0, yanlis = 0;

            BufferedReader tbr = new BufferedReader(new FileReader(testfile));

            while ((line = tbr.readLine()) != null) {
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();

            System.out.println("\nTest Dosyasında " + testsatir + " Kadar Veri Var.");

            for (int j = 0; j < testsatir; j++) {
                for (int k = 0; k < fullsatir; k++) {

                    double minkowski = (double) (Math.abs(testMatris[j][0] - Matris[k][0])) + (double) Math.abs(testMatris[j][1] - Matris[k][1]);

                        if (minkowski < enyakin) {
                            enyakin = minkowski;
                            enYakinSonucu = Matris[k][2];
                        }
                        

                }
                System.out.println("\n" + (j + 1) + ".eleman için Eğitimdeki Sonuç: " + enYakinSonucu);
                if (enYakinSonucu == testMatris[j][2]) {

                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;

                } else {
                    System.out.println("\n Olması gereken sonuc : " + enYakinSonucu + " \t Olan sonuc: " + testMatris[j][2]);
                    yanlis++;
                }

            }

            System.out.println("\n\n\n" + (testsatir - 1) + "adetlik eğitim verisinde " + dogru + " adet doğru, " + yanlis + " adet yanlis cikmistir.");
            double yuzde = (double) ((dogru) * 100) / (dogru + yanlis);
            System.out.println("\n Başarı Oranı %" + yuzde);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int sutunSayisiniBul(File aranacak) throws FileNotFoundException, IOException {

        String line;

        FileReader fr = new FileReader(aranacak);
        BufferedReader br = new BufferedReader(fr);

        line = br.readLine();

        String parts[] = line.split(" ");

        return parts.length;
    }
    
    public int Mod(int[] a) {
        int count = 1, tempCount;
        int popular = a[0];
        int temp = 0;
        for (int i = 0; i < (a.length - 1); i++) {
            temp = a[i];
            tempCount = 0;
            for (int j = 1; j < a.length; j++) {
                if (temp == a[j]) {
                    tempCount++;
                }
            }
            if (tempCount > count) {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }
    
    public void OklidveTekrar(){
        
        try {
            
            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);
            
            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0,testi=0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();
            
            //her sütünun max değeri alınacak

//            for(int j=0;j<sutun;j++){
//                
//                max[j] = Matris[0][j];
//                
//                for(int m=0;m<satir;m++){
//                    
//                    if(Matris[m][j] > max[j]){
//                        
//                        max[j] = Matris[m][j];
//                        
//                    }
//                }
//            }
//            
            //
            
            System.out.println("\nEğitim Dosyasında "+i+" Kadar Veri Vardı ve İşlendi.");
            
            double[] enYakinUc = new double[kparametresi];
            int[] enYakinUcunSonucu = new int[kparametresi];
            
            for(int j = 0; j<kparametresi; j++){
                enYakinUc[j] = 999;
            }
            
            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0,yanlis=0;
                   
            BufferedReader tbr = new BufferedReader(new FileReader(testfile));
            
            while ((line = tbr.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();
            
            System.out.println("\nTest Dosyasında "+testsatir+" Kadar Veri Var.");
            
            for(int j = 0;j<testsatir;j++){
                for(int k = 0;k<fullsatir;k++){
                    
                    double oklid = (Math.pow(Math.abs(testMatris[j][0] - Matris[k][0]), 2) + Math.pow(Math.abs(testMatris[j][1] - Matris[k][1]), 2));
                    oklid = (double) Math.sqrt(oklid);

                    for (int m = 0; m < kparametresi; m++) {
                        if (oklid < enYakinUc[m]) {
                            enYakinUc[m] = oklid;
                            enYakinUcunSonucu[m] = Matris[k][2];
                            break;
                        }
                        
                    }
                    
                }
                int ucunsonucu = Mod(enYakinUcunSonucu);
                System.out.println("\n"+(j+1)+".eleman için Eğitimdeki Sonuç: "+ucunsonucu);
                if(ucunsonucu == testMatris[j][2]){
                    
                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;
                    
                }
                
                else{
                    System.out.println("\n Olması gereken sonuc : "+ucunsonucu+" \t Olan sonuc: "+testMatris[j][2]);
                    yanlis++;
                }
                
            }
            
            System.out.println("\n\n\n"+(testsatir-1)+"adetlik eğitim verisinde "+dogru+" adet doğru, "+yanlis+" adet yanlis cikmistir.");
            double yuzde= (double) ((dogru)*100)/(dogru+yanlis);
            System.out.println("\n Başarı Oranı %"+yuzde);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void OklidveAgirlik() {
        try {

            int fullsutun = sutunSayisiniBul(file);
            int fullsatir = satirSayisiniBul(file);

            int[][] Matris = new int[fullsatir][fullsutun];
            int[] max = new int[fullsutun];
            String line;
            int i = 0, testi = 0;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) { //Matrise atamalar yapıldı
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    Matris[i][j] = m;
                }
                i++;
            }

            br.close();

            System.out.println("\nEğitim Dosyasında " + i + " Kadar Veri Vardı ve İşlendi.");

            double enyakin = 999;
            int enYakinSonucu = -1;

            int testsutun = sutunSayisiniBul(testfile);
            int testsatir = satirSayisiniBul(testfile);
            int[][] testMatris = new int[testsatir][testsutun];
            int dogru = 0, yanlis = 0;

            BufferedReader tbr = new BufferedReader(new FileReader(testfile));

            while ((line = tbr.readLine()) != null) {
                String parts[] = line.split(" ");
                for (int j = 0; j < parts.length; j++) {
                    int m = Integer.parseInt(parts[j]);
                    testMatris[testi][j] = m;
                }
                testi++;
            }

            br.close();

            System.out.println("\nTest Dosyasında " + testsatir + " Kadar Veri Var.");

            for (int j = 0; j < testsatir; j++) {
                for (int k = 0; k < fullsatir; k++) {

                    double oklid = (Math.pow(Math.abs(testMatris[j][0] - Matris[k][0]), 2) + Math.pow(Math.abs(testMatris[j][1] - Matris[k][1]), 2));
                    oklid = (double) Math.sqrt(oklid);

                    if (oklid < enyakin) {
                        enyakin = oklid;
                        enYakinSonucu = Matris[k][2];
                    }

                }
                System.out.println("\n" + (j + 1) + ".eleman için Eğitimdeki Sonuç: " + enYakinSonucu);
                if (enYakinSonucu == testMatris[j][2]) {

                    System.out.println("\n Sonucu Doğru bildi!");
                    dogru++;

                } else {
                    System.out.println("\n Olması gereken sonuc : " + enYakinSonucu + " \t Olan sonuc: " + testMatris[j][2]);
                    yanlis++;
                }

            }

            System.out.println("\n\n\n" + (testsatir - 1) + "adetlik eğitim verisinde " + dogru + " adet doğru, " + yanlis + " adet yanlis cikmistir.");
            double yuzde = (double) ((dogru) * 100) / (dogru + yanlis);
            System.out.println("\n Başarı Oranı %" + yuzde);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(kNN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
