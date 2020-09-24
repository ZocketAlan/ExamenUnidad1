package ExamenU1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FiguraTipoLogoDVD extends JPanel {

    private JFrame frame;
    private Container container;
    private final int[] FIG = {0x0000000,
        0x00001c0,
        0x00001c0,
        0x18003e0,
        0x3c005d0,
        0x7e009c8,
        0xe7fffff,
        0xc3fffff,
        0xc3fffff,
        0xc3009c8,
        0xc3005d0,
        0x81003e0,
        0x00001c0,
        0x00001c0,
        0x0000000};
    private final int MASK = 0x8000000;
    private final int WIDTH = 32;
    private int coordinateX, coordinateY;
    private Thread thread;

    public FiguraTipoLogoDVD() {
        frame = new JFrame("Examen Unidad 1");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        container = frame.getContentPane();
        container.setSize(600, 600);
        container.add(this, BorderLayout.CENTER);

        thread = new Thread();
    }

    @Override
    protected void paintComponent(Graphics grap) {
        super.paintComponent(grap);

//        this.coordinateX = (int)(Math.random()*500);
//        this.coordinateY = (int)(Math.random()*500);
//        System.err.println("X: " + coordinateX + "\tY: " + coordinateY);
        for (int i = 0; i < FIG.length; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int temp = this.FIG[i] & (this.MASK >> j);
                if (temp != 0) {
                    grap.drawLine(this.coordinateX + i,
                            this.coordinateY + j,
                            this.coordinateX + i,
                            this.coordinateY + j);
                }
            }
        }
    }

    public void Draw() throws InterruptedException {
        int collision = 1, sum = 0;
        this.coordinateX = (int) (Math.random() * 600 + 1);
        this.coordinateY = (int) (Math.random() * 600 + 1);
        for (;;) {
            if (coordinateX <= 25 || coordinateX >= 575 || coordinateY <= 25 || coordinateY >= 575) {
                this.coordinateX = (int) (Math.random() * 600 + 1);
                this.coordinateY = (int) (Math.random() * 600 + 1);
                sum += collision;
                System.out.println("Number of collision: " + sum);

                if (sum == 10) {
                    System.exit(0);
                    System.err.println("Game over.");
                }
            } else {

                this.coordinateX += ((int) (Math.floor(Math.random() * 2)) == 1) ? 40 : -40;
                this.coordinateY += ((int) (Math.floor(Math.random() * 2)) == 1) ? 40 : -40;
            }
            paint(getGraphics());
            thread.sleep(100);
        }
    }
}
