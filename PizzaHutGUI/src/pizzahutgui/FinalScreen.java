/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import pizzahutgui.CLASS.AppGlobal;
import pizzahutgui.CLASS.Location;
import pizzahutgui.CLASS.Category;
import pizzahutgui.CLASS.Customer;
import pizzahutgui.CLASS.Order;
import pizzahutgui.CLASS.Sauce;
import pizzahutgui.CLASS.Validation;

/**
 *
 * @author nush
 */
class FinalScreen extends JFrame implements ItemListener,ActionListener{
    static JFrame finishingframe;
    static JLabel pizzaLable,crustLable,sauceLable,
                  cheeseLable,pizzaPriceLable,crustPriceLable,
                  saucePriceLable,cheesePriceLable,countLable,totalPriceLable,totalPrice,errorText;
    static JTextField totalInput,countInput;
    static JButton enterButton;
    JPanel panel = new JPanel(null);
    
    public void FinishProcess() throws SQLException {
            
        System.out.println(AppGlobal.email);
        finishingframe = new JFrame("Pizza Hut Finish Order");        
        finishingframe.getContentPane();        
        Dimension screenSize = new Dimension(500, 400);
        finishingframe.setPreferredSize(screenSize);  
        
        //Start Set Pizza 
            pizzaLable = new JLabel("Selected Pizza: "+AppGlobal.pizza);   
            pizzaLable.setBounds(80, 60, 200, 40);
            panel.add(pizzaLable);
            pizzaPriceLable = new JLabel("0:00");
            pizzaPriceLable.setText(AppGlobal.pizzaPrice);
            pizzaPriceLable.setBounds(300,55, 200, 50); 
            panel.add(pizzaPriceLable); 
        //End Pizza
        
        //Start Set Sauce
            Sauce sauce = new Sauce();
            sauceLable= new JLabel("Selected Sauce: "+AppGlobal.sauce);   
            sauceLable.setBounds(80, 100, 200, 40);
            panel.add(sauceLable);
            saucePriceLable = new JLabel("0:00"); 
            saucePriceLable.setText(AppGlobal.saucePrice);
            saucePriceLable.setBounds(300,95, 200, 50);
            panel.add(saucePriceLable); 
        //End Sauce
        
        //Start Set Crust
            crustLable= new JLabel("Selected Crust: "+AppGlobal.crust);
            crustLable.setBounds(80, 140, 200, 40);
            panel.add(crustLable);  
            crustPriceLable = new JLabel("0:00"); 
            crustPriceLable.setText(AppGlobal.crustPrice);
            crustPriceLable.setBounds(300,135, 200, 50);
            panel.add(crustPriceLable); 
        //End Crust
        
        //Start Set Cheese
            cheeseLable= new JLabel("Selected Cheese: "+AppGlobal.cheese);
            cheeseLable.setBounds(80, 180, 200, 40);
            panel.add(cheeseLable);
            cheesePriceLable = new JLabel("0:00");
            cheesePriceLable.setText(AppGlobal.cheesePrice);
            cheesePriceLable.setBounds(300,175, 200, 50);
            panel.add(cheesePriceLable);               
        //End Cheese
        
                
        //Start Set Button
            enterButton = new JButton("Enter");    
            enterButton.setBounds(300,300, 80, 40);
            enterButton.setEnabled(false);
            panel.add(enterButton); 
        //End Button
        
        //Start Set How many Pizza
            countLable= new JLabel("Total Pizza: ");
            countLable.setBounds(80, 220, 200, 40);
            panel.add(countLable); 
            countInput = new JTextField("0");
            countInput.setBounds(300,225, 50, 30);
            panel.add(countInput); 
            errorText = new JLabel("");
            errorText.setBounds(320,225, 50, 30);
            countInput.addKeyListener(new KeyAdapter() {
            
            public void keyReleased(KeyEvent ke) {
                String value = countInput.getText();                
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
                   countInput.setEditable(true);                  
                   //errorText.setText("");
                   //updateButton.setEnabled(true); 
                    if(value.isEmpty()){
                        totalInput.setText(" ");
                    }else{
                        Double total = Double.parseDouble(countInput.getText())*(
                            Double.parseDouble(AppGlobal.pizzaPrice) 
                            + Double.parseDouble(AppGlobal.saucePrice) 
                            + Double.parseDouble(AppGlobal.crustPrice) 
                            + Double.parseDouble(AppGlobal.cheesePrice)
                            ); 
                            DecimalFormat df = new DecimalFormat("####0.00");
                            System.out.println(df.format(total));
                            totalInput.setText(String.valueOf(df.format(total)));
                            
                            if(total >0){
                                enterButton.setEnabled(true);
                            }
                    }        
                } else {
                   countInput.setEditable(false);
                   errorText.setText("* Enter only numeric digits(0-9)");
                }
                System.out.println(value);
            }
         });
        //End How many Pizza
        
        //Start Total Price
            totalPrice= new JLabel("Total Price: ");
            totalPrice.setBounds(80, 260, 200, 40);
            panel.add(totalPrice); 
            totalInput = new JTextField();
            totalInput.setBounds(300,265, 100, 30);
            totalInput.setEditable(false);
            panel.add(totalInput); 
        
        //End Total Price

        
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            finishingframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            finishingframe.add(panel);
            finishingframe.setSize(700, 400);
            finishingframe.setVisible(true);
            
            enterButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    Customer customer = new Customer();
                    Order order = new Order();
                    try {
                        int customerId = customer.AddNewCustomer(AppGlobal.name, AppGlobal.email);
                        order.setCustomerId(customerId);
                        order.setPizzaid(Integer.parseInt(AppGlobal.pizzaId));
                        order.setCheeseid(Integer.parseInt(AppGlobal.cheeseId));
                        order.setCrust(Integer.parseInt(AppGlobal.crustId));                        
                        order.setSauce(Integer.parseInt(AppGlobal.sauceId));
                        
                        if(order.AddOrder() > 0){
                            System.out.println("added");
                        }
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(FinalScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
 
                }
            });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
