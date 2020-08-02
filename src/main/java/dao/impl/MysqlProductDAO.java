package dao.impl;

import core.model.Product;
import core.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import service.dao.IProductDAO;
import service.dao.IUserDAO;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Collection;
import java.util.List;

@Stateless
public class MysqlProductDAO implements IProductDAO  {
    public MysqlProductDAO(){
        Configuration configuration = new Configuration().configure();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Product.class);

        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
        userDAO=new MysqlUserDAO();
    }

    IUserDAO userDAO;
    protected SessionFactory sessionFactory;

    public Collection<Product> getProducts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        Predicate likeRestriction = builder.and(
                builder.equal(productRoot.get("issold"),0)
        );
        criteria.select(productRoot).where(likeRestriction);

        TypedQuery<Product> query = session.createQuery(criteria);
        List products = query.getResultList();
        session.close();
        return products;
    }

    public Collection<Product> getProductsByUserId(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        Predicate likeRestriction = builder.and(
                builder.equal(productRoot.get("owner").get("id").as(Integer.class),user.getId())
        );
        criteria.select(productRoot).where(likeRestriction);
        TypedQuery<Product> query = session.createQuery(criteria);
        List products = query.getResultList();
        session.close();
        return products;
    }

    public Collection<Product> getNotAuthorizedProducts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        Predicate likeRestriction = builder.and(
                builder.equal(productRoot.get("isaccapted"),0)
        );
        criteria.select(productRoot).where(likeRestriction);

        TypedQuery<Product> query = session.createQuery(criteria);
        List products = query.getResultList();
        session.close();
        return products;
    }

    public Collection<Product> getAuthorizedProducts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        Predicate likeRestriction = builder.and(
                builder.equal(productRoot.get("isaccapted"),1)
        );
        criteria.select(productRoot).where(likeRestriction);

        TypedQuery<Product> query = session.createQuery(criteria);
        List products = query.getResultList();
        session.close();
        return products;
    }

    public Product getProductById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class,id);
        session.close();
        return product;
    }

    public Product addProduct(Product newProduct) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newProduct);
        session.getTransaction().commit();
        session.close();
        return newProduct;
    }

    public boolean modifyProduct(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteProductById(int id) {
        Product product = new Product();
        product.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
