import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalTime;

public class LoginScreen{
    private Stage stage;
    private TextField usernameField;
    private PasswordField passwordField;
    private Canvas clockCanvas;

    public LoginScreen(Stage stage){
        this.stage=stage;
        this.stage.setTitle("Hotel Management System - Login");
    }

    public void show(){
        VBox root=new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f0f0;");

        Label titleLabel=new Label("Hotel Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        clockCanvas=new Canvas(200,200);
        drawClock();
        Timeline timeline=new Timeline(new KeyFrame(Duration.seconds(1),e->drawClock()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        GridPane loginPane=new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setHgap(10);
        loginPane.setVgap(15);
        loginPane.setPadding(new Insets(20));
        loginPane.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 5;");

        Label userLabel=new Label("Username:");
        usernameField=new TextField();
        usernameField.setPromptText("Enter username");

        Label passLabel=new Label("Password:");
        passwordField=new PasswordField();
        passwordField.setPromptText("Enter password");

        Button loginBtn=new Button("Login");
        loginBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        loginBtn.setOnAction(e->handleLogin());

        loginPane.add(userLabel,0,0);
        loginPane.add(usernameField,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(passwordField,1,1);
        loginPane.add(loginBtn,1,2);

        Label hintLabel=new Label("Default: admin/admin");
        hintLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");

        root.getChildren().addAll(titleLabel,clockCanvas,loginPane,hintLabel);

        Scene scene=new Scene(root,500,600);
        stage.setScene(scene);
        stage.show();
    }

    private void drawClock(){
        GraphicsContext gc=clockCanvas.getGraphicsContext2D();
        double width=clockCanvas.getWidth();
        double height=clockCanvas.getHeight();
        double centerX=width/2;
        double centerY=height/2;
        double radius=Math.min(width,height)/2-10;

        gc.clearRect(0,0,width,height);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
        gc.strokeOval(centerX-radius,centerY-radius,radius*2,radius*2);

        for(int i=0;i<12;i++){
            double angle=Math.toRadians(i*30-90);
            double x1=centerX+Math.cos(angle)*(radius-15);
            double y1=centerY+Math.sin(angle)*(radius-15);
            double x2=centerX+Math.cos(angle)*(radius-5);
            double y2=centerY+Math.sin(angle)*(radius-5);
            gc.strokeLine(x1,y1,x2,y2);
        }

        LocalTime now=LocalTime.now();
        int hour=now.getHour()%12;
        int minute=now.getMinute();
        int second=now.getSecond();

        double hourAngle=Math.toRadians((hour*30+minute*0.5)-90);
        double minuteAngle=Math.toRadians((minute*6)-90);
        double secondAngle=Math.toRadians((second*6)-90);

        gc.setLineWidth(4);
        gc.strokeLine(centerX,centerY,centerX+Math.cos(hourAngle)*(radius*0.5),
                centerY+Math.sin(hourAngle)*(radius*0.5));

        gc.setLineWidth(2);
        gc.strokeLine(centerX,centerY,centerX+Math.cos(minuteAngle)*(radius*0.7),
                centerY+Math.sin(minuteAngle)*(radius*0.7));

        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeLine(centerX,centerY,centerX+Math.cos(secondAngle)*(radius*0.8),
                centerY+Math.sin(secondAngle)*(radius*0.8));

        gc.setFill(Color.BLACK);
        gc.fillOval(centerX-5,centerY-5,10,10);
    }

    private void handleLogin(){
        String username=usernameField.getText();
        String password=passwordField.getText();

        if(username.isEmpty() || password.isEmpty()){
            showAlert("Error","Please enter username and password");
            return;
        }

        DataManager dm=DataManager.getInstance();
        boolean loginSuccess=false;

        for(Employee emp:dm.getEmployees()){
            if(emp.login(username,password)){
                loginSuccess=true;
                break;
            }
        }

        if(loginSuccess){
            MainScreen mainScreen=new MainScreen(stage);
            mainScreen.show();
        }else{
            showAlert("Login Failed","Invalid username or password");
        }
    }

    private void showAlert(String title,String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
