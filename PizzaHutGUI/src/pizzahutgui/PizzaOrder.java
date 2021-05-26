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
import pizzahutgui.CLASS.AppGlobal;
import pizzahutgui.CLASS.Location;
import pizzahutgui.CLASS.Category;
import pizzahutgui.CLASS.Cheese;
import pizzahutgui.CLASS.Crust;
import pizzahutgui.CLASS.Pizza;
import pizzahutgui.CLASS.Sauce;


/**
 *
 * @author nush
 */
class PizzaOrder extends JFrame implements ItemListener,ActionListener{
    static JFrame pizzaorderframe;
    static JComboBox categoryList,pizzaList,crustList,sauceList,cheeseList;
    static JLabel categoryLable,pizzaLable,crustLable,sauceLable,cheeseLable,pizzaPriceLable,crustPriceLable,saucePriceLable,cheesePriceLable;
    static JTextField nameInput, emailInput;
    static JButton enterButton;
    boolean categoryChanaged = false;
    JPanel panel = new JPanel(null);
    Pizza pizza = new Pizza();
    Category category = new Category();   
    Sauce sauce = new Sauce();      
    Crust crust = new Crust();  
    Cheese cheese = new Cheese();
    
    
    public void PizzaOrder() {
        pizzaorderframe = new JFrame("Pizza Hut");        
        pizzaorderframe.getContentPane();        
        Dimension screenSize = new Dimension(500, 400);
        pizzaorderframe.setPreferredSize(screenSize);                     
        
        PizzaOrder s = new PizzaOrder();

        //Start Set Category
            categoryLable = new JLabel("Select Category ");     
            categoryLable.setBounds(20, 20, 200,50);
            panel.add(categoryLable);
            categoryList = category.CategoryCombo();
            categoryList.setName("catgoryList");
            categoryList.setBounds(150, 20, 200,50);  
            panel.add(categoryList);  
            categoryList.addItemListener(this);
        //End Category  
            
        //Start Set Pizza 
            pizzaLable = new JLabel("Select Pizza ");   
            pizzaLable.setBounds(20, 60, 200, 40);
            panel.add(pizzaLable);              
            pizzaList = new JComboBox();
            pizzaList.setName("pizzaList");
            pizzaList.setBounds(150, 60, 200, 50);
            pizzaList.setEnabled(false);
            panel.add(pizzaList);   
            pizzaPriceLable = new JLabel("0:00");
            pizzaList.addItemListener(this);
        //End Pizza
        
        //Start Set Sauce
            sauceLable= new JLabel("Select Sauce ");   
            sauceLable.setBounds(20, 100, 200, 40);
            panel.add(sauceLable);
            sauceList = sauce.SauceCombo();
            sauceList.setName("sauceList");
            sauceList.setBounds(150, 100, 200, 50);
            sauceList.setEnabled(false);
            panel.add(sauceList);  
            saucePriceLable = new JLabel("0:00");            
            sauceList.addItemListener(this);            
        //End Sauce
            
        //Start Set Crust
            crustLable= new JLabel("Select Crust ");
            crustLable.setBounds(20, 140, 200, 40);
            panel.add(crustLable);
            crustList = crust.CrustCombo();
            crustList.setName("crustList");
            crustList.setBounds(150, 140, 200, 50);
            crustList.setEnabled(false);
            panel.add(crustList);  
            crustPriceLable = new JLabel("0:00");            
            crustList.addItemListener(this);
        //End Crust
        
        //Start Set Cheese
            cheeseLable= new JLabel("Select Cheese ");
            cheeseLable.setBounds(20, 180, 200, 40);
            panel.add(cheeseLable);
            cheeseList = cheese.CheeseCombo();
            cheeseList.setName("cheeseList");
            cheeseList.setBounds(150, 180, 200, 50);
            cheeseList.setEnabled(false);
            panel.add(cheeseList);
            cheesePriceLable = new JLabel("0:00");
            cheeseList.addItemListener(this);
        //End Cheese
        
        
            
        //Start Set Button
            enterButton = new JButton("Enter");    
            enterButton.setBounds(200, 250, 80, 40);
            enterButton.setEnabled(false);
            panel.add(enterButton); 
        //End Button


        //
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            pizzaorderframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            pizzaorderframe.add(panel);
            pizzaorderframe.setSize(700, 400);
            pizzaorderframe.setVisible(true);
            
            enterButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){                    
                    try {
                        FinalScreen finalscreen = new FinalScreen();
                        finalscreen.FinishProcess();
                        pizzaorderframe.setVisible(false);
                    } catch (SQLException ex) {
                        Logger.getLogger(PizzaOrder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                }
            });
            
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Category category = new Category(); 
        JComboBox combo = (JComboBox) e.getSource(); 
        
        if(e.getStateChange() == ItemEvent.SELECTED) {
            String selectedCombo = combo.getName().toString();
            //String selectedCombo = combo.getSelectedIndex();  
            switch (selectedCombo) {
                case "catgoryList":                   
                    category.setName(e.getItem().toString());
                    AppGlobal.category = e.getItem().toString();
                    pizzaList.removeAllItems();
                    for(String s:pizza.PizzaListByCat(e.getItem().toString())){
                        pizzaList.addItem(s);                
                    } 
                    pizzaList.setEnabled(true);
                    sauceList.setEnabled(true);
                    crustList.setEnabled(true);                
                    cheeseList.setEnabled(true);
                    enterButton.setEnabled(true);
                case "pizzaList":                   
                    pizzaPriceLable.setBounds(400,60, 200, 50); 
                    ArrayList<Pizza> pizzas = new ArrayList<>();
                    pizzas = pizza.PizzaPriceIdByNameList(e.getItem().toString());                    
                    for (int i = 0; i < pizzas.size(); i++) { 
                        pizzaPriceLable.setText("$ "+pizzas.get(i).getPrice().toString());
                        AppGlobal.pizzaPrice = pizzas.get(i).getPrice().toString();
                        AppGlobal.pizza = pizzas.get(i).getName().toString();
                        AppGlobal.pizzaId = String.valueOf(pizzas.get(i).getId());
                        //System.out.println(pizzas.get(i).getPrice().toString());
                    }
                    
                    panel.add(pizzaPriceLable); 
                case "cheeseList": 
                    ArrayList<Cheese> cheeses = new ArrayList<>();
                    cheeses = cheese.CheesePriceIdByNameList(e.getItem().toString());
                    for (int i = 0; i < cheeses.size(); i++) { 
                        cheesePriceLable.setText("$ "+cheeses.get(i).getPrice().toString());
                        AppGlobal.cheesePrice = cheeses.get(i).getPrice().toString();
                        AppGlobal.cheeseId = String.valueOf(cheeses.get(i).getId());
                        AppGlobal.cheese = cheeses.get(i).getName().toString();
                    }
                    cheesePriceLable.setBounds(400,180, 200, 50);
                    
                    panel.add(cheesePriceLable); 
                case "crustList":   
                    ArrayList<Crust> crusts = new ArrayList<>();
                    crusts = crust.CrustPriceIdByNameList(e.getItem().toString());
                    for (int i = 0; i < crusts.size(); i++) { 
                        crustPriceLable.setText("$ "+crusts.get(i).getPrice().toString());
                        AppGlobal.crustId = String.valueOf(crusts.get(i).getId());
                        AppGlobal.crust = crusts.get(i).getName().toString();
                        AppGlobal.crustPrice = crusts.get(i).getPrice().toString();
                    }
                    crustPriceLable.setBounds(400,140, 200, 50);
                    
                    panel.add(crustPriceLable);                          
                case "sauceList":                    
                    saucePriceLable.setBounds(400,100, 200, 50);
                    ArrayList<Sauce> sauces = new ArrayList<>();
                    sauces = sauce.SaucePriceIdByNameList(e.getItem().toString());
                    for (int i = 0; i < sauces.size(); i++) { 
                        saucePriceLable.setText("$ "+sauces.get(i).getPrice().toString());
                        AppGlobal.sauceId = String.valueOf(sauces.get(i).getId());
                        AppGlobal.sauce = sauces.get(i).getName().toString();
                        AppGlobal.saucePrice = sauces.get(i).getPrice().toString();
                    }  
                    
                    panel.add(saucePriceLable);                     
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
