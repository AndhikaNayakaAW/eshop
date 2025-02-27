// main/java/id.ac.ui.cs.advprog.eshop/repository/CarRepository.java
package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface CarRepository {
    Car create(Car car);
    List<Car> findAll();
    Car findById(String id);
    Car update(String id, Car updatedCar);
    void delete(String id);

    @Repository
    class InMemoryCarRepository implements CarRepository {
        private List<Car> carData = new ArrayList<>();

        @Override
        public Car create(Car car) {
            if (car.getCarId() == null) {
                car.setCarId(UUID.randomUUID().toString());
            }
            carData.add(car);
            return car;
        }

        @Override
        public List<Car> findAll() {
            return new ArrayList<>(carData);
        }

        @Override
        public Car findById(String id) {
            for (Car car : carData) {
                if (car.getCarId().equals(id)) {
                    return car;
                }
            }
            return null;
        }

        @Override
        public Car update(String id, Car updatedCar) {
            for (int i = 0; i < carData.size(); i++) {
                Car car = carData.get(i);
                if (car.getCarId().equals(id)) {
                    car.setCarName(updatedCar.getCarName());
                    car.setCarColor(updatedCar.getCarColor());
                    car.setCarQuantity(updatedCar.getCarQuantity());
                    return car;
                }
            }
            return null;
        }

        @Override
        public void delete(String id) {
            carData.removeIf(car -> car.getCarId().equals(id));
        }
    }
}
