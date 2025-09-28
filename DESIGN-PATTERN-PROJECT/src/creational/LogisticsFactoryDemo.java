


interface Vehicle {
    void startDelivery();
}


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


abstract class Logistics {
    public void planDelivery() {
        Vehicle vehicle = createVehicle();
       
        System.out.println("Planning delivery using " + vehicle.getClass().getSimpleName());
        vehicle.startDelivery();
    }

    
    protected abstract Vehicle createVehicle();
}


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


public class LogisticsFactoryDemo {
    public static void main(String[] args) {
        Logistics road = new RoadLogistics();
        road.planDelivery();

        Logistics sea = new SeaLogistics();
        sea.planDelivery();
    }
}
