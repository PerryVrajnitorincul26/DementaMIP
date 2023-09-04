package com.example.mipexfr;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class MainMenuUI extends JFrame {
    private AppUserRepo userRepository;

    public MainMenuUI(AppUserRepo userRepository){
        this.userRepository = userRepository;

        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton userManagementButton = new JButton("User Management");
        JButton productManagementButton = new JButton("Product Management");
        JButton otherFeatureButton = new JButton("Other Feature");

        userManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserManagementPage();
            }
        });

       // productManagementButton.addActionListener(new ActionListener() {
       //     @Override
       //     public void actionPerformed(ActionEvent e) {
       //         openProductManagementPage();
       //     }
       // });

        otherFeatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOtherFeaturePage();
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

    //private void openProductManagementPage() {
    //    // Create and display the Product Management page with the productRepository
    //    ProductManagementUI productManagementUI = new ProductManagementUI(productRepository);
    //    productManagementUI.setVisible(true);
    //}

    private void openOtherFeaturePage() {
        // Create and display the Other Feature page
        // You can pass any other necessary repositories or data here
    }

    //public static void main(String[] args) {
    //    // Initialize Spring context and get the repositories
    //    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    //    AppUserRepository userRepository = context.getBean(AppUserRepository.class);
    //    ProductRepository productRepository = context.getBean(ProductRepository.class);

    //    // Create and display the main menu with repositories
    //    javax.swing.SwingUtilities.invokeLater(() -> {
    //        MainMenuUI mainMenuUI = new MainMenuUI(userRepository, productRepository);
    //        mainMenuUI.setVisible(true);
    //    });
    //}
}
