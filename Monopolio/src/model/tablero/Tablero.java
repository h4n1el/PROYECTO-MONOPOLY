public class Tablero{
    private final ArrayList<Ubicacion> tableroUbicacion = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoMarron = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoAzulClaro = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoRosado = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoNaranja = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoRojo = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoAmarillo = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoVerde = new ArrayList();
    private final ArrayList<UbicacionPropiedad> GrupoAzul = new ArrayList();
    private int casas = 32;
    private int hoteles = 10;

    public Tablero(){
        UbicacionPoblada();
        gruposPoblados();
        
    }

    private void UbicacionPoblada(){
        this.tableroUbicacion.add(new IrUbicacion("ir", 200));
        this.tableroUbicacion.add(new UbicacionPropiedad("Old Kent Road", 60, new int[]{2, 10, 30, 90, 160, 250}, 50));
        this.tableroUbicacion.add(new UbicacionCofreComunidad("Cofre de comunidad"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Whitechapel Road", 60, new int[]{4, 20, 60, 180, 320, 450}, 50));
        this.tableroUbicacion.add(new UbicacionImpuesto("ingreso de impuesto", 200));
        this.tableroUbicacion.add(new UbicacionCarril("Kings Cross Station"));
        this.tableroUbicacion.add(new UbicacionPropiedad("the angel islington", 100, new int[]{6, 30, 90, 270, 400, 550}, 50));
        this.tableroUbicacion.add(new UbicacionAleatoria("Aleatorio"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Euston Road", 100, new int[]{6, 30, 90, 270, 400, 550}, 50));
        this.tableroUbicacion.add(new UbicacionPropiedad("Pentonville Road", 120, new int[]{8, 40, 100, 300, 450, 600}, 50));
        this.tableroUbicacion.add(new UbicacionCarcel("Carcel"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Pall Mall", 140, new int[]{10, 50, 150, 450, 625, 750}, 100));
        this.tableroUbicacion.add(new UbicacionUtilidad("Electry Company"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Whitehall", 140, new int[]{10, 50, 150, 450, 625, 750}, 100));
        this.tableroUbicacion.add(new UbicacionPropiedad("Northumberland Avenue", 160, new int[]{12, 60, 180, 500, 700, 900}, 100));
        this.tableroUbicacion.add(new UbicacionCarril("Marylebone Station"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Malbotough", 180, new int[]{14, 70, 200, 550, 750, 950}, 100));
        this.tableroUbicacion.add(new UbicacionPropiedad("Vine Street", 200, new int[]{16, 80, 220, 600, 800, 1000}, 100));
        this.tableroUbicacion.add(new UbicacionParkeo("parkeo libre"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Strand", 220, new int[]{18, 90, 250, 700, 875, 1050}, 150));
        this.tableroUbicacion.add(new UbicacionAleatoria("Aleatorio"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Fleet Street", 220, new int[]{18, 90, 250, 700, 875, 1050}, 150));
        this.tableroUbicacion.add(new UbicacionPropiedad("Trafalgar Square", 240, new int[]{20, 100, 300, 750, 925, 1100}, 150));
        this.tableroUbicacion.add(new UbicacionCarril("Fenchurch St. Station"));
        this.tableroUbicacion.add(new UbicacionPropiedad("leicester Square", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150));
        this.tableroUbicacion.add(new UbicacionPropiedad("Convetry Street", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150));
        this.tableroUbicacion.add(new UbicacionUtilidad("waterworks"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Piccadilly", 280, new int[]{24, 120, 360, 850, 1025, 1200}, 150));
        this.tableroUbicacion.add(new IrACarcel("ve a la carcel", 10));
        this.tableroUbicacion.add(new UbicacionPropiedad("Regent Street", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200));
        this.tableroUbicacion.add(new UbicacionPropiedad("Oxford Street", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200));
        this.tableroUbicacion.add(new UbicacionCofreComunidad("Cofre de comunidad"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Bond Street", 320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200));
        this.tableroUbicacion.add(new UbicacionCarril("Liverpool St. Station"));
        this.tableroUbicacion.add(new UbicacionAleatoria("Aleatorio"));
        this.tableroUbicacion.add(new UbicacionPropiedad("Park Lane", 350, new int[]{35, 175, 500, 1100, 1300, 1500}, 200));
        this.tableroUbicacion.add(new UbicacionImpuesto("super impuesto", 100));
        this.tableroUbicacion.add(new UbicacionPropiedad("Mayfair", 400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200));
        
        
    }

    private void gruposPoblados(){
        this.GrupoMarron.add((UbicacionPropiedad)this.tableroUbicacion.get(1));
        this.GrupoMarron.add((UbicacionPropiedad)this.tableroUbicacion.get(3));
        this.GrupoAzulClaro.add((UbicacionPropiedad)this.tableroUbicacion.get(6));
        this.GrupoAzulClaro.add((UbicacionPropiedad)this.tableroUbicacion.get(8));
        this.GrupoAzulClaro.add((UbicacionPropiedad)this.tableroUbicacion.get(9));
        this.GrupoRosado.add((UbicacionPropiedad)this.tableroUbicacion.get(11));
        this.GrupoRosado.add((UbicacionPropiedad)this.tableroUbicacion.get(13));
        this.GrupoRosado.add((UbicacionPropiedad)this.tableroUbicacion.get(14));
        this.GrupoNaranja.add((UbicacionPropiedad)this.tableroUbicacion.get(16));
        this.GrupoNaranja.add((UbicacionPropiedad)this.tableroUbicacion.get(18));
        this.GrupoNaranja.add((UbicacionPropiedad)this.tableroUbicacion.get(19));
        this.GrupoRojo.add((UbicacionPropiedad)this.tableroUbicacion.get(21));
        this.GrupoRojo.add((UbicacionPropiedad)this.tableroUbicacion.get(23));
        this.GrupoRojo.add((UbicacionPropiedad)this.tableroUbicacion.get(24));
        this.GrupoAmarillo.add((UbicacionPropiedad)this.tableroUbicacion.get(26));
        this.GrupoAmarillo.add((UbicacionPropiedad)this.tableroUbicacion.get(27));
        this.GrupoAmarillo.add((UbicacionPropiedad)this.tableroUbicacion.get(29));
        this.GrupoVerde.add((UbicacionPropiedad)this.tableroUbicacion.get(31));
        this.GrupoVerde.add((UbicacionPropiedad)this.tableroUbicacion.get(32));
        this.GrupoVerde.add((UbicacionPropiedad)this.tableroUbicacion.get(34));
        this.GrupoAzul.add((UbicacionPropiedad)this.tableroUbicacion.get(37));
        this.GrupoAzul.add((UbicacionPropiedad)this.tableroUbicacion.get(39));
    }

    public ArrayList<UbicacionPropiedad> getGrupo(UbicacionPropiedad propiedad){
        if(this.GrupoMarron.contains(propiedad)){
            return this.GrupoMarron;
        }
        if(this.GrupoAzulClaro.contains(propiedad)){
            return this.GrupoAzulClaro;
        }
        if(this.GrupoRosado.contains(propiedad)){
            return this.GrupoRosado
        }
        if(this.GrupoNaranja.contains(propiedad)){
            return this.GrupoNaranja;
        }
        if(this.GrupoRojo.contains(propiedad)){
            return this.GrupoRojo;
        }
        if(this.GrupoAmarillo.contains(propiedad)){
            return this.GrupoAmarillo;
        }
        if(this.GrupoVerde.contains(propiedad)){
            return this.GrupoVerde;
        }
        if(this.GrupoAzul.contains(propiedad)){
            return this.GrupoAzul;
        }
        return new ArrayList<>();
    }

    public void ColorGrupo(ArrayList<UbicacionPropiedad> grupo){
        AsimilarColorGrupo.assimilate(grupo);
    }

    public int desarrolloUniforme(UbicacionPropiedad propiedad){
        return DesarrolloEquilibrado.desarrolloUniforme(this, propiedad);
    }

    public Ubicacion getUbicacion(int posicion){
        if(posicion >= 0 && posicion <= 39) {
            return this.tableroUbicacion.get(posicion);
        }
        return null;
    }

    public int getUbicacionPosicion(Ubicacion ubicacion){
        retun this.tableroUbicacion.indexOf(ubicacion);// indexof busca un caracter o texto dentro de una cadena en la posicion y devuelve el numero de la posicion
    }

    public int getNumeroCasasRestantes(){
        return this.casas;
    }

    public int getNumeroHotelesRestantes(){
        return this.hoteles;
    }

    public void agregarCasas(int casas){
        this.casas += casas;
    }

    public void agregarHoteles(int hoteles){
        this.hoteles += hoteles;
    }
    
    
}