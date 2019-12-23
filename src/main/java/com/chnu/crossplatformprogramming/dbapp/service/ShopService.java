package com.chnu.crossplatformprogramming.dbapp.service;

import com.chnu.crossplatformprogramming.dbapp.model.Shop;
import com.chnu.crossplatformprogramming.dbapp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public void saveShop(int id, String title, String address) {
        shopRepository.saveShop(id, title, address);
    }

    public List<Shop> getShopById(int shopId) {
        return shopRepository.getShopById(shopId);
    }

    public List<Integer> getAllShopIds() {
        return shopRepository.getAllShopIds();
    }

    public Integer countOfShops() {
        return shopRepository.countOfShops();
    }

    public void updateShop(int shopId, String title, String address) {
        shopRepository.updateShop(shopId, title, address);
    }

    public void deleteShop(int shopId) {
        shopRepository.deleteShop(shopId);
    }
}