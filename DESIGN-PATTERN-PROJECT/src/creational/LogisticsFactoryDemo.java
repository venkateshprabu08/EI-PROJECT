// File: LogisticsFactoryDemo.java

// Product interface
interface Vehicle {
    void startDelivery();
}

// Concrete Products
class RoadTruck implements Vehicle {
    @Override
    public void startDelivery() {
        System.out.println("Starting delivery by road (Truck).");
    }
}

class CargoShip implements Vehicle {
    @Override
    public void startDelivery() {
        System.out.println("Starting delivery by sea (Ship).");
    }
}

// Creator (Factory Method)
abstract class Logistics {
    public void planDelivery() {
        Vehicle vehicle = createVehicle();
        // common logic before delivery
        System.out.println("Planning delivery using " + vehicle.getClass().getSimpleName());
        vehicle.startDelivery();
    }

    // Factory method
    protected abstract Vehicle createVehicle();
}

// Concrete Creators
class RoadLogistics extends Logistics {
    @Override
    protected Vehicle createVehicle() {
        return new RoadTruck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    protected Vehicle createVehicle() {
        return new CargoShip();
    }
}

// Demo runner
public class LogisticsFactoryDemo {
    public static void main(String[] args) {
        Logistics road = new RoadLogistics();
        road.planDelivery();

        Logistics sea = new SeaLogistics();
        sea.planDelivery();
    }
}
