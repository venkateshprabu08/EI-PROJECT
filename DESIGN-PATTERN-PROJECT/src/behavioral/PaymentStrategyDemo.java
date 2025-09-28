package behavioral;


import java.util.*;

// Strategy interface
interface PaymentStrategy {
    void pay(int amount);
}


class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    public CreditCardStrategy(String name, String cardNumber, String cvv, String expiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = expiry;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card. Name: " + name);
       
    }
}


class PayPalStrategy implements PaymentStrategy {
    private String emailId;
    private String password;

    public PayPalStrategy(String email, String pwd) {
        this.emailId = email;
        this.password = pwd;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal. Email: " + emailId);
      
    }
}


class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    static class Item {
        String name;
        int price;
        Item(String n, int p){ name = n; price = p; }
    }

    public void addItem(String name, int price) {
        items.add(new Item(name, price));
    }

    public int calculateTotal() {
        int sum = 0;
        for (Item i : items) sum += i.price;
        return sum;
    }

    public void pay(PaymentStrategy strategy) {
        int amount = calculateTotal();
        strategy.pay(amount);
    }
}


public class PaymentStrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Book", 300);
        cart.addItem("Pen", 20);

       
        PaymentStrategy card = new CreditCardStrategy("Alice", "1234-5678-9012-3456", "123", "12/27");
        cart.pay(card);

      
        cart.addItem("Notebook", 120);
        PaymentStrategy paypal = new PayPalStrategy("alice@example.com", "password123");
        cart.pay(paypal);
    }
}
