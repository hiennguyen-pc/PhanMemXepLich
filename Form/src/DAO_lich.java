
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DAO_lich {
     public static BacSi[] capNhatDanhSach(BacSi[] danhSachBacSi1, BacSi[] danhSachBacSi2){
        BacSi[] danhSach1 = danhSachBacSi1.clone();
        BacSi[] danhSach2 = danhSachBacSi2.clone();
        for(BacSi bacSi1 : danhSach1){
            for(BacSi bacSi2 : danhSach2){
                if(bacSi1.equals(bacSi2)){
                    bacSi1.cong1Lan();
                }
            }
        }
        return danhSach1;
    }
     public static  void delete(){
         SqlProvider pro=new SqlProvider("MSI", "QL_BacSi", "sa","123");
         pro.Open();
         String query="delete from LichTruc ";
         int n=pro.excuteUpdate(query);
         pro.Close();
     }
     public static void statr_xepLich(){
        SqlProvider pro=new SqlProvider("MSI", "QL_BacSi", "sa","123");
        DAO_BacSi e= new DAO_BacSi();
        ArrayList<String> bs= new ArrayList<String>();
        bs=e.loadMaBS();
        int soBacSi = 30;
        int soPhong = 10;
        int soCa = 3;
        int soNgay = 30;
        int maxPop = 100;
        double mutationRate = 0;
        //Khởi tạo danh sách bác sĩ
        BacSi[] danhSachBacSi = new BacSi[soBacSi];
        for(int i = 0; i < danhSachBacSi.length; i++){
            danhSachBacSi[i] = new BacSi(i, bs.get(i));
        }      
    
        
            
        /////////////////////////////////////////////////////////
        
        ArrayList<ArrayList<CaTruc>> danhSach = new ArrayList<ArrayList<CaTruc>>();
        for(int ngay = 0; ngay < soNgay; ngay++){
            ArrayList<CaTruc> danhSachCaTruc = new ArrayList<CaTruc>();
            for(int i = 0; i < soCa; i++){
                QuanThe quanThe = new QuanThe(danhSachBacSi, soPhong, mutationRate, maxPop);
                while(!quanThe.isDone){
                    CaTruc caTrucBest = quanThe.calcFitness();
                    if(caTrucBest != null){
                        System.out.println("Ngay " + ngay + ",Ca " + i);
                        System.out.println("");
                        danhSachCaTruc.add(caTrucBest);
                        break;
                    }
                    quanThe.naturalSelection();
                    quanThe.generate();
                }
                danhSachBacSi = capNhatDanhSach(danhSachBacSi ,danhSachCaTruc.get(i).getDanhSachBacSiTruc());
            }
            danhSach.add(danhSachCaTruc);
        }
             for (int i = 0; i < soNgay; i++) {
             for (int j = 0; j < soCa; j++) {
                 for (int k = 0; k < soPhong; k++) {
                    pro.Open();
                    String name=danhSach.get(i).get(j).getBasiCatruc(k).tenBacSi;
                    String ngayTruc=String.valueOf(i+1)+"/08/2020";
                    int phongtruc=k+1;
                    int Ca_truc=j+1;
                    String query="insert into LichTruc values('"+name+"','"+ngayTruc+"','"+phongtruc+"','"+Ca_truc+"')";
                    int n=pro.excuteUpdate(query);
                    pro.Close();
                 }
                 
             }
         }
    
     }
    
}
