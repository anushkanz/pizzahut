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
public class Sauce {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    static JComboBox sauceList;
    
    private int id;
    private String name;    
    private Double price; 
    
    public Sauce(DBManager dbManager, Connection conn, int id, String name, Double price) {
        this.dbManager = dbManager;
        this.conn = conn;
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public Sauce(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public static JComboBox getSauceList() {
        return sauceList;
    }

    public static void setSauceList(JComboBox sauceList) {
        Sauce.sauceList = sauceList;
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
    
    public ArrayList<Sauce> SauceList() throws SQLException{
        String query = "SELECT * FROM SAUCE"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Sauce> sauces = new ArrayList<>();

        while (rs.next()) {
            Sauce sauce = new Sauce();
            sauce.setId(rs.getInt("sauceId"));
            sauce.setName(rs.getString("name"));
            sauce.setPrice(rs.getDouble("price"));
            sauces.add(sauce);
        }
        
        return sauces;
    }
    
    public JComboBox SauceCombo()
    {
        try {
            String[] sauceArray = new String[this.SauceList().size()];
            for (int i = 0; i < this.SauceList().size(); i++) {
                Sauce sauceL = this.SauceList().get(i);                    
                sauceArray[i] = sauceL.getName();
            }
            sauceList = new JComboBox(sauceArray);

        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sauceList;
    }
    
    
    public ArrayList<Sauce> SaucePriceIdByNameList(String SauceName)
    {        
        ArrayList<String> sauceList = new ArrayList();
        ArrayList<Sauce> sauces = new ArrayList<>();
        try {            
            for (int i = 0; i < this.SauceList().size(); i++) { 
                if(SauceName.equals(this.SauceList().get(i).name)){                    
                    Sauce sauce = new Sauce();
                    sauce.setId(this.SauceList().get(i).id);
                    sauce.setName(this.SauceList().get(i).name);
                    sauce.setPrice(this.SauceList().get(i).price);
                    sauces.add(sauce);
                    break;
                }        
            }       
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sauces;
    }
}
