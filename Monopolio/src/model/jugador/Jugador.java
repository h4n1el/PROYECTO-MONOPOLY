import java.util.ArrayList;

import gamedata.mazo.cartas.CartaCarcel;

public class Jugador{
    private final String nombre;
    private final ArrayList<Ubicacion> ubicacionDueno;
    private final ArrayList<CartaCarcel> carcelDueno;
    private final SimpleBooleanProperty enCarcel; // SimpleBooleanProperty Crea una nueva instancia de la propiedad que permite vinculacion(binding)
    private final SimpleBooleanProperty tieneCartaCarcel;
    private final SimpleBooleanProperty continua;
    private final SimpleBooleanProperty dinero;
    private final SimpleBooleanProperty tableroPosicion;
    private int turnoEnCarcel;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.ubicacionDueno = new ArrayList();
        this.carcelDueno = new ArrayList();
        this.enCarcel = new SimpleBooleanProperty((Object)this, enCarcel, false);// Establece el "bean" propietario de la propiedad, permitiendo que otros objetos identifiquen a quien pertenece esta propiedad.
        this.tieneCartaCarcel = new SimpleBooleanProperty((Object)this, tieneCartaCarcel, false);
        this.continua = new SimpleBooleanProperty((Object)this, continua, false); 
        this.dinero = new SimpleBooleanProperty((Object)this, dinero, 1500);
        this.tableroPosicion = new SimpleBooleanProperty((Object)this, "posicion", 0);
        
    }

    public String getNombre(){return this.nombre;}

    public void agregarUbicacion(Ubicacion ubicacion){
        if(!ubicacionDueno.contains(ubicacion)){
            this.ubicacionDueno.add(ubicacion);
        }
    }
    public void eliminarUbicacion(Ubicacion ubicacion){
        if(ubicacionDueno.contains(ubicacion)){
            this.ubicacionDueno.remove(ubicacion);
        }
    }

    public ArrayList<PropertyUbicacion> gPropertyUbicacions(){
        ArrayList<PropertyUbicacion> dueno = new ArrayList<PropertyUbicacion>();
        this.ubicacionDueno.forEach(ubicacion -> {
            if(ubicacion instanceof PropertyUbicacion){
                dueno.add((PropertyUbicacion)ubicacion);
            }
            
            });
            return dueno;
    }
    public ArrayList<UtilidadUbicacion> getDuenoUbicacion(){}
}
