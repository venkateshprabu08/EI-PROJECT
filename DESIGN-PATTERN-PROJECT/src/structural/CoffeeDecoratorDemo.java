// File: CoffeeDecoratorDemo.java

// Component
interface Coffee {
    String getDescription();
    double cost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}

// Base Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    public double cost() {
        return decoratedCoffee.cost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.2;
    }
}

// Demo runner
public class CoffeeDecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.printf("%s : $%.2f%n", coffee.getDescription(), coffee.cost());

        Coffee milkCoffee = new MilkDecorator(coffee);
        System.out.printf("%s : $%.2f%n", milkCoffee.getDescription(), milkCoffee.cost());

        Coffee fancy = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.printf("%s : $%.2f%n", fancy.getDescription(), fancy.cost());
    }
}
