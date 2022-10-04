package quienesquien.newpackage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class VentanaJuego implements ActionListener {

    JFrame framePrincipal = new JFrame();

    //Definimos la ventana con todos sus elementos:
    //un menú, botones de personajes, botones de preguntas, texto con instrucciones,
    //Indicaciones de si el jugador ha acertado o no, etc.
    String instrucciones1 = "Pulsa las preguntas para descartar personajes.";
    String instrucciones2 = "Cuando creas saber cuál es el personaje, pulsa en él.";
    String instrucciones3 = "Por cada pregunta, suma un punto.";
    String instrucciones4 = "Por cada intento fallido, suma dos puntos.";
    String instrucciones5 = "Cuantos menos puntos, mejor.";

    JMenuBar menuBar = new JMenuBar();
    JMenu opciones = new JMenu("Opciones");
    JMenuItem nuevaPartida = new JMenuItem("Nueva partida");
    JMenuItem puntuacion = new JMenuItem("Puntuación");
    JMenuItem salir = new JMenuItem("Salir");

    JLabel tuPersonaje = new JLabel("Tu personaje:");   
    JButton pregunta1 = new JButton("¿Es una mujer?");
    JButton pregunta2 = new JButton("¿Es rubio/a?");
    JButton pregunta3 = new JButton("¿tiene algún complemento?");
    JButton pregunta4 = new JButton("¿Es mayor?");
    JButton pregunta5 = new JButton("¿Tiene ropa verde?");
    JButton pregunta6 = new JButton("¿Está muy serio?");
    JLabel respuestaCorrecta = new JLabel("");
    JLabel respuestaIncorrecta = new JLabel("");
    JLabel instruccionesLabel1 = new JLabel(instrucciones1);
    JLabel instruccionesLabel2 = new JLabel(instrucciones2);
    JLabel instruccionesLabel3 = new JLabel(instrucciones3);
    JLabel instruccionesLabel4 = new JLabel(instrucciones4);
    JLabel instruccionesLabel5 = new JLabel(instrucciones5);
    
    JButton personaje1 = new JButton("Eren");
    JButton personaje2 = new JButton("Mikasa");
    JButton personaje3 = new JButton("Zeke");
    JButton personaje4 = new JButton("Instructor");
    JButton personaje5 = new JButton("Dotpixis");
    JButton personaje6 = new JButton("Armin");
    JButton personaje7 = new JButton("Hange");
    JButton personaje8 = new JButton("Gabi");
    JButton personaje9 = new JButton("Anie");
    JButton botonAdivinar = new JButton("Adivinar personaje");

    JPanel orangePanel = new JPanel();
    JPanel greenPanel = new JPanel();
    JPanel pinkPanel = new JPanel();

    Personaje personajeSolucion = new Personaje();

    int puntos = 0;

    VentanaJuego() {

        //Abrimos conexión con la base de datos para obtener los personajes;
        ConexionDb conn = new ConexionDb();
        Personaje[] listaPersonajes = conn.traerPersonajes();

        //Inicializar  menu
        menuBar.add(opciones);
        opciones.add(nuevaPartida);
        opciones.add(puntuacion);
        opciones.add(salir);

        //Inicializar panel de los personajes
        orangePanel.setBackground(Color.gray);
        orangePanel.setBounds(0, 0, 650, 650);
        orangePanel.setLayout(new GridLayout(3, 3));
        orangePanel.add(personaje1);
        orangePanel.add(personaje2);
        orangePanel.add(personaje3);
        orangePanel.add(personaje4);
        orangePanel.add(personaje5);
        orangePanel.add(personaje6);
        orangePanel.add(personaje7);
        orangePanel.add(personaje8);
        orangePanel.add(personaje9);

        personaje1.addActionListener(this);
        personaje2.addActionListener(this);
        personaje3.addActionListener(this);
        personaje4.addActionListener(this);
        personaje5.addActionListener(this);
        personaje6.addActionListener(this);
        personaje7.addActionListener(this);
        personaje8.addActionListener(this);
        personaje9.addActionListener(this);
        nuevaPartida.addActionListener(this);
        salir.addActionListener(this);
        puntuacion.addActionListener(this);
        
        tuPersonaje.setFont(new Font("Calibri", Font.PLAIN, 25));

        //Asignamos a cada personaje su imagen
        try {
            Image img1 = ImageIO.read(getClass().getResource("img/eren.jpg"));
            personaje1.setIcon(new ImageIcon(img1));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img2 = ImageIO.read(getClass().getResource("img/mikasa.bmp"));
            personaje2.setIcon(new ImageIcon(img2));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img3 = ImageIO.read(getClass().getResource("img/zeke.bmp"));
            personaje3.setIcon(new ImageIcon(img3));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            Image img4 = ImageIO.read(getClass().getResource("img/instructor.bmp"));
            personaje4.setIcon(new ImageIcon(img4));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img5 = ImageIO.read(getClass().getResource("img/dot.bmp"));
            personaje5.setIcon(new ImageIcon(img5));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img6 = ImageIO.read(getClass().getResource("img/armin.bmp"));
            personaje6.setIcon(new ImageIcon(img6));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img7 = ImageIO.read(getClass().getResource("img/hange.bmp"));
            personaje7.setIcon(new ImageIcon(img7));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img8 = ImageIO.read(getClass().getResource("img/gabi.bmp"));
            personaje8.setIcon(new ImageIcon(img8));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img9 = ImageIO.read(getClass().getResource("img/anie.bmp"));
            personaje9.setIcon(new ImageIcon(img9));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //Inicializar panel de preguntas
        greenPanel.setBackground(Color.gray);
        greenPanel.setBounds(650, 0, 350, 650);
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        greenPanel.add(tuPersonaje);
        greenPanel.add(pregunta1);
        greenPanel.add(pregunta2);
        greenPanel.add(pregunta3);
        greenPanel.add(pregunta4);
        greenPanel.add(pregunta5);
        greenPanel.add(pregunta6);
        greenPanel.add(instruccionesLabel1);
        greenPanel.add(instruccionesLabel2);
        greenPanel.add(instruccionesLabel3);
        greenPanel.add(instruccionesLabel4);
        greenPanel.add(instruccionesLabel5);     
        

        //Inicializar panel de respuesta correcta/incorrecta
        pinkPanel.setBackground(Color.gray);
        pinkPanel.setBounds(0, 650, 1000, 150);
        pinkPanel.add(respuestaCorrecta);
        pinkPanel.add(respuestaIncorrecta);

        //Inicializamos ventana de juego y metemos sus componentes
        framePrincipal.setJMenuBar(menuBar);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1000, 800);
        framePrincipal.setLayout(null);
        framePrincipal.setVisible(true);
        framePrincipal.add(orangePanel);
        framePrincipal.add(greenPanel);
        framePrincipal.add(pinkPanel);

        pregunta1.addActionListener(this);
        pregunta2.addActionListener(this);
        pregunta3.addActionListener(this);
        pregunta4.addActionListener(this);
        pregunta5.addActionListener(this);
        pregunta6.addActionListener(this);

        //Mediante el método getRandom seleccionamos a un personaje aleatorio de la lista de personajes
        personajeSolucion = listaPersonajes[getRandom(listaPersonajes.length - 1)];
        personajeSolucion = listaPersonajes[7];
       
    }

    public static int getRandom(int max) {
        return (int) (Math.random() * max);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Listener de las preguntas. Determina los personajes que se tachan cuando se selecciona una pregunta
        if (e.getSource() == pregunta1 && personajeSolucion.getGenero().equals("Mujer")) {
            puntos++;
            try {
                Image img1c = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img3c = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img4c = ImageIO.read(getClass().getResource("img/instructorc.bmp"));
                Image img5c = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                Image img6c = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                personaje1.setIcon(new ImageIcon(img1c));
                personaje3.setIcon(new ImageIcon(img3c));
                personaje4.setIcon(new ImageIcon(img4c));
                personaje5.setIcon(new ImageIcon(img5c));
                personaje6.setIcon(new ImageIcon(img6c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta1 && !personajeSolucion.getGenero().equals("Mujer")) {
            puntos++;
            try {
                Image img2c = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img7c = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                Image img8c = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                Image img9c = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje2.setIcon(new ImageIcon(img2c));
                personaje7.setIcon(new ImageIcon(img7c));
                personaje8.setIcon(new ImageIcon(img8c));
                personaje9.setIcon(new ImageIcon(img9c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta4 && personajeSolucion.getEdad()) {
            puntos++;
            try {
                Image img1c = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img2c = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img6c = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                Image img7c = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                Image img8c = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                Image img9c = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje1.setIcon(new ImageIcon(img1c));
                personaje2.setIcon(new ImageIcon(img2c));
                personaje6.setIcon(new ImageIcon(img6c));
                personaje7.setIcon(new ImageIcon(img7c));
                personaje8.setIcon(new ImageIcon(img8c));
                personaje9.setIcon(new ImageIcon(img9c));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta4 && !personajeSolucion.getEdad()) {
            puntos++;
            try {
                Image img3c = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img4c = ImageIO.read(getClass().getResource("img/instructorc.bmp"));
                Image img5c = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                personaje3.setIcon(new ImageIcon(img3c));
                personaje4.setIcon(new ImageIcon(img4c));
                personaje5.setIcon(new ImageIcon(img5c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta2 && personajeSolucion.getRubio()) {
            puntos++;
            try {
                Image img1c = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img2c = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img4c = ImageIO.read(getClass().getResource("img/instructorc.bmp"));
                Image img5c = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                Image img7c = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                Image img8c = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                personaje1.setIcon(new ImageIcon(img1c));
                personaje2.setIcon(new ImageIcon(img2c));
                personaje4.setIcon(new ImageIcon(img4c));
                personaje5.setIcon(new ImageIcon(img5c));
                personaje7.setIcon(new ImageIcon(img7c));
                personaje8.setIcon(new ImageIcon(img8c));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta2 && !personajeSolucion.getRubio()) {
            puntos++;
            try {
                Image img3c = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img6c = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                Image img9c = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje3.setIcon(new ImageIcon(img3c));
                personaje6.setIcon(new ImageIcon(img6c));
                personaje9.setIcon(new ImageIcon(img9c));
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else if (e.getSource() == pregunta3 && personajeSolucion.getComplementos()) {
            puntos++;
            try {
                Image img1c = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img4c = ImageIO.read(getClass().getResource("img/intructorc.bmp"));
                Image img5c = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                Image img6c = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                Image img8c = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                Image img9c = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje1.setIcon(new ImageIcon(img1c));
                personaje4.setIcon(new ImageIcon(img4c));
                personaje5.setIcon(new ImageIcon(img5c));
                personaje6.setIcon(new ImageIcon(img6c));
                personaje8.setIcon(new ImageIcon(img8c));
                personaje9.setIcon(new ImageIcon(img9c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta3 && !personajeSolucion.getComplementos()) {
            puntos++;
            try {
                Image img2c = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img3c = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img7c = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                personaje2.setIcon(new ImageIcon(img2c));
                personaje3.setIcon(new ImageIcon(img3c));
                personaje7.setIcon(new ImageIcon(img7c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta5 && personajeSolucion.getRopaVerde()) {
            puntos++;
            try {
                Image img1c = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img2c = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img3c = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img5c = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                Image img8c = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                Image img9c = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje1.setIcon(new ImageIcon(img1c));
                personaje2.setIcon(new ImageIcon(img2c));
                personaje3.setIcon(new ImageIcon(img3c));
                personaje5.setIcon(new ImageIcon(img5c));
                personaje8.setIcon(new ImageIcon(img8c));
                personaje9.setIcon(new ImageIcon(img9c));

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta5 && !personajeSolucion.getRopaVerde()) {
            puntos++;
            try {
                Image img6c = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                Image img4c = ImageIO.read(getClass().getResource("img/instructorc.bmp"));
                Image img7c = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                personaje6.setIcon(new ImageIcon(img6c));
                personaje4.setIcon(new ImageIcon(img4c));
                personaje7.setIcon(new ImageIcon(img7c));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta6 && personajeSolucion.getSeriedad()) {
            puntos++;
            try {
                Image img1 = ImageIO.read(getClass().getResource("img/erenc.jpg"));
                Image img2 = ImageIO.read(getClass().getResource("img/mikasac.bmp"));
                Image img5 = ImageIO.read(getClass().getResource("img/dotc.bmp"));
                Image img8 = ImageIO.read(getClass().getResource("img/gabic.bmp"));
                personaje1.setIcon(new ImageIcon(img1));
                personaje2.setIcon(new ImageIcon(img2));
                personaje5.setIcon(new ImageIcon(img5));
                personaje8.setIcon(new ImageIcon(img8));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == pregunta6 && !personajeSolucion.getSeriedad()) {
            puntos++;
            try {
                Image img3 = ImageIO.read(getClass().getResource("img/zekec.bmp"));
                Image img4 = ImageIO.read(getClass().getResource("img/instructorc.bmp"));
                Image img6 = ImageIO.read(getClass().getResource("img/arminc.bmp"));
                Image img7 = ImageIO.read(getClass().getResource("img/hangec.bmp"));
                Image img9 = ImageIO.read(getClass().getResource("img/aniec.bmp"));
                personaje3.setIcon(new ImageIcon(img3));
                personaje4.setIcon(new ImageIcon(img4));
                personaje6.setIcon(new ImageIcon(img6));
                personaje7.setIcon(new ImageIcon(img7));
                personaje9.setIcon(new ImageIcon(img9));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        //Listener de los personajes. Si el personaje es correcto, abre la ventana de puntuación. Si es incorrecto lo notifica y suma puntos.
        if (e.getSource() == personaje1 && personajeSolucion.getNombre().equals("Eren")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje1 && !personajeSolucion.getNombre().equals("Eren")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje2 && personajeSolucion.getNombre().equals("Mikasa")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje2 && !personajeSolucion.getNombre().equals("Mikasa")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje3 && personajeSolucion.getNombre().equals("Zeke")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje3 && !personajeSolucion.getNombre().equals("Zeke")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje4 && personajeSolucion.getNombre().equals("Instructor")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje4 && !personajeSolucion.getNombre().equals("Instructor")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje5 && personajeSolucion.getNombre().equals("Dotpixis")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje5 && !personajeSolucion.getNombre().equals("Dotpixis")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje6 && personajeSolucion.getNombre().equals("Armin")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje6 && !personajeSolucion.getNombre().equals("Armin")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje7 && personajeSolucion.getNombre().equals("Hange")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje7 && !personajeSolucion.getNombre().equals("Hange")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje8 && personajeSolucion.getNombre().equals("Gabi")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje8 && !personajeSolucion.getNombre().equals("Gabi")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        } else if (e.getSource() == personaje9 && personajeSolucion.getNombre().equals("Anie")) {
            respuestaCorrecta.setText("Correcto");
            VentanaPuntuacion ventanaPuntuacion = new VentanaPuntuacion(puntos, framePrincipal);
        } else if (e.getSource() == personaje9 && !personajeSolucion.getNombre().equals("Anie")) {
            puntos = puntos + 2;
            respuestaCorrecta.setText("Error");
        }

        if (e.getSource() == nuevaPartida) {
            framePrincipal.dispose();
            VentanaJuego ventanaJuego = new VentanaJuego();
        } else if (e.getSource() == salir) {
            framePrincipal.dispose();
        } else if (e.getSource() == puntuacion) {
            File file = new File("puntuacion.txt");

            Desktop desktop = Desktop.getDesktop();

            if (file.exists()) {
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            file = new File("puntuacion.txt");

            if (file.exists()) {
                try {
                    desktop.open(file);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
