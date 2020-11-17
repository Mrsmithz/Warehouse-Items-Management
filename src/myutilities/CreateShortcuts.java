package myutilities;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import java.net.URL;
public class CreateShortcuts {
    public static BufferedImage roundedImage(String path, int width, int height)throws IOException{
        BufferedImage original = ImageIO.read(new File(path));
        BufferedImage master = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dd = master.createGraphics();
        g2dd.setBackground(Color.WHITE);
        g2dd.setPaint(Color.WHITE);
        g2dd.fillRect(0,0,master.getWidth(), master.getHeight());
        g2dd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2dd.drawImage(original, 0, 0, width, height, null);
        int diameter = Math.min(master.getWidth(), master.getHeight());
        BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = mask.createGraphics();
        applyQualityRenderingHints(g2d);
        g2d.fillOval(0, 0, diameter-1, diameter-1);
        g2d.dispose();

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2d = masked.createGraphics();
        applyQualityRenderingHints(g2d);
        int y = (diameter - master.getHeight()) / 2;
        int x = (diameter - master.getWidth()) / 2;
        g2d.drawImage(master,x,y,null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g2d.drawImage(mask,0,0,null);
        g2d.dispose();
        return masked;
    }
    public static void applyQualityRenderingHints(Graphics2D g2d) {

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }
    public static JPanel createImagePanel(String url){
        if (url.equals("")){
            return new JPanel();
        }
        else{
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JLabel test = new JLabel(new ImageIcon(getImage(url)));
            panel.add(test);
            return panel;
        }
    }

    private static BufferedImage getImage(String url){
        try{
            return ImageIO.read(CreateShortcuts.class.getResource(url));
        }
        catch(IOException e){
            System.out.println(e);
            return null;
        }
    }

    public static JInternalFrame createMyJInternalFrame(String title, boolean resizeable, boolean closable, boolean maximizable, boolean iconifiable){
        JInternalFrame frame = new JInternalFrame(title, resizeable, closable, maximizable, iconifiable);
        try{
            frame.setMaximum(true);
        }
        catch(java.beans.PropertyVetoException error){
            System.out.println(error);
        }
        frame.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        return frame;
    }
}
