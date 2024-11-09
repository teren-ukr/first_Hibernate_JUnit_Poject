package ua.cn.stu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ua.cn.stu.domain.Product;

public class HibernateDAOFactory {

    //------------------------------------------------------
    private static HibernateDAOFactory instance;
    private ProductDAO productDAO;
    private SessionFactory sessionFactory;

    //------------------------------------------------------
    private HibernateDAOFactory() {
        // Ініціалізація sessionFactory один раз при створенні екземпляра класу
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/shop");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "14881488");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create"); // "update" може бути безпечнішим варіантом
        configuration.setProperty("hibernate.show_sql", "true");

        // Додаємо анотований клас Product
        configuration.addAnnotatedClass(Product.class);

        // Використовуємо ServiceRegistry для побудови SessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }


    //------------------------------------------------------
    public static HibernateDAOFactory getInstance() {
        if (instance == null) {
            instance = new HibernateDAOFactory();
        }
        return instance;
    }

    //------------------------------------------------------
    public Session getSession() {
        return sessionFactory.openSession();
    }

    //------------------------------------------------------
    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAO(getSession());
        }
        return productDAO;
    }
}
