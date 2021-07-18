/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class pojo_BacSi {
    private String ten,ngaySinh,gioiTinh,maBS;
    public pojo_BacSi(){
        ten=ngaySinh=gioiTinh=maBS ="";
    }
    public pojo_BacSi(String maBS,String ten,String ngaySinh,String gioiTinh){
        this.maBS=maBS;
        this.ten=ten;
        this.ngaySinh=ngaySinh;
        this.gioiTinh=gioiTinh;
    }
    public String getMaBS(){
        return maBS;
        
    }
    public void setMaBS(String MaBS){
        this.maBS=MaBS;
    }
    public String getTen(){
        return ten;
        
    }
    public void setTen(String Ten){
        this.ten=ten;
    }
    public  String getNgaySinh(){
        return ngaySinh;
    }
    public void setNgaySing(String ngaysinh){
        this.ngaySinh=ngaysinh;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }
    public void setGioiTinh(String gioitinh)
    {
        this.gioiTinh=gioitinh;
    }
}
