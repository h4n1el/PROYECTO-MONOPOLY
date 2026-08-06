public class IrACarcel extends Ubicacion{
    private final int carcelPosicion;

    public IrACarcel(String nombre, int carcelPosicion){
        super(nombre);
        this.carcelPosicion = carcelPosicion;
    }
    public int getCarcelPosicionn(){return this.carcelPosicion;}
}