package com.example.mipexfr;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SpringBootApplication
public class UserManagementUI extends JFrame {
    private AppUserRepo userRepository;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox isAdminCheckBox;
    private JButton addButton, updateButton, deleteButton;
    private JList<AppUser> userList;

    public UserManagementUI(AppUserRepo userRepository) {
        this.userRepository = userRepository;
        setTitle("User Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and configure UI components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        isAdminCheckBox = new JCheckBox("Is Admin");
        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        userList = new JList<>();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Admin:"));
        inputPanel.add(isAdminCheckBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(new JScrollPane(userList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        // Add UI components to the frame
        // ...

        pack();
        setLocationRelativeTo(null);
    }

    private void addUser() {
        // Retrieve data from UI fields
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdmin = isAdminCheckBox.isSelected();

        // Create a new AppUser object and save it to the database
        AppUser user = new AppUser(username, password, isAdmin);
        userRepository.save(user);

        // Update the user list
        refreshUserList();
    }

    private void updateUser() {
        // Retrieve selected user from the list
        AppUser selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Select a user to update.");
            return;
        }

        // Update the selected user's data
        selectedUser.setUsername(usernameField.getText());
        selectedUser.setPassword(new String(passwordField.getPassword()));
        selectedUser.setAdmin(isAdminCheckBox.isSelected());

        // Save the updated user to the database
        userRepository.save(selectedUser);

        // Update the user list
        refreshUserList();
    }

    private void deleteUser() {
        // Retrieve selected user from the list
        AppUser selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(this, "Select a user to delete.");
            return;
        }

        // Delete the selected user from the database
        userRepository.delete(selectedUser);

        // Update the user list
        refreshUserList();
    }

    private void refreshUserList() {
        // Fetch all users from the database and update the JList
        var users = userRepository.findAll();
        DefaultListModel<AppUser> model = new DefaultListModel<>();
        for (AppUser user : users) {
            model.addElement(user);
        }
        userList.setModel(model);
    }

    //public static void main(String[] args) {
    //    // Initialize Spring context and get the userRepository bean
    //    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    //    AppUserRepository userRepository = context.getBean(AppUserRepository.class);

    //    // Create and display the user management UI
    //    javax.swing.SwingUtilities.invokeLater(() -> {
    //        UserManagementUI userManagementUI = new UserManagementUI(userRepository);
    //        userManagementUI.setVisible(true);
    //    });
    //}
}
