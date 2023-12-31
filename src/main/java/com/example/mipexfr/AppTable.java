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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="app_user", referencedColumnName="id",nullable=false)
    private AppUser app_user;

    protected AppTable() {
    }
    public AppTable(int seats, int customers, AppUser app_user) {
        this.seats = seats;
        this.customers = customers;
        this.app_user = app_user;
    }

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

    public AppUser getApp_user() {
        return app_user;
    }

    public void setApp_user(AppUser app_user) {
        this.app_user = app_user;
    }

    @Override
    public String toString() {
        return "AppTable{" +
                "tableId=" + tableId +
                ", seats=" + seats +
                ", customers=" + customers +
                ", app_user=" + app_user +
                '}';
    }
}
