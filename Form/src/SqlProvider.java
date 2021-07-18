
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SqlProvider {
    connecttionSQLsever sql;
    public SqlProvider(String sqlInstanceName, String database, String userName,String password){   
        sql=new connecttionSQLsever(sqlInstanceName, database, userName, password);
    }    
    public ResultSet excuteQuery(String s){
        ResultSet rs=null;
        try {
            
            Statement st = sql.GetConnect().createStatement();
            rs=st.executeQuery(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(F_XepLich.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public  Connection getConnection(){
        return sql.GetConnect();
        
    }
    public void Open()
    {
        sql.OpenConnection();
    }
    public void Close()
    {
        sql.CloseConnection();
    }
    public int excuteUpdate(String s){
        int rs=-1;
        try {
            sql.OpenConnection();
            Statement st = sql.GetConnect().createStatement();
            rs=st.executeUpdate(s);
            sql.CloseConnection();
        } catch (SQLException ex) {
            Logger.getLogger(F_XepLich.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
