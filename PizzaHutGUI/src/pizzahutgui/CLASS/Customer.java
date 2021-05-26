/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui.CLASS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nush
 */
public class Customer {
    
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    int customerId;
    String name;
    String email;
    
    public Customer() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<Customer> CustomerList() throws SQLException{
        String query = "SELECT * FROM CUSTOMERS"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Customer> customers = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerId"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customers.add(customer);
        }
        return customers;
    }
    
    public int getCustomerByEmail(String email){    
        try {        
            String[] customerArray = new String[this.CustomerList().size()];
            for (int i = 0; i < this.CustomerList().size(); i++) {
                Customer customerL = this.CustomerList().get(i);                    
                if(customerL.getEmail().equals(this.getEmail()) ){
                    customerId = customerL.getCustomerId();
                }else{
                    customerId = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return customerId;
    }
    
    
    public int AddNewCustomer(String name, String email) throws SQLException{  
        int customerId =0;
        if(this.getCustomerByEmail(email) == 0){ 
            ResultSet rs = null;
            String query_insert = "INSERT INTO 'CUSTOMERS'(NAME,EMAIL) VALUE ('"+name+"','"+email+"')";             
            rs = dbManager.queryDB(query_insert);  
            if(rs.last()){
                customerId = rs.getInt("CUSTOMERID");
            }else{
                customerId = 0;
            }
        }else{
            customerId = this.getCustomerByEmail(email);
        }
        return customerId;
    }
}
