package Lab_3.food;

import Lab_3.food.model.FoodOrder;
import Lab_3.food.model.PremiumOrder;
import Lab_3.food.model.RegularOrder;
import Lab_3.food.service.OrderService;
import Lab_3.food.utility.OrderUtility;

public class Main {
    public static void main(String[] args) {
        FoodOrder.setRestaurantName("Campus Kitchen");

        FoodOrder[] orders = {
            createRegularOrder(101, "Aarav", 450),
            createPremiumOrder(102, "Diya", 800),
            createRegularOrder(103, "Kabir", 320),
            createPremiumOrder(104, "Meera", 650),
            createRegularOrder(105, "Rohan", 500),
            createPremiumOrder(106, "Sara", 950)
        };

        System.out.println("Restaurant: " + FoodOrder.getRestaurantName());
        System.out.println("==================== ORDER BILLS ====================");
        new OrderService().displayBill(orders);

        System.out.println("==================== ORDER SUMMARIES ====================");
        for (FoodOrder order : orders) {
            System.out.println(OrderUtility.generateOrderSummary(order));
        }
        FoodOrder.displayTotalOrders();
    }

    private static FoodOrder createRegularOrder(int id, String customerName, double amount) {
        validateOrder(id, customerName, amount);
        return new RegularOrder(id, customerName, amount);
    }

    private static FoodOrder createPremiumOrder(int id, String customerName, double amount) {
        validateOrder(id, customerName, amount);
        return new PremiumOrder(id, customerName, amount);
    }

    private static void validateOrder(int id, String customerName, double amount) {
        if (id <= 0 || !OrderUtility.isValidCustomerName(customerName) || !OrderUtility.isValidAmount(amount)) {
            throw new IllegalArgumentException("Invalid order details");
        }
    }
}
