package Lab_5;

import java.util.*;

public class Q7 {
    public static void main(String[] args) {
        Cart cart = new Cart();
        try {
            cart.add("Laptop", 2, 75000);
            cart.add("Mouse", 0, 1000);
            System.out.println("Search Laptop: " + cart.search("Laptop"));
            cart.remove("Laptop", 1);
            cart.add("Laptop", 1, 75000);
        } catch (ProductException exception) {
            System.out.println("Product error: " + exception.getMessage());
        }
        try {
            cart.pay(500);
        } catch (PaymentException | OrderException exception) {
            System.out.println("Payment error: " + exception.getMessage());
        }
        try {
            cart.pay(150000);
            System.out.println("Order placed.");
        } catch (PaymentException | OrderException exception) {
            System.out.println("Order error: " + exception.getMessage());
        }
    }

    static class Cart {
        private final Map<String, Integer> stock = Map.of("Laptop", 2, "Mouse", 0);
        private final Map<String, Double> prices = Map.of("Laptop", 75000.0, "Mouse", 1000.0);
        private final Map<String, Integer> items = new HashMap<>();
        private boolean paid;
        void add(String name, int quantity, double price) throws ProductException {
            if (!stock.containsKey(name)) throw new ProductNotFoundException("Product not found.");
            if (quantity <= 0 || quantity > stock.get(name)) throw new OutOfStockException("Requested quantity is unavailable.");
            items.merge(name, quantity, Integer::sum);
        }
        void remove(String name, int quantity) throws ProductException {
            if (!items.containsKey(name)) throw new ProductNotFoundException("Product is not in the cart.");
            if (quantity <= 0 || quantity > items.get(name)) throw new OutOfStockException("Invalid removal quantity.");
            if (quantity == items.get(name)) items.remove(name);
            else items.put(name, items.get(name) - quantity);
        }
        String search(String name) {
            return stock.containsKey(name) ? name + " is available" : "Product not found";
        }
        void pay(double amount) throws PaymentException, OrderException {
            if (items.isEmpty()) throw new EmptyCartException("Cart is empty.");
            if (paid) throw new OrderException("Order has already been paid.");
            double total = items.entrySet().stream().mapToDouble(e -> prices.get(e.getKey()) * e.getValue()).sum();
            if (amount <= 0) throw new InvalidPaymentException("Payment must be positive.");
            if (amount < total) throw new InsufficientFundsException("Insufficient funds.");
            paid = true;
            items.clear();
        }
    }
    static class ApplicationException extends Exception { ApplicationException(String m) { super(m); } }
    static class ProductException extends ApplicationException { ProductException(String m) { super(m); } }
    static class ProductNotFoundException extends ProductException { ProductNotFoundException(String m) { super(m); } }
    static class OutOfStockException extends ProductException { OutOfStockException(String m) { super(m); } }
    static class PaymentException extends ApplicationException { PaymentException(String m) { super(m); } }
    static class InvalidPaymentException extends PaymentException { InvalidPaymentException(String m) { super(m); } }
    static class InsufficientFundsException extends PaymentException { InsufficientFundsException(String m) { super(m); } }
    static class OrderException extends ApplicationException { OrderException(String m) { super(m); } }
    static class EmptyCartException extends OrderException { EmptyCartException(String m) { super(m); } }
}
