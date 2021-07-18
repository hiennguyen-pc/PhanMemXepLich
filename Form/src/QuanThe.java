
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class QuanThe {
    //Khởi tạo các tham số
    CaTruc[] danhSachQuanThe;
    ArrayList<CaTruc> pool = new ArrayList<CaTruc>();
    boolean isDone = false;
    //Khởi tạo các hàm khởi tạo
    public QuanThe(BacSi[] danhSachBacSi, int soPhong, double mutationRate, int maxPop){
        this.danhSachQuanThe = new CaTruc[maxPop];
        for(int i = 0; i < maxPop; i++){
            this.danhSachQuanThe[i] = new CaTruc(danhSachBacSi, soPhong, mutationRate);
        }
    }
    
    //Hàm tính điểm
    public CaTruc calcFitness(){
        for(CaTruc caTruc : this.danhSachQuanThe){
            caTruc.calcFitness();
            //Nếu điểm ca trực = max
            if(caTruc.fitness == caTruc.max){
                System.out.println("Hoan Thanh 1 Ca");
                caTruc.inThongTin();
                this.isDone = true;
                return caTruc;
            }
        }
        return null;
    }
    
    //Hàm tạo ra bể lai
    public void naturalSelection(){
        //Đây là bể lai, điểm càng cao thì nó xuất hiện càng nhiều
        //VD nó có 25 điểm thì nó xuất hiện 25 lần trong bể lai này
        for(CaTruc caTruc : this.danhSachQuanThe){
            for(int i = 0; i < caTruc.fitness; i++){
                pool.add(caTruc);
            }
        }
    }
    
    public void generate(){
        //Chọn 2 cá thể ngẫu nhiên từ bể lai
        Random rand = new Random();
        for(int i = 0; i < this.danhSachQuanThe.length; i++){
            int rand1 = rand.nextInt(this.pool.size());
            //System.err.println(rand1);
            int rand2 = rand.nextInt(this.pool.size());
            //System.err.println(rand2);
            this.danhSachQuanThe[i] = this.pool.get(rand1).crossOver(this.pool.get(rand2));
            this.danhSachQuanThe[i].mutate();
        }
        this.pool.clear();
    }
}
