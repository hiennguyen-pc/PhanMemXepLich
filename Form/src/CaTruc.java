
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class CaTruc {
    //Khởi tạo các tham số cần thiết
    BacSi[] danhSachBacSi;
    BacSi[] danhSachCacBuocDi;
    BacSi[] danhSachBacSiTruc;
    int soPhong;
    double max;
    double fitness;
    double mutationRate;
    //Khởi tạo các hàm khởi tạo ở đây
    public CaTruc(BacSi[] danhSachBacSi, int soPhong, double mutationRate){
        //Khởi tạo các biến cho lớp này
        this.danhSachBacSi = danhSachBacSi.clone();
        this.danhSachCacBuocDi = danhSachBacSi.clone();
        this.soPhong = soPhong;
        this.mutationRate = mutationRate;
        
        //Khởi tạo ngẫu nhiên danh sách bác sĩ trực trong ca này
        this.danhSachBacSiTruc = new BacSi[this.soPhong];
        Random rand = new Random();
        for(int i = 0; i < this.danhSachBacSiTruc.length; i++){
            int randomIndex = rand.nextInt(this.danhSachCacBuocDi.length);
            if(this.danhSachCacBuocDi[randomIndex] != null){
                this.danhSachBacSiTruc[i] = this.danhSachCacBuocDi[randomIndex];
                this.danhSachCacBuocDi[randomIndex] = null;
            }
            else{
                i -= 1;
            }
        }
    }
    
    public void calcFitness(){
        double diemTrungBinh = 0;
        double score = 0;
        int diemTrenTrungBinh = 0;
        int diemDuoiTrungBinh = 5;
        //Tính điểm trung bình
        for(BacSi bacSi : this.danhSachBacSi){
            diemTrungBinh += bacSi.soLanTruc;
        }
        diemTrungBinh = diemTrungBinh / this.danhSachBacSi.length;
        
        //Tìm số bác sĩ có số ca trực dưới trung bình
        ArrayList<BacSi> danhSachBacSiDuoiTrungBinh = new ArrayList<BacSi>();
        for(BacSi bacSi : this.danhSachBacSi){
            if(bacSi.soLanTruc < diemTrungBinh){
                danhSachBacSiDuoiTrungBinh.add(bacSi);
            }
        }
        
        //Tính điểm max
        if(danhSachBacSiDuoiTrungBinh.size() >= this.danhSachBacSiTruc.length){
            this.max = this.danhSachBacSiTruc.length * diemDuoiTrungBinh;
        }
        else{
            this.max = danhSachBacSiDuoiTrungBinh.size() * diemDuoiTrungBinh + (this.danhSachBacSiTruc.length - danhSachBacSiDuoiTrungBinh.size()) * diemTrenTrungBinh;
        }
        
        //Tính điểm
        for(BacSi bacSi : this.danhSachBacSiTruc){
            if(danhSachBacSiDuoiTrungBinh.contains(bacSi)){
                score += diemDuoiTrungBinh;
            }
            else{
                score += diemTrenTrungBinh;
            }
        }
        
        this.fitness = score;
    }
    //lai
    public CaTruc crossOver(CaTruc parentB){
        Random rand = new Random();
        CaTruc child = new CaTruc(this.danhSachBacSi, this.soPhong, this.mutationRate);
        //ArrayList<BacSi> danhSachBacSiDaXuatHien = new ArrayList<BacSi>();
        BacSi[] danhSachBacSiTrucClone1 = this.danhSachBacSiTruc.clone();
        BacSi[] danhSachBacSiTrucClone2 = parentB.danhSachBacSiTruc.clone();
        HashSet danhSachKhongTrung = new HashSet();
        
        //Nhập từ danh sách 1
        for(BacSi bacSi : danhSachBacSiTrucClone1){
            danhSachKhongTrung.add(bacSi);
        }
        //Nhập từ danh sách 2
        for(BacSi bacSi : danhSachBacSiTrucClone2){
            danhSachKhongTrung.add(bacSi);
        }
        
        ArrayList<BacSi> danhSachTrungGian = new ArrayList<BacSi>();
        danhSachTrungGian.addAll(danhSachKhongTrung);
        //Chọn ngẫu nhiên 30 bác sĩ từ danh sách trung gian cho vào đứa con
        for(int i = 0; i < child.danhSachBacSiTruc.length; i++){
            int randomIndex = rand.nextInt(danhSachTrungGian.size());
            child.danhSachBacSiTruc[i] = danhSachTrungGian.get(randomIndex);
            danhSachTrungGian.remove(randomIndex);
        }
        
        return child;
    }
    //Đột biến
    public void mutate(){
        Random rand = new Random();
        int randomIndex1 = rand.nextInt(this.danhSachBacSiTruc.length);
        double randomNumber = Math.random();
        if(randomNumber < this.mutationRate){
            while(true){
                BacSi[] danhSachBacSiTrucClone = this.danhSachBacSiTruc.clone();
                int randomIndex2 = rand.nextInt(this.danhSachBacSiTruc.length);
                danhSachBacSiTrucClone[randomIndex1] = this.danhSachCacBuocDi[randomIndex2];
                HashSet danhSachTrungGian = new HashSet();
                for(BacSi bacSi : danhSachBacSiTrucClone){
                    danhSachTrungGian.add(bacSi);
                }
                if(danhSachTrungGian.size() == danhSachBacSiTrucClone.length){
                    this.danhSachBacSiTruc = danhSachBacSiTrucClone;
                    break;
                }
            }
        } 
    }
    
    public BacSi[] getDanhSachBacSiTruc(){
        return this.danhSachBacSiTruc.clone();
    }
    
    public void inThongTin(){
        for(BacSi bacSi : this.danhSachBacSiTruc){
            System.out.println(bacSi);
        }
    }
    public  BacSi getBasiCatruc(int a ){
        return this.danhSachBacSiTruc[a];
    }
}