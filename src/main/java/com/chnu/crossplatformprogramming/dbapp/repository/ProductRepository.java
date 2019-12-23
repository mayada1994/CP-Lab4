package com.chnu.crossplatformprogramming.dbapp.repository;

import com.chnu.crossplatformprogramming.dbapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveProduct(int id, String title, double cost, int count, int shopId) {
        String sql = "INSERT INTO product (id, title, cost, count, shop_id) " +
                "VALUES (:id, :title, :cost, :count, :shop_id)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("title", title)
                .addValue("cost", cost)
                .addValue("count", count)
                .addValue("shop_id", shopId);
        jdbcTemplate.update(sql, parameterSource);
    }

    public List<Product> getAllProductByShopId(int shopId) {
        String sql = "SELECT p.id, p.title, p.cost, p.count, s.title AS shop_title, s.address AS shop_address " +
                "FROM product AS p INNER JOIN shop AS s ON p.shop_id = s.id WHERE s.id = :id";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("id", shopId), productRowMapper);
    }

    public List<Integer> getAllProductIds() {
        String sql = "SELECT id FROM product";
        return jdbcTemplate.queryForList(sql, new MapSqlParameterSource(), Integer.class);
    }

    public Product getProductById(int productId) {
        try {
            String sql = "SELECT p.id, p.title, p.cost, p.count, s.title AS shop_title, s.address AS shop_address " +
                    "FROM product AS p INNER JOIN shop AS s ON p.shop_id = s.id WHERE p.id = :id";
            return jdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource("id", productId), productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateProduct(int productId, String title, double cost, int count) {
        String sql = "UPDATE product SET title = :title, cost = :cost, count = :count WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("cost", cost)
                .addValue("count", count)
                .addValue("id", productId);
        jdbcTemplate.update(sql, parameterSource);
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE id = :id";
        jdbcTemplate.update(sql, new MapSqlParameterSource("id", productId));
    }
}