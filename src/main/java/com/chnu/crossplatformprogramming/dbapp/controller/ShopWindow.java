package com.chnu.crossplatformprogramming.dbapp.controller;

import com.chnu.crossplatformprogramming.dbapp.model.Shop;
import com.chnu.crossplatformprogramming.dbapp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class ShopWindow extends JDialog {
    private final ShopService shopService;

    private JComboBox<Integer> comboId = new JComboBox<>();
    static JTextField shopTxtId = new JTextField();
    private JTextField txtTitle = new JTextField();
    private JTextField txtAddress = new JTextField();

    @Autowired
    public ShopWindow(ShopService shopService) {
        this.shopService = shopService;

        this.setTitle("Info about shop");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(8, 2));
        this.setBounds(100, 50, 400, 200);

        JLabel lbSelectId = new JLabel("Select shop by id");
        this.add(lbSelectId);
        this.add(comboId);
        JLabel lbId = new JLabel("id");
        this.add(lbId);
        this.add(shopTxtId);
        JLabel lbTitle = new JLabel("title");
        this.add(lbTitle);
        this.add(txtTitle);
        JLabel lbAddress = new JLabel("Address");
        this.add(lbAddress);
        this.add(txtAddress);

        JButton btnAdd = new JButton("Add");
        this.add(btnAdd);
        JButton btnUpdate = new JButton("Update");
        this.add(btnUpdate);
        JButton btnRemove = new JButton("Delete");
        this.add(btnRemove);
        JButton btnClear = new JButton("Clear field");
        this.add(btnClear);

        comboId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showShopData();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addShop();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateShop();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeShop();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearShopInfo();
            }
        });

        refreshIdList();

        this.setVisible(true);
    }

    private void showShopData() {
        Integer shopId = (Integer) comboId.getSelectedItem();

        if (shopService.countOfShops() != 0) {
            if (shopId != null) {
                List<Shop> shops = shopService.getShopById(shopId);
                for (Shop shop : shops) {
                    shopTxtId.setText(String.valueOf(shop.getId()));
                    txtTitle.setText(shop.getTitle());
                    txtAddress.setText(String.valueOf(shop.getAddress()));
                }
            }
        } else {
            clearShopInfo();
        }
    }

    private void refreshIdList() {
        List<Integer> allShopIds = shopService.getAllShopIds();
        comboId.removeAllItems();

        for (Integer id : allShopIds) {
            comboId.addItem(id);
        }
    }

    private void clearShopInfo() {
        shopTxtId.setText("");
        txtTitle.setText("");
        txtAddress.setText("");
    }

    private void addShop() {
        shopService.saveShop(Integer.parseInt(shopTxtId.getText()), txtTitle.getText(), txtAddress.getText());

        refreshIdList();

        comboId.setSelectedItem(Integer.parseInt(shopTxtId.getText()));
    }

    private void updateShop() {
        shopService.updateShop(Integer.parseInt(shopTxtId.getText()), txtTitle.getText(), txtAddress.getText());
    }

    private void removeShop() {
        shopService.deleteShop(Integer.parseInt(shopTxtId.getText()));

        refreshIdList();

        showShopData();
    }
}