package configuracionJuego;

import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import model.dados.Dados;
import model.jugador.Jugador;
import model.jugador.Usuario;
import model.licitacion.Licitacion;
import model.mazo.CartaComunidad;
import model.mazo.CartaFortuna;
import model.mazo.Cartas;
import model.tablero.Tablero;
import trade.Intercambio;

public class DatosJuego {
    private final Dados dados = new Dados();
    private final Tablero tablero = new Tablero();
    private final CartaFortuna cartaFortuna = new CartaFortuna();
    private final CartaComunidad cartaComunidad = new CartaComunidad();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private final SimpleObjectProperty<Jugador> jugadorActual = new SimpleObjectProperty<>(this, "jugadorActual", null);
    private Intercambio intercambioActual;
    private Licitacion subastaActual;
    private Cartas cartaActual;
    private Usuario usuarioActual;
    private int doblesSeguidos;

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
        this.setJugadorActual();
    }

    public void quitarJugador(Jugador jugador) {
        if(this.listaJugadores.contains(jugador)) {
            this.listaJugadores.remove(jugador);
            if(this.listaJugadores.size() > 1){
                this.nextJugador();
            }
        }
    }

    public Jugador getJugadorActual() {
        return jugadorActual.get();
    }

    public SimpleObjectProperty<Jugador> jugadorActualProperty() {
        return this.jugadorActual;
    }

    public void setJugadorActual(){
        if(this.listaJugadores != null){
            this.jugadorActual.set(this.listaJugadores.get(0));
            this.jugadorActual.get().setContinua(true);
        }
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    public void nextJugador() {
       int indice = this.listaJugadores.indexOf(this.jugadorActual.get());
       if(indice + 1 < this.listaJugadores.size()){
        this.jugadorActual.set(this.listaJugadores.get(indice + 1));
        this.doblesSeguidos = 0;
        this.jugadorActual.get().setContinua(true);
       } else if(indice + 1 == this.listaJugadores.size()){
        this.jugadorActual.set(this.listaJugadores.get(0));
        this.doblesSeguidos = 0;
        this.jugadorActual.get().setContinua(true);
       }
    }

    public int getDoblesSeguidos() {
        return this.doblesSeguidos;
    }

    public void incrementarDoblesSeguidos() {
        this.doblesSeguidos++;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void siguienteCartaFortuna(){
        this.cartaActual = this.cartaFortuna.getNextCarta();
    }

    public void devolverCartaFortuna(Cartas carta){
        if(carta.getFromMazoCartas()){
            this.cartaFortuna.returnCarta(carta);
            this.cartaActual = null;
        }
    }

    public void siguienteCartaComunidad(){
        this.cartaActual = this.cartaComunidad.getNextCartas();
    }

    public void devolverCartaComunidad(Cartas carta){
        if(carta.getFromMazoCartas()){
            this.cartaComunidad.returnCartas(carta);
            this.cartaActual = null;
        }
    }

    public Cartas getCartaActual() {
        return this.cartaActual;
    }

    public void nuevoIntercambio(Jugador jugadorDesde){
        this.intercambioActual = new Intercambio(jugadorDesde);

    }

    public void limpiarIntercambio(){
        this.intercambioActual = null;
    }

    public Licitacion getSubastaActual() {
        return this.subastaActual;
    }

    public void limpiarSubastaActual() {
        this.subastaActual = null;
    }

    public Dados getDados() {
        return this.dados;
    }
}
