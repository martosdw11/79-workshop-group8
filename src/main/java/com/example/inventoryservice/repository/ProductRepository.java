package com.example.inventoryservice.repository;

import com.example.inventoryservice.db.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepository extends HibernateTemplate<Product>{

    public List<Product> getAll() {
        Session sess = open();
        try {
            Query query = sess.createQuery("FROM Product");
            return query.getResultList();
        } finally {
            close(sess);
        }
    }

}
