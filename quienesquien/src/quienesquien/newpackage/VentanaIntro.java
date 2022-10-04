package quienesquien.newpackage;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class VentanaIntro implements ActionListener {

    JFrame frame = new JFrame();
    FlowLayout layout = new FlowLayout();
    JButton botonJugar = new JButton();
    JButton botonSalir = new JButton();
    FondoPanel fondo = new FondoPanel();
    JLabel titulo = new JLabel("ADIVINA EL PERSONAJE");

    public VentanaIntro() {

        botonJugar.setFocusable(false);
        botonSalir.setFocusable(false);
        botonJugar.addActionListener(this);
        botonSalir.addActionListener(this);

        //Creamos ventana con imagen de fondo y botones para jugar y salir
        frame.setContentPane(fondo);
        frame.add(titulo);
        frame.add(botonJugar);
        frame.add(botonSalir);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(layout);
        frame.setVisible(true);
        titulo.setFont(new Font("Calibri", Font.CENTER_BASELINE, 48));

        try {
            Image icono = ImageIO.read(getClass().getResource("start.jpg"));
            botonJugar.setIcon(new ImageIcon(icono));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image icono = ImageIO.read(getClass().getResource("exit.jpg"));
            botonSalir.setIcon(new ImageIcon(icono));
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //El boton de jugar cierra la ventana y abre la ventana de juego
        if (e.getSource() == botonJugar) {
            frame.dispose();
            VentanaJuego ventanaJuego = new VentanaJuego();
        } else if (e.getSource() == botonSalir) {
            frame.dispose();
        }
    }

    class FondoPanel extends JPanel {

        public Image imagen;

        @Override
        public void paint(Graphics g) {
            ImageIcon imagen = new ImageIcon(getClass().getResource("img/aot2.jpg"));
            g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
            super.paint(g);
        }
    }

}
