package com.chnu.crossplatformprogramming.dbapp.repository;

import com.chnu.crossplatformprogramming.dbapp.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setTitle(rs.getString("title"));
        product.setCost(rs.getDouble("cost"));
        product.setCount(rs.getInt("count"));
        product.setShopTitle(rs.getString("shop_title"));
        product.setShopAddress(rs.getString("shop_address"));
        return product;
    }
}