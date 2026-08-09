package utils;

import java.util.ArrayList;
import model.tablero.UbicacionPropiedad;

public final class ColorGrupo {

    private ColorGrupo() {
        
    }

    public static void assimilate(ArrayList<UbicacionPropiedad> grupo) {
        if (grupo == null || grupo.isEmpty()) return;

        boolean mismoDueno = true;
        Object primerDueno = grupo.get(0).getDueno();

        // Si la primera propiedad no tiene dueño, nadie posee el grupo completo
        if (primerDueno == null) {
            mismoDueno = false;
        } else {
            for (UbicacionPropiedad propiedad : grupo) {
                if (propiedad.getDueno() != primerDueno) {
                    mismoDueno = false;
                    break;
                }
            }
        }

        if (mismoDueno) {
            grupo.forEach(propiedad -> propiedad.setColorEstado(true));
            ColorGrupo.grupoMejorable(grupo);
            ColorGrupo.mejorableHotelCheck(grupo);
        } else {
            
            grupo.forEach(propiedad -> {
                propiedad.setColorEstado(false);
                propiedad.setEsMejorableEstado(false);
                propiedad.setMejorasHotel(false);
            });
        }
    }

    // Regla de edificacion uniforme: solo puedes construir casa en las propiedades con menor cantidad de casas del grupo
    private static void grupoMejorable(ArrayList<UbicacionPropiedad> grupo) {
        for (UbicacionPropiedad propiedad : grupo) {
            if (propiedad.getEsHotel()) {
                propiedad.setEsMejorableEstado(false);
                continue;
            }

            boolean mejora = true;
            for (UbicacionPropiedad otro : grupo) {
                // Si otra propiedad del grupo tiene menos casas, esta propiedad NO se puede mejorar aún
                if (!otro.equals(propiedad) && propiedad.getNumeroCasas() > otro.getNumeroCasas()) {
                    mejora = false;
                    break;
                }
            }
            propiedad.setEsMejorableEstado(mejora);
        }
    }

   
    private static void mejorableHotelCheck(ArrayList<UbicacionPropiedad> grupo) {
        boolean propiedadesListas = true;

        for (UbicacionPropiedad propiedad : grupo) {
            if (propiedad.getNumeroCasas() < 4 && !propiedad.getEsHotel()) {
                propiedadesListas = false;
                break;
            }
        }

        for (UbicacionPropiedad propiedad : grupo) {
            boolean mejora = propiedadesListas && (propiedad.getNumeroCasas() == 4);
            propiedad.setMejorasHotel(mejora);
        }
    }
}