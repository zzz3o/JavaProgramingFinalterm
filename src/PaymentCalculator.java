public class PaymentCalculator {
    public static int calculateAmount(String restaurant, int quantity) {
        int price = restaurant.equals("교직원식당") ? Constants.EMPLOYEE_PRICE : Constants.OTHER_PRICE;
        return quantity * price;
    }
}
