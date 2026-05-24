package com.mseada.tmf.processor;

import com.mseada.tmf.model.ProductOrder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory stub implementation of the Product Ordering service (TMF622).
 *
 * <p>Replace with a real persistence layer as needed.
 */
@Service("productOrderService")
public class ProductOrderService {

    private final Map<String, ProductOrder> store = new ConcurrentHashMap<>();

    public Collection<ProductOrder> findAll() {
        return store.values();
    }

    public ProductOrder findById(String id) {
        return store.get(id);
    }

    public ProductOrder create(ProductOrder order) {
        store.put(order.getId(), order);
        return order;
    }

    /**
     * Applies a partial update (PATCH semantics) to an existing order.
     *
     * <p>Only non-null fields in {@code patch} overwrite the stored order.
     */
    public ProductOrder patch(String id, ProductOrder patch) {
        ProductOrder existing = store.get(id);
        if (existing == null) {
            return null;
        }
        if (patch.getState() != null)       existing.setState(patch.getState());
        if (patch.getDescription() != null) existing.setDescription(patch.getDescription());
        store.put(id, existing);
        return existing;
    }
}
