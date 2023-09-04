package com.example.mipexfr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableManagementUI extends JFrame {
    private AppTableRepo tableRepository;
    private AppUserRepo userRepository;
    private JList<String> tableList;
    private DefaultListModel<String> tableListModel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton changeButton;

    private AppTable selectedTable;

    public TableManagementUI(AppTableRepo tableRepository, AppUserRepo userRepository) {
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;

        setTitle("Table Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and configure UI components
        tableListModel = new DefaultListModel<>();
        tableList = new JList<>(tableListModel);
        JScrollPane tableListScrollPane = new JScrollPane(tableList);

        addButton = new JButton("Add Table");
        editButton = new JButton("Edit Table");
        deleteButton = new JButton("Delete Table");
        changeButton = new JButton("Change User");

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignUserToTable();

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTable();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(changeButton);

        // Add UI components to the frame
        add(tableListScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set selection mode to single selection
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a ListSelectionListener to track selected items
        tableList.addListSelectionListener(e -> {
            int selectedIndex = tableList.getSelectedIndex();
            if (selectedIndex >= 0) {
                selectedTable = tableRepository.findById(selectedIndex + 1L);
            } else {
                selectedTable = null;
            }
        });

        // Refresh the table list
        refreshTableList();

        pack();
        setLocationRelativeTo(null);
    }

    private void addTable() {
        String seatsStr = JOptionPane.showInputDialog("Enter number of seats:");
        if (seatsStr != null && !seatsStr.isEmpty()) {
            try {
                int seats = Integer.parseInt(seatsStr);
                String customersStr = JOptionPane.showInputDialog("Enter number of customers:");
                if (customersStr != null && !customersStr.isEmpty()) {
                    try {
                        int customers = Integer.parseInt(customersStr);
                        AppTable table = new AppTable(seats, customers, null);
                        tableRepository.save(table);
                        refreshTableList();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input for customers. Please enter a valid number.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for seats. Please enter a valid number.");
            }
        }
        refreshTableList();
    }

    private void editTable() {
        if (selectedTable != null) {
            String seatsStr = JOptionPane.showInputDialog("Enter new number of seats:", selectedTable.getSeats());
            if (seatsStr != null && !seatsStr.isEmpty()) {
                try {
                    int seats = Integer.parseInt(seatsStr);
                    String customersStr = JOptionPane.showInputDialog("Enter new number of customers:", selectedTable.getCustomers());
                    if (customersStr != null && !customersStr.isEmpty()) {
                        try {
                            int customers = Integer.parseInt(customersStr);
                            selectedTable.setSeats(seats);
                            selectedTable.setCustomers(customers);
                            tableRepository.save(selectedTable);
                            refreshTableList();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input for customers. Please enter a valid number.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for seats. Please enter a valid number.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a table to edit.");
        }
        refreshTableList();
    }
    private void assignUserToTable() {
        if (selectedTable != null) {
            String userIdStr = JOptionPane.showInputDialog("Enter user ID to assign to the table (or leave empty to unassign):");
            if (userIdStr != null) {
                try {
                    Long userId = Long.parseLong(userIdStr);
                    // Find the user by ID, assuming you have a UserRepository
                    AppUser user = userRepository.findById(userId).orElse(null);

                    if (user != null || userIdStr.isEmpty()) {
                        // Assign the user to the table or unassign if userIdStr is empty
                        selectedTable.setApp_user(user);
                        tableRepository.save(selectedTable);
                        refreshTableList();
                    } else {
                        JOptionPane.showMessageDialog(this, "User not found. Please enter a valid user ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for user ID. Please enter a valid number.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a table to assign/unassign a user.");
        }
        refreshTableList();
    }

    private void deleteTable() {
        if (selectedTable != null) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this table?", "Delete Table", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                tableRepository.delete(selectedTable);
                refreshTableList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a table to delete.");
        }
        refreshTableList();
    }

    private void refreshTableList() {
        tableListModel.clear();
        var tables = tableRepository.findAll();
        for (AppTable table : tables) {
            tableListModel.addElement("Table ID: " + table.getTableId() +
                    ", Seats: " + table.getSeats() +
                    ", Customers: " + table.getCustomers()+
                    ", Assigned Waiter:" + table.getApp_user());
        }
    }

    // Your other methods and fields
}
