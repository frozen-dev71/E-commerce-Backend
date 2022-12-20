package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.exceptions.InsufficientQuantityException;
import com.ecommerce.Ecommerce.exceptions.OrderException;
import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.CartDTO;
import com.ecommerce.Ecommerce.models.Orders;

public interface OrderService {
    public Orders buyProductByProductId(String sessionId, Integer productId, String productName, Integer quantity)
            throws CustomerException, ProductNotFoundException, InsufficientQuantityException;

    public CartDTO visitYourCart(String customerKey) throws CustomerException, OrderException;

    public String paymentAmount(String customerKey, Double amount) throws CustomerException, OrderException;

    public Orders deleteProductByOrderId(String customerKey, Integer orderId) throws CustomerException, OrderException;
}
