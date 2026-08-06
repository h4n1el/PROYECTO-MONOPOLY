import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
  public void start(Stage primerEstado) throws Exception
  {
    FXMLLoader load = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));

    primerEstado.setTitle("Registro Bancario");
    primerEstado.setScene(new Scene(load.load()));
    primerEstado.setWidth(900);
    primerEstado.setHeight(800);
    primerEstado.show();
  }

    
     public static void main(String[] args) {
        launch(args);
    }
}