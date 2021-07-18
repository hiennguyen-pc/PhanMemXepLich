
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DAO_BacSi {
    SqlProvider pro;
    public  DAO_BacSi(){
        pro=new SqlProvider("MSI", "QL_BacSi", "sa","123");
        
    }
    public ArrayList<String> loadBS(){
        ArrayList<String> list=new ArrayList<String>();
        pro.Open();
        try {
            String query="select *from BacSi";
            ResultSet rs=pro.excuteQuery(query);
            while (rs.next()) {                
                String ten=rs.getString("TenBS");
                list.add(ten);
            }
        } catch (SQLException e) {
            Logger.getLogger(F_XepLich.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    public ArrayList<String> loadMaBS(){
        ArrayList<String> list=new ArrayList<String>();
        pro.Open();
        try {
            String query="select *from BacSi";
            ResultSet rs=pro.excuteQuery(query);
            while (rs.next()) {                
                String ten=rs.getString("id");
                list.add(ten);
            }
        } catch (SQLException e) {
            Logger.getLogger(F_XepLich.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    public Vector LayBacSI_vector(){
        Vector vdata=new Vector();
        pro.Open();
        try {
            String query="select *from BacSi";
            ResultSet rs=pro.excuteQuery(query);
            while (rs.next()) {
                String maBS=rs.getString("id");
                String ten=rs.getString("TenBS");
                String ngaysinh=rs.getString("NgaySinh");
                String gioitinh=rs.getString("GioiTinh");
                Vector<String> v=new Vector<String>();
                v.add(maBS);
                v.add(ten);
                v.add(ngaysinh);
                v.add(gioitinh);
                vdata.add(v);        
            }
            pro.Close();
        } catch (SQLException e) {
            Logger.getLogger(F_XepLich.class.getName()).log(Level.SEVERE, null, e);
        }
        return vdata;
    }
    public boolean ThemBS(pojo_BacSi bs){
        boolean kq=false;
   
            pro.Open();
            String maBS=bs.getMaBS();
            String ten=bs.getTen();
            String NgaySinh=bs.getNgaySinh();
            String gioitinh=bs.getGioiTinh();
            String query="insert into BacSI values('"+maBS+"',"+'N'+"'"+ten+"','"+NgaySinh+"',"+'N'+"'"+gioitinh+"')";
            int n=pro.excuteUpdate(query);
            if(n==1){
                kq=true;
            }
            pro.Close();
      
        return kq;
    }
    public boolean XoaBS(pojo_BacSi bs){
        boolean kq=false;
        pro.Open();
        String ma=bs.getMaBS();
        String ten=bs.getTen();
        String NgaySinh=bs.getNgaySinh();
        String gioitinh=bs.getGioiTinh();
        String query="delete BacSI where id='"+ma+"'";
        int n=pro.excuteUpdate(query);
        if(n==1){
            kq=true;
        }
        return kq;
    }

}
