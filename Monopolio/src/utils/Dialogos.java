package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public enum Dialogos {
    ABOUT{
        @Override
        public void mostrarDialogo(Stage estado) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.getDialogPane().getStylesheets().add(this.getClass().getResource("/Monopolio/recursos/dialogos.css").toExternalForm());

            alert.getDialogPane().getStyleClass().add("dialogo");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(estado);

            
            
            alert.setTitle("Confirmacin");
            alert.setHeaderText("estas seguro?");
            alert.setContentText("¿Desea vender esta propiedad?");

            ButtonType buttonTypeYes = new ButtonType("Sí", ButtonType.YES.getButtonData());
            ButtonType buttonTypeNo = new ButtonType("No", ButtonType.NO.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {
                    System.out.println("Se ha confirmado la acción.");
                } else if (response == buttonTypeNo) {
                    System.out.println("Se ha cancelado la acción.");
                }
            });
        }
    };

    public abstract void mostrarDialogo(Stage estado);
}