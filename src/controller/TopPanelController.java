package controller;
import views.*;
public class TopPanelController {
    private TopPanel topPanel;
    public TopPanelController(){
        this.topPanel = new TopPanel(this);
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }
}
