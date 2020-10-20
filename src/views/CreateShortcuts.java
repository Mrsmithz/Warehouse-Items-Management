package views;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.*;
public class CreateShortcuts {
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
