package Lab_3.food.model;

public abstract class FoodOrder {
    private static int orderCount;
    private static String restaurantName = "Campus Kitchen";

    private int orderId;
    private String customerName;
    private double amount;

    protected FoodOrder(int orderId, String customerName, double amount) {
        setOrderId(orderId);
        setCustomerName(customerName);
        setAmount(amount);
        orderCount++;
    }

    public abstract double calculateDeliveryCharge();

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
        FoodOrder.restaurantName = restaurantName;
    }

    public static void displayTotalOrders() {
        System.out.println("Total orders created: " + orderCount);
    }
}
