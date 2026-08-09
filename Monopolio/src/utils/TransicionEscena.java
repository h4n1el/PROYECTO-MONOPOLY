package utils;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransicionEscena {

    private TransicionEscena() {
       
    }

    public static void transicionEscena(Stage estado, Parent jerarquia) {
        if (estado == null || jerarquia == null || estado.getScene() == null) {
            return;
        }

        Parent rootActual = estado.getScene().getRoot();

        // 1. Animación de desvanecimiento de la escena actual (Fade Out)
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), rootActual);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Al terminar el Fade Out, cambiamos el contenido y redimensionamos
        fadeOut.setOnFinished(evento -> {
            // Cambiar el Root de la escena a la nueva jerarquía
            estado.getScene().setRoot(jerarquia);
            jerarquia.setOpacity(0.0); // Ocultar inicialmente para el Fade In

            // Calcular duraciones de redimensionado de ventana
            Duration duracionResize = estadoProporcion(estado, jerarquia);
            double anchoObjetivo = jerarquia.prefWidth(-1.0);
            double altoObjetivo = jerarquia.prefHeight(-1.0);

            // 2. Animación para ajustar el tamaño del Stage al nuevo contenido
            Timeline resizeTimeline = new Timeline(
                new KeyFrame(duracionResize,
                    new KeyValue(estadoWidth(estado), anchoObjetivo),
                    new KeyValue(estadoHeight(estado), altoObjetivo)
                )
            );

            // 3. Animación de aparición de la nueva vista (Fade In)
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), jerarquia);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            // Ejecutar ajuste de ventana y luego aparición de la nueva escena
            SequentialTransition seqIn = new SequentialTransition(resizeTimeline, fadeIn);
            seqIn.play();
        });

        
        fadeOut.play();
    }

    private static Duration estadoProporcion(Stage estado, Parent jerarquia) {
        double diffX = Math.abs(estado.getWidth() - jerarquia.prefWidth(-1.0));
        double diffY = Math.abs(estado.getHeight() - jerarquia.prefHeight(-1.0));
        double maxDiff = Math.max(diffX, diffY);

        if (maxDiff < 50.0) return Duration.millis(200);
        if (maxDiff < 150.0) return Duration.millis(400);
        if (maxDiff < 300.0) return Duration.millis(800);
        return Duration.millis(1200);
    }

    private static WritableValue<Double> estadoWidth(Stage estado) {
        return new WritableValue<Double>() {
            @Override
            public Double getValue() {
                return estado.getWidth();
            }

            @Override
            public void setValue(Double value) {
                estado.setWidth(value);
            }
        };
    }

    private static WritableValue<Double> estadoHeight(Stage estado) {
        return new WritableValue<Double>() {
            @Override
            public Double getValue() {
                return estado.getHeight();
            }

            @Override
            public void setValue(Double value) {
                estado.setHeight(value);
            }
        };
    }
}
