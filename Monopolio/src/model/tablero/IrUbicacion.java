public class IrUbicacion extends Ubicacion{
    private final int valor;

    public IrUbicacion(String nombre, int valor){
        super(nombre);
        this.valor = Math.abs(valor);
    }
    public int getValor(){
        return this.valor;
    }
}