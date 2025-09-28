// File: WeatherObserverDemo.java
import java.util.*;

// Observer interface
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Subject interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    // Called when new measurements arrive
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

// Concrete Observer 1
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float t, float h, float p) {
        this.temperature = t;
        this.humidity = h;
        display();
    }

    public void display() {
        System.out.printf("Current conditions: %.1f°C and %.1f%% humidity%n", temperature, humidity);
    }
}

// Concrete Observer 2
class StatisticsDisplay implements Observer {
    private List<Float> temps = new ArrayList<>();

    @Override
    public void update(float t, float h, float p) {
        temps.add(t);
        display();
    }

    public void display() {
        float sum = 0;
        for (float f : temps) sum += f;
        System.out.printf("Avg/Min/Max temperature = %.1f/%.1f/%.1f%n",
                sum / temps.size(), Collections.min(temps), Collections.max(temps));
    }
}

// Demo runner
public class WeatherObserverDemo {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statsDisplay = new StatisticsDisplay();

        station.registerObserver(currentDisplay);
        station.registerObserver(statsDisplay);

        station.setMeasurements(25.0f, 65.0f, 1013.1f);
        station.setMeasurements(27.5f, 70.0f, 1012.8f);

        station.removeObserver(currentDisplay);

        station.setMeasurements(22.0f, 90.0f, 1009.5f);
    }
}
