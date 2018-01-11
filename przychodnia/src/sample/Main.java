package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.admin.AdminGui;

public class Main extends Application {
    int WIDTH = 300;
    int HEIGHT = 400;
    ConnectionManagerInterface manager;
    DataValidationInterface validation;



    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label infoLabel = new Label();
        infoLabel.setText("Zaloguj się do systemu");

        Label peselLabel = new Label("PESEL");
        TextField peselTextField = new TextField();
        HBox peselHBox = new HBox(peselLabel, peselTextField);
        peselHBox.setSpacing(10);
        peselHBox.setAlignment(Pos.CENTER);

        Label passwdLabel = new Label("Haslo");
        TextField passwdTextField = new TextField();
        HBox passwdHBox = new HBox(passwdLabel, passwdTextField);
        passwdHBox.setSpacing(10);
        passwdHBox.setAlignment(Pos.CENTER);

        Button logInButton = new Button("Zaloguj");
        logInButton.setOnAction(e -> {
//            boolean onLogInButtonClicked(peselTextField.getText(), passwdTextField.getText());
            AdminGui gui = new AdminGui(primaryStage);


        });

        Button createAccountButton = new Button("Zaloz konto");
        createAccountButton.setOnAction(e -> {

        });

        VBox all = new VBox(infoLabel, peselHBox, passwdHBox, logInButton);
        all.setSpacing(20);
        all.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(all, WIDTH, HEIGHT));
        primaryStage.show();


    }

    boolean onLogInButtonClicked(String pesel, String passwd){
        if(!(validation.peselValidation(pesel) && validation.passwdValidation(passwd)))
            return false;

        TypUzytkownika typ = manager.logIn(pesel, passwd);
        AdminGui gui;
        switch(typ){
            case Administrator:

        }


        return true;
    }





    public static void main(String[] args) {
        launch(args);
    }
}
