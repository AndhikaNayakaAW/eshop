// src/test/java/id/ac/ui/cs/advprog/eshop/service/ProductServiceImplTest.java
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl();

        // Inject the mocked repository into the service using reflection
        try {
            Field field = ProductServiceImpl.class.getDeclaredField("productRepository");
            field.setAccessible(true);
            field.set(productService, productRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreate() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        productService.create(product);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("1");
        Product product2 = new Product();
        product2.setProductId("2");
        List<Product> products = Arrays.asList(product1, product2);
        Iterator<Product> productIterator = products.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.update(product)).thenReturn(product);

        Product updatedProduct = productService.update(product);
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    public void testDelete() {
        String productId = "123";
        when(productRepository.delete(productId)).thenReturn(true);

        boolean result = productService.delete(productId);
        assertTrue(result);
        verify(productRepository, times(1)).delete(productId);
    }
}
