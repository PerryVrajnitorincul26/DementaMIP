package com.example.mipexfr;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class MainMenuUI extends JFrame {
    private AppUserRepo userRepository;
    private AppTableRepo tableRepository;
    private AppStockRepo stockRepository;
    private AppProductRepo productRepository;

    public MainMenuUI(AppUserRepo userRepository,AppTableRepo tableRepository, AppProductRepo productRepository, AppStockRepo stockRepository){
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton userManagementButton = new JButton("User Management");
        JButton productManagementButton = new JButton("Stock Management");
        JButton otherFeatureButton = new JButton("Table Management");

        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserManagementPage();
            }
        });

        productManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductManagementPage();
            }
        });

        otherFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTableManagementPage();
            }
        });

        add(userManagementButton);
        add(productManagementButton);
        add(otherFeatureButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void openUserManagementPage() {
        // Create and display the User Management page with the userRepository
        UserManagementUI userManagementUI = new UserManagementUI(userRepository);
        userManagementUI.setVisible(true);
    }

    private void openProductManagementPage() {
        // Create and display the Product Management page with the productRepository
        StockManagementUI productManagementUI = new StockManagementUI(stockRepository,productRepository);
        productManagementUI.setVisible(true);
    }

    private void openTableManagementPage() {
        TableManagementUI tabMan = new TableManagementUI(tableRepository,userRepository);
        tabMan.setVisible(true);

    }
}
