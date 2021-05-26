
import com.weihua.HibernateFac.HibernateUtils;
import com.weihua.model.Holiday;
import com.weihua.model.LeaveType;
import com.weihua.model.Users;
import com.weihua.services.HolidayService;
import com.weihua.services.HolidayServiceImpl;
import com.weihua.services.LeaveTypeService;
import com.weihua.services.LeaveTypeServiceImpl;
import com.weihua.services.UserService;
import com.weihua.services.UserServiceImpl;
import com.weihua.utils.DatabaseInit;
import com.weihua.utils.MD5;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Weihua Li
 * @note Please be noted that this is only FYI
 */
public class ProgramEntry {

    public static void main(String[] args) {
        exportDatabase();
//        testUsers();
//        testHoliday();
//        testLeaveType();
//        testMD5();

        //DONOT Forget to shutdown
        HibernateUtils.shutdown();
    }

    public static void exportDatabase() {
        /**
         * Read Hibernate XML File Initialize Database
         */
        Configuration cfg = new Configuration().configure();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);

        try {
            DatabaseInit.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HibernateUtils.shutdown();
    }

    public static void testHoliday() {
        HolidayService hs = new HolidayServiceImpl();
        Date LeeWeihuaBirthday = new Date();
        LeeWeihuaBirthday.setDate(26);
        LeeWeihuaBirthday.setMonth(8);
        Holiday holiday = new Holiday(LeeWeihuaBirthday, "Li Weihua's Day", "Good day!");
        hs.addHoliday(holiday);
        List<Holiday> list = hs.browseHoliday();
        for (Iterator<Holiday> iter = list.iterator(); iter.hasNext();) {
            System.out.println(iter.next().getHolidayDesc());
        }
        System.out.println("==================");
        boolean b = hs.checkHoliday(new Date());
        System.out.println(b);
        Date d1 = new Date();
        Date d2 = new Date();
        d1.setDate(4);
        d2.setDate(1);
        d2.setMonth(6);
        int holidayno = hs.getNoHolidays(d1, d2);
        System.out.println(holidayno);

    }

    public static void testUsers() {
        UserService as = new UserServiceImpl();
        try {
            Users u1 = as.loadUser(1);
            System.out.println(u1.getAuthorizedBy().getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //test search
        List<Users> users = as.searchUserByName("tsu");
        for (Iterator<Users> iter = users.iterator(); iter.hasNext();) {
            System.out.println(iter.next().getUserName());
        }

        //test retrieve by role
        List<Users> user2 = as.retrieveUserByRole(1);
        for (Iterator<Users> iter = user2.iterator(); iter.hasNext();) {
            System.out.println(iter.next().getUserName());
        }
    }

    public static void testLeaveType() {
        LeaveTypeService as = new LeaveTypeServiceImpl();
        try {
            List<LeaveType> list = as.browseLeaveType();
            for (Iterator<LeaveType> iter = list.iterator(); iter.hasNext();) {
                LeaveType leaveType = iter.next();
                System.out.println(leaveType.getLeaveName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testMD5() {
        MD5 m = new MD5();
        System.out.println("MD5 Encryption");
        String mytest = "It is a nice day!";
        System.out.println("Original String: " + mytest);
        System.out.println("Encrypted String: " + m.getMD5ofStr(mytest));
    }
}
