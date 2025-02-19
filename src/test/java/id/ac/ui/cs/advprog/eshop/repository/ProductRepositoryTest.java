package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // Clear the repository before each test (if you want a fresh state).
        // If needed, you can reset or re-initialize the repository here.
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductPositive() {
        // Create and add a product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Edit product details
        product.setProductName("Sampo Cap Bambang Updated");
        product.setProductQuantity(120);
        productRepository.update(product);

        // Retrieve and verify the updated product
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product updatedProduct = productIterator.next();
        assertEquals("Sampo Cap Bambang Updated", updatedProduct.getProductName());
        assertEquals(120, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditProductNegative() {
        // Attempt to edit a non-existent product in an empty repository
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Fake Product");
        nonExistentProduct.setProductQuantity(999);

        Product result = productRepository.update(nonExistentProduct);
        assertNull(result, "Expected null when updating a non-existent product");
    }

    /**
     * Additional test: try to update a product that does not exist 
     * in a NON-empty repository (to fully exercise the 'for' loop).
     */
    @Test
    void testEditProductNegativeNonEmpty() {
        // Create one product
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Existing Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Attempt to update a different, non-existent product
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Fake Product");
        nonExistentProduct.setProductQuantity(999);

        Product result = productRepository.update(nonExistentProduct);
        assertNull(result, "Expected null when updating a non-existent product in non-empty repo");
    }

    /**
     * Additional test: update the second product in a list of multiple products
     * to ensure the loop iterates more than once.
     */
    @Test
    void testEditSecondProductInList() {
        // Create two products
        Product product1 = new Product();
        product1.setProductId("id1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Update the second product
        product2.setProductName("Product 2 Updated");
        product2.setProductQuantity(25);
        Product updatedProduct = productRepository.update(product2);

        // Verify the second product got updated
        assertNotNull(updatedProduct);
        assertEquals("Product 2 Updated", updatedProduct.getProductName());
        assertEquals(25, updatedProduct.getProductQuantity());

        // Ensure the first product remains unchanged
        Iterator<Product> productIterator = productRepository.findAll();
        Product first = productIterator.next();
        Product second = productIterator.next();
        assertEquals("id1", first.getProductId());
        assertEquals("Product 1", first.getProductName());
        assertEquals("id2", second.getProductId());
        assertEquals("Product 2 Updated", second.getProductName());
    }

    @Test
    void testDeleteProductPositive() {
        // Create and add a product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Delete the product
        boolean result = productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertTrue(result);

        // Verify the product is deleted
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNegative() {
        // Attempt to delete a non-existent product
        boolean result = productRepository.delete("non-existent-id");
        assertFalse(result, "Expected false when deleting a non-existent product");
    }

    /**
     * Additional test: delete the second product in a multi-product list
     * to ensure the while loop iterates more than once.
     */
    @Test
    void testDeleteSecondProductInList() {
        // Create two products
        Product product1 = new Product();
        product1.setProductId("id1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Delete the second product
        boolean result = productRepository.delete("id2");
        assertTrue(result, "Expected true when deleting an existing product");

        // Verify only the first product remains
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remaining = productIterator.next();
        assertEquals("id1", remaining.getProductId());
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteVariousIdScenarios() {
        // 1. Product with a null ID
        Product nullIdProduct = new Product();
        nullIdProduct.setProductId(null);
        nullIdProduct.setProductName("Null ID Product");
        productRepository.create(nullIdProduct);

        // 2. Product with a mismatch ID
        Product mismatchIdProduct = new Product();
        mismatchIdProduct.setProductId("mismatch-id");
        mismatchIdProduct.setProductName("Mismatch ID Product");
        productRepository.create(mismatchIdProduct);

        // 3. Product with a matching ID
        Product matchingIdProduct = new Product();
        matchingIdProduct.setProductId("matching-id");
        matchingIdProduct.setProductName("Matching ID Product");
        productRepository.create(matchingIdProduct);

        // Attempt to delete "some-other-id" => covers scenario (2a):
        //    productId != null, but mismatch => should return false
        boolean result1 = productRepository.delete("some-other-id");
        assertFalse(result1, "Should be false when deleting an ID not in the repository");

        // Attempt to delete null => covers scenario (1) from the "caller's" perspective:
        //    if (product.getProductId() != null && product.getProductId().equals(null)) =>
        //    the second part won't even run for the null-ID product
        boolean result2 = productRepository.delete(null);
        assertFalse(result2, "Should be false when productId is null (no match)");

        // Attempt to delete "mismatch-id" => covers scenario (2b):
        //    product.getProductId() != null, equals => mismatch vs. others,
        //    but exactly matches the mismatchIdProduct
        boolean result3 = productRepository.delete("mismatch-id");
        assertTrue(result3, "Should be true when deleting the product with mismatch-id");

        // Attempt to delete "matching-id" => covers scenario (3):
        //    product.getProductId() != null AND product.getProductId().equals(productId) => true
        boolean result4 = productRepository.delete("matching-id");
        assertTrue(result4, "Should be true when deleting the product with matching-id");
    }

}
