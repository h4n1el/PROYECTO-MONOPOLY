package Monopolio.gamedata.mazo.CartaCarrilCercano;
public class CartaUtilidadCercana extends Cartas{
    private final int multiplicador;

    public EstacionTrenCercana(String descripcion, boolean fromMazoCartas, int multiplicador)
    {
        super(descripcion, fromMazoCartas);
        this.multiplicador = 10;
    }
    public int getMultiplicador(){return this.multiplicador;}
}