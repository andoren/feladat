package core.service;

import core.model.*;

import java.util.Collection;

public interface IProductSerivce {
    Collection<Product> getProducts();
    Collection<Product> getProductsByUserId(User user);
    Collection<Product> getNotAuthorizedProducts();
    Collection<Product> getAuthorizedProducts();
    Product getProductById(int id);
    Product addProduct(Product newProduct);
    boolean modifyProduct(Product product);
    boolean deleteProductById(int id);
}
