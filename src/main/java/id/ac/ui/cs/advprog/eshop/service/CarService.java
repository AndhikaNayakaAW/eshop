// main/java/id.ac/ui/cs/advprog/eshop/service/CarService.java
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    // Full set of operations:
    Car create(Car car);
    void update(String carId, Car car);
    void deleteCarById(String carId);
    Car findById(String carId);
    List<Car> findAll();

    // Nested interface for read-only operations.
    interface Query {
        Car findById(String carId);
        List<Car> findAll();
    }

    // Nested interface for write operations.
    interface Command {
        Car create(Car car);
        void update(String carId, Car car);
        void deleteCarById(String carId);
    }
}
