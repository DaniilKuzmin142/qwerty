package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.graph.RootGraph;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;
import org.hibernate.stat.SessionStatistics;

import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Map;



import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
           session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users  (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age SMALLINT)").executeUpdate();
           transaction.commit();

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }



@Override
public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS");
            transaction.commit();
        }catch (Exception exception){
            exception.printStackTrace();
        }

}

@Override
public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName,age);
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь с именем "+ name + "добавлен");

        }catch (Exception exception){
            exception.printStackTrace();
        }

}

@Override
public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null){
                session.delete(user);
            }
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

}

@Override
public List<User> getAllUsers() {
        List<User> users = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            users = session.createQuery("from User", User.class).list();


        }catch (Exception e){
            e.printStackTrace();
        }
    return users;
}

@Override
public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        }catch (Exception exception){
            exception.printStackTrace();
        }

}
}
