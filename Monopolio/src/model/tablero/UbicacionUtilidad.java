package Monopolio.model.tablero;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.jugador.Jugador;

public class UbicacionUtilidad extends Ubicacion {
    private final int multiplicador;
    private final int dobleMultiplicador;
    private final int precio;
    private boolean esDueno;
    private boolean esHipotecado;
    private final ObjectProperty<Jugador> dueno = new SimpleObjectProperty<>(this, "dueno", null);

    public UbicacionUtilidad(String nombre) {
        super(nombre);
        this.precio = 150;
        this.multiplicador = 4;
        this.dobleMultiplicador = 10;
        this.esDueno = false;
        this.esHipotecado = false;
    }

    public int getPrecio() {
        return this.precio;
    }

    public int getMultiplicacion() {
        return this.multiplicador;
    }

    public int getDobleMultiplicador() {
        return this.dobleMultiplicador;
    }

    public boolean getEsDueno() {
        return this.dueno.get() != null; 
    }

    private void setEsDueno(boolean estado) { 
        this.esDueno = estado;
    }

    public boolean getEsHipotecado() { 
        return this.esHipotecado;
    }

    public void setEsHipotecado(boolean estado) {
        this.esHipotecado = estado;
    }

    public Jugador getDueno() {
        return this.dueno.get();
    }

    public ObjectProperty<Jugador> duenoProperty() { 
        return this.dueno;
    }

    public void setDueno(Jugador jugador) {
        this.dueno.set(jugador);
        if (jugador != null) { 
            this.setEsDueno(true);
        } else {
            this.setEsDueno(false);
        }
    }

    public String getUsuarioDueno() {
        return "La renta es " + this.multiplicador + " veces el valor de los dados lanzados "
             + "si el propietario posee una sola compañía de servicios.\n"
             + "Si el propietario posee ambas, el multiplicador es: " + this.dobleMultiplicador + ".";
    }
}