package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.exceptions.InsufficientQuantityException;
import com.ecommerce.Ecommerce.exceptions.OrderException;
import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.CartDTO;
import com.ecommerce.Ecommerce.models.Orders;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.repositories.OrderRepositoryDAO;
import com.ecommerce.Ecommerce.repositories.ProductRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepositoryDAO orderRepositoryDAO;

    @Autowired
    private ProductRepositoryDAO productRepositoryDAO;

    @Override
    public Orders buyProductByProductId(String sessionId, Integer productId, String productName, Integer quantity) throws CustomerException, ProductNotFoundException, InsufficientQuantityException {
        Optional<Product> opt = productRepositoryDAO.findById(productId);

        if (opt.isPresent()) {

            Product product = opt.get();
            if (product.getStock() < quantity) {

                throw new InsufficientQuantityException("Product Stock is less than your quantity");
            } else {

                Orders order = new Orders();
                order.setDateAndTime(LocalDateTime.now());
                order.setProductId(productId);
                order.setProductName(productName);
                order.setQuantity(quantity);
                order.setSessionId(sessionId);
                order.setTotalCost(quantity * product.getCost());

                Orders obj = orderRepositoryDAO.save(order);

                product.setStock(product.getStock() - quantity);
                productRepositoryDAO.save(product);
                return obj;
            }

        } else {

            throw new ProductNotFoundException("No product found with this product Id");
        }
    }

    @Override
    public CartDTO visitYourCart(String customerKey) throws CustomerException, OrderException {
        List<Orders> list = orderRepositoryDAO.findBySessionId(customerKey);

        if (list.isEmpty()) {
            throw new OrderException("You do not have any item in your cart");
        }

        Double sum = 0.00;

        for (Orders order : list) {

            sum += order.getTotalCost();

        }

        CartDTO cart = new CartDTO();
        cart.setList(list);
        cart.setTotalBill(sum);

        return cart;
    }

    @Override
    public String paymentAmount(String customerKey, Double amount) throws CustomerException, OrderException {
        List<Orders> list = orderRepositoryDAO.findBySessionId(customerKey);
        Double sum = 0.00;

        for (Orders order : list) {

            sum += order.getTotalCost();
        }

        int x = Double.compare(amount, sum);

        if (x == 0) {
            orderRepositoryDAO.deleteAll();
            return "Payment Successfully done";

        } else {
            throw new OrderException("Amount should be equal to : " + sum);
        }
    }

    @Override
    public Orders deleteProductByOrderId(String customerKey, Integer orderId) throws CustomerException, OrderException {

        Optional<Orders> opt = orderRepositoryDAO.findById(orderId);

        if (opt.isPresent()) {
            orderRepositoryDAO.deleteById(orderId);
            return opt.get();

        } else {
            throw new OrderException("No order Present with this Order Id");
        }
    }
}
