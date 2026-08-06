package Monopolio.gamedata.mazo.CartaFortuna;

import java.util.ArrayList;

public class CartaFortuna
{
    private final ArrayList<Cartas> mazo = new ArrayList();
    private final ArrayList<Cartas> barajaMazo;

    public MazoDeCartas(){
        this.mazo.add(new CartaMovidaA("Avanza para pasar. \nCollect \u00a3200", true, 0));
        this.mazo.add(new CartaMovidaA("Avanza a trafalgar square. \nsi pasas vete, collect \u00a3200", true , 24));
        this.mazo.add(new CartaMovidaA("Avanza a Pall Mall \nsi pasas vete, collect \u00a3200", true, 11));
        this.mazo.add(new CartaUtilidadCercana("Avanza a token de utilidad mas cercano \nsi no lo tienes puedes comprarlo del banco, Si es de su propiedad, arroje el dado y pague al propietario un total de diez veces la cantidad arrojada", true));
        this.mazo.add(new CartaCarrilCercano("Avanza al token del carril mas cercano, y paga al dueno el doble del alquiler", true));
        this.mazo.add(new CartaCarrilCercano("Avanza al token del carril mas cercano, y paga al dueno el doble del alquiler", true));
        this.mazo.add(new CartaPago("El banco te paga dividendos de \u00a350." true, 50, false));
        this.mazo.add(new CartaCarcel(true));
        this.mazo.add(new CartaMovidaPor("devuelvete 3 casillas", true , -3));
        this.mazo.add(new CartaMovidaA("ve a la carcel. \nno pases, no collect \u00a3200.", true, 10));
        this.mazo.add(new CartaPagoDoble("hacer reparaciones a todas tus propiedades \npor cada casa paga \u00a3200, por cada hotel pago \u00a3100.",true, 25, 100));
        this.mazo.add(new CartaPago("pagar multa por exceso de velocidad \u00315.", true, -15, false));
        this.mazo.add(new CartaMoverA("toma un viaje hacia King s Cross Station. \nsi pasa vete, collect \n00a3200." true, -50, true));
        this.mazo.add(new CartaMoverA("camina hacia Mayfair.", true, 39));
        this.mazo.add(new CartaPago("Has sido elegido presidente de la junta directiva \n paga a cada jugador \u00a350.", true, -50, true));
        this.mazo.add(new CartaPago("su prestamo de construccion ha vencido. \nCollect \u00a3150", true, 150, true));
        this.barajaMazo = getBarajaMazo();


    }
    private ArrayList<Cartas> getBarajaMazo(){
        ArrayList mazoCopia = (ArrayList)this.mazo.clone();
        ArrayList<Cartas> baraja = new ArrayList<Cartas>();
        Random generador = new Random();

        for(int i = 0; i < this.deck.size(); i++){
            int h = generador.nextInt(mazoCopia.size());
            baraja.add((Cartas)mazoCopia.get(h));
            mazoCopia.remove(h);
        }
        return baraja;
    }

    public Cartas getNextCarta(){
        if(barajaMazo.isEmpty()){
            
            barajaMazo = getBarajaMazo();
        }

        return this.barajaMazo.remove(0);
         
    }

    public void returnCarta(Cartas carta){
        this.barajaMazo.add(carta);
    }

    public boolean contadorCartaCarcel(){
        return this.barajaMazo.stream().anyMatch(Cartas -> carta instanceof CartaCarcel);
    }
}