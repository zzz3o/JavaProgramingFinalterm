import java.awt.Color;

/**
 * 애플리케이션에서 사용하는 색상을 정의한 유틸리티 클래스입니다.
 *
 * <p>색상을 정적 변수로 선언하여 어디서든 쉽게 재사용할 수 있도록 합니다.</p>
 */
public class ColorPalette {

    // 기본 색상 정의
    public static final Color BACKGROUND_COLOR = new Color(0x283618); // 프레임 배경,payment 버튼 텍스트 색상
    public static final Color ButtonChecked_COLOR = new Color(188, 108, 37); // Amount 배경, 식당 버튼 선택시 색상
    public static final Color Button_COLOR = new Color(96, 108, 56); // 메뉴 패널, 식당버튼 배경색
    public static final Color Payment_COLOR = new Color(254, 250, 224); // payment,quantity 배경색 .. 타이틀,식당 텍스트 색상
    public static final Color AmountChecked_COLOR = new Color(163, 83, 12);
    public static final Color PaymentChecked_COLOR = new Color(214, 210, 189);
}
