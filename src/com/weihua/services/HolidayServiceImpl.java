package com.weihua.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.weihua.HibernateFac.HibernateUtils;
import com.weihua.utils.BaseLog;
import com.weihua.model.Holiday;

public class HolidayServiceImpl extends BaseLog implements HolidayService {

    @Override
    public boolean addHoliday(Holiday holiday) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.save(holiday);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - addHoliday!");
            e.printStackTrace();
        }
        return status;
    }

    public List<Holiday> browseHoliday() {
        List<Holiday> holidayList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Holiday as h order by h.id";
            Query query = session.createQuery(hql);
            holidayList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - browseHoliday!");
            e.printStackTrace();
        }
        return holidayList;
    }

    @Override
    public boolean delHoliday(int holidayid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            Holiday holiday = (Holiday) session.load(Holiday.class, holidayid);
            session.delete(holiday);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - delHoliday!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Holiday loadHoliday(int holidayid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Holiday holiday = null;
        try {
            tx = session.beginTransaction();
            holiday = (Holiday) session.load(Holiday.class, holidayid);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - loadHoliday!");
            e.printStackTrace();
        }
        return holiday;
    }

    @Override
    public boolean updateHoliday(Holiday holiday) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.update(holiday);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - updateHoliday!");
            e.printStackTrace();
        }
        return status;
    }

    // Created 2010.6.5
    @Override
    public boolean checkHoliday(Date dateCheck) {
        boolean status = false;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dataCheckStr = df.format(dateCheck);
            String sql = "select * from holiday where holidayday='"
                    + dataCheckStr + "'";
            Query query = session.createSQLQuery(sql);
            if (!query.list().isEmpty()) {
                status = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - checkHoliday!");
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public int getNoHolidays(Date dateFrom, Date dateTo) {
        int noHolidays = 0;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateFromStr = df.format(dateFrom);
            String dateToStr = df.format(dateTo);
            String sql = "select distinct id from holiday where holidayday>='"
                    + dateFromStr + "'" + " and holidayday<='" + dateToStr
                    + "'";
            System.out.println(sql);
            Query query = session.createSQLQuery(sql);
            noHolidays = query.list().size();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in HolidayServiceImpl - checkHoliday!");
            e.printStackTrace();
        }
        return noHolidays;
    }

}
