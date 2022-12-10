

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import javax.swing.*;

public class VistaBarras extends Vista {
    JPanel panel;
    JFrame ventana;

    DefaultCategoryDataset data;

    Bitacora b1 = new Bitacora(this.getClass());
    public VistaBarras() {
    }

    private void iniciar(){
        ventana.add(panel);
        ventana.setLocation(0,0);

        data = createDataset();

        //Create chart
        JFreeChart chart= ChartFactory.createBarChart(
                "Conteo de votos", "Candidatos", "Número de votos", data,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setBaseItemLabelsVisible(true);

        chart.getCategoryPlot().setRenderer(renderer);
        chart.getCategoryPlot().getRangeAxis().setVisible(false);

        ChartPanel chartPanel=new ChartPanel(chart);
        panel.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(0, "1", "");
        dataset.addValue(0, "2", "");
        dataset.addValue(0, "3", "");

        return dataset;
    }

    @Override
    public void update(Modelo model) {
        b1.log.debug("SE HIZO UN UPDATE EN LAS BARRAS");
        Integer[] valores = (Integer[]) model.getData();
        data.setValue(valores[0], "1", "");
        data.setValue(valores[1], "2", "");
        data.setValue(valores[2], "3", "");
    }

    @Override
    public void initView() {
        panel = new JPanel();
        ventana = new JFrame("Conteo de votos barras");
        ventana.setSize(500,500);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        iniciar();
        ventana.pack();
        ventana.setVisible(true);

    }

}
