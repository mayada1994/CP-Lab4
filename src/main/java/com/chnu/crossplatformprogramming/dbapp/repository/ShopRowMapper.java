package com.chnu.crossplatformprogramming.dbapp.repository;

import com.chnu.crossplatformprogramming.dbapp.model.Shop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopRowMapper implements RowMapper<Shop> {

    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shop shop = new Shop();
        shop.setId(rs.getInt("id"));
        shop.setTitle(rs.getString("title"));
        shop.setAddress(rs.getString("address"));
        return shop;
    }
}