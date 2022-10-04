/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quienesquien.newpackage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
class Lamina extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("QUIÉN PARECE QUIÉN", 300, 100);
        File miimagen = new File("src/quienesquien/newpackage/aot2.jpg");
        try {
            imagen = ImageIO.read(miimagen);
        } catch (IOException e) {
            System.out.println("la imagen no se encuentra");
        }
        //int anchoImagen = imagen.getWidth(this);
        //int alturaImagen = imagen.getHeight(this);
        g.drawImage(imagen, 0, 0, 800, 600, null);

    }
    private Image imagen;
}