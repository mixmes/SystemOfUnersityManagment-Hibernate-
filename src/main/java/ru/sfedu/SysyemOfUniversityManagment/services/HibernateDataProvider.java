package ru.sfedu.SysyemOfUniversityManagment.services;

import jakarta.persistence.OptimisticLockException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.SysyemOfUniversityManagment.utils.HibernateUtil;

import java.util.Optional;

public class HibernateDataProvider {
    private Logger logger = LogManager.getLogger(HibernateDataProvider.class);
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private long id;
    private Session session;
    public long saveObject(Object object){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (long) session.save(object);
        transaction.commit();
        session.close();
        logger.info("Record was saved");
        return id;
    }
    public void updateObject(Object object){
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        logger.info("Record was updated");
        session.close();
    }
    public<T> Optional<T> getObjectById(long id, Class<T> tClass) throws Exception {
        Optional<T> bean ;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            bean = Optional.ofNullable(session.get(tClass, id));
            transaction.commit();
            session.close();
            logger.info("Record of " +tClass.getName()+" class with this id "+id+" was found");
        }catch (HibernateException hibExc){
            bean = Optional.ofNullable(null);
            logger.error("Record of " +tClass.getName()+" class with this id "+id+" wasn't found");
            throw new Exception("Record wasn't found");
        }
        return bean;
    }
    public void deleteObject(Object object) throws Exception {
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
            session.close();
            logger.info("Record was deleted");
        }catch (OptimisticLockException hibEx){
            logger.error("Record wasn't found");
            throw new Exception("Record wasn't found");
        }

    }
}
