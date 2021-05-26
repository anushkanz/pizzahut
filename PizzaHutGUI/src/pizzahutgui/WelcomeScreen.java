/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui;
import com.sun.jmx.snmp.Timestamp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import pizzahutgui.CLASS.AppGlobal;
import pizzahutgui.CLASS.Location;
import pizzahutgui.CLASS.Category;
import pizzahutgui.CLASS.Validation;




class WelcomeScreen extends JFrame implements ItemListener,ActionListener{
    static JFrame welcomeframe;
    static JComboBox timeList,locationList;
    static JLabel locationLable, timeLable, nameLable, emailLable, errorLable;
    static JTextField nameInput, emailInput;
    static JButton enterButton;
    
    
    public static void welcome() throws SQLException {
        Validation validation = new Validation();
        Border border = BorderFactory.createLineBorder(Color.red, 2);
        welcomeframe = new JFrame("Welcome Pizza Hut");
        JPanel panel = new JPanel();
        welcomeframe.getContentPane();        
        
        nameLable = new JLabel("Name ");        
        emailLable = new JLabel("Email ");
        errorLable = new JLabel(); 
        nameInput = new JTextField("");
        emailInput = new JTextField("");
        enterButton = new JButton("Enter");
        WelcomeScreen s = new WelcomeScreen();
        
 
        //Start Set Location
            locationLable = new JLabel("Select Location ");
            Dimension sizeLocationLable = locationLable.getPreferredSize();
            locationLable.setBounds(20, 120, sizeLocationLable.width, sizeLocationLable.height);
            
            Location location = new Location();
            ArrayList<Location> locationListData = location.LocationList();        
            String[] locationArray = new String[locationListData.size()];

            for (int i = 0; i < location.LocationList().size(); i++) { 
                Location locationL = location.LocationList().get(i);
                locationArray[i] = locationL.getName();
            }
            locationList = new JComboBox(locationArray);   
            Dimension sizeLocationList = locationList.getPreferredSize();                 
        //End Location
        
        //Start Set Time
            timeLable = new JLabel("Select Time ");        
                     
            locationList.setBounds(sizeLocationLable.width+20, 120, sizeLocationList.width, sizeLocationList.height);        
            locationList.addItemListener(s);
            Dimension sizeTimeLable = timeLable.getPreferredSize();
            timeLable.setBounds(sizeLocationList.width+sizeLocationLable.width+50, 120, sizeTimeLable.width, sizeTimeLable.height);

            ArrayList<String> TimeList = new ArrayList<String>();
            String[] timeArray = new String[24];
            for (int i = 0; i < 24; i++) {
                timeArray[i] = String.valueOf(i)+".00";
            }

            timeList = new JComboBox(timeArray);
            Dimension sizeTimeList = timeList.getPreferredSize();
            timeList.setBounds(sizeLocationList.width+sizeLocationLable.width+sizeTimeLable.width+60, 120, sizeTimeList.width, sizeTimeList.height);
        //End Time
        
        //Start Set Name
            nameLable.setBounds(20,50,50,50);
            nameInput.setBounds(80,60,200,30); 
        //End Name
        
        //Start Set Email
            emailLable.setBounds(300,50,200,50);              
            emailInput.setBounds(360,60,200,30);
        //End Email
        
        //Start Set Enter Button
            Dimension size = enterButton.getPreferredSize();
            enterButton.setBounds(300, 180, size.width, size.height);
            panel.setLayout(null);
        //End Enter Button
        
        panel.add(nameLable);
        panel.add(nameInput); 
        panel.add(emailLable);
        panel.add(emailInput); 
        //panel.add(timeList);
        //panel.add(timeLable);  
        panel.add(locationLable);        
        panel.add(locationList); 
        
        enterButton.setBounds(300, 180, 80, 40);
        panel.add(enterButton);        
        
        enterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String nameInputValue = nameInput.getText();               
                String emailInputValue = emailInput.getText();                
                    
                    errorLable.setBounds(100,220,200,50);
                    errorLable.setForeground(Color.red);
                    errorLable.setBorder(border);
                    errorLable.setHorizontalAlignment(JLabel.CENTER);
                    errorLable.setVerticalAlignment(JLabel.CENTER);
                                        
                    if(nameInputValue.equals(null) || nameInputValue.equals("") || emailInputValue.equals(null) || emailInputValue.equals("")){
                        errorLable.setText("Name and Email are can not be empty.");
                        panel.add(errorLable); 
                    }else{
                        AppGlobal.name = nameInputValue;
                        errorLable.setText("");
                        Boolean emailAccept = true;        
                        emailAccept = validation.isValidEmailAddress(emailInputValue);
                        if(!emailAccept){  
                            errorLable.setText("Invalid Email adderss format");
                            panel.add(errorLable);                          
                            System.out.println("Inside "+emailAccept);  
                        }else{
                            System.out.println("Else Inside "+emailAccept); 
                            AppGlobal.email = emailInputValue;
                            PizzaOrder pizzaorder = new PizzaOrder();
                            pizzaorder.PizzaOrder();
                            welcomeframe.setVisible(false);   
                        }      
                    }
                                                        
            }
        });
        
        Date currentDate = new Date(System.currentTimeMillis() + 1800 * 1000);
        AppGlobal.time = currentDate.toString();
        System.out.println(currentDate);  
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        welcomeframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        welcomeframe.add(panel);
        welcomeframe.setSize(700, 400);
        welcomeframe.setVisible(true);                
    }

    
    public static void main(String args[]) throws SQLException{
           //PizzaOrder order = new PizzaOrder();
           //order.PizzaOrder();
         welcome();
    }
    
    public void actionPerformed(ActionEvent e) {
            errorLable.setText("Test");  
    }
     
    @Override
    
    public void itemStateChanged(ItemEvent e) {
        Location location = new Location();
            String item = (String)e.getItem();            
            if(e.getStateChange() == ItemEvent.SELECTED) {
                location.setName(locationList.getSelectedItem().toString());
                AppGlobal.location = locationList.getSelectedItem().toString();
                System.out.println(locationList.getSelectedItem().toString());
            }
    }
}

