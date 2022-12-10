
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class VistaVotos{
    private JButton botonChip;
    private JButton botonBrad;
    private JButton botonMack;
    private JPanel pane;

    public VistaVotos() {
        iniciar();
    }

    private void iniciar() {
        JFrame ventanaVotos = new JFrame("Votos");
        ventanaVotos.setSize(400,436);
        ventanaVotos.setTitle("Presidential Election Process");
        ventanaVotos.setLocationRelativeTo(null);
        ventanaVotos.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventanaVotos.setResizable(false);
        ventanaVotos.setLayout(null);
        ventanaVotos.setVisible(true);

        ////////////////////SECCIÓN DE DISEÑO DE LA VENTANA DE VOTOS
        Color colorComponentes = new Color(191, 216, 254);
        Color colorComponentes2 = new Color(142, 183, 247);
        pane = new JPanel();
        pane.setBounds(0, 0, 400, 400);
        pane.setBackground(colorComponentes);
        pane.setLayout(null);
        //ventanaVotos.getContentPane().add(panel);

        botonChip = new JButton("Chip Wright");
        botonChip.setBackground(colorComponentes2);
        botonChip.setBounds(70, 70, 260, 40);
        botonChip.setForeground(Color.white);
        botonChip.setFont(new Font("Cousine", 1, 16));
        pane.add(botonChip);

        botonMack = new JButton("Mack SF");
        botonMack.setBackground(colorComponentes2);
        botonMack.setBounds(70, 170, 260, 40);
        botonMack.setForeground(Color.white);
        botonMack.setFont(new Font("Cousine", 1, 16));
        pane.add(botonMack);

        botonBrad = new JButton("Brad Kalvo");
        botonBrad.setBackground(colorComponentes2);
        botonBrad.setBounds(70, 270, 260, 40);
        botonBrad.setForeground(Color.white);
        botonBrad.setFont(new Font("Cousine", 1, 16));
        pane.add(botonBrad);

        ventanaVotos.setContentPane(pane);

        ActionListener actionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("aaaaaaa");
            }
        };
        botonBrad.addActionListener(actionListener);
        botonChip.addActionListener(actionListener);
    }

}
