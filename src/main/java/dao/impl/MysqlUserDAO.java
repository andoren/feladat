package dao.impl;

import core.model.Product;
import core.model.Role;
import core.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.dao.IUserDAO;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;


public class MysqlUserDAO implements IUserDAO {
    public MysqlUserDAO(){
        Configuration configuration = new Configuration().configure();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Product.class);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());


    }

    private SessionFactory sessionFactory;

    public Collection<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        return query.getResultList();
    }

    public Collection<User> getUsersByRole(Role role) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate likeRestriction = builder.and(
                builder.equal(userRoot.get("role"),role)
        );

        criteria.select(userRoot).where(likeRestriction);

        TypedQuery<User> query = session.createQuery(criteria);

        session.close();
        return query.getResultList();
    }

    public boolean modifyUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.close();
        return user;

    }

    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public boolean deleteUserById(int id) {

        User user = new User();
        user.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    public User logIn(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user ;
        user = (User) session.createQuery("FROM User U WHERE U.username = :userName and U.password = :passWord").setParameter("userName", username).setParameter("passWord",password)
                .uniqueResult();

        if (user != null) {
            user.setPassword("TemporaryPassword#2");
            return user;
        }
        return null;
    }




}