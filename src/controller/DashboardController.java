package controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
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
        updateChart();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateChart();
            }
        }, 1000, 1000);
    }
    private ChartPanel createPieChart(String title, DefaultPieDataset dataset){
        JFreeChart piechart = ChartFactory.createPieChart3D(title, dataset, false, true, false);
        PiePlot piePlot = (PiePlot)piechart.getPlot();
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} : {1} ({2})");
        piePlot.setLabelGenerator(gen);
        piePlot.setForegroundAlpha(0.5f);
        ChartPanel chartPanel = new ChartPanel(piechart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return chartPanel;
    }
    private ChartPanel createBarChart(String title, DefaultCategoryDataset dataset){
        JFreeChart barchart = ChartFactory.createBarChart(title, "", "Item Prices", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot cplot = (CategoryPlot)barchart.getPlot();
        cplot.setBackgroundPaint(SystemColor.inactiveCaption);
        ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer renderer = (BarRenderer)barchart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new Color(255, 100, 111));
        ChartPanel chartPanel = new ChartPanel(barchart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15,15));
        return chartPanel;
    }
    private DefaultCategoryDataset createPricesBarDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 1;
        for (HashMap<String, Object> map : data){
            if (count <= 10){
                dataset.setValue((double)map.get("item_price"), "Item Prices", (Comparable) map.get("item_name"));
                count++;
            }
            else{
                break;
            }
        }
        return dataset;
    }
    private DefaultPieDataset createQuantitiesPieDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultPieDataset dataset = new DefaultPieDataset();
        int count = 1;
        for (HashMap<String, Object> map : data) {
            if (count <= 10){
                dataset.setValue((Comparable) map.get("item_name"), (int)map.get("quantity"));
                count++;
            }
            else{
                break;
            }
        }
        return dataset;
    }
    private DefaultPieDataset createTypesPieDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultPieDataset dataset = new DefaultPieDataset();
        HashMap<String, Integer> datamap = new HashMap<>();
        int count = 1;
        for (HashMap<String, Object> map : data){
            if (count <= 10)
                if (!datamap.containsKey(map.get("item_type"))){
                    datamap.put((String) map.get("item_type"), 1);
                }
                else{
                    String key = (String)map.get("item_type");
                    datamap.put(key, datamap.get(key)+1);
                }
            else {
                break;
            }
        }
        for(String keys : datamap.keySet()){
            dataset.setValue((Comparable)keys, (int)datamap.get(keys));
        }
        return dataset;
    }
    private DefaultCategoryDataset createWeightDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 1;
        for (HashMap<String, Object> map: data){
            if (count <= 10){
                dataset.setValue((double)map.get("item_weight"), "Item Weight", (Comparable) map.get("item_name"));
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
    private void updateChart(){
        if (mc.getUser() != null) {
            this.dashboardGUI.getTypesPieChartPanel().removeAll();
            this.dashboardGUI.getQuantitiesPieChartPanel().removeAll();
            this.dashboardGUI.getPricesBarChartPanel().removeAll();
            this.dashboardGUI.getWeightBarChartPanel().removeAll();
            this.dashboardGUI.getQuantitiesPieChartPanel().add(createPieChart("ITEM QUANTITIES", createQuantitiesPieDataset()));
            this.dashboardGUI.getTypesPieChartPanel().add(createPieChart("ITEM TYPES", createTypesPieDataset()));
            this.dashboardGUI.getPricesBarChartPanel().add(createBarChart("ITEM PRICES", createPricesBarDataset()));
            this.dashboardGUI.getWeightBarChartPanel().add(createBarChart("ITEM WEIGHTS", createWeightDataset()));
            this.dashboardGUI.getQuantitiesPieChartPanel().validate();
            this.dashboardGUI.getWeightBarChartPanel().validate();
            this.dashboardGUI.getPricesBarChartPanel().validate();
            this.dashboardGUI.getTypesPieChartPanel().validate();
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
