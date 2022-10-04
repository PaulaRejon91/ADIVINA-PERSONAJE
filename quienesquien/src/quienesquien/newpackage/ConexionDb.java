package quienesquien.newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDb {

    String url = "jdbc:mysql://localhost:3306/QuienEsQuien";
    String username = "root";
    String password = "";

    public Personaje[] traerPersonajes() {

        System.out.println("Connecting database...");

        try {
            //Conectamos a la base de datos MySQL y hacemos select de los personajes
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT nombre, genero, rubio, complementos, edad, ropaVerde, seriedad FROM quienesquien.personajes";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            
            Personaje[] listaPersonajes = new Personaje[9];        
            
            //Por cada fila de la tabla de personajes que recorre el buble while, 
            //Se inicializa un personaje nuevo del array de personajes y se asignan sus atributos
            int i = 0;    
            while (rs.next()) {
                listaPersonajes[i] = new Personaje();
                listaPersonajes[i].setNombre(rs.getString("nombre"));
                listaPersonajes[i].setGenero(rs.getString("genero"));
                listaPersonajes[i].setEsRubio(rs.getBoolean("rubio"));
                listaPersonajes[i].setComplementos(rs.getBoolean("complementos"));
                listaPersonajes[i].setEsMayor(rs.getBoolean("edad"));
                listaPersonajes[i].setRopaVerde(rs.getBoolean("ropaVerde"));
                listaPersonajes[i].setSeriedad(rs.getBoolean("seriedad"));
                i++;
            }
            
            st.close();
            
            return listaPersonajes;

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        
    }
}
