package com.weihua.services;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.weihua.HibernateFac.HibernateUtils;
import com.weihua.utils.BaseLog;
import com.weihua.model.LeaveType;

public class LeaveTypeServiceImpl extends BaseLog implements LeaveTypeService {

    @Override
    public boolean addLeaveType(LeaveType leaveType) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.save(leaveType);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveTypeServiceImpl - addLeaveType!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<LeaveType> browseLeaveType() {
        List<LeaveType> leaveTypeList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from LeaveType as leavetype order by leavetype.id";
            Query query = session.createQuery(hql);
            leaveTypeList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveTypeServiceImpl - browseLeaveType!");
            e.printStackTrace();
        }
        return leaveTypeList;
    }

    @Override
    public boolean delLeaveType(int leaveTypeid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            LeaveType leaveType = (LeaveType) session.load(LeaveType.class, leaveTypeid);
            session.delete(leaveType);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveTypeServiceImpl - delLeaveType!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public LeaveType loadLeaveType(int leaveTypeid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        LeaveType leaveType = null;
        try {
            tx = session.beginTransaction();
            leaveType = (LeaveType) session.load(LeaveType.class, leaveTypeid);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveTypeServiceImpl - loadLeaveType!");
            e.printStackTrace();
        }
        return leaveType;
    }

    @Override
    public boolean updateLeaveType(LeaveType leaveType) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.update(leaveType);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveTypeServiceImpl - updateLeaveType!");
            e.printStackTrace();
        }
        return status;
    }

}
