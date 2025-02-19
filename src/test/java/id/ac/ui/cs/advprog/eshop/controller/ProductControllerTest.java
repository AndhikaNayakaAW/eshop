// src/test/java/id/ac/ui/cs/advprog/eshop/controller/ProductControllerTest.java
package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("CreateProduct"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productName", "Test Product")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    public void testProductListPage() throws Exception {
        Product prod1 = new Product();
        prod1.setProductId("1");
        Product prod2 = new Product();
        prod2.setProductId("2");
        List<Product> products = Arrays.asList(prod1, prod2);
        when(service.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", products))
                .andExpect(view().name("ProductList"));
    }

    @Test
    public void testEditProductPage_ProductFound() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        when(service.findAll()).thenReturn(Arrays.asList(product));

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("product", product))
                .andExpect(view().name("EditProduct"));
    }

    @Test
    public void testEditProductPage_ProductNotFound() throws Exception {
        // No products returned by service
        when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    /**
     * Additional test: ensure we handle multiple products
     * and the matching one is the second item in the list.
     * This helps cover the loop in editProductPage fully.
     */
    @Test
    public void testEditProductPage_ProductFoundSecondInList() throws Exception {
        Product product1 = new Product();
        product1.setProductId("111");
        product1.setProductName("First Product");

        Product product2 = new Product();
        product2.setProductId("222");
        product2.setProductName("Second Product");

        when(service.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Request editing product "222" — ensures the loop doesn't return on first product
        mockMvc.perform(get("/product/edit/222"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("product", product2))
                .andExpect(view().name("EditProduct"));
    }

    /**
     * Additional test: if a product in the list has a null ID,
     * we skip it without throwing an exception.
     * (Only needed if your code checks for null IDs or if you
     *  want to ensure coverage for that scenario.)
     */
    @Test
    public void testEditProductPage_ProductWithNullId() throws Exception {
        Product nullIdProduct = new Product();
        nullIdProduct.setProductId(null);
        nullIdProduct.setProductName("Null ID Product");

        when(service.findAll()).thenReturn(Arrays.asList(nullIdProduct));

        // If the controller code doesn't explicitly handle null,
        // you'll need a safe check like (p.getProductId() != null && p.getProductId().equals(id))
        // to avoid NullPointerExceptions.
        mockMvc.perform(get("/product/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("productId", "123")
                        .param("productName", "Edited Product")
                        .param("productQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).update(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).delete("123");
    }

    /**
     * Additional test: if we try to delete a product that doesn't exist,
     * we still redirect to /product/list. (This covers the scenario where
     * service.delete() might return false, or do nothing.)
     */
    @Test
    public void testDeleteProduct_NotFound() throws Exception {
        // Optionally mock service.delete(...) if it returns a boolean
        // and you want to check how it handles "not found".
        // e.g., when(service.delete("999")).thenReturn(false);

        mockMvc.perform(get("/product/delete/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(service, times(1)).delete("999");
    }
}

