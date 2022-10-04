package quienesquien.newpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPuntuacion implements ActionListener {

    JFrame framePrincipal = new JFrame();
    JPanel panelFormulario = new JPanel();
    JPanel panelBotones = new JPanel();
    JLabel enhorabuena = new JLabel ("¡Enhorabuena has acertado!");
    JLabel nombreJugador = new JLabel("Introduce tus iniciales: ");
    JButton botonEnviar = new JButton("Enviar");
    JButton botonCancelar = new JButton("Cancelar");
    JTextField textIniciales = new JTextField(25);
    JFrame frameJuego = new JFrame();
    int puntos = 0;
    String iniciales = "";

    //La ventana puntuación obtiene por parámetros la puntuación del jugador y el JFrame de la ventana de juego
    //Esto último es para poder cerrar la ventana de juego desde la ventana de puntuación.
    VentanaPuntuacion(int puntos, JFrame frameJuego) {
        this.puntos = puntos;
        this.frameJuego = frameJuego;
        JLabel puntuacionTexto = new JLabel("Tu puntuación es: " + VentanaPuntuacion.this.puntos);

        botonEnviar.setFocusable(false);
        botonCancelar.setFocusable(false);
        botonEnviar.addActionListener(this);
        botonCancelar.addActionListener(this);

        panelBotones.setLayout(null);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setBounds(0, 125, 400, 75);
        panelBotones.setBackground(Color.gray);
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBounds(0, 0, 400, 125);
        panelFormulario.setBackground(Color.gray);
        textIniciales.setSize(150, 20);
        textIniciales.setMaximumSize(textIniciales.getPreferredSize());
        enhorabuena.setAlignmentX(Component.CENTER_ALIGNMENT);
        puntuacionTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreJugador.setAlignmentX(Component.CENTER_ALIGNMENT);
        framePrincipal.setLayout(null);
        framePrincipal.setSize(416, 240);
        framePrincipal.add(panelFormulario);
        framePrincipal.add(panelBotones);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelBotones.add(botonEnviar);
        panelBotones.add(botonCancelar);
        panelFormulario.add(enhorabuena);
        panelFormulario.add(puntuacionTexto);
        panelFormulario.add(nombreJugador);
        panelFormulario.add(textIniciales);
        framePrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //El botón enviar cierra la ventana de puntuación y pasa las iniciales escritas por el usuario al método para escribir en el fichero.
        if (e.getSource() == botonEnviar) {
            framePrincipal.dispose();
            iniciales = textIniciales.getText();
            try {
                escribirPuntuacion(iniciales);

            } catch (IOException x) {
                System.out.println("Ha habido un error.");
                x.printStackTrace();
            }

            //El botón cancelar cierra la ventana de puntuaciones y la ventana de juego.
        } else if (e.getSource() == botonCancelar) {
            framePrincipal.dispose();
            cerrarJuego();
        }
    }
    
    public void escribirPuntuacion(String iniciales) throws IOException {
        try {
            File fichero = new File("puntuacion.txt");
            
            //Comprueba que el fichero no esté creado para crearlo.
            if (fichero.createNewFile()) {
                System.out.println("Documento creado: " + fichero.getName());
            } else {
                System.out.println("El documento ya existe.");
            }

            FileWriter myWriter = new FileWriter("puntuacion.txt");
            
            //Escribe las iniciales y la puntuación del jugador en el documento

            myWriter.write(String.valueOf(iniciales + " -------> " + VentanaPuntuacion.this.puntos));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Ha habido un error.");
            e.printStackTrace();
        }
    }
    
    public void cerrarJuego(){
        VentanaPuntuacion.this.frameJuego.dispose();
    }
   
}
