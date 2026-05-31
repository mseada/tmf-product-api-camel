package com.mseada.babysitter.service;

import com.mseada.babysitter.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerService {

    private final Map<String, Customer> store = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        List<Customer> samples = Arrays.asList(
            new Customer(
                "cust001", "Ahmed Al-Rashidi", "ahmed.rashidi@example.com",
                "+20 100 111 2222", "15 Street 9, Maadi Degla", "Maadi",
                2, Arrays.asList("3 years", "6 years"),
                LocalDate.of(2024, 1, 15), "Active"
            ),
            new Customer(
                "cust002", "Fatma El-Sayed", "fatma.elsayed@example.com",
                "+20 111 222 3333", "27 Hassan Sabri, Zamalek", "Zamalek",
                1, Arrays.asList("18 months"),
                LocalDate.of(2024, 3, 22), "Active"
            ),
            new Customer(
                "cust003", "Khaled Mansour", "khaled.mansour@example.com",
                "+20 122 333 4444", "Block 5, Villa 12, New Cairo", "New Cairo",
                3, Arrays.asList("2 years", "5 years", "8 years"),
                LocalDate.of(2023, 11, 10), "Active"
            ),
            new Customer(
                "cust004", "Mona Abdel-Aziz", "mona.aziz@example.com",
                "+20 109 444 5555", "45 Abbas El-Akkad, Nasr City", "Nasr City",
                1, Arrays.asList("4 years"),
                LocalDate.of(2024, 6, 5), "Active"
            ),
            new Customer(
                "cust005", "Tarek Gabr", "tarek.gabr@example.com",
                "+20 115 555 6666", "22 El-Thawra, Heliopolis", "Heliopolis",
                2, Arrays.asList("1 year", "7 years"),
                LocalDate.of(2023, 8, 30), "Inactive"
            )
        );

        for (Customer c : samples) {
            store.put(c.getId(), c);
        }
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public Customer findById(String id) {
        return store.get(id);
    }

    public Customer findByEmail(String email) {
        return store.values().stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    public Customer create(Customer customer) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            customer.setId("cust" + UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        }
        if (customer.getRegistrationDate() == null) {
            customer.setRegistrationDate(LocalDate.now());
        }
        if (customer.getStatus() == null || customer.getStatus().isBlank()) {
            customer.setStatus("Active");
        }
        store.put(customer.getId(), customer);
        return customer;
    }
}
