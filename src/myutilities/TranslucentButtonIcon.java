package myutilities;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class TranslucentButtonIcon implements Icon {
    private static final int R = 8;
    private int width;
    private int height;

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (c instanceof AbstractButton) {
            AbstractButton b = (AbstractButton) c;
            Insets i = b.getMargin();
            int w = c.getWidth();
            int h = c.getHeight();
            width = w - i.left - i.right;
            height = h - i.top - i.bottom;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Shape area = new RoundRectangle2D.Float(x - i.left, y - i.top, w - 1, h - 1, R, R);
            Color color = new Color(0f, 0f, 0f, .3f);
            ButtonModel m = b.getModel();
            if (m.isPressed()) {
                color = new Color(0f, 0f, 0f, .3f);
            } else if (m.isRollover()) {
                color = new Color(1f, 1f, 1f, .3f);
            }
            g2.setPaint(color);
            g2.fill(area);
            g2.setPaint(Color.WHITE);
            g2.draw(area);
            g2.dispose();
        }
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
