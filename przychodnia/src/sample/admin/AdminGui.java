package sample.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.ConnectionManagerInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class AdminGui {
    private int HEIGHT = 800;
    private int WIDTH = 800;
    private BorderPane root;
    AdminConnectionInterface manager;

    public AdminGui(Stage stage){
        root = new BorderPane();
        manager = new FakeAdminConnection();

        Label infolabel = new Label("Menu administratora:");
        infolabel.setFont(new Font("Arial", 20));
        Button usersButton = new Button("Uzytkownicy");
        Button financeButton = new Button("Finanse");
        Button employeeButton = new Button("Pracownicy");
        HBox menu = new HBox(infolabel, usersButton, financeButton, employeeButton);
        root.setTop(menu);
        usersButton.setOnAction(e ->{
            userMode();
                }

        );
        //Zarzadzanie uzytkownikami
        Button addUserButton = new Button("Dodaj uzytkownika");
        Button deleteUserButton = new Button("Usun uzytkownika");
        Button changePasswdButton = new Button( "Zmien haslo");

        //Finanse firmy

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
    }

    void userMode(){

        TableView<UserProperty> table  = new TableView<UserProperty>();

        ObservableList<UserProperty> data = FXCollections.observableArrayList();
        UserView [] users = manager.getUsers();

        for(UserView user : users){
            data.add(new UserProperty(user));
            System.out.println("");
        }


        TableColumn peselColmumn = new TableColumn("Uzytkowik");
        peselColmumn.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("pesel"));
        TableColumn typeColumn = new TableColumn("Typ uzytkownika");
        typeColumn.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("typ"));

        table.setItems(data);
        table.getColumns().addAll(peselColmumn, typeColumn);

        root.setCenter(table);

        Button deleteUserButton = new Button("Usun uzytkownika");
        deleteUserButton.setOnAction(e -> {
        });

        Button changePasswdButton = new Button("Zmien haslo");
        changePasswdButton.setOnAction(e -> {
        });

        Button addUserButton = new Button("Dodaj uzytkownika");
        addUserButton.setOnAction(e -> {
        });

        VBox buttomsBox = new VBox(addUserButton, changePasswdButton, deleteUserButton);
        root.setRight(buttomsBox);







    }





}
