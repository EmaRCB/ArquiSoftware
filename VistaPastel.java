import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;

public class VistaPastel extends Vista {
    JPanel panel;
    JFrame ventana;

    DefaultPieDataset data = new DefaultPieDataset();

    Bitacora b1 = new Bitacora(this.getClass());
    public VistaPastel(){
    }

    private void iniciar(){
        ventana.add(panel);
        ventana.setLocation(0,500);

        JFreeChart chart = ChartFactory.createPieChart(
                "Conteo de votos", data, true, true, false);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : {1} votos");
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }

    @Override
    public void update(Modelo model) {
        b1.log.debug("SE HIZO UN UPDATE EN LA VISTA PASTEL");
        Integer[] valores = (Integer[]) model.getData();
        data.setValue("Candidato 1", valores[0]);
        data.setValue("Candidato 2", valores[1]);
        data.setValue("Candidato 3", valores[2]);
    }

    @Override
    public void initView() {
        panel = new JPanel();
        ventana = new JFrame("Conteo de votos pastel");
        ventana.setSize(500,500);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        iniciar();
        ventana.pack();
        ventana.setVisible(true);
    }



}
