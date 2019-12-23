package com.chnu.crossplatformprogramming.dbapp.controller;

import com.chnu.crossplatformprogramming.dbapp.model.Product;
import com.chnu.crossplatformprogramming.dbapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class ProductWindow extends JDialog {
    private final ProductService productService;

    private JComboBox<Integer> comboId = new JComboBox<>();
    private JTextField txtId = new JTextField();
    private JTextField txtTitle = new JTextField();
    private JTextField txtCost = new JTextField();
    private JTextField txtCount = new JTextField();

    @Autowired
    public ProductWindow(ProductService productService) {
        this.productService = productService;

        this.setTitle("Info about product");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(8, 2));
        this.setBounds(100, 50, 400, 200);

        JLabel lbSelectId = new JLabel("Select product by id");
        this.add(lbSelectId);
        this.add(comboId);
        JLabel lbId = new JLabel("id");
        this.add(lbId);
        this.add(txtId);
        JLabel lbTitle = new JLabel("title");
        this.add(lbTitle);
        this.add(txtTitle);
        JLabel lbCost = new JLabel("cost");
        this.add(lbCost);
        this.add(txtCost);
        JLabel lbCount = new JLabel("count");
        this.add(lbCount);
        this.add(txtCount);

        JButton btnAdd = new JButton("Add");
        this.add(btnAdd);
        JButton btnUpdate = new JButton("Update");
        this.add(btnUpdate);
        JButton btnRemove = new JButton("Delete");
        this.add(btnRemove);
        JButton btnClear = new JButton("Clear");
        this.add(btnClear);

        comboId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showProductData();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearProductInfo();
            }
        });

        refreshIdList();

        this.setVisible(true);
    }

    private void showProductData() {
        Integer productId = (Integer) comboId.getSelectedItem();

        if (productId != null) {
            Product product = productService.getProductById(productId);
            txtId.setText(String.valueOf(product.getId()));
            txtTitle.setText(product.getTitle());
            txtCost.setText(String.valueOf(product.getCost()));
            txtCount.setText(String.valueOf(product.getCount()));
        }
    }

    private void refreshIdList() {
        List<Integer> allProductIds = productService.getAllProductIds();
        for (Integer id : allProductIds) {
            comboId.addItem(id);
        }
    }

    private void clearProductInfo() {
        txtId.setText("");
        txtTitle.setText("");
        txtCost.setText("");
        txtCount.setText("");
    }

    private void addProduct() {
        productService.saveProduct(Integer.parseInt(txtId.getText()), txtTitle.getText(),
                Double.parseDouble(txtCost.getText()), Integer.parseInt(txtCount.getText()),
                Integer.parseInt(ShopWindow.shopTxtId.getText()));

        refreshIdList();

        comboId.setSelectedItem(txtId.getText());
    }

    private void updateProduct() {
        productService.updateProduct(Integer.parseInt(txtId.getText()), txtTitle.getText(),
                Double.parseDouble(txtCost.getText()), Integer.parseInt(txtCount.getText()));
    }

    private void removeProduct() {
        productService.deleteProduct(Integer.parseInt(txtId.getText()));

        refreshIdList();

        showProductData();
    }
}