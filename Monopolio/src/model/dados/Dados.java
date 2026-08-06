import java.util.Random;

public class Dados{
    private final Random dado1 = new Random();
    private final Random dado2 = new Random();
    private int giroDado1;
    private int girodado2;

    public int primerDadoVuelta(){
        this.giroDado1 = this.dado1.nextInt(6) + 1;
        return this.giroDado1;
    }

    public int segundoDadoVuelta(){
        this.girodado2 = this.dado2.nextInt(6) + 1;
        return this.giroDado2;
    }

    public int getValorDado1(){
        return this.giroDado1;
    }

    public int getValorDado(){
        return this.girodado2;
    }

    public int sumaDado(){
        return this.giroDado1 + this.girodado2;
    }

    public boolean Duplicado(){
        return this.giroDado1 == this.girodado2;
    }

}