public class Navegacion{
    public static <T>(String rutaFXML, String titulo){

        try {
            FXMLLoader cargar = new FXMLLoader(rutaFXML
            Navegacion.class.getResource(rutaFXML));
            
            Stage estado = new Stage();
            Scene escena = new Scene(cargar.load());
            estado.setTitle(titulo);
            estado.setScene(escena);
            estado.show();
            return cargar.getController();

        } catch (Exception e) {
            throw new RuntimeException("no se pudo cambiar de ventana")
        }


    }
}