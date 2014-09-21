package com.farata.course.mwd.auction.data;

import com.farata.course.mwd.auction.entity.Product;

import java.util.List;

/**
 * TODO
 *
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 * @since 9/20/14
 */
public interface IDataEngine {
    List<Product> findAllProducts();

    List<Product> findAllFeaturedProducts();

    Product findProductById(int id);
}
