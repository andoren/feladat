package service.impl;

import core.model.Product;
import core.model.User;
import core.service.IProductSerivce;
import dao.impl.MysqlProductDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.dao.IProductDAO;


import java.util.Collection;

@Stateless
public class ProductService implements IProductSerivce {
    @EJB
    private IProductDAO dao;

    public Collection<Product> getProducts() {
        return dao.getProducts();
    }

    public Collection<Product> getProductsByUserId(User user) {
        return dao.getProductsByUserId(user);
    }


    public Collection<Product> getNotAuthorizedProducts() {
        return dao.getNotAuthorizedProducts();
    }

    public Collection<Product> getAuthorizedProducts() {
        return dao.getAuthorizedProducts();
    }

    public Product getProductById(int id) {
        return dao.getProductById(id);
    }

    public Product addProduct(Product newProduct, int userId) {
        return dao.addProduct(newProduct,userId);
    }

    public boolean modifyProduct(Product product) {
        return dao.modifyProduct(product);
    }

    public boolean deleteProductById(int id) {
        return dao.deleteProductById(id);
    }
}