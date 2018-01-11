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
     AdminConnectionManager manager;

    public AdminGui(Stage stage){
        root = new BorderPane();

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

        TableView<UserView> table  = new TableView();
//        table.setMaxSize(100, 100);
        ObservableList<UserView> data = FXCollections.observableArrayList();
        UserView [] users = manager.getUsers();

        data.addAll(Arrays.asList(users));

        TableColumn peselColmumn = new TableColumn("Uzytkowik");
        peselColmumn.setCellFactory(new PropertyValueFactory<UserView, String>("PESEL"));
        TableColumn typeColumn = new TableColumn("Typ uzytkownika");
        peselColmumn.setCellFactory(new PropertyValueFactory<UserView, String>("typ"));
        table.getColumns().addAll(peselColmumn, typeColumn);

        root.setCenter(table);

        Button deleteUserButton = new Button("Usun uzytkownika");
        deleteUserButton.setOnAction(e -> {
            UserView user = table.getSelectionModel().getSelectedItem();
            manager.deteleUser(user.getPESEL());
            userMode();

        });

        Button changePasswdButton = new Button("Zmien haslo");
        changePasswdButton.setOnAction(e -> {
            UserView user = table.getSelectionModel().getSelectedItem();
            Label peselLabel = new Label(user.getPESEL());
            TextField newPawsswdTextField = new TextField("Nowe haslo");
            Button changeButton = new Button("Zmien");
            changeButton.setOnAction(event -> {
                manager.changePasswd(user.getPESEL(), newPawsswdTextField.getText());
            });

            HBox hBox = new HBox(peselLabel, newPawsswdTextField, changeButton);
            root.setBottom(hBox);
        });

        Button addUserButton = new Button("Dodaj uzytkownika");
        addUserButton.setOnAction(e -> {
            TextField peselTextField = new TextField("PESEL");
            TextField passwdTextField = new TextField("Nowe haslo");
            Button addButton = new Button("Dodaj");
            addButton.setOnAction(event -> {
                manager.addUser(peselTextField.getText(), passwdTextField.getText());
            });
        });

        VBox buttomsBox = new VBox(addUserButton, changePasswdButton, deleteUserButton);
        root.setRight(buttomsBox);


        //Dodanie uzytkownika




    }



}
