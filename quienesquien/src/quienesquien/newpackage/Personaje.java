package quienesquien.newpackage;

public class Personaje {
    
    private String nombre;
    private String genero;
    private boolean esRubio;
    private boolean complementos;
    private boolean esMayor;
    private boolean ropaVerde;
    private boolean seriedad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getRubio() {
        return esRubio;
    }

    public void setEsRubio(boolean esRubio) {
        this.esRubio = esRubio;
    }

    public boolean getComplementos() {
        return complementos;
    }

    public void setComplementos(boolean complementos) {
        this.complementos = complementos;
    }

    public boolean getEdad() {
        return esMayor;
    }

    public void setEsMayor(boolean esMayor) {
        this.esMayor = esMayor;
    }

    public Boolean getRopaVerde() {
        return ropaVerde;
    }

    public void setRopaVerde(Boolean ropaVerde) {
        this.ropaVerde = ropaVerde;
    }
    
    public boolean getSeriedad() {
        return seriedad;
    }

    public void setSeriedad(boolean explorador) {
        this.seriedad = explorador;
    }
    
    public Personaje(){
        
    }

    public Personaje(String nombre, String genero, boolean esRubio, Boolean complementos, boolean esMayor, Boolean ropaVerde, boolean seriedad) {
        this.nombre = nombre;
        this.genero = genero;
        this.esRubio = esRubio;
        this.complementos = complementos;
        this.esMayor = esMayor;
        this.ropaVerde = ropaVerde;
        this.seriedad = seriedad;
    }
    
}
