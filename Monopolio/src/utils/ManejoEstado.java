package utils;

import java.io.IOException;
import java.lang.reflect.Method;

import utils.interfaz.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import utils.interfaz.LateData;
import utils.interfaz.Manejable;

public class ManejoEstado {
    private Stage estado = new Stage();
    private FXMLLoader carga;
    private double xOffset;
    private double yOffset;
    private Scene scene;

    public ManejoEstado(Stage estado) {
        this.estado = estado;
        this.initialDisplay();
    }

    public void getDialogo(Dialogos dialogo) {
        dialogo.mostrarDialogo(this.estado);
    }

    public Alert getDialogoJuego(DialogoJuego dialogo){
        return dialogo.mostrarDialogo(this.estado);
    }

    public LateData getLateData(){
        //this.carga != null se encarga de que el objeto FXML haya sido inicializado y no sea nulo
        if(this.carga != null && this.carga.getController() instanceof LateData)// esto se encarga de que el controlador del FXML implemente la interfaz LateData
            {
            LateData data = (LateData) this.carga.getController();
            return data;
        }
        return null; // si el controlador no implementa la interfaz LateData o el carga es nulo, se devuelve null
    }

    public void salirPrograma(){
        this.estado.close();
    }

    public void iconificar(){
        this.estado.setIconified(true);
    }

    private void initialDisplay() {
        this.carga = null;
        Scene escena = null;
        try {
            this.carga = new FXMLLoader();
            this.carga.setLocation(getClass().getResource("/Monopolio/vistas/Inicio.fxml"));
            Parent raiz = (Parent) this.carga.load();
            escena = new Scene(raiz);
        } catch (IOException e) {
            this.dialogoError("Hubo un problema de entrada/salida.", e);
        }
        this.estado.initStyle(StageStyle.TRANSPARENT);
        this.estado.getIcons().add(new Image(getClass().getResource("/Monopolio/recursos/Icono.png").toExternalForm()));
        this.estado.setTitle("Monopolio");
        this.estado.setScene(escena);
        this.estado.show();
        this.habilitarCambioPosicion();
        this.pasarManejoEstado();
    }

    public void cambiarEscena(View vista) {
        try {
            Parent jerarquia = this.cargarJerarquia(vista);
            this.ejecutarTransicion(jerarquia);
            this.pasarManejoEstado();
        } catch (Exception e) {
            this.dialogoError("Hubo un problema", e);
        }
    }

    private Parent cargarJerarquia(View vista) {
        Parent root = null;
        this.carga = null;
        try {
            this.carga = new FXMLLoader();
            String rutaFXML = this.obtenerRutaFXML(vista);
            if (rutaFXML == null || rutaFXML.isBlank()) {
                System.out.println("No se pudo obtener la ruta FXML para la vista: " + vista);
            }
            this.carga.setLocation(getClass().getResource(rutaFXML));
            root = (Parent) this.carga.load();
        } catch (IOException e) {
            this.dialogoError("Hubo un problema de entrada/salida.", e);
        }
        return root;
    }

    private String obtenerRutaFXML(View vista) {
        if (vista == null) {
            return null;
        }

        String[] posiblesMetodos = { "getRutaFXML", "getRuta", "getFXML", "getFxml", "getRutaFxml" };

        for (String nombreMetodo : posiblesMetodos) {
            try {
                Method metodo = vista.getClass().getMethod(nombreMetodo);
                Object resultado = metodo.invoke(vista);
                if (resultado instanceof String) {
                    return (String) resultado;
                }
            } catch (NoSuchMethodException ignored) {
            } catch (Exception e) {
                this.dialogoError("No se pudo obtener la ruta FXML desde la vista.", e);
                break;
            }
        }

        throw new IllegalStateException("No se encontró un método válido para obtener la ruta FXML en la vista proporcionada.");
    }

    private void pasarManejoEstado() {
        if (this.carga.getController() instanceof Manejable) {
            Manejable manejo = (Manejable) this.carga.getController();
            manejo.setManejable(this);
        }
    }

    private void ejecutarTransicion(Parent raiz) {
        TransicionEscena.transicionEscena(this.estado, raiz);
    }

    private void dialogoError(String mensaje, Exception excepcion) {
        Alert alertaError = new Alert(Alert.AlertType.NONE);
        alertaError.initStyle(StageStyle.TRANSPARENT);
        alertaError.getDialogPane().getStylesheets().add(getClass().getResource("/Monopolio/recursos/dialogos.css").toExternalForm());
        alertaError.getDialogPane().getStyleClass().add("dialogo");
        alertaError.initModality(Modality.APPLICATION_MODAL);
        alertaError.initOwner((Window) this.estado);
        alertaError.setContentText(mensaje + "\n" + excepcion.getCause());
        alertaError.getButtonTypes().add(ButtonType.OK);
        alertaError.showAndWait();
        this.estado.close();
    }

    private void habilitarCambioPosicion() {
        Scene escena = this.estado.getScene();
        escena.setOnMousePressed(e -> {
            this.xOffset = this.estado.getX() - e.getScreenX();
            this.yOffset = this.estado.getY() - e.getScreenY();
        });
        escena.setOnMouseDragged(e -> {
            this.estado.setX(e.getScreenX() + this.xOffset);
            this.estado.setY(e.getScreenY() + this.yOffset);
        });
    }
}


