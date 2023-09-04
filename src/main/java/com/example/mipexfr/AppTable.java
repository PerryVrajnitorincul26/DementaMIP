package com.example.mipexfr;

import jakarta.persistence.*;


@Entity
@Table(name = "app_table")
public class AppTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long tableId;

    private int seats;

    private int customers;

    private long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="app_user", referencedColumnName="id",nullable=false,unique=true)
    private AppUser app_user;

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;

    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;

    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;

    }
}
