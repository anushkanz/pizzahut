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
public class Category {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    static JComboBox categoryList;
    
    private int id;
    private String name;    
    private String details; 
    private int status;    
    
    public Category() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public Category(int id,String name,String details,int status) {
        this.id = id;
        this.name = name;        
        this.details = details;
        this.status = status;        
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public ArrayList<Category> CategoryList() throws SQLException{
        String query = "SELECT * FROM CATEGORY"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Category> categories = new ArrayList<>();

        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("categoryId"));
            category.setName(rs.getString("name"));
            category.setDetails(rs.getString("details"));
            category.setStatus(rs.getInt("status"));
            categories.add(category);
        }
        
        return categories;
    } 
    
    public JComboBox CategoryCombo()
    {
        try {
            String[] categoryArray = new String[this.CategoryList().size()];
            for (int i = 0; i < this.CategoryList().size(); i++) {
                Category categoryL = this.CategoryList().get(i);                    
                categoryArray[i] = categoryL.getName();
            }
            categoryList = new JComboBox(categoryArray);

        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryList;
    }
    
    
    public Void mainrun() throws SQLException{
        String query = "SELECT * FROM CATEGORY"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        while (rs.next()) {
            System.out.println("Location:  "+rs.getInt("LOCATIONID"));   
        }
        return null;
        

    }
}
