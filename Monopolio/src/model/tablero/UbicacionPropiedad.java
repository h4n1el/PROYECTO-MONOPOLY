package Monopolio.model.tablero;

import Monopolio.model.jugador.Jugador;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;

public class UbicacionPropiedad extends Ubicacion {
    private final int precio;
    private final int valorHipoteca;
    private final int precioCasa;
    private final int rentaBase;
    private final int RentaCasa1;
    private final int RentaCasa2;
    private final int RentaCasa3;
    private final int RentaCasa4;
    private final int RentaHotel;
    private final SimpleIntegerProperty casas; //notifica cambios
    private int numeroCasas;
    private boolean mejorasHotel;
    private boolean esHotel;
    private boolean esMejorable;
    private boolean esDueno;
    private boolean esHipotecado;
    private boolean color;
    private final SimpleObjectProperty<Jugador> dueno;

    public UbicacionPropiedad(String nombre, int precio, int[] renta, int precioCasa) {
        super(nombre);
        this.precio = precio;
        this.valorHipoteca = precio / 2;
        this.rentaBase = renta[0];
        this.RentaCasa1 = renta[1];
        this.RentaCasa2 = renta[2];
        this.RentaCasa3 = renta[3];
        this.RentaCasa4 = renta[4];
        this.RentaHotel = renta[5];
        this.casas = new SimpleIntegerProperty(this, "casa", 0);
        this.mejorasHotel = false;
        this.esHotel = false;
        this.esMejorable = false;
        this.esDueno = false;
        this.esHipotecado = false;
        this.color = false;
        this.dueno = new SimpleObjectProperty<>();
        this.precioCasa = precioCasa;
    }

    public int getPrecio() {
        return this.precio;
    }

    public int getValorHipoteca() {
        return this.valorHipoteca;
    }

    public void setHipotecado(boolean hipotecado) {
        if (this.casas.get() == 0) {
            this.esHipotecado = hipotecado;
            if (this.esHipotecado) {
                this.esMejorable = false;
            }
        }
    }

    public boolean getEstadoHipoteca() {
        return this.esHipotecado;
    }

    public int getRenta() {
        int i;
        int cantidadCasas = this.casas.get();

        if (this.esHotel) {
            i = this.RentaHotel;
        } else if (cantidadCasas == 0 && !this.color) {
            i = this.rentaBase;
        } else if (cantidadCasas == 0 && this.color) {
            i = this.rentaBase * 2;
        } else if (cantidadCasas == 1) {
            i = this.RentaCasa1;
        } else if (cantidadCasas == 2) {
            i = this.RentaCasa2;
        } else if (cantidadCasas == 3) {
            i = this.RentaCasa3;
        } else {
            i = this.RentaCasa4;
        }
        return i;
    }

    public void construirHotel() {
        int cantidadCasas = this.casas.get();

        if (cantidadCasas < 4 && this.esMejorable) {
            this.casas.set(cantidadCasas + 1);
            if (this.casas.get() == 4) {
                this.mejorasHotel = true;
            }
        } else if (cantidadCasas == 4 && this.mejorasHotel) {
            this.mejorarAHotel();
        }
    }

    public void venderHotel() {
        int cantidadCasas = this.casas.get();

        if (cantidadCasas == 0 && this.esMejorable) {
            this.desmejorar();
        } else if (cantidadCasas > 0) {
            this.casas.set(cantidadCasas - 1);
        }
    }

    public int getNumeroCasas() {
        return this.casas.get();
    }

    public SimpleIntegerProperty getCasaProperty() {
        return this.casas;
    }

    public boolean getEsHotel() {
        return this.esHotel;
    }

    public boolean getMejorasHotel() {
        return this.mejorasHotel;
    }

    public void setMejorasHotel(boolean mejora) {
        int cantidadCasas = this.casas.get();

        if (cantidadCasas == 4 && mejora) {
            this.mejorasHotel = true;
        } else if (!mejora) {
            this.mejorasHotel = false;
        }
    }

    private void mejorarAHotel() {
        int cantidadCasas = this.casas.get();
        if (this.mejorasHotel && cantidadCasas == 4) {
            this.esHotel = true;      
            this.casas.set(0);        
            this.mejorasHotel = false; 
        }
    }

    private void desmejorar() {
        if (this.esHotel) {
            this.esHotel = false;
            this.casas.set(4);
        }
    }

    private void pasarPropiedad(Jugador jugador) {
        this.dueno.set(jugador);

        if (jugador == null) {
            this.casas.set(0);
            this.esHotel = false;
            this.esHipotecado = false;
        }
    }

    public void quitarPropiedad() {
        int cantidadCasas = this.casas.get();
        this.dueno.set(null);
        this.esDueno = false;
        this.esMejorable = false;
        this.esHotel = false;
        this.esHipotecado = false;
        this.color = false;
        if (cantidadCasas != 0) {
            this.casas.set(0);
        }
    }

    public Jugador getDueno() {
        return this.dueno.get();
    }

    public ObjectProperty<Jugador> getDuenoProperty() {
        return this.dueno;
    }

    public boolean getColorEstado() {
        return this.color;
    }

    public void setColorEstado(boolean estado) {
        this.color = estado;
        if (!this.color) {
            this.esMejorable = false;
        }
    }

    public boolean getESMejorableEstado() {
        return this.esMejorable;
    }

    public void setEsMejorableEstado(boolean estado) {
        this.esMejorable = estado;
    }

    public int getPrecioCasa() {
        return this.precioCasa;
    }

    public boolean getEsDueno() {
        return this.esDueno;
    }

    public void quitarCasa() {
        if (this.casas.get() == 0 && this.esHotel) {
            this.desmejorar();
        } else if (this.casas.get() > 0) {
            this.casas.set(this.casas.get() - 1);
        }
    }

    public String getUsuarioDueno() {
        return this.getNombre()
                + "\n\nPrecio = \u00a3 " + this.precio
                + "\nvalor de hipoteca: \u00a3" + this.valorHipoteca
                + "\nPrecio de la casa: \u00a3" + this.precioCasa
                + "\n\n Renta base: \u00a3" + this.rentaBase
                + "\n alquiler de la casa 1: \u00a3" + this.RentaCasa1
                + "\n alquiler de la casa 2: \u00a3" + this.RentaCasa2
                + "\n alquiler de la casa 3: \u00a3" + this.RentaCasa3
                + "\n alquiler de la casa 4: \u00a3" + this.RentaCasa4
                + "\n alquiler de hotel: \u00a3" + this.RentaHotel + "\n";
    }

    public int getValor() {
        int valor = this.precio;
        if (this.esHotel) {
            valor += 5 * this.precioCasa;
        } else {
            valor += this.casas.get() * this.precioCasa;
        }

        if (this.esHipotecado) {
            valor /= 2;
        }

        return valor;
    }
}
