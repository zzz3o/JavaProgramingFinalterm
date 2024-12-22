/**
 * 결제 금액을 계산하는 클래스입니다.
 * 주어진 레스토랑과 수량에 따라 결제 금액을 계산하여 반환합니다.
 *
 * @author Seo  Woojin (zzzeow3@gmail.com)
 *
 * @created 2024-12-22
 * @lastModified 2024-12-26
 *
 * @changelog
 * <ul>
 *   <li>2024-12-22: 최초 생성 (Seo  Woojin)</li>
 * </ul>
 */
public class PaymentCalculator {
    /**
     * @param restaurant 결제할 레스토랑의 이름 (예: "교직원식당", "학생식당")
     * @param quantity 결제할 항목의 수량
     * @return 계산된 결제 금액
     */
    public static int calculateAmount(String restaurant, int quantity) {
        // 교직원식당은 6000원, 그외 식당들은 4500원으로 설정
        int price = restaurant.equals("교직원식당") ? Constants.EMPLOYEE_PRICE : Constants.OTHER_PRICE;
        return quantity * price;
    }
}
