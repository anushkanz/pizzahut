/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui.CLASS;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author nush
 */
public class Location {
    
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    private int id;
    private String name;    
    private String city; 
    private String suburb;
    private String address;
    private String telephone;
    
    public Location() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
        
    public Location(int id,String name,String city,String suburb,String address,String telephone) {
        this.id = id;
        this.name = name;        
        this.city = city;
        this.suburb = suburb;        
        this.address = address;
        this.telephone = telephone;  
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
     
    public void setName(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setSuburb(String suburb){
        this.suburb = suburb;
    }
    
    public String getSuburb() {
        return suburb;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public ArrayList<Location> LocationList() throws SQLException{
        String query = "SELECT * FROM LOCATION"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);
        
        ArrayList<Location> locations = new ArrayList<>();
       // Location location = new Location();

        while (rs.next()) {
            Location location = new Location();
            location.setId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setCity(rs.getString("city"));
            location.setSuburb(rs.getString("suburb"));
            location.setAddress(rs.getString("address"));
            location.setTelephone(rs.getString("telephone"));
            locations.add(location);
        }
        
        return locations;
    } 
    
    public Void mainrun() throws SQLException{
        String query = "SELECT * FROM LOCATION"; 
        ResultSet rs = null;
        rs = dbManager.queryDB(query);

        
        while (rs.next()) {
            System.out.println("Location:  "+rs.getInt("LOCATIONID"));   
        }
        return null;       

    }
    
   

    
}
