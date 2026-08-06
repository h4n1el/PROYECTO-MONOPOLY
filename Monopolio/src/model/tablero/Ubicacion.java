public abstract class Ubicacion{
    private final SimpleStringProperty nombre;

    public Ubicacion(String nombre){
        this.nombre = new SimpleStringProperty(nombre);
    }

    public SimpleStringProperty Nombre(){
        return this.nombre;
    }

    public String getNombre(){
        return this.nombre.get();
    }

    public void setNombre(String nuevoNombre){
        this.nombre.set(nuevoNombre);
    }

}