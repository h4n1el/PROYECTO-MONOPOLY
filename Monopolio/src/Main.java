import javafx.application.Application;
import javafx.stage.Stage;
import utils.ManejoEstado;


public class Main extends Application {

    @Override
    public void start(Stage estadoPrincipal) {
        new ManejoEstado(estadoPrincipal);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
