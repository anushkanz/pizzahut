package com.weihua.services;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.weihua.HibernateFac.HibernateUtils;

import com.weihua.utils.BaseLog;
import com.weihua.utils.MD5;
import com.weihua.model.Users;

public class UserServiceImpl extends BaseLog implements UserService {

    @Override
    public boolean addUser(Users user) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            MD5 md5 = new MD5();
            //Encrypt password with MD5 
            user.setPassword(md5.getMD5ofStr(user.getPassword()));
            session.save(user);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - addUser!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Users userLogin(String loginName, String loginPwd) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Users user = null;
        //Transfer password to MD5
        MD5 md5 = new MD5();
        loginPwd = md5.getMD5ofStr(loginPwd);
        try {
            String hql = "select a from Users as a where a.loginName =:loginName and a.password =:password";
            Query query = session.createQuery(hql);
            query.setString("loginName", loginName);
            query.setString("password", loginPwd);
            query.setMaxResults(1);
            tx = session.beginTransaction();
            user = (Users) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - userLogin!");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Users> browseUser() {
        List<Users> userList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Users as u order by u.id";
            Query query = session.createQuery(hql);
            userList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - browseUser!");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean delUser(int userid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            Users user = (Users) session.load(Users.class, userid);
            session.delete(user);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - delUser!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Users loadUser(int userid) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Users user = null;
        try {
            tx = session.beginTransaction();
            user = (Users) session.load(Users.class, userid);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - loadUser!");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(Users user) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean status = false;
        try {
            tx = session.beginTransaction();
            //session.update(user);
            session.merge(user);
            tx.commit();
            status = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - updateUser!");
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Users> searchUserByName(String userName) {
        List<Users> userList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Users as u where u.userName like ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, "%" + userName + "%");
            userList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - browseUser!");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public List<Users> retrieveUserByRole(int adminType) {
        List<Users> userList = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Users as u where u.adminType=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, adminType);
            userList = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            logger.info("ERROR Occurs in UserServiceImpl - retrieveUserByRole!");
            e.printStackTrace();
        }
        return userList;
    }

}
