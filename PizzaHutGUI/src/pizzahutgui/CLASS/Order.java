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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nush
 */
public class Order {
    int orderId;
    int customerId;
    int location;
    int pizzaid;
    int cheeseid;
    int crust;
    int sauce;
    String time;
    String note;
    int count;
    
    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    
    public Order() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }
    
    public Order(int orderId, int customerId, int location, int pizzaid, int cheeseid, int crust, int sauce, String time, String note, int count) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.location = location;
        this.pizzaid = pizzaid;
        this.cheeseid = cheeseid;
        this.crust = crust;
        this.sauce = sauce;
        this.time = time;
        this.note = note;
        this.count = count;
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getPizzaid() {
        return pizzaid;
    }

    public void setPizzaid(int pizzaid) {
        this.pizzaid = pizzaid;
    }

    public int getCheeseid() {
        return cheeseid;
    }

    public void setCheeseid(int cheeseid) {
        this.cheeseid = cheeseid;
    }

    public int getCrust() {
        return crust;
    }

    public void setCrust(int crust) {
        this.crust = crust;
    }

    public int getSauce() {
        return sauce;
    }

    public void setSauce(int sauce) {
        this.sauce = sauce;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int AddOrder() throws SQLException{
        ResultSet rs = null;
        orderId = 0;
        String query_insert = "INSERT INTO 'ORDERS'(CUSTOMERID,LOCATIONID,PIZZAID,CHEESEID,CRUST,SAUCE,TIME,NOTE,COUNT) "
                + "VALUE ('"+this.getCustomerId()+"','"+this.getLocation()+"','"+this.getPizzaid()+"','"+this.getCheeseid()+"','"+this.getCrust()+"','"+this.getSauce()+"','"+this.getTime()+"','"+this.getNote()+"','"+this.getCount()+"')";
        rs = dbManager.queryDB(query_insert); 
        if(rs.last()){
            orderId = rs.getInt("ORDERID");
        }
        return orderId;
    }
}
