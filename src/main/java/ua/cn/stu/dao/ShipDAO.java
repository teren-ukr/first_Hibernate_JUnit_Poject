package ua.cn.stu.dao;

import org.hibernate.Session;
import ua.cn.stu.domain.Product;
import ua.cn.stu.domain.Ship;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class ShipDAO extends GenericDAO<Ship> {

    public ShipDAO(Session session) {
        super(session, Ship.class);
    }

    public List<Ship> getProductsByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Ship> criteria = builder.createQuery(Ship.class);
        Root<Ship> root = criteria.from(Ship.class);
        criteria.select(root).where(builder.equal(root.get("name"), name));
        return getSession().createQuery(criteria).getResultList();
    }



}
