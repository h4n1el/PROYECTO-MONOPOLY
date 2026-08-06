package Monopolio.gamedata.mazo.CartaCarrilCercano;
public class CartaCarrilCercano extends Cartas{
    private final int multiplicador;

    public EstacionTrenCercana(String descripcion, boolean fromMazoCartas, int multiplicador)
    {
        super(descripcion, fromMazoCartas);
        this.multiplicador = 2;
    }
    public int getMultiplicador(){return this.multiplicador;}
}