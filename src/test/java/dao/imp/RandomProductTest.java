package dao.imp;

import core.exceptions.*;
import core.model.Product;
import core.model.User;
import dao.impl.MysqlProductDAO;
import dao.impl.MysqlUserDAO;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collection;

public class RandomProductTest {

    MysqlProductDAO dao = new MysqlProductDAO();
    MysqlUserDAO userDAO = new MysqlUserDAO();

    public void getProducts(){
        Collection<Product> products = dao.getProducts();
        for (Product product:products
                ) {
            System.out.println(product.getName());
        }
    }
    @Test
    public void getProductsAuth(){
        Collection<Product> products = dao.getAuthorizedProducts();
        for (Product product:products
                ) {
            System.out.println(product.getName());
        }
    }
    @Test
    public void getProductsNotAuth(){
        Collection<Product> products = dao.getNotAuthorizedProducts();
        for (Product product:products
                ) {
            System.out.println(product.getName());
        }
    }

    public void addProduct() throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidUsernameException, InvalidEmailException, InvalidPassword, InvalidRealnameException, InvalidProductPriceException {
        Product newProduct = new Product();
        newProduct.setName("Hibernate");
        newProduct.setCreated_date(LocalDate.now());
        newProduct.setDescription("Első kapcsolótáblás hozzáadás");
        newProduct.setImagepath("C:\\imagePAth");
        newProduct.setIsAccapted(false);
        newProduct.setIsSold(false);
        newProduct.setPrice(10000);
        Product meow = dao.addProduct(newProduct,2);
    }
    @Test
    public void getUserProducts(){
        User user = userDAO.getUserById(2);
        Collection<Product> products = dao.getProductsByUserId(user);
        for (Product product:products
                ) {
            System.out.println(product.getName());
        }
    }
}
