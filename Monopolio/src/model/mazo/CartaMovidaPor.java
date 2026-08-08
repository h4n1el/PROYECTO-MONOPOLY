package Monopolio.gamedata.mazo.CartaMovidaPor;
public class CartaMovidaPor extends Cartas{
    private final int distancia;

    public CartaMovidaPor(String descripcion, boolean fromMazoCartas, int distancia)
    {
        super(descripcion, fromMazoCartas);
        this.distancia = distancia;
    }
    public int getDistancia(){return this.distancia;}
}
