package Monopolio.gamedata.mazo.CartaPagoDoble;

public class CartaPagoDoble extends Cartas
{
    private final int primerValor;
    private final int segundovalor;

    public CartaPagoDoble(int PrimerValor, int Segundovalor)
    {
        super(descripcion, fromMazoCartas);
        this.primerValor = PrimerValor;
        this.segundovalor = Segundovalor;
    }

    public int getPrimerValor(){return this.primerValor;}
    public int getSegundoValor(){return this.segundoValor;}
}