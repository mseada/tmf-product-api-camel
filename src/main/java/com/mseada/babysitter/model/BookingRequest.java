package com.mseada.babysitter.model;

import java.time.LocalDate;

public class BookingRequest {
    private String babysitterId;
    private String customerId;
    private String packageType;
    private LocalDate startDate;
    // Optional fields for inline customer creation
    private String customerName;
    private String customerEmail;
    private String customerPhone;

    public BookingRequest() {}

    public String getBabysitterId() { return babysitterId; }
    public void setBabysitterId(String babysitterId) { this.babysitterId = babysitterId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getPackageType() { return packageType; }
    public void setPackageType(String packageType) { this.packageType = packageType; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
}
