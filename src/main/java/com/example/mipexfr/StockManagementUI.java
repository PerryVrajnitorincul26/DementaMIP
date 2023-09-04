package com.example.mipexfr;
import org.hibernate.metamodel.mapping.internal.MappingModelCreationProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StockManagementUI extends JFrame {
    private AppStockRepo stockRepository;
    private AppProductRepo productRepository; // Repository for retrieving product names
    private JList<String> stockList;
    private DefaultListModel<String> stockListModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private AppStock selectedStock;

    public StockManagementUI(AppStockRepo stockRepository, AppProductRepo productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;

        setTitle("Stock Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and configure UI components
        stockListModel = new DefaultListModel<>();
        stockList = new JList<>(stockListModel);
        JScrollPane stockListScrollPane = new JScrollPane(stockList);

        addButton = new JButton("Add Stock");
        editButton = new JButton("Edit Stock");
        deleteButton = new JButton("Delete Stock");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStock();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStock();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add UI components to the frame
        add(stockListScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set selection mode to single selection
        stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a ListSelectionListener to track selected items
        stockList.addListSelectionListener(e -> {
            int selectedIndex = stockList.getSelectedIndex();
            if (selectedIndex >= 0) {
                selectedStock = stockRepository.findById(selectedIndex + 1L).orElse(null);
            } else {
                selectedStock = null;
            }
        });

        // Refresh the stock list
        refreshStockList();

        pack();
        setLocationRelativeTo(null);
    }

    private void addStock() {
        String productIdStr = JOptionPane.showInputDialog("Enter product ID:");
        if (productIdStr != null && !productIdStr.isEmpty()) {
            try {
                Long productId = Long.parseLong(productIdStr);

                // Find the product by ID
                AppProduct product = productRepository.findById(productId).orElse(null);

                if (product != null) {
                    double price = Double.parseDouble(JOptionPane.showInputDialog("Enter stock price:"));
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter stock quantity:"));

                    AppStock stock = new AppStock(quantity,price,product);
                    stockRepository.save(stock);
                    refreshStockList();
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found. Please enter a valid product ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.");
            }
        }
    }

    private void editStock() {
        if (selectedStock != null) {
            String productIdStr = JOptionPane.showInputDialog("Enter new product ID:", selectedStock.getProduct().getId());
            if (productIdStr != null && !productIdStr.isEmpty()) {
                try {
                    Long productId = Long.parseLong(productIdStr);

                    // Find the product by ID
                    AppProduct product = productRepository.findById(productId).orElse(null);

                    if (product != null) {
                        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter new stock price:", selectedStock.getPrice()));
                        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock quantity:", selectedStock.getQuantity()));

                        selectedStock.setProduct(product);
                        selectedStock.setPrice(price);
                        selectedStock.setQuantity(quantity);

                        stockRepository.save(selectedStock);
                        refreshStockList();
                    } else {
                        JOptionPane.showMessageDialog(this, "Product not found. Please enter a valid product ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a stock item to edit.");
        }
    }

    private void deleteStock() {
        if (selectedStock != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this stock item?", "Delete Stock", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                stockRepository.delete(selectedStock);
                refreshStockList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a stock item to delete.");
        }
    }

    private void refreshStockList() {
        stockListModel.clear();
        List<AppStock> stocks = stockRepository.findAll();
        for (AppStock stock : stocks) {
            stockListModel.addElement("Product: " + stock.getProduct().getName() +
                    ", Price: " + stock.getPrice() +
                    ", Quantity: " + stock.getQuantity());
        }
    }}
