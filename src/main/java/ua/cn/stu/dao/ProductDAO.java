package ua.cn.stu.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import ua.cn.stu.domain.Product;



public class ProductDAO extends GenericDAO<Product> {

    public ProductDAO(Session session) {
        super(session, Product.class);
    }

    public List<Product> getProductsByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root).where(builder.equal(root.get("name"), name));
        return getSession().createQuery(criteria).getResultList();
    }

}


