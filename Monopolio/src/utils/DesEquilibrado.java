package utils;

import java.util.ArrayList;
import model.tablero.Tablero;
import model.tablero.UbicacionPropiedad;

public final class DesEquilibrado {

    private DesEquilibrado() {
       
    }

    public static int desarrolloUniforme(Tablero tablero, UbicacionPropiedad propiedad) {
        if (tablero.getNumeroCasasRestantes() <= 0) {
            return 0;
        }

        ArrayList<UbicacionPropiedad> grupo = tablero.getGrupo(propiedad);
        
        
        int minCasas = 5;
        for (UbicacionPropiedad h : grupo) {
            if (h.getNumeroCasas() < minCasas && !h.getEsHotel()) {
                minCasas = h.getNumeroCasas();
            }
        }

        // Construir un hotel
        if (propiedad.getNumeroCasas() == minCasas && propiedad.getMejorasHotel()) {
            propiedad.agregarCasas(); 
            tablero.agregarCasas(4);  
            tablero.agregarHoteles(-1);
            return propiedad.getPrecioCasa();
        }

        // Construir una casa
        if (propiedad.getNumeroCasas() == minCasas && !propiedad.getMejorasHotel() && !propiedad.getEsHotel()) {
            if (!propiedad.getESMejorableEstado()) {
                return 0;
            }
            propiedad.agregarCasas();
            tablero.agregarCasas(-1);
            return propiedad.getPrecioCasa();
        }

        
        for (UbicacionPropiedad h : grupo) {
            if (h != propiedad && h.getNumeroCasas() == minCasas && h.getESMejorableEstado()) {
                h.agregarCasas();
                if (propiedad.getEsHotel()) {
                    tablero.agregarCasas(4);
                    tablero.agregarHoteles(-1);
                } else {
                    tablero.agregarCasas(-1);
                }
                return h.getPrecioCasa();
            }
        }

        return 0;
    }

    public static int reducirUniforme(Tablero tablero, UbicacionPropiedad propiedad) {
        int reembolso = 0;
        ArrayList<UbicacionPropiedad> grupo = tablero.getGrupo(propiedad);
        UbicacionPropiedad masMejorado = recuperarMejoras(grupo);

        if (propiedad.getEsHotel()) {
            if (tablero.getNumeroCasasRestantes() < 4) {
                reembolso = reembolsarMejorasHotel(tablero, propiedad);
            } else {
                propiedad.quitarCasa();
                tablero.agregarCasas(-4);
                tablero.agregarHoteles(1);
                reembolso = propiedad.getPrecioCasa() / 2;
            }
        } else if (propiedad.getNumeroCasas() == masMejorado.getNumeroCasas() && propiedad.getNumeroCasas() != 0) {
            propiedad.quitarCasa();
            tablero.agregarCasas(1);
            reembolso = propiedad.getPrecioCasa() / 2;
        } else if (propiedad != masMejorado) {
            if (masMejorado.getEsHotel()) {
                if (tablero.getNumeroHotelesRestantes() < 4) {
                    reembolso = reembolsarMejorasHotel(tablero, propiedad);
                } else {
                    masMejorado.quitarCasa();
                    tablero.agregarCasas(-4);
                    tablero.agregarHoteles(1);
                    reembolso = masMejorado.getPrecioCasa() / 2;
                }
            } else if (masMejorado.getNumeroCasas() != 0) {
                masMejorado.quitarCasa();
                tablero.agregarCasas(1);
                reembolso = masMejorado.getPrecioCasa() / 2;
            }
        }

        return reembolso;
    }

    public int removerTodaMejoraGrupoColor(Tablero tablero, UbicacionPropiedad propiedad) {
        int reembolso = 0;
        for (UbicacionPropiedad h : tablero.getGrupo(propiedad)) {
            while (h.getNumeroCasas() != 0 || h.getEsHotel()) {
                reembolso += reducirUniforme(tablero, h);
            }
        }
        return reembolso;
    }

    public static int reembolsarMejorasHotel(Tablero tablero, UbicacionPropiedad propiedad) {
        int casasTotales = 0;
        ArrayList<UbicacionPropiedad> grupo = tablero.getGrupo(propiedad);
        ArrayList<UbicacionPropiedad> sonHoteles = new ArrayList<>();
        ArrayList<UbicacionPropiedad> noHoteles = new ArrayList<>();

        for (UbicacionPropiedad h : grupo) {
            if (h.getEsHotel()) {
                sonHoteles.add(h);
                casasTotales += 5;
            } else {
                noHoteles.add(h);
                casasTotales += h.getNumeroCasas();
            }
        }

        if (sonHoteles.size() == grupo.size()) {
            hotelesRetroceden(tablero, sonHoteles, propiedad);
        } else {
            noTodosHotelesDesmejorados(tablero, sonHoteles, noHoteles, propiedad);
        }

        int nuevaCasasTotales = 0;
        for (UbicacionPropiedad h : grupo) {
            nuevaCasasTotales += h.getNumeroCasas();
        }

        return (casasTotales - nuevaCasasTotales) * (propiedad.getPrecioCasa() / 2);
    }

    public static UbicacionPropiedad recuperarMejoras(ArrayList<UbicacionPropiedad> grupo) {
        UbicacionPropiedad masMejorado = grupo.get(0);
        for (UbicacionPropiedad h : grupo) {
            if (h.getEsHotel()) {
                return h;
            }
            if (h.getNumeroCasas() > masMejorado.getNumeroCasas()) {
                masMejorado = h;
            }
        }
        return masMejorado;
    }

    private static void hotelesRetroceden(Tablero tablero, ArrayList<UbicacionPropiedad> sonHoteles, UbicacionPropiedad propiedad) {
        if (sonHoteles.isEmpty()) return;

        int casasRestantes = tablero.getNumeroCasasRestantes();
        int residuo = casasRestantes % sonHoteles.size();
        int cadaPropiedad = casasRestantes / sonHoteles.size();

        for (UbicacionPropiedad h : sonHoteles) {
            while (h.getNumeroCasas() != cadaPropiedad) {
                h.quitarCasa();
            }
            tablero.agregarCasas(1);
        }

        if (residuo != 0) {
            for (int i = 0; i < residuo; i++) {
                if (residuo == 1) {
                    sonHoteles.get(sonHoteles.size() - 1).agregarCasas();
                } else {
                    for (UbicacionPropiedad h2 : sonHoteles) {
                        if (h2 != propiedad && h2.getNumeroCasas() <= cadaPropiedad) {
                            h2.agregarCasas();
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void noTodosHotelesDesmejorados(Tablero tablero, ArrayList<UbicacionPropiedad> sonHoteles, ArrayList<UbicacionPropiedad> noHoteles, UbicacionPropiedad propiedad) {
        if (sonHoteles.isEmpty()) return;

        int casasRestantes = tablero.getNumeroCasasRestantes();
        int residuo = casasRestantes % sonHoteles.size();
        int cadaPropiedad = casasRestantes / sonHoteles.size();

        while (!noHoteles.isEmpty()) {
            UbicacionPropiedad masMe = recuperarMejoras(noHoteles);
            int diferencia = (casasRestantes / sonHoteles.size()) - masMe.getNumeroCasas();
            
            if (diferencia <= 0) {
                break;
            }
            masMe.quitarCasa();
            tablero.agregarCasas(1);
        }

        for (UbicacionPropiedad h : sonHoteles) {
            while (h.getNumeroCasas() != cadaPropiedad) {
                h.quitarCasa();
            }
            tablero.agregarHoteles(1);
        }

        if (residuo != 0) {
            for (int i = 0; i < residuo; i++) {
                if (residuo == 1) {
                    sonHoteles.get(sonHoteles.size() - 1).agregarCasas();
                } else {
                    for (UbicacionPropiedad h2 : sonHoteles) {
                        if (h2 != propiedad && h2.getNumeroCasas() <= cadaPropiedad) {
                            h2.agregarCasas();
                            break;
                        }
                    }
                }
            }
        }
    }
}