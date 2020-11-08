package myutilities;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel {
    private JPanel mainPanel;
    private JLabel imageLabel;
    public ImagePanel(String url){
        imageLabel = new JLabel();
        mainPanel = createImagePanel(url);
    }
    public ImagePanel(){
        imageLabel = new JLabel();
        mainPanel = new JPanel();
        setComponents();
    }
    private void setComponents(){
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imageLabel);
    }
    private JPanel createImagePanel(String url){
        if (url.equals("")){
            return new JPanel();
        }
        else{
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            imageLabel.setIcon(new ImageIcon(getImage(url)));
            panel.add(imageLabel);
            return panel;
        }
    }
    private BufferedImage getImage(String url){
        try{
            return ImageIO.read(CreateShortcuts.class.getResource(url));
        }
        catch(IOException e){
            System.out.println(e);
            return null;
        }
    }
    public void setImage(String url){
        this.imageLabel.setIcon(new ImageIcon(getImage(url)));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }
}
