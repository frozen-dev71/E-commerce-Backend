package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.exceptions.InsufficientQuantityException;
import com.ecommerce.Ecommerce.exceptions.OrderException;
import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.CartDTO;
import com.ecommerce.Ecommerce.models.CurrentCustomerSession;
import com.ecommerce.Ecommerce.models.Orders;
import com.ecommerce.Ecommerce.repositories.CustomerSessionDao;
import com.ecommerce.Ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerSessionDao customerSessionDao;

    @GetMapping("/buyProduct/{customerKey}/{productId}/{productName}/{quantity}")
    public ResponseEntity<Orders> buyProductByProductIdHandler(@PathVariable("customerKey") String key,
                                                               @PathVariable("productId") Integer productId, @PathVariable("productName") String productName, @PathVariable("quantity") Integer quantity)
            throws CustomerException, ProductNotFoundException, InsufficientQuantityException {

        CurrentCustomerSession ccs = customerSessionDao.findByUuid(key);

        if (ccs == null) {

            throw new CustomerException("No customer exist with this key");
        } else {

            Orders order = orderService.buyProductByProductId(key, productId,productName, quantity);

            return new ResponseEntity<Orders>(order, HttpStatus.OK);
        }
    }

    @GetMapping("/{customerKey}")
    public ResponseEntity<CartDTO> visitYourCartHandler(@PathVariable("customerKey") String key)
            throws OrderException, CustomerException {

        CurrentCustomerSession ccs = customerSessionDao.findByUuid(key);
        if (ccs == null) {
            throw new CustomerException("No customer exist with this key");
        } else {

            CartDTO cartdto = orderService.visitYourCart(key);
            return new ResponseEntity<CartDTO>(cartdto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{customerKey}/{amount}")
    public ResponseEntity<String> payYourAmount(@PathVariable("customerKey") String key,
                                                @PathVariable("amount") Double amount) throws OrderException, CustomerException {

        CurrentCustomerSession css = customerSessionDao.findByUuid(key);

        if (css == null) {

            throw new CustomerException("No customer exists with this Key");
        } else {

            String string = orderService.paymentAmount(key, amount);

            return new ResponseEntity<String>(string, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{customerKey}/{orderId}")
    public ResponseEntity<Orders> deleteProductByOrderIdHandler(@PathVariable("customerKey") String key,
                                                                @PathVariable("orderId") Integer orderId) throws CustomerException, OrderException {

        CurrentCustomerSession ccs = customerSessionDao.findByUuid(key);

        if (ccs == null) {
            throw new CustomerException("No customer exist with this key");
        } else {
            Orders order = orderService.deleteProductByOrderId(key, orderId);
            return new ResponseEntity<Orders>(order, HttpStatus.OK);
        }

    }
}
