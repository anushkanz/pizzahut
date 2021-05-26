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
public class Pizza {
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    static JComboBox pizzaList;
    
    private int id;
    private int categoryid;    
    private String details;
    private Double price;  
    private String name;   
    
    public Pizza() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public Pizza(DBManager dbManager, Connection conn, int id, int categoryid, String details, Double price, String name) {
        this.dbManager = dbManager;
        this.conn = conn;
        this.id = id;
        this.categoryid = categoryid;
        this.details = details;
        this.price = price;
        this.name = name;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public Statement getStatement() {
        return statement;
    }

    public int getId() {
        return id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public String getDetails() {
        return details;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    
    public ArrayList<Pizza> PizzaList() throws SQLException{
        
        //Get Pizza list by category list
        ArrayList<Pizza> pizzaList = new ArrayList<>();
   
            String query = "SELECT * FROM PIZZA"; 
            ResultSet rs = null;
            rs = dbManager.queryDB(query);
            
            while (rs.next()) {
                Pizza pizza = new Pizza();
                pizza.setId(rs.getInt("pizzaId"));
                pizza.setName(rs.getString("name"));
                pizza.setDetails(rs.getString("details"));
                pizza.setCategoryid(rs.getInt("categoryId"));
                pizza.setPrice(rs.getDouble("price"));
                pizzaList.add(pizza);
            }
        
       return pizzaList;
    }
    
    
    
    public ArrayList<String> PizzaListByCat(String CategoryName)
    {
        ArrayList<String> pizzaArray = new ArrayList();
        try {
            int selectedCategoryId = 0;
            //Get Category ID by given name
            Category category = new Category(); 
            ArrayList<Category> categoryList = category.CategoryList();        
            for(Category categoryValue : categoryList){  
                if(categoryValue.getName().equals(CategoryName)){ 
                    selectedCategoryId = categoryValue.getId();
                    break;
                }
            }
            
            for (int i = 0; i < this.PizzaList().size(); i++) {                
                if(selectedCategoryId == this.PizzaList().get(i).categoryid ){                    
                    Pizza pizzL = this.PizzaList().get(i);                    
                    pizzaArray.add(pizzL.getName());
                }                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pizzaArray;        
    }
    
    
    public ArrayList<Pizza> PizzaPriceIdByNameList(String PizzaName)
    {        
        ArrayList<String> pizzaList = new ArrayList();
        ArrayList<Pizza> pizzas = new ArrayList<>();
        try {            
            if(PizzaName != null && !PizzaName.trim().isEmpty()) {            
                for (int i = 0; i < this.PizzaList().size(); i++) { 
                    if(PizzaName.equals(this.PizzaList().get(i).name)){                    
                        Pizza pizza = new Pizza();
                        pizza.setId(this.PizzaList().get(i).id);
                        pizza.setName(this.PizzaList().get(i).name);
                        pizza.setPrice(this.PizzaList().get(i).price);
                        pizzas.add(pizza);
                        break;
                    }        
                } 
            }    
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pizzas;
    }
}
