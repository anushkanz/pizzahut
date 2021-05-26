package com.weihua.services;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.weihua.HibernateFac.HibernateUtils;
import com.weihua.utils.BaseLog;
import com.weihua.model.Leave;

public class LeaveServiceImpl extends BaseLog implements LeaveService {

    @Override
    public boolean addLeave(Leave leave) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.save(leave);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - addLeave!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Leave> browseLeave() {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as a order by a.id";
            Query query = session.createQuery(hql);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - browseLeave!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public boolean delLeave(int leaveid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            Leave leave = (Leave) session.load(Leave.class, leaveid);
            session.delete(leave);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - delLeave!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Leave loadLeave(int leaveid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Leave leave = null;
        try {
            tx = session.beginTransaction();
            leave = (Leave) session.load(Leave.class, leaveid);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - loadLeave!");
            e.printStackTrace();
        }
        return leave;
    }

    @Override
    public boolean updateLeave(Leave leave) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            session.update(leave);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - updateLeave!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Leave> browseLeaveByUserId(int userId, int firstResult,
            int maxResults) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveUser.id =? order by l.appDate desc";
            Query query = session.createQuery(hql);
            query.setParameter(0, userId);
            leaveList = query.setFirstResult(firstResult).setMaxResults(
                    maxResults).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeaveByUserId!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseLeaveByUserId(int userId) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveUser.id =" + userId;
            Query query = session.createQuery(hql);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeaveByUserId!");
            e.printStackTrace();
        }
        return leaveList;

    }

    @Override
    public List<Leave> browseLeaveByType(int typeId, int firstResult,
            int maxResults) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveType.id =" + typeId;
            Query query = session.createQuery(hql);
            leaveList = query.setFirstResult(firstResult).setMaxResults(
                    maxResults).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeaveByType!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseLeave(int firstResult, int maxResults) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l order by l.appDate desc";
            Query query = session.createQuery(hql);
            leaveList = query.setFirstResult(firstResult).setMaxResults(
                    maxResults).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in LeaveServiceImpl - browseLeave!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseLeaveByDataZone(Date dateFormer, Date dateLatter) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.startDate>=? and endDate<=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, dateFormer);
            query.setParameter(1, dateLatter);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeaveByDataZone!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseOthersWhoLeave(int userId) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Date date = new Date();
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveStatus='Approved' and l.leaveUser.id !=? and l.startDate<=? and l.endDate >=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, userId);
            query.setParameter(1, date);
            query.setParameter(2, date);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseOthersWhoLeave!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseLeavesByStatusAndUser(int userId, String status1,
            String status2) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where (l.leaveStatus=? or l.leaveStatus=?) and l.leaveUser.id=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, status1);
            query.setParameter(1, status2);
            query.setParameter(2, userId);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeavesByStatusAndUser!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browsePendingLeaves(int userId) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveUser.authorizedBy.id="
                    + userId + " and l.leaveStatus='" + "Applied" + "'";
            Query query = session.createQuery(hql);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browsePendingLeaves!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> retrieveLeaveByManager(int userId) {

        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Leave as l where l.leaveUser.authorizedBy.id = ? and (l.leaveStatus='Applied' or l.leaveStatus='Updated')";
            Query query = session.createQuery(hql);
            query.setParameter(0, userId);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeavesByStatusAndUser!");
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public List<Leave> browseOthersWhoLeaveByMonth(int month, int userId) {
        List<Leave> leaveList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Date monthBegin = new Date();
            monthBegin.setMonth(month - 1);
            monthBegin.setDate(1);
            Date monthEnd = new Date();
            monthEnd.setMonth(month - 1);
            monthEnd.setDate(31);
            String hql = "from Leave as l where (l.startDate >=? and l.startDate<=?) or (l.endDate >=? and l.endDate<=?) or (l.startDate <=? and l.endDate >= ?) and l.leaveStatus='Approved' and l.leaveUser.id !=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, monthBegin);
            query.setParameter(1, monthEnd);
            query.setParameter(2, monthBegin);
            query.setParameter(3, monthEnd);
            query.setParameter(4, monthBegin);
            query.setParameter(5, monthEnd);
            query.setParameter(6, userId);
            leaveList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger
                    .info("ERROR Occurs in LeaveServiceImpl - browseLeaveByDataZone!");
            e.printStackTrace();
        }
        return leaveList;
    }

}
