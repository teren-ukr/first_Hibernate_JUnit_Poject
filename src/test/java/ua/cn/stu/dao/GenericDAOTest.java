package ua.cn.stu.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.cn.stu.domain.Product;
import ua.cn.stu.domain.Ship;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenericDAOTest {
    @Test
    public void test() {
        System.out.println("Hello World");
    }


    private Product product1;
    private Product product2;
    private Product product3;
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;

    @BeforeEach
    public void before() {
        // Create test products before starting test
        product1 = new Product("Test product name 1", "Test product description 1");
        product2 = new Product("Test product name 2", "Test product description 2");
        product3 = new Product("Test product name 3", "Test product description 3");

        ship1 = new Ship("Test ship name 1", "Test ship description 1");
        ship2 = new Ship("Test ship name 2", "Test ship description 2");
        ship3 = new Ship("Test ship name 3", "Test ship description 3");

        // Ensure that the products are created in DB
        HibernateDAOFactory.getInstance().getProductDAO().create(product1);
        HibernateDAOFactory.getInstance().getProductDAO().create(product2);
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product3);


        HibernateDAOFactory.getInstance().getShipDAO().create(ship1);
        HibernateDAOFactory.getInstance().getShipDAO().create(ship2);
        HibernateDAOFactory.getInstance().getDAO(Ship.class).create(ship3);


    }

    @AfterEach
    public void after() {
        // Видалення всіх продуктів після завершення тестів
        tearDownProducts();
        tearDownShips();
    }

    private void tearDownProducts() {
        List<Product> productList = HibernateDAOFactory.getInstance().getDAO(Product.class).findAll();
        for (Product product : productList) {
            HibernateDAOFactory.getInstance().getDAO(Product.class).delete(product);
        }
    }

    private void tearDownShips() {
        List<Ship> shipList = HibernateDAOFactory.getInstance().getDAO(Ship.class).findAll();
        for (Ship ship : shipList) {
            HibernateDAOFactory.getInstance().getDAO(Ship.class).delete(ship);
        }
    }


    // -----------------------------------------------------
    // Тести для Product
    @Test
    public void whenGetAllProductsThenAllShouldBePresent() {
        List<Product> productList = HibernateDAOFactory.getInstance().getDAO(Product.class).findAll();
        assertEquals(3, productList.size());

        for (Product product : productList) {
            assertNotNull(product);
            assertNotNull(product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getDescription());
        }
    }

    @Test
    public void whenAddProductThenItShouldBePresentInDb() {
        Product product = new Product("Test product name", "Test product description");
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product);

        List<Product> productListAfter = HibernateDAOFactory.getInstance().getDAO(Product.class).findAll();
        assertNotNull(product.getId());
        assertEquals(4, productListAfter.size());
    }

    @Test
    public void whenDeleteProductThenItShouldNotBePresentInDb() {
        Product product = new Product("Test product name", "Test product description");
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product);
        HibernateDAOFactory.getInstance().getDAO(Product.class).delete(product);

        List<Product> productListAfter = HibernateDAOFactory.getInstance().getDAO(Product.class).findAll();
        assertEquals(3, productListAfter.size());
    }

    @Test
    public void whenGetAllProductsByNameThenAllWithSuchNameShouldBePresent() {
        Product product1 = new Product("Specific product name", "Description 1");
        Product product2 = new Product("Specific product name", "Description 2");
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product1);
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product2);

        List<Product> productListByName = HibernateDAOFactory.getInstance()
                .getProductDAO()
                .getProductsByName("Specific product name");

        assertEquals(2, productListByName.size());
        for (Product product : productListByName) {
            assertEquals("Specific product name", product.getName());
        }
    }


    //------------------------------------------------------
    // Тести для Ship
    @Test
    public void whenGetAllShipsThenAllShouldBePresent() {
        List<Ship> shipList = HibernateDAOFactory.getInstance().getDAO(Ship.class).findAll();
        assertEquals(3, shipList.size());

        for (Ship ship : shipList) {
            assertNotNull(ship);
            assertNotNull(ship.getId());
            assertNotNull(ship.getCabin());
            assertNotNull(ship.getPassenger());
        }
    }

    @Test
    public void whenAddShipThenItShouldBePresentInDb() {
        Ship ship = new Ship("New cabin", "New passenger");
        HibernateDAOFactory.getInstance().getDAO(Ship.class).create(ship);

        List<Ship> shipListAfter = HibernateDAOFactory.getInstance().getDAO(Ship.class).findAll();
        assertNotNull(ship.getId());
        assertEquals(4, shipListAfter.size());
    }

    @Test
    public void whenDeleteShipThenItShouldNotBePresentInDb() {
        Ship ship = new Ship("Temporary cabin", "Temporary passenger");
        HibernateDAOFactory.getInstance().getDAO(Ship.class).create(ship);
        HibernateDAOFactory.getInstance().getDAO(Ship.class).delete(ship);

        List<Ship> shipListAfter = HibernateDAOFactory.getInstance().getDAO(Ship.class).findAll();
        assertEquals(3, shipListAfter.size());
    }

    @Test
    public void whenGetAllShipsByNameThenAllWithSuchNameShouldBePresent() {
        Product product1 = new Product("Specific ship name", "Description 1");
        Product product2 = new Product("Specific ship name", "Description 2");
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product1);
        HibernateDAOFactory.getInstance().getDAO(Product.class).create(product2);

        List<Product> productListByName = HibernateDAOFactory.getInstance()
                .getProductDAO()
                .getProductsByName("Specific ship name");

        assertEquals(2, productListByName.size());
        for (Product product : productListByName) {
            assertEquals("Specific ship name", product.getName());
        }
    }





    //------------------------------------------------------
    @Test
    public void myMethodForReserch(){
        HibernateDAOFactory factory = HibernateDAOFactory.getInstance();
        ProductDAO manager = new ProductDAO(factory.getSession());

        Product product2 = new Product("Floppa as product1", "Test floppa");
        manager.create(product2);

        List<Product> list = manager.findAll();

        for(Product productEnt : list){
            print(productEnt.getName());
            print(productEnt.getDescription());
        }

    }

    public void print(String str){
        System.out.print("\u001B[35m");
        System.out.println(str);
        System.out.print("\u001B[0m");
    }



}
