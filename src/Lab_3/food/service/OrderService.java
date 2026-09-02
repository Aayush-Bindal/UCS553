package Lab_3.food.service;

import Lab_3.food.model.Discountable;
import Lab_3.food.model.FoodOrder;

public class OrderService {
    public void displayBill(FoodOrder[] orders) {
        double totalBill = 0;
        double totalDiscount = 0;
        double totalDelivery = 0;
        double totalPayable = 0;

        for (FoodOrder order : orders) {
            double bill = order.getAmount();
            double discount = ((Discountable) order).applyDiscount();
            double delivery = order.calculateDeliveryCharge();
            double payable = bill - discount + delivery;

            System.out.printf("Order %d (%s)%n", order.getOrderId(), order.getCustomerName());
            System.out.printf("  Bill: Rs. %.2f%n", bill);
            System.out.printf("  Discount: Rs. %.2f%n", discount);
            System.out.printf("  Delivery charge: Rs. %.2f%n", delivery);
            System.out.printf("  Final payable amount: Rs. %.2f%n%n", payable);

            totalBill += bill;
            totalDiscount += discount;
            totalDelivery += delivery;
            totalPayable += payable;
        }

        System.out.printf("Total bill: Rs. %.2f%n", totalBill);
        System.out.printf("Total discount: Rs. %.2f%n", totalDiscount);
        System.out.printf("Total delivery charges: Rs. %.2f%n", totalDelivery);
        System.out.printf("Total final payable amount: Rs. %.2f%n", totalPayable);
    }
}
