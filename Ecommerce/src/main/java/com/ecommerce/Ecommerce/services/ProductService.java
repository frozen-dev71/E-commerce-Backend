package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product) throws ProductNotFoundException;

    public Product updateProduct(Product product) throws ProductNotFoundException;

    public List<Product> getAllProducts() throws ProductNotFoundException;

    public Product deleteProductById(Integer productId) throws ProductNotFoundException;

    public Product viewProductById(Integer productId) throws ProductNotFoundException;

    public List<Product> viewProductByName(String name) throws ProductNotFoundException;

    public Product changeQuantityOfProductByProductId(Integer productId, Integer newQuantity)
            throws ProductNotFoundException;

    public Product changePriceOfProductByProductId(Integer productId, Double newPrice) throws ProductNotFoundException;

}
