package edu.cs.clemson.jymonte.dissertation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.camera.NewtCameraMouseController;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;


public class ScreenShotTest extends JFrame {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScreenShotTest().setVisible(true);
            }
        });
    }
        
    private final Chart chart;
    private JButton button;
    private JLabel label;
    private JPanel panel;
    private JPanel topPanel;
    
    public ScreenShotTest() {
        initComponents();
        int size = 10000;
        float x;
        float y;
        float z;
        float a;
        Coord3d[] points = new Coord3d[size];
        Color[] colors = new Color[size];
        Random r = new Random();
        r.setSeed(0);
        for(int i = 0; i<size; i++) {
            x = r.nextFloat()-0.5f;
            y = r.nextFloat()-0.5f;
            z = r.nextFloat()-0.5f;
            points[i] = new Coord3d(x, y, z);
            a = 0.5f;
            colors[i] = new Color(x, y, z, a);
        }
        Scatter scatter = new Scatter(points, colors);
        scatter.setWidth(6);
        chart = AWTChartComponentFactory.chart(Quality.Nicest, "newt");
        chart.addController(new NewtCameraMouseController());
        chart.getScene().add(scatter);

        panel.add((Component)chart.getCanvas());
        panel.add(label);
    }

//    public BufferedImage getScreenShot() {
//        try {
//            TextureIO.write(chart.screenshot(), new File("c:/screenShot.png"));
//        } catch(IOException|GLException e) {
//            System.err.println(e);
//        }
//        AWTRenderer3d renderer = (AWTRenderer3d)chart.getCanvas().getRenderer();
//        renderer.nextDisplayUpdateScreenshot();
//        chart.render();
//        BufferedImage screenShot = renderer.getLastScreenshotImage();
//        return screenShot;
//    }


    private void initComponents() {
        label = new JLabel();
        topPanel = new JPanel();
        button = new JButton();
        panel = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 430));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        button.setText("Take ScreenShot");
        button.setAlignmentX(1.0F);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                label.setIcon(new ImageIcon(getScreenShot()));
//            }
//        });
        topPanel.add(button);
        getContentPane().add(topPanel, BorderLayout.PAGE_START);
        panel.setBackground(new java.awt.Color(153, 204, 255));
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(new GridLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
    }
}