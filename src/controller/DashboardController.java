package controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;
public class DashboardController {
    private DashboardGUI dashboardGUI;
    private MainController mc;
    private Timer timer;
    public DashboardController(MainController mc){
        this.mc = mc;
        this.dashboardGUI = new DashboardGUI(this);
        this.dashboardGUI.getPieChartPanel().add(createPieChart());
        this.dashboardGUI.getBarChartPanel().add(createBarChart());
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000, 1000);
    }
    private ChartPanel createPieChart(){
        JFreeChart piechart = ChartFactory.createPieChart3D("ITEM QUANTITIES", createPieDataset(), false, true, false);
        ChartPanel chartPanel = new ChartPanel(piechart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return chartPanel;
    }
    private ChartPanel createBarChart(){
        JFreeChart barchart = ChartFactory.createBarChart("ITEM PRICES", "", "Item Prices", createBarDataset(), PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barchart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15,15));
        return chartPanel;
    }
    private DefaultCategoryDataset createBarDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 1;
        for (HashMap<String, Object> map : data){
            if (count <= 20){
                dataset.setValue((double)map.get("item_price"), "Item Prices", (Comparable) map.get("item_name"));
            }
            else{
                break;
            }
        }
        return dataset;
    }
    private DefaultPieDataset createPieDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultPieDataset dataset = new DefaultPieDataset();
        int count = 1;
        for (HashMap<String, Object> map : data) {
            if (count <= 20){
                dataset.setValue((Comparable) map.get("item_name"), (int)map.get("quantity"));
                count++;
            }
            else{
                break;
            }
        }
        return dataset;
    }
    private ArrayList<HashMap<String, Object>> getData(){
        ResultSet rs;
        try{
            rs = mc.getUser().getAllItem();
            ResultSetMetaData meta = rs.getMetaData();
            int col = meta.getColumnCount();
            ArrayList<HashMap<String, Object>> list = new ArrayList<>();
            while (rs.next()){
                HashMap<String, Object> map = new HashMap<>(col);
                for (int i=1;i<=col;i++){
                    map.put(meta.getColumnName(i), rs.getObject(i));
                }
                list.add(map);
            }
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public DashboardGUI getDashboardGUI() {
        return dashboardGUI;
    }

    public void setDashboardGUI(DashboardGUI dashboardGUI) {
        this.dashboardGUI = dashboardGUI;
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }
}
