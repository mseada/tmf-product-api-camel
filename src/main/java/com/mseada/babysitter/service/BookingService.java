package com.mseada.babysitter.service;

import com.mseada.babysitter.model.Babysitter;
import com.mseada.babysitter.model.Booking;
import com.mseada.babysitter.model.BookingRequest;
import com.mseada.babysitter.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingService {

    private final Map<String, Booking> store = new LinkedHashMap<>();

    @Autowired
    private BabysitterService babysitterService;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        // Seed a couple of sample bookings
        Booking b1 = new Booking(
            "bk001", "bs001", "cust001", "WEEKLY",
            LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 7),
            "CONFIRMED", 2800.0
        );
        Booking b2 = new Booking(
            "bk002", "bs003", "cust003", "MONTHLY",
            LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 30),
            "CONFIRMED", 18000.0
        );
        Booking b3 = new Booking(
            "bk003", "bs006", "cust002", "DAILY",
            LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 20),
            "COMPLETED", 380.0
        );
        store.put(b1.getId(), b1);
        store.put(b2.getId(), b2);
        store.put(b3.getId(), b3);
    }

    public List<Booking> findAll() {
        return new ArrayList<>(store.values());
    }

    public Booking findById(String id) {
        return store.get(id);
    }

    public Booking create(BookingRequest request) {
        // Resolve or create customer
        String customerId = request.getCustomerId();
        if (customerId == null || customerId.isBlank()) {
            // Try to find by email first
            if (request.getCustomerEmail() != null && !request.getCustomerEmail().isBlank()) {
                Customer existing = customerService.findByEmail(request.getCustomerEmail());
                if (existing != null) {
                    customerId = existing.getId();
                } else {
                    // Create a new customer inline
                    Customer newCustomer = new Customer();
                    newCustomer.setName(request.getCustomerName() != null ? request.getCustomerName() : "Unknown");
                    newCustomer.setEmail(request.getCustomerEmail());
                    newCustomer.setPhone(request.getCustomerPhone());
                    newCustomer.setStatus("Active");
                    newCustomer.setRegistrationDate(LocalDate.now());
                    Customer created = customerService.create(newCustomer);
                    customerId = created.getId();
                }
            } else {
                customerId = "guest-" + UUID.randomUUID().toString().substring(0, 8);
            }
        }

        Babysitter babysitter = babysitterService.findById(request.getBabysitterId());
        if (babysitter == null) {
            throw new IllegalArgumentException("Babysitter not found: " + request.getBabysitterId());
        }

        String packageType = request.getPackageType() != null ? request.getPackageType().toUpperCase() : "DAILY";
        LocalDate startDate = request.getStartDate() != null ? request.getStartDate() : LocalDate.now();
        LocalDate endDate;
        double totalAmount;

        switch (packageType) {
            case "WEEKLY":
                endDate = startDate.plusDays(6);
                totalAmount = babysitter.getWeeklyRate();
                break;
            case "MONTHLY":
                endDate = startDate.plusDays(29);
                totalAmount = babysitter.getMonthlyRate();
                break;
            default: // DAILY
                endDate = startDate;
                totalAmount = babysitter.getDailyRate();
                packageType = "DAILY";
                break;
        }

        String id = "bk" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Booking booking = new Booking(id, request.getBabysitterId(), customerId, packageType,
                                      startDate, endDate, "PENDING", totalAmount);
        store.put(id, booking);
        return booking;
    }
}
