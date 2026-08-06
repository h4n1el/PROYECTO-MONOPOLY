package Monopolio.gamedata.mazo.CartaPago;
public class CartaPago
{
    private final int valor;
    private final boolean porJugador;

    public CartaPago(String descripcion, boolean fromMazoCartas, int valor, boolean porJugador)
    {
        super(descripcion, fromMazoCartas);
        this.valor = valor;
        this.porJugador = porJugador;
    }
    public int getDescripcion(){return this.valor;}
    public boolean getPorJugador(){return this.porJugador;}
}