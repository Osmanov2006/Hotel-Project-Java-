import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.*;

public class MainScreen{
    private Stage stage;
    private GridPane roomGrid;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private ListView<String> customerListView;
    private TextArea customerDetailArea;

    public MainScreen(Stage stage){
        this.stage=stage;
        this.stage.setTitle("Hotel Management System");
    }

    public void show(){
        BorderPane root=new BorderPane();
        root.setPadding(new Insets(10));

        VBox topSection=new VBox(10);
        topSection.setPadding(new Insets(10));

        Label titleLabel=new Label("Room Availability");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox dateBox=new HBox(15);
        dateBox.setAlignment(Pos.CENTER);

        Label startLabel=new Label("Start Date:");
        startDatePicker=new DatePicker(LocalDate.now());

        Label endLabel=new Label("End Date:");
        endDatePicker=new DatePicker(LocalDate.now().plusDays(1));

        Button updateBtn=new Button("Update View");
        updateBtn.setOnAction(e->updateRoomColors());

        Button managementBtn=new Button("Management");
        managementBtn.setOnAction(e->openManagement());

        dateBox.getChildren().addAll(startLabel,startDatePicker,endLabel,endDatePicker,updateBtn,managementBtn);

        topSection.getChildren().addAll(titleLabel,dateBox);
        root.setTop(topSection);

        roomGrid=new GridPane();
        roomGrid.setHgap(10);
        roomGrid.setVgap(10);
        roomGrid.setPadding(new Insets(20));
        roomGrid.setAlignment(Pos.CENTER);

        createRoomGrid();

        ScrollPane scrollPane=new ScrollPane(roomGrid);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        VBox rightPanel=new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setPrefWidth(300);

        Label customerLabel=new Label("Customers in Selected Room:");
        customerLabel.setStyle("-fx-font-weight: bold;");

        customerListView=new ListView<>();
        customerListView.setPrefHeight(200);
        customerListView.setOnMouseClicked(e->{
            if(e.getClickCount()==1){
                showCustomerDetails();
            }
        });

        Label detailLabel=new Label("Customer Details:");
        detailLabel.setStyle("-fx-font-weight: bold;");

        customerDetailArea=new TextArea();
        customerDetailArea.setEditable(false);
        customerDetailArea.setPrefHeight(200);

        rightPanel.getChildren().addAll(customerLabel,customerListView,detailLabel,customerDetailArea);
        root.setRight(rightPanel);

        Scene scene=new Scene(root,1200,700);
        stage.setScene(scene);
        stage.show();

        updateRoomColors();
    }

    private void createRoomGrid(){
        DataManager dm=DataManager.getInstance();
        List<Room> rooms=dm.getRooms();

        int row=0,col=0;
        for(Room room:rooms){
            Button roomBtn=new Button("Room "+room.getRoomNumber()+"\n"+room.getRoomType());
            roomBtn.setPrefSize(120,80);
            roomBtn.setUserData(room.getRoomNumber());
            roomBtn.setOnAction(e->showRoomCustomers(room.getRoomNumber()));

            roomGrid.add(roomBtn,col,row);

            col++;
            if(col==5){
                col=0;
                row++;
            }
        }
    }

    private void updateRoomColors(){
        LocalDate start=startDatePicker.getValue();
        LocalDate end=endDatePicker.getValue();

        if(start==null || end==null){
            showAlert("Error","Please select valid dates");
            return;
        }

        if(start.isAfter(end)){
            showAlert("Error","Start date must be before end date");
            return;
        }

        DataManager dm=DataManager.getInstance();
        List<Reservation> reservations=dm.getReservations();

        for(var node:roomGrid.getChildren()){
            if(node instanceof Button){
                Button btn=(Button)node;
                int roomNum=(int)btn.getUserData();

                long totalDays=start.until(end).getDays()+1;
                long occupiedDays=0;

                for(Reservation res:reservations){
                    if(res.getRoomNumber()==roomNum && res.overlaps(start,end)){
                        LocalDate overlapStart=res.getStartDate().isBefore(start)?start:res.getStartDate();
                        LocalDate overlapEnd=res.getEndDate().isAfter(end)?end:res.getEndDate();
                        occupiedDays+=overlapStart.until(overlapEnd).getDays()+1;
                    }
                }

                if(occupiedDays==0){
                    btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                }else if(occupiedDays>=totalDays){
                    btn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                }else{
                    btn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
                }
            }
        }
    }

    private void showRoomCustomers(int roomNumber){
        customerListView.getItems().clear();
        customerDetailArea.clear();

        LocalDate start=startDatePicker.getValue();
        LocalDate end=endDatePicker.getValue();

        DataManager dm=DataManager.getInstance();
        List<Customer> customers=new ArrayList<>();

        for(Reservation res:dm.getReservations()){
            if(res.getRoomNumber()==roomNumber && res.overlaps(start,end)){
                customers.add(res.getCustomer());
            }
        }

        Collections.sort(customers);

        for(Customer c:customers){
            customerListView.getItems().add(c.getName()+" - "+c.getCustomerId());
        }
    }

    private void showCustomerDetails(){
        String selected=customerListView.getSelectionModel().getSelectedItem();
        if(selected==null) return;

        String customerId=selected.split(" - ")[1];

        DataManager dm=DataManager.getInstance();
        for(Reservation res:dm.getReservations()){
            Customer c=res.getCustomer();
            if(c.getCustomerId().equals(customerId)){
                String details="Name: "+c.getName()+"\n"+
                        "ID: "+c.getCustomerId()+"\n"+
                        "Phone: "+c.getPhone()+"\n"+
                        "Email: "+c.getEmail()+"\n"+
                        "Check-in: "+c.getCheckInDate()+"\n"+
                        "Check-out: "+c.getCheckOutDate()+"\n"+
                        "Reservation Start: "+res.getStartDate()+"\n"+
                        "Reservation End: "+res.getEndDate();
                customerDetailArea.setText(details);
                break;
            }
        }
    }

    private void openManagement(){
        ManagementScreen mgmt=new ManagementScreen();
        mgmt.show();
    }

    private void showAlert(String title,String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
