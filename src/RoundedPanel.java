import javax.swing.*;
import java.awt.*;

/**
 * 코너가 둥근 패널을 구현한 클래스입니다.
 *
 * <p>이 클래스는 JPanel의 기본적인 기능을 확장하여 모서리가 둥근 패널을 생성할 수 있도록 합니다.
 * 사용자 인터페이스 디자인을 보다 세련되게 만들기 위해 사용됩니다.
 * 또한, 둥근 모서리를 가진 버튼을 생성하는 정적 메서드도 제공합니다.</p>
 *
 * @author Seo Woojin
 * @created 2024-12-26
 * @lastModified 2024-12-26
 *
 * @changelog
 * <ul>
 *   <li>2024-12-26: RoundedPanel 클래스 최초 생성 (Seo Woojin)</li>
 *   <li>2024-12-26: 둥근 버튼 생성 기능 추가 (Seo Woojin)</li>
 * </ul>
 */
public class RoundedPanel extends JPanel {
    private int cornerRadius;

    /**
     * 주어진 코너 반경으로 RoundedPanel을 생성합니다.
     *
     * @param cornerRadius 둥근 코너의 반경
     */
    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
    }

    /**
     * 둥근 모서리를 가진 JButton을 생성하는 정적 메서드입니다.
     *
     * @param text           버튼에 표시될 텍스트
     * @param cornerRadius   둥근 코너의 반경
     * @param backgroundColor 버튼의 배경색
     * @param foregroundColor 버튼의 텍스트 색상
     * @return 둥근 모서리를 가진 JButton 객체
     */
    public static JButton createRoundedButton(String text, int cornerRadius, Color backgroundColor, Color foregroundColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // 기본 배경 설정을 비활성화하여 커스텀 배경 색상 유지
            }
        };
        button.setOpaque(false);
        button.setForeground(foregroundColor);
        button.setBorderPainted(false);
        return button;
    }
}
