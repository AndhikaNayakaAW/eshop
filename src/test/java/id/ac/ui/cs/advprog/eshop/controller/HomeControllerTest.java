// src/test/java/id/ac/ui/cs/advprog/eshop/controller/HomeControllerTest.java
package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {

    @Test
    public void testHome() {
        HomeController homeController = new HomeController();
        String view = homeController.home();
        assertEquals("index", view);
    }
}
