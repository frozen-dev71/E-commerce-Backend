package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.repositories.AdminSessionDAO;
import com.ecommerce.Ecommerce.services.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private AdminSessionDAO adminSessionDAO;

    //	adding new product in database
    @PostMapping("admin/products/{adminkey}")
    public ResponseEntity<Product> addProductHandler(@PathVariable("adminkey") String key,
                                                     @Valid @RequestBody Product product) throws ProductNotFoundException, AdminException {

        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {
            Product sObj = productService.addProduct(product);
            return new ResponseEntity<Product>(sObj, HttpStatus.CREATED);
        }
    }

    // updating existing product details

    @PutMapping("admin/products/{adminkey}")
    public ResponseEntity<Product> updateProductHandler(@Valid @RequestBody Product product,
                                                        @PathVariable("adminkey") String key) throws ProductNotFoundException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {
            Product sObj = productService.updateProduct(product);

            return new ResponseEntity<Product>(sObj, HttpStatus.ACCEPTED);
        }

    }

    //	getting all products details from database

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsHandler() throws ProductNotFoundException {

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    // delete existing product by ProductId

    @DeleteMapping("admin/products/{id}/{adminkey}")
    public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("id") Integer productId,
                                                            @PathVariable("adminkey") String key) throws ProductNotFoundException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            Product product = productService.deleteProductById(productId);

            return new ResponseEntity<Product>(product, HttpStatus.OK);

        }
    }

    // view product by ProductId

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("id") Integer productId)
            throws ProductNotFoundException {

        Product product = productService.viewProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    // view Product by Product name

    @GetMapping("/product/{name}")
    public ResponseEntity<List<Product>> viewProductByProductNameHandler(@PathVariable("name") String name)
            throws ProductNotFoundException {

        List<Product> list = productService.viewProductByName(name);

        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

    }

    // set new Quantity of product

    @PutMapping("admin/setProductQuantity/{id}/{quantity}/{adminkey}")
    public ResponseEntity<Product> setProductQuantityByProductIdHandler(@PathVariable("adminkey") String key,
                                                                        @PathVariable("id") Integer productid, @PathVariable("quantity") Integer quantity)
            throws ProductNotFoundException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);
        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            Product product = productService.changeQuantityOfProductByProductId(productid, quantity);

            return new ResponseEntity<Product>(product, HttpStatus.OK);

        }
    }

    // set new Price of product

    @PutMapping("admin/setProductPrice/{id}/{price}/{adminkey}")
    public ResponseEntity<Product> setProductPriceByProductIdHandler(@PathVariable("adminkey") String key,
                                                                     @PathVariable("id") Integer productId, @PathVariable("price") Double price)
            throws ProductNotFoundException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);
        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            Product product = productService.changePriceOfProductByProductId(productId, price);

            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }
    }


}
