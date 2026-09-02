package Lab_3.food.utility;

import Lab_3.food.model.Discountable;
import Lab_3.food.model.FoodOrder;

public final class OrderUtility {
    private OrderUtility() {
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidCustomerName(String customerName) {
        return customerName != null && !customerName.trim().isEmpty();
    }

    public static String generateOrderSummary(FoodOrder order) {
        double bill = order.getAmount();
        double discount = ((Discountable) order).applyDiscount();
        double deliveryCharge = order.calculateDeliveryCharge();
        double finalAmount = bill - discount + deliveryCharge;

        return String.format(
            "Order ID: %d | Customer: %s | Bill: Rs. %.2f | Discount: Rs. %.2f | Delivery: Rs. %.2f | Payable: Rs. %.2f",
            order.getOrderId(), order.getCustomerName(), bill, discount, deliveryCharge, finalAmount
        );
    }
}
