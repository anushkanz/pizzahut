/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui.CLASS;
import javax.swing.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nush
 */
public class Crust {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    static JComboBox crustList;
    
    private int id;
    private String name;    
    private Double price; 
    
    public Crust(DBManager dbManager, Connection conn, int id, String name, Double price) {
        this.dbManager = dbManager;
        this.conn = conn;
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public Crust(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public static JComboBox getCrustList() {
        return crustList;
    }

    public static void setCrustList(JComboBox crustList) {
        Crust.crustList = crustList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public ArrayList<Crust> CrustList() throws SQLException{
        String query = "SELECT * FROM CRUST"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Crust> crusts = new ArrayList<>();

        while (rs.next()) {
            Crust crust = new Crust();
            crust.setId(rs.getInt("crustId"));
            crust.setName(rs.getString("name"));
            crust.setPrice(rs.getDouble("price"));
            crusts.add(crust);
        }
        
        return crusts;
    }
    
    public JComboBox CrustCombo()
    {
        try {
            String[] crustArray = new String[this.CrustList().size()];
            for (int i = 0; i < this.CrustList().size(); i++) {
                Crust crustL = this.CrustList().get(i);                    
                crustArray[i] = crustL.getName();
            }
            crustList = new JComboBox(crustArray);

        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crustList;
    }
    

    
    public ArrayList<Crust> CrustPriceIdByNameList(String CrustName)
    {        
        ArrayList<String> crustList = new ArrayList();
        ArrayList<Crust> crusts = new ArrayList<>();
        try {            
            for (int i = 0; i < this.CrustList().size(); i++) { 
                if(CrustName.equals(this.CrustList().get(i).name)){                    
                    Crust crust = new Crust();
                    crust.setId(this.CrustList().get(i).id);
                    crust.setName(this.CrustList().get(i).name);
                    crust.setPrice(this.CrustList().get(i).price);
                    crusts.add(crust);
                    break;
                }        
            }       
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crusts;
    }
}
