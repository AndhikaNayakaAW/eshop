//main/java/id.ac.ui.cs.advprog.eshop/service/CarService.java
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    public Car create(Car car);
    public List<Car> findAll();
    public Car findById(String carId);
    public void update(String carId, Car car);
    public void deleteCarById(String carId);
}