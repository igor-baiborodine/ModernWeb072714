package com.farata.course.mwd.auction.dao;

import com.farata.course.mwd.auction.model.Product;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


public class ProductDao {

  private JdbcTemplate jdbcTemplate;

  public ProductDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Product getById(long productId) {
    return jdbcTemplate.queryForObject("SELECT * FROM PRODUCT WHERE PRODUCT_ID=?",
        // Spring implements Java 8 Functional interfaces for common patterns.
        new Object[] { productId }, ProductDao::mapToProduct);
  }

  public List<Product> getAll() {
    return jdbcTemplate.<Product> query("SELECT * FROM PRODUCT", ProductDao::mapToProduct);
  }

  private static Product mapToProduct(ResultSet resultSet, int rowNum) throws SQLException {
    Product p = new Product();
    p.setAuctionEndTime(resultSet.getDate("AUCTION_END_TIME"));
    p.setDescription(resultSet.getString("DESCRIPTION"));
    p.setMinimalPrice(resultSet.getBigDecimal("MINIMAL_PRICE"));
    p.setId(resultSet.getLong("PRODUCT_ID"));
    p.setQuantity(resultSet.getInt("QUANTITY"));
    p.setReservedPrice(resultSet.getBigDecimal("RESERVED_PRICE"));
    p.setThumb(resultSet.getString("THUMB"));
    p.setTitle(resultSet.getString("TITLE"));
    p.setWatchers(resultSet.getInt("WATCHERS"));
    return p;
  }
}
