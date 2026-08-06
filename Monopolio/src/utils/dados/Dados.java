package Monopolio.gamedata.dados.Dados;
import java.util.Random;

public class Dados{
    private final Random dado1 = new Random();
    private final Random dado2 = new Random();
    
    private int rueda1;
    private int rueda2;

    public int rodarPrimerDado(){
        this.rueda1 = this.dado1.nextInt(6) + 1;
        return this.rueda1;
    }
    public int rodarSegundoDado(){
        this.rueda2 = this.dado2.nextInt(6) + 1;
        return this.rueda2;
    }

    public int getRueda1UltimoValor(){
        return this.rueda1;
    }
    public int getRueda2Ultimovalor(){
        return this.rueda2;
    }

    public int RuedaTotal(){
        return this.rueda1 + this.rueda2;
    }

    public boolean esDoble(){
        return this.rueda1 == this.rueda2;
    }
    
}