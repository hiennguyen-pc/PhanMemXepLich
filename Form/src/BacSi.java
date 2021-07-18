
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MyPC
 */
public class BacSi {
    //Khởi tạo các thuộc tính ở đây
    String tenBacSi;
    int soLanTruc = 0;
    int id;
    //Khởi tạo các hàm khởi tạo ở đây
    public BacSi(int id, String tenBacSi){
        this.id = id;
        this.tenBacSi = tenBacSi;
    }
    
    public BacSi(int id, int tenBacSiNum){
        String tenBacSi = Integer.toString(tenBacSiNum);
        this.id = id;
        this.tenBacSi = tenBacSi;
    }
    
    //Khởi tạo các phương thức ở đây
    public void cong1Lan(){
        this.soLanTruc += 1;
    }
    //Overide các hàm ở đây
    @Override
    public String toString(){
        return this.tenBacSi;// + ", Tong so lan truc: " + this.tongSoLanTruc;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.tenBacSi);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        final BacSi bacSiB = (BacSi) obj;
        if(this.tenBacSi.equals(bacSiB.tenBacSi) && this.id == bacSiB.id){
            return true;
        }
        else
            return false;
    }
}
