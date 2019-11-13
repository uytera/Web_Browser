package com.database.dao;

import com.database.dataSet.UserProfile;
import com.database.executor.Executor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserProfile get(String login) throws HibernateException {
        return (UserProfile) session.get(UserProfile.class, login);
    }

    public String getUserLogin(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return ((UserProfile) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getLogin();
    }

    public String insertUser(String login, String passsword, String email) throws HibernateException {
        return (String) session.save(new UserProfile(login, passsword, email));
    }
}
    /*
    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UserProfile get(String login) {
        try {
            return executor.execQuery("select * from users where login='" + login + "'", result -> {
                result.next();
                return new UserProfile(result.getString(1), result.getString(2), result.getString(3));
            });
        }
        catch(SQLException e){
            return null;
        }
    }

    public void insertUser(String login, String passsword, String email) throws SQLException {
        executor.execUpdate("insert into users (login, passsword, email) values ('" + login + "','"+ passsword + "','"+ email + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (login varchar(256), passsword varchar(256), email varchar(256), primary key (login))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }

}
*/
