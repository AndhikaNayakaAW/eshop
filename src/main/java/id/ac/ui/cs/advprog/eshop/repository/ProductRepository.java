//main/java/id.ac.ui.cs.advprog.eshop/repository/ProductRepository.java
package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product update(Product updatedProduct) {
        for (Product product : productData) {
            if (product.getProductId().equals(updatedProduct.getProductId())) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product; // Ensures full coverage
            }
        }
        return null; // If product not found
    }

    public boolean delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() != null && product.getProductId().equals(productId)) {
                iterator.remove();
                return true; // Successfully deleted
            }
        }
        return false; // Ensures coverage
    }
}
