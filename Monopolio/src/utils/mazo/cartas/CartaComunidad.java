import java.util.ArrayList;
import java.util.Random;

package Monopolio.gamedata.mazo.CartaComunidad;

public class CartaComunidad{
    private final ArrayList<Cartas> mazo = new ArrayList();
    private final ArrayList<Cartas> bajaraMazo;

    public CartaComunidad(){
        this.mazo.add(new CartaMovidaA("avanza para pasar \nCollect \u00a3200.", false, 0));
        this.mazo.add(new CartaPago("error bancario a tu favor. \nCollect \u00a3200.", false, 200, false));
        this.mazo.add(new CartaPago("honorarios medicos \npago \u00a350", false, -50, false));
        this.mazo.add(new CartaCarcel(false));
        this.mazo.add(new CartaMoverA("ir a la carcel. \nNo pasar, no collect \u00a3200", false, 10));
        this.mazo.add(new CartaPago("gran noche de opera \nCollect \u00a350 abrir asientos para cada jugador", false, 50, true));
        this.mazo.add(new CartaPago("se vence el fondo navideno. \nCollect \u00a3200", false, 200, false));
        this.mazo.add(new CartaPago("se vence el seguro de vida \nCollect \u00a3100.", false, 100, false));
        this.mazo.add(new CartaPago("pago de honorarios hospitalarios \u00a3100", false, -100, false));
        this.mazo.add(new CartaPago("pago de honorarios escolares\u00a3150", false, -150, false));
        this.mazo.add(new CartaPago("recibir \u00a325 honorarios de consultorio.", false, 25, false));
        this.mazo.add(new CartaPagoDoble("se le aplica una contribucion especial para la reparacion de la calle \n \u00a340 per casa, \u00a3115 per hotel.",false , 40, 115));
        this.mazo.add(new CartaPago("has ganado el segundo premio en el concurse de belleza. \nCollect \u00a310.", false, 10, false));
        this.mazo.add(new CartaPago("tu heredes \u00a3100.",false, 100, false));
        this.barajaMazo = this.getBarajaMazo();

    
    }
    private ArrayList<Cartas> getBarajaMazos(){
        ArrayList mazoCopia = (ArrayList)this.mazo.clone();// clone duplica la estructura de la baraja actual
        ArrayList<Cartas> baraja = new ArrayList<Cartas>();
        Random generador = new Random();
        for(int i = 0; i < this.mazo.size()){
            int j = nextInt(mazoCopia.size());
            barajaMazo.add((Cartas)mazoCopia.get(j));
            mazoCopia.remove(j);
            
        }
        return baraja;
    }
    public Cartas getNextCartas(){
        Cartas carta = this.baraja.get(0);
        this.baraja.remove(0);
        return carta;
    }

    public void returnCartas(Cartas carta){
        this.bajaraMazo.add(carta);

    }
    public boolean contadorCartaCarcel(){
        return this.bajaraMazo.stream().anyMatch(carta -> carta instanceof CartaCarcel);
    }
}