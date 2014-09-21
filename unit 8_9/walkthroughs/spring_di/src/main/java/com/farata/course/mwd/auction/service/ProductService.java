package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.data.IDataEngine;
import com.farata.course.mwd.auction.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    IDataEngine dataEngine;

    @Autowired
    public void setDataEngine(IDataEngine IDataEngine) {
        this.dataEngine = IDataEngine;
    }

    public List<Product> getAllProducts() {
        final List<Product> allProducts = dataEngine.findAllProducts();
        return allProducts;
    }

    public List<Product> getFeaturedProducts() {
        return dataEngine.findAllFeaturedProducts();
    }

    public Product getProductById(int productId) {
        return dataEngine.findProductById(productId);
    }
}
