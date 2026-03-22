import javafx.application.Application;
import javafx.stage.Stage;

public class HotelManagementSystem extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        LoginScreen loginScreen=new LoginScreen(primaryStage);
        loginScreen.show();
    }
}