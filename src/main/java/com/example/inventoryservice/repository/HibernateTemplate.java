package com.example.inventoryservice.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateTemplate<T>{

    @Autowired
    protected SessionFactory sessionFactory;

    public synchronized Session open() {
        return sessionFactory.openSession();
    }

    public void close(Session sess){
        sess.close();
    }

    public synchronized Transaction getTrans(Session sess){
        return sess.beginTransaction();
    }

    public synchronized void close(boolean success, Session sess, Transaction trans) {
        if (success)
            trans.commit();
        else
            trans.rollback();
        sess.close();
    }
    public boolean saveData(Object object) throws HibernateException {
        //sess = sessionFactory.openSession();
        Session sess = open();
        Transaction trans = getTrans(sess);
        boolean isSuccess = false;
        try {
            //trans = sess.beginTransaction();
            sess.saveOrUpdate(object);
            //trans.commit();
            isSuccess = true;

        } catch (HibernateException e) {
            throw e;
        } finally {
            // sess.flush();
            // sess.clear();
            //sess.close();
            close(isSuccess, sess, trans);
        }
        return isSuccess;
    }


}
