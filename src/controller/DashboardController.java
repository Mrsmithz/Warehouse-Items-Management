package controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;
public class DashboardController implements MouseListener {
    private DashboardGUI dashboardGUI;
    private MainController mc;
    private Timer timer;
    private ChartPanel typeChartPanel, quantitiesChartPanel, priceChartPanel, weightChartPanel;
    //Kpun
    private Font infoFont;
    public DashboardController(MainController mc){
        this.mc = mc;
        this.dashboardGUI = new DashboardGUI(this);
        setComponents();
        updateChart();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateChart();
            }
        }, 1000, 1000);
    }
    private void setComponents(){
        quantitiesChartPanel = createPieChart("ITEM QUANTITIES", createQuantitiesPieDataset());
        typeChartPanel = createPieChart("ITEM TYPES", createTypesPieDataset());
        priceChartPanel = createBarChart("ITEM PRICES", createPricesBarDataset());
        weightChartPanel = createBarChart("ITEM WEIGHTS", createWeightDataset());
        quantitiesChartPanel.addMouseListener(this);
        typeChartPanel.addMouseListener(this);
        priceChartPanel.addMouseListener(this);
        weightChartPanel.addMouseListener(this);

        this.dashboardGUI.getQuantitiesPieChartPanel().add(quantitiesChartPanel);
        this.dashboardGUI.getTypesPieChartPanel().add(typeChartPanel);
        this.dashboardGUI.getPricesBarChartPanel().add(priceChartPanel);
        this.dashboardGUI.getWeightBarChartPanel().add(weightChartPanel);

        ((PiePlot)quantitiesChartPanel.getChart().getPlot()).setLabelPaint(new Color(254, 163, 127)); //label color
        quantitiesChartPanel.getChart().getTitle().setPaint(new Color(250, 127, 114)); //title color

        ((PiePlot)typeChartPanel.getChart().getPlot()).setLabelPaint(new Color(241, 212, 212));
        typeChartPanel.getChart().getTitle().setPaint(new Color(252, 163, 204));

        priceChartPanel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(245, 180, 97)); //bar color
        priceChartPanel.getChart().getTitle().setPaint(new Color(200, 121, 47)); //title color
        priceChartPanel.getChart().getCategoryPlot().getRangeAxis().setLabelPaint(new Color(255, 224, 93));
        priceChartPanel.getChart().getCategoryPlot().getDomainAxis().setTickLabelPaint(new Color(243, 234, 194));
        priceChartPanel.getChart().getCategoryPlot().getRangeAxis().setTickLabelPaint(new Color(243, 234, 194));

        weightChartPanel.getChart().getCategoryPlot().getRenderer().setSeriesPaint(0, new Color(154, 211, 188));
        weightChartPanel.getChart().getTitle().setPaint(new Color(243, 234, 194));
        weightChartPanel.getChart().getCategoryPlot().getRangeAxis().setLabelPaint(new Color(14, 145, 140));
        weightChartPanel.getChart().getCategoryPlot().getDomainAxis().setTickLabelPaint(new Color(190, 219, 187));
        weightChartPanel.getChart().getCategoryPlot().getRangeAxis().setTickLabelPaint(new Color(190, 219, 187));

    }
    private ChartPanel createPieChart(String title, DefaultPieDataset dataset){
        Color trans = new Color(0xff, 0xff, 0xff, 0);
        try {
            InputStream bodyInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Medium.ttf");
            infoFont = Font.createFont(Font.TRUETYPE_FONT, bodyInput).deriveFont(12f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(infoFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFreeChart piechart = ChartFactory.createPieChart3D(title, dataset, false, true, false);
        PiePlot piePlot = (PiePlot)piechart.getPlot();
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} : {1} ({2})");
        piePlot.setLabelGenerator(gen);
        piePlot.setForegroundAlpha(0.5f);
        piePlot.setShadowGenerator(null);
        piePlot.setBackgroundPaint(trans);
        piePlot.setOutlinePaint(null);
        piePlot.setLabelOutlinePaint(null);
        piePlot.setLabelShadowPaint(null);
        piePlot.setLabelBackgroundPaint(null);
        piePlot.setSectionOutlinesVisible(false);
        //piePlot.setLabelFont(new Font("Angsana New", Font.BOLD, 15));
        piePlot.setLabelFont(infoFont);
        piePlot.setLabelLinkPaint(Color.RED);
        piePlot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
        ChartPanel chartPanel = new ChartPanel(piechart);
        chartPanel.getChart().setBackgroundPaint(trans);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return chartPanel;


    }
    private ChartPanel createBarChart(String title, DefaultCategoryDataset dataset){
        Color trans = new Color(0xff, 0xff, 0xff, 0);
        //Font labelFont = new Font("Angsana New", Font.BOLD, 13);
        try {
            InputStream bodyInput = this.getClass().getResourceAsStream("/font/SukhumvitSet-Medium.ttf");
            infoFont = Font.createFont(Font.TRUETYPE_FONT, bodyInput).deriveFont(12f);
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge2.registerFont(infoFont);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFreeChart barchart = ChartFactory.createBarChart(title, "", title, dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot cplot = (CategoryPlot)barchart.getPlot();
        cplot.getDomainAxis().setLabelFont(infoFont);
        cplot.getRangeAxis().setLabelFont(infoFont);
        cplot.getDomainAxis().setTickLabelFont(infoFont);
        cplot.getRangeAxis().setTickLabelFont(infoFont);
        cplot.setShadowGenerator(null);
        //cplot.getDomainAxis().setTickLabelPaint(Color.RED);
        //cplot.getRangeAxis().setLabelPaint(Color.ORANGE);
        cplot.getRangeAxis().setTickLabelPaint(Color.CYAN);
        cplot.setBackgroundPaint(trans);
        cplot.setOutlinePaint(null);
        ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer renderer = (BarRenderer)barchart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, new Color(150, 100, 111));
        ChartPanel chartPanel = new ChartPanel(barchart);
        chartPanel.getChart().setBackgroundPaint(trans);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 15,15));
        return chartPanel;
    }
    private DefaultCategoryDataset createPricesBarDataset(){
        ArrayList<HashMap<String, Object>> data = getData();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Collections.shuffle(data);
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
        Collections.shuffle(data);
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
        Collections.shuffle(data);
        HashMap<String, Integer> datamap = new HashMap<>();
        int count = 1;
        for (HashMap<String, Object> map : data){
            if (count <= 10)
                if (!datamap.containsKey(map.get("item_type"))){
                    datamap.put((String) map.get("item_type"), 1);
                    count++;
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
        Collections.shuffle(data);
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
        if (mc.getUser() != null && dashboardGUI.getMainFrame().isVisible()) {
            ((PiePlot)this.quantitiesChartPanel.getChart().getPlot()).setDataset(createQuantitiesPieDataset());
            ((PiePlot)this.typeChartPanel.getChart().getPlot()).setDataset(createTypesPieDataset());
            ((CategoryPlot)this.weightChartPanel.getChart().getPlot()).setDataset((createWeightDataset()));
            ((CategoryPlot)this.priceChartPanel.getChart().getPlot()).setDataset(createPricesBarDataset());

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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(quantitiesChartPanel)
        || e.getSource().equals(typeChartPanel)
        || e.getSource().equals(priceChartPanel)
        || e.getSource().equals(weightChartPanel)){
            timer.cancel();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(quantitiesChartPanel)
        || e.getSource().equals(typeChartPanel)
        || e.getSource().equals(priceChartPanel)
        || e.getSource().equals(weightChartPanel)){
            this.timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateChart();
                }
            }, 1000, 1000);
        }
    }
}
