package com.mseada.babysitter.model;

import java.time.LocalDate;

public class Booking {
    private String id;
    private String babysitterId;
    private String customerId;
    private String packageType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private double totalAmount;

    public Booking() {}

    public Booking(String id, String babysitterId, String customerId, String packageType,
                   LocalDate startDate, LocalDate endDate, String status, double totalAmount) {
        this.id = id;
        this.babysitterId = babysitterId;
        this.customerId = customerId;
        this.packageType = packageType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBabysitterId() { return babysitterId; }
    public void setBabysitterId(String babysitterId) { this.babysitterId = babysitterId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getPackageType() { return packageType; }
    public void setPackageType(String packageType) { this.packageType = packageType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
