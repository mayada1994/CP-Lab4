package com.chnu.crossplatformprogramming.dbapp.repository;

import com.chnu.crossplatformprogramming.dbapp.model.Product;
import com.chnu.crossplatformprogramming.dbapp.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ShopRowMapper shopRowMapper = new ShopRowMapper();

    @Autowired
    public ShopRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveShop(int id, String title, String address) {
        String sql = "INSERT INTO shop (id, title, address) VALUES (:id, :title, :address)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("title", title)
                .addValue("address", address);
        jdbcTemplate.update(sql, parameterSource);
    }

    public List<Shop> getShopById(int shopId) {
        String sql = "SELECT * FROM shop WHERE id = :id";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("id", shopId), shopRowMapper);
    }

    public Integer countOfShops() {
        String sql = "SELECT COUNT(*) FROM shop";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
    }

    public List<Integer> getAllShopIds() {
        String sql = "SELECT id FROM shop";
        return jdbcTemplate.queryForList(sql, new MapSqlParameterSource(), Integer.class);
    }

    public void updateShop(int shopId, String title, String address) {
        String sql = "UPDATE shop SET title = :title, address = :address WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("address", address)
                .addValue("id", shopId);
        jdbcTemplate.update(sql, parameterSource);
    }

    public void deleteShop(int shopId) {
        String sql = "DELETE FROM shop WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", shopId));
    }
}