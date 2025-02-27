// main/java/id/ac/ui/cs/advprog/eshop/service/CarServiceImpl.java
package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    // Constructor injection ensures that CarServiceImpl depends only on the CarRepository abstraction.
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        carRepository.create(car);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
