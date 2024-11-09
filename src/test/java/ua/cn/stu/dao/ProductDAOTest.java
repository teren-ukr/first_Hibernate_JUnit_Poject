package ua.cn.stu.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.cn.stu.domain.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDAOTest {

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void before() {
        // Create test products before starting test
        product1 = new Product("Test product name 1", "Test product description 1");
        product2 = new Product("Test product name 2", "Test product description 2");
        product3 = new Product("Test product name 3", "Test product description 3");

        // Ensure that the products are created in DB
        HibernateDAOFactory.getInstance().getProductDAO().create(product1);
        HibernateDAOFactory.getInstance().getProductDAO().create(product2);
        HibernateDAOFactory.getInstance().getProductDAO().create(product3);
    }

    @AfterEach
    public void after() {
        // Remove all products after completing test
        List<Product> productList = HibernateDAOFactory.getInstance().getProductDAO().findAll();
        for (Product product : productList) {
            HibernateDAOFactory.getInstance().getProductDAO().delete(product);
        }
    }

    @Test
    public void whenGetAllThenAllShouldBePresent() {
        // Given
        // When
        List<Product> productList = HibernateDAOFactory.getInstance().getProductDAO().findAll();
        // Then
        assertEquals(3, productList.size());

        for (Product product : productList) {
            assertNotNull(product);
            assertNotNull(product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getDescription());
        }
    }

    @Test
    public void whenAddProductThenItShouldBePresentInDb() throws Exception {
        // Given
        Product product = new Product("Test product name", "Test product description");
        // When
        HibernateDAOFactory.getInstance().getProductDAO().create(product);
        // Then
        List<Product> productListAfter = HibernateDAOFactory.getInstance().getProductDAO().findAll();
        assertNotNull(product.getId());
        assertEquals(4, productListAfter.size());
    }

    @Test
    public void whenDeleteProductThenItShouldNotBePresentInDb() throws Exception {
        // Given
        Product product = new Product("Test product name", "Test product description");
        HibernateDAOFactory.getInstance().getProductDAO().create(product);
        // When
        HibernateDAOFactory.getInstance().getProductDAO().delete(product);
        // Then
        List<Product> productListAfter = HibernateDAOFactory.getInstance().getProductDAO().findAll();
        assertEquals(3, productListAfter.size());
        for (Product productEnt : productListAfter) {
            assertNotEquals("Test product name", productEnt.getName());
            assertNotEquals("Test product description", productEnt.getDescription());
        }
    }

    @Test
    public void whenGetAllProductsByNameThenAllWithSuchNameShouldBePresent() {
        // Given
        Product product1 = new Product("Specific product name", "Test product description1");
        Product product2 = new Product("Specific product name", "Test product description2");
        Product product3 = new Product("Specific product name", "Test product description3");

        HibernateDAOFactory.getInstance().getProductDAO().create(product1);
        HibernateDAOFactory.getInstance().getProductDAO().create(product2);
        HibernateDAOFactory.getInstance().getProductDAO().create(product3);

        // When
        List<Product> productListByName = HibernateDAOFactory.getInstance().getProductDAO().getProductsByName("Specific product name");

        // Then
        assertEquals(3, productListByName.size());
        for (Product productEnt : productListByName) {
            assertEquals("Specific product name", productEnt.getName());
        }
    }
}
