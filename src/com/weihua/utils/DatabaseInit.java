package com.weihua.utils;

import com.weihua.model.Holiday;
import com.weihua.model.LeaveType;
import com.weihua.model.Leave;
import com.weihua.model.Users;
import com.weihua.services.HolidayService;
import com.weihua.services.HolidayServiceImpl;
import com.weihua.services.LeaveTypeService;
import com.weihua.services.LeaveTypeServiceImpl;
import com.weihua.services.LeaveServiceImpl;
import com.weihua.services.UserServiceImpl;
import com.weihua.services.UserService;
import com.weihua.services.LeaveService;
import java.util.Date;

public class DatabaseInit {

    public static void initData() throws Exception {
        /**
         * Initialize User Table *
         */
        UserService us = new UserServiceImpl();

        Users u1 = new Users("Weihua Li", "liweihua", "mark", 0, 0, "Administrative", "LiWeihua.jpg", null);
        Users u2 = new Users("Tsu Swe", "tsuswe", "tsuswe", 1, 3, "Professional", "TsuSwe.jpg", null);
        Users u3 = new Users("Kailash", "kailash", "kailash", 0, 2, "Administrative", "kailash.png", null);
        Users u4 = new Users("Wong", "wong", "wong", 1, 1, "Administrative", "Wong.jpg", null);
        Users u5 = new Users("Padmini", "padmini", "padmini", 2, 2, "Professional", "Padmini.jpg", null);
        Users u6 = new Users("Zhou Ming", "zhouming", "zhouming", 2, 0, "Professional", "Zhouming.jpg", null);

        //In order to avoid:object references an unsaved transient instantiation
        us.addUser(u1);
        us.addUser(u2);
        us.addUser(u3);
        us.addUser(u4);
        us.addUser(u5);
        us.addUser(u6);

        u1.setAuthorizedBy(u2);
        u2.setAuthorizedBy(u4);
        u3.setAuthorizedBy(u2);
        u4.setAuthorizedBy(u2);
        u5.setAuthorizedBy(u4);
        u6.setAuthorizedBy(u4);

        us.updateUser(u1);
        us.updateUser(u2);
        us.updateUser(u3);
        us.updateUser(u4);
        us.updateUser(u5);
        us.updateUser(u6);

        /**
         * Initialize LeaveType *
         */
        LeaveType lt1 = new LeaveType("Annual for Admin", "Annual-DESCRIPTION", 14);
        LeaveType lt2 = new LeaveType("Annual for Pro.", "Annual-DESCRIPTION", 18);
        LeaveType lt3 = new LeaveType("Medical", "Medical-DESCRIPTION", 16);
        LeaveType lt4 = new LeaveType("Conference", "Conference-DESCRIPTION", 0);
        LeaveType lt5 = new LeaveType("Childcare", "Childcare-DESCRIPTION", 3);
        LeaveType lt6 = new LeaveType("Compensation", "Compensation-DESCRIPTION", 0);

        LeaveTypeService lts = new LeaveTypeServiceImpl();
        lts.addLeaveType(lt1);
        lts.addLeaveType(lt2);
        lts.addLeaveType(lt3);
        lts.addLeaveType(lt4);
        lts.addLeaveType(lt5);

        /**
         * Initialize Leave *
         */
        Date date1 = new Date();
        date1.setMonth(4);
        date1.setDate(5);

        Date date2 = new Date();
        date2.setMonth(6);
        date2.setDate(10);

        Date date3 = new Date();
        date3.setMonth(5);
        date3.setDate(7);

        Date date4 = new Date();
        date4.setMonth(6);
        date4.setDate(22);

        Date date5 = new Date();
        date5.setMonth(11);
        date5.setDate(5);

        Leave l1 = new Leave(u1, lt1, "Applied", new Date(), date1, date2, "No reason1", "Pine grove 02-81", "Derek", "");
        Leave l2 = new Leave(u1, lt2, "Applied", new Date(), date1, date3, "No reason2", "Pine grove 02-81", "Derek", "");
        Leave l3 = new Leave(u1, lt3, "Applied", new Date(), date2, date4, "No reason3", "Pine grove 02-81", "Derek", "");
        Leave l4 = new Leave(u2, lt4, "Applied", new Date(), date3, date5, "No reason4", "Pine grove 02-81", "Derek", "");
        Leave l5 = new Leave(u2, lt5, "Applied", new Date(), date1, date4, "No reason5", "Pine grove 02-81", "Derek", "");
        Leave l6 = new Leave(u2, lt4, "Applied", new Date(), date1, date3, "No reason6", "Pine grove 02-81", "Derek", "");
        Leave l7 = new Leave(u3, lt3, "Applied", new Date(), date1, date3, "No reason7", "Pine grove 02-81", "Derek", "");
        Leave l8 = new Leave(u3, lt2, "Applied", new Date(), date1, date2, "No reason8", "Pine grove 02-81", "Derek", "");
        Leave l9 = new Leave(u3, lt6, "Applied", new Date(), date1, date5, "No reason9", "Pine grove 02-81", "Derek", "");
        Leave l10 = new Leave(u1, lt6, "Applied", new Date(), date1, date5, "No reason10", "Pine grove 02-81", "Derek", "");

        LeaveService ls = new LeaveServiceImpl();
        ls.addLeave(l1);
        ls.addLeave(l2);
        ls.addLeave(l3);
        ls.addLeave(l4);
        ls.addLeave(l5);
        ls.addLeave(l6);
        ls.addLeave(l7);
        ls.addLeave(l8);
        ls.addLeave(l9);
        ls.addLeave(l10);

        //Initialize Holiday
        Date date_a = new Date();
        date_a.setMonth(0);
        date_a.setDate(1);
        Holiday h1 = new Holiday(date_a, "New Year's Day", "New Year day, Every one is happy");

        Date date_b = new Date();
        date_b.setMonth(1);
        date_b.setDate(14);
        Holiday h2 = new Holiday(date_b, "Chinese New Year", "Chinese New Year day, Every one is happy");

        Date date_c = new Date();
        date_c.setMonth(1);
        date_c.setDate(15);
        Holiday h3 = new Holiday(date_c, "Chinese New Year", "Chinese New Year day, Every one is happy");
        Date date_d = new Date();
        date_d.setMonth(3);
        date_d.setDate(2);
        Holiday h4 = new Holiday(date_d, "Good Friday", "Everyone is happy, no class!");
        Date date_e = new Date();
        date_e.setMonth(4);
        date_e.setDate(28);
        Holiday h5 = new Holiday(date_e, "Vesak Day", "Everyone is happy, no class!");
        Date date_f = new Date();
        date_f.setMonth(7);
        date_f.setDate(9);
        Holiday h6 = new Holiday(date_f, "National Day", "Everyone is happy, no class!");

        Date date_g = new Date();
        date_g.setMonth(8);
        date_g.setDate(10);
        Holiday h7 = new Holiday(date_g, "Hari Raya Puasa", "Everyone is happy, no class!");

        Date date_h = new Date();
        date_h.setMonth(10);
        date_h.setDate(5);
        Holiday h8 = new Holiday(date_h, "Deepavali", "Everyone is happy, no class!");

        Date date_i = new Date();
        date_i.setMonth(10);
        date_i.setDate(17);
        
        HolidayService hs = new HolidayServiceImpl();
        hs.addHoliday(h1); 
        hs.addHoliday(h2); 
        hs.addHoliday(h3); 
        hs.addHoliday(h4); 
        hs.addHoliday(h5); 
        hs.addHoliday(h6); 
        hs.addHoliday(h7); 
        hs.addHoliday(h8); 

    }

}
