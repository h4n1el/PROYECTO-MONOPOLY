package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;

public enum DialogoJuego {
    
    EXIT {
        @Override
        public Alert mostrarDialogo(Stage escenario) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.TRANSPARENT);
            
            
            URL cssResource = this.getClass().getResource("/Monopolio/recursos/dialogos.css");
            if (cssResource != null) {
                alert.getDialogPane().getStylesheets().add(cssResource.toExternalForm());
            }
            
            alert.getDialogPane().getStyleClass().add("dialogo");
            alert.initModality(Modality.APPLICATION_MODAL); // esto hace que bloquee la interaccion con otras ventanas hasta que se cierre el dialogo
            alert.initOwner(escenario);

            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Estás seguro?");
            alert.setContentText("¿Desea realizar esta accion?");

            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            return alert;
        }
    };

    // es necesario tener un metodo abstracto para que cada constante del enum pueda proporcionar su propia implementación
    public abstract Alert mostrarDialogo(Stage escenario);
}
