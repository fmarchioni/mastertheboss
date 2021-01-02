package com.packt.quarkus.chapter5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;


@ApplicationScoped
public class OrderRepository {

    @Inject
    EntityManager entityManager;

    public List<Orders> findAll(Long customerId) {

        return  (List<Orders>) entityManager.createNamedQuery("Orders.findAll")
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public Orders findOrderById(Long id) {

        Orders order = entityManager.find(Orders.class, id);
        if (order == null) {
            throw new WebApplicationException("Order with id of " + id + " does not exist.", 404);
        }
        return order;
    }
    @Transactional
    public void updateOrder(Orders order) {
        Orders orderToUpdate = findOrderById(order.getId());
        orderToUpdate.setItem(order.getItem());
        orderToUpdate.setPrice(order.getPrice());
    }
    @Transactional
    public void createOrder(Orders order, Customer c) {
        order.setCustomer(c);
        entityManager.persist(order);

    }
    @Transactional
    public void deleteOrder(Long orderId) {
        Orders o = findOrderById(orderId);
        entityManager.remove(o);
    }
}
