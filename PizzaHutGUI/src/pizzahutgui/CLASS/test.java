/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui.CLASS;

import java.sql.SQLException;

/**
 *
 * @author nush
 */
public class test {
     public static void main(String args[]) throws SQLException {
        Location location = new Location();
        System.out.println("Location"); 
        for (int i = 0; i < location.LocationList().size(); i++) { 
            Location locationL = location.LocationList().get(i);
            System.out.println("Location:  "+locationL.getName()); 
        }
    }
}
