package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.JUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    
    }
    
    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            //session.createSQLQuery("CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NULL,PRIMARY KEY (`id`));");
            session.createSQLQuery("CREATE TABLE user (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NULL,PRIMARY KEY (`id`));").addEntity(User.class).executeUpdate();
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
    }
    
    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.createSQLQuery("DROP TABLE user").executeUpdate();
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            ;
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            
            session.remove(id);
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
    }
    
    @Override
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
       
        try {
           
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
           
            user = session.createQuery("from User").list();
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return user;
    }
    
    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = JUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.createQuery("DELETE FROM User").executeUpdate();
            
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}