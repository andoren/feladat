package service.dao;

import core.model.Product;
import core.model.User;

import java.util.Collection;


public interface IProductDAO {
    Collection<Product> getProducts();
    Collection<Product> getProductsByUserId(User user);
    Collection<Product> getNotAuthorizedProducts();
    Collection<Product> getAuthorizedProducts();
    Product getProductById(int id);
    Product addProduct(Product newProduct);
    boolean modifyProduct(Product product);
    boolean deleteProductById(int id);
}