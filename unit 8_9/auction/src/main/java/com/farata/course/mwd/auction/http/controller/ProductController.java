package com.farata.course.mwd.auction.http.controller;

import com.farata.course.mwd.auction.dao.ProductDao;
import com.farata.course.mwd.auction.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductDao productDao;

  @RequestMapping(method = RequestMethod.GET)
  public List<Product> getProducts() {
    return productDao.getAll();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public Product getProductById(@PathVariable long id) {
    return productDao.getById(id);
  }
}
