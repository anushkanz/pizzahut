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
public class Cheese {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    static JComboBox cheeseList;
    
    private int id;
    private String name;    
    private Double price; 

    public Cheese(DBManager dbManager, Connection conn, int id, String name, Double price) {
        this.dbManager = dbManager;
        this.conn = conn;
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public Cheese(){
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
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
    
    public ArrayList<Cheese> CheeseList() throws SQLException{
        String query = "SELECT * FROM CHEESE"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Cheese> cheeses = new ArrayList<>();

        while (rs.next()) {
            Cheese cheese = new Cheese();
            cheese.setId(rs.getInt("cheeseId"));
            cheese.setName(rs.getString("name"));
            cheese.setPrice(rs.getDouble("price"));
            cheeses.add(cheese);
        }
        
        return cheeses;
    }
    
    public JComboBox CheeseCombo()
    {
        try {
            String[] cheeseArray = new String[this.CheeseList().size()];
            for (int i = 0; i < this.CheeseList().size(); i++) {
                Cheese cheeseL = this.CheeseList().get(i);                    
                cheeseArray[i] = cheeseL.getName();
            }
            cheeseList = new JComboBox(cheeseArray);

        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cheeseList;
    }
    
    
    
    
    public ArrayList<Cheese> CheesePriceIdByNameList(String CheeseName)
    {        
        ArrayList<String> cheeseList = new ArrayList();
        ArrayList<Cheese> cheeses = new ArrayList<>();
        try {            
            for (int i = 0; i < this.CheeseList().size(); i++) { 
                if(CheeseName.equals(this.CheeseList().get(i).name)){                    
                    Cheese cheese = new Cheese();
                    cheese.setId(this.CheeseList().get(i).id);
                    cheese.setName(this.CheeseList().get(i).name);
                    cheese.setPrice(this.CheeseList().get(i).price);
                    cheeses.add(cheese);
                    break;
                }        
            }       
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cheeses;
    }
    
}
