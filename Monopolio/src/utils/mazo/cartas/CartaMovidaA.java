package Monopolio.gamedata.mazo.CartaMovidaA;
public class CartaMovidaA extends Cartas
{
    private final int moverUbicacion;

    public CartaMovidaA(String descripcion, boolean fromMazoCartas, int moverUbicacion)
    {
        super(descripcion, fromMazoCartas);
        if(moverUbicacion > 39){
            this.moverUbicacion = 39;
        }else if(ubicacion < 0){
            this.moverUbicacion = 0;
        }else {
            this.moverUbicacion = ubicacion
        }
        
    }

    public int getMoverUbicacion(){return this.moverUbicacion;}
}