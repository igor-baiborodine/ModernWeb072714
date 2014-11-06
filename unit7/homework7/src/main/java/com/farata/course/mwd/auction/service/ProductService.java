package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.Product;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @PostConstruct
    void init() {
        initProducts();
    }

    void init(List<Product> products) {
        this.products = Lists.newArrayList(products);
    }

    private List<Product> products;

    private void initProducts() {

        products = Lists.newArrayListWithExpectedSize(6);
        Random random = new Random(LocalDateTime.now().getHour());

        for (int i = 1; i <= 6; i++) {
            Product product = new Product(i, "Item " + i, "images/0" + i + ".jpg",
                "",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore adipiscing elit. Ut enim.",
                2,
                LocalDateTime.now().plusDays(random.nextInt(10)),
                new BigDecimal(12),
                new BigDecimal(35),
                "123", 5
            );
            products.add(product);
        }
    }

    public List<Product> findAllProducts() {

        return Collections.unmodifiableList(products);
    }

    public List<Product> findProductsByFeatured(boolean featured) {

        List<Product> productsByFeatured = products.stream()
                .filter(product -> product.isFeatured() == featured)
                .collect(Collectors.toList());
        logger.info("Found [{}] products for featured[{}]", productsByFeatured.size(), featured);

        return productsByFeatured;
    }

    public Product findProductById(int id) {

        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getId() == id)
                .collect(Collectors.toList());
        Product product = (filteredProducts.size() == 1) ? filteredProducts.get(0) : null;
        logger.info("Found for id[{}] product[{}]", id, product);

        return product;
    }
}
