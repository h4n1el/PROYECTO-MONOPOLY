package Monopolio.gamedata.mazo.Cartas;

public abstract class Cartas{
    private final String descripcion;
    private final boolean fromMazoCartas;

    public Cartas(String descripcion, boolean fromMazocartas)
    {
        this.descripcion = descripcion;
        this.fromMazoCartas = fromMazocartas;
    }

    public String getDescripcion(){return this.descripcion;}
    public boolean getFromMazoCartas(){return this.fromMazoCartas;}
}