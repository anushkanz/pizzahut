/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzahutgui.CLASS;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author nush
 */
public class Validation {
    
    public Validation() {}
     
    //Numbers validation
    public boolean isNumber(String num1){
        try
        {
            Integer.parseInt(num1);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    
    //In range
    public boolean isNumberRange(int upnumber,int downnumber,String checknumber){
        try
        {
            int start = upnumber;            
            int close = downnumber;            
            int check = Integer.parseInt(checknumber);
            
            if (downnumber <= check && check <= upnumber){
                return true;
            }else{
                return false;
            }
            
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
    //Special Charactors
    public boolean isCharactors(String input){
        
        Pattern my_pattern = Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher my_match = my_pattern.matcher(input);
        boolean check = my_match.find();
        if (check){
            return true;
        }else{
            return false;
        }
    }
    
    //Email validation
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pat = Pattern.compile(ePattern);
        Matcher mat = pat.matcher(email);
        boolean check = mat.find();
        if (check){
            return true;
        }else{
            return false;
        }
    }
    
  

}
