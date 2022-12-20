package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.Ecommerce.models.Product;
import com.ecommerce.Ecommerce.repositories.ProductRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepositoryDAO productRepositoryDAO;

    @Override
    public Product addProduct(Product product) throws ProductNotFoundException {
        if (productRepositoryDAO.findById(product.getProductId()).isPresent()) {
            throw new ProductNotFoundException("Product already exist with this ProductId");
        } else {
            Product obj = productRepositoryDAO.save(product);
            return obj;
        }
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        Optional<Product> opt = productRepositoryDAO.findById(product.getProductId());
        if (opt.isPresent()) {
            return productRepositoryDAO.save(product);
        } else {
            throw new ProductNotFoundException("No Product found with this ProductId");
        }
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> list = productRepositoryDAO.findAll();
        if (list.isEmpty()) {
            throw new ProductNotFoundException("No product found.");
        } else {
            return list;
        }
    }

    @Override
    public Product deleteProductById(Integer productId) throws ProductNotFoundException {
        Optional<Product> opt = productRepositoryDAO.findById(productId);
        if (opt.isPresent()) {
            Product product = opt.get();
            productRepositoryDAO.delete(product);
            return product;
        } else {
            throw new ProductNotFoundException("No product present with this ProductId");
        }
    }

    @Override
    public Product viewProductById(Integer productId) throws ProductNotFoundException {
        Optional<Product> product = productRepositoryDAO.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException("Product does not exist with this ProductId");
        }
    }

    @Override
    public List<Product> viewProductByName(String name) throws ProductNotFoundException {
        List<Product> list = productRepositoryDAO.findByProductName(name);
        if (list.isEmpty()) {
            throw new ProductNotFoundException("Product does not exist with this Name");
        } else {
            return list;
        }
    }

    @Override
    public Product changeQuantityOfProductByProductId(Integer productId, Integer newQuantity) throws ProductNotFoundException {
        Optional<Product> opt = productRepositoryDAO.findById(productId);
        if (opt.isPresent()) {
            Product product = opt.get();
            product.setStock(newQuantity);
            productRepositoryDAO.save(product);
            return product;
        } else {
            throw new ProductNotFoundException("Product does not exist with this ProductId");
        }
    }

    @Override
    public Product changePriceOfProductByProductId(Integer productId, Double newPrice) throws ProductNotFoundException {
        Optional<Product> opt = productRepositoryDAO.findById(productId);

        if (opt.isPresent()) {
            Product product = opt.get();
            product.setCost(newPrice);
            productRepositoryDAO.save(product);
            return product;
        } else {

            throw new ProductNotFoundException("Product does not exist with this ProductId");
        }
    }
}
