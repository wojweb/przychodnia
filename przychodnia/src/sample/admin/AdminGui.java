package sample.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.ConnectionManagerInterface;
import sample.TypUzytkownika;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class AdminGui {
    private int HEIGHT = 800;
    private int WIDTH = 800;
    private int SPACING = 10;
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
        usersButton.setOnAction(e -> userMode());
        financeButton.setOnAction(e -> financesMode());
        employeeButton.setOnAction(e -> employeeMode());

        //Finanse firmy

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
    }


    void userMode(){

        TableView<UserProperty> table  = new TableView<UserProperty>();

        ObservableList<UserProperty> data = FXCollections.observableArrayList();
        ArrayList<UserView> users = manager.getUsers();

        for(UserView user : users){
            data.add(new UserProperty(user));
        }


        TableColumn peselColmumn = new TableColumn("Uzytkowik");
        peselColmumn.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("pesel"));
        TableColumn typeColumn = new TableColumn("Typ uzytkownika");
        typeColumn.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("typ"));

        table.setItems(data);
        table.getColumns().addAll(peselColmumn, typeColumn);

        root.setCenter(table);

        Button addUserButton = new Button("Dodaj uzytkownika");
        addUserButton.setOnAction(e -> {
            onAddUserButton();
        });

        Button changePasswdButton = new Button("Zmien haslo");
        changePasswdButton.setOnAction(e -> {
            UserProperty userProperty = table.getSelectionModel().getSelectedItem();
            onChangePasswdButton(userProperty);
        });

        Button deleteUserButton = new Button("Usun uzytkownika");
        deleteUserButton.setOnAction(e -> {
            UserProperty userProperty = table.getSelectionModel().getSelectedItem();
            onDeleteUserButton(userProperty);
        });





        VBox buttomsBox = new VBox(addUserButton, changePasswdButton, deleteUserButton);
        root.setRight(buttomsBox);
    }

    void onAddUserButton(){
        Stage addUserStage = new Stage();
        Label infoLabel = new Label("Wprowadz dane nowego uzytkownika");

        Label peselLabel = new Label("PESEL");
        TextField peselTextField = new TextField("");
        HBox peselHBox = new HBox(peselLabel, peselTextField);
        peselHBox.setAlignment(Pos.CENTER);
        peselHBox.setSpacing(SPACING);

        Label typeLabel = new Label("Typ");
        ComboBox<TypUzytkownika> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll(TypUzytkownika.values());
        HBox typeHBox = new HBox(typeLabel,typeComboBox);
        typeHBox.setAlignment(Pos.CENTER);
        typeHBox.setSpacing(SPACING);

        Label passwdLabel = new Label("Hasło");
        PasswordField passwdField = new PasswordField();
        HBox passwdHBox = new HBox(passwdLabel, passwdField);
        passwdHBox.setAlignment(Pos.CENTER);
        passwdHBox.setSpacing(SPACING);

        Label repeatPasswdLabel = new Label("Powtórz hasło");
        PasswordField repeatPasswdField = new PasswordField();
        HBox repeatPasswdHBox = new HBox(repeatPasswdLabel, repeatPasswdField);
        repeatPasswdHBox.setAlignment(Pos.CENTER);
        repeatPasswdHBox.setSpacing(SPACING);


        Button addButton = new Button("Dodaj");
        addButton.setOnAction(e -> {
            boolean state = false;
            if(passwdField.getText().equals(repeatPasswdField.getText())){
                state = manager.addUser(new UserView(peselTextField.getText(), passwdField.getText(), typeComboBox.getValue()));

            }

            if(state){
                userMode();
                addUserStage.close();
            }else{
                infoLabel.setText("Niepoprawne dane");
            }
        });

        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> {
            userMode();
            addUserStage.close();
        });

        HBox buttomsHBox = new HBox(addButton, cancelButton);
        buttomsHBox.setAlignment(Pos.CENTER);
        buttomsHBox.setSpacing(SPACING);

        VBox AllVBox = new VBox(infoLabel, peselHBox, typeHBox,
                passwdHBox, repeatPasswdHBox, buttomsHBox);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);
        Scene scene = new Scene(AllVBox, 300, 400);
        addUserStage.setTitle("Nowy użytkownik");
        addUserStage.setScene(scene);
        addUserStage.show();
    }

    void onChangePasswdButton(UserProperty userProperty){
        Stage changePasswdStage = new Stage();

        Label peselLabel =  new Label("Użytkownik: " + userProperty.getPesel());
        Label infoLabel = new Label("Wprowadz nowe haslo");

        Label passwdLabel = new Label("Nowe hasło");
        PasswordField passwdField = new PasswordField();
        HBox passwdHBox = new HBox(passwdLabel, passwdField);
        passwdHBox.setAlignment(Pos.CENTER);
        passwdHBox.setSpacing(SPACING);

        Label repeatPasswdLabel = new Label("Powtorz nowe haslo");
        PasswordField repeatPasswdField = new PasswordField();
        HBox repeatPasswdHBox = new HBox(repeatPasswdLabel, repeatPasswdField);
        repeatPasswdHBox.setAlignment(Pos.CENTER);
        repeatPasswdHBox.setSpacing(SPACING);

        Button changeButton = new Button("Zmień");
        changeButton.setOnAction(e -> {
            boolean state = false;
            if(passwdField.getText().equals(repeatPasswdField.getText())){
                UserView userView = new UserView(userProperty);
                userView.setPasswd(passwdField.getText());
                state = manager.changePasswd(userView);
            }
            if(state){
                changePasswdStage.close();
                userMode();
            }else{
                infoLabel.setText("Niepoprawne wartosc");
            }
        });
        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e ->{
            changePasswdStage.close();
            userMode();
        });
        HBox buttonsHBox = new HBox(changeButton, cancelButton);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(SPACING);

        VBox AllVBox = new VBox(peselLabel, infoLabel, passwdHBox, repeatPasswdHBox, buttonsHBox);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);

        Scene scene = new Scene(AllVBox, 300, 400);
        changePasswdStage.setScene(scene);
        changePasswdStage.show();


    }

    void onDeleteUserButton(UserProperty userProperty){
        manager.deleteUser(new UserView(userProperty));
        userMode();
    }

    void financesMode(){
        TableView<FinancesProperty> table = new TableView<>();
        ObservableList<FinancesProperty> data = FXCollections.observableArrayList();
        ArrayList<FinanceView> finances = manager.getFinances();

        Iterator<FinanceView> iterator = finances.iterator();
        while(iterator.hasNext())
            data.add(new FinancesProperty(iterator.next()));

        TableColumn yearColumn = new TableColumn("Rok");
        yearColumn.setCellValueFactory(new PropertyValueFactory<FinancesProperty, String>("year"));
        TableColumn incomeColumn = new TableColumn("Przychody");
        incomeColumn.setCellValueFactory(new PropertyValueFactory<FinancesProperty, String>("income"));
        TableColumn costsColumn = new TableColumn("Koszty");
        costsColumn.setCellValueFactory(new PropertyValueFactory<FinancesProperty, String>("costs"));
        TableColumn balanceColumn = new TableColumn("Bilans");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<FinancesProperty, String>("balance"));

        table.setItems(data);
        table.getColumns().addAll(yearColumn, incomeColumn, costsColumn, balanceColumn);

        root.setCenter(table);
        root.setRight(null);
     }

     void employeeMode(){
        TableView<EmployeeProperty> table = new TableView<>();
        ObservableList<EmployeeProperty> data = FXCollections.observableArrayList();

        ArrayList<EmployeeView> employees = manager.getEmployees();
        for(EmployeeView view : employees)
            data.add(new EmployeeProperty(view));

        TableColumn PESELColumn = new TableColumn("PESEL");
        PESELColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("PESEL"));
        TableColumn nameColumn = new TableColumn("Imie");
        nameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("name"));
        TableColumn secondNameColumn = new TableColumn("Drugie imie");
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("secondName"));
        TableColumn surnameColumn = new TableColumn("Nazwisko");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("surname"));
        TableColumn positionColumn = new TableColumn("Pozycja");
        positionColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("position"));
        TableColumn specializationColumn = new TableColumn("Specjalizacja");
        specializationColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("specialization"));
        TableColumn streetColumn = new TableColumn("Ulica");
        streetColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("street"));
        TableColumn cityColumn = new TableColumn("Miasto");
        cityColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("city"));
        TableColumn postcodeColumn = new TableColumn("Kod pocztowy");
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("postcode"));
        TableColumn telephoneColumn = new TableColumn("Telefon");
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("telephone"));
        TableColumn wageColumn = new TableColumn("Zarobki");
        wageColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("telephone"));
        TableColumn dateOfEmployeeColumn = new TableColumn("Data zatrudnienia");
        dateOfEmployeeColumn.setCellValueFactory(new PropertyValueFactory<EmployeeProperty, String>("dateOfEmployee"));

        table.setItems(data);
        table.getColumns().addAll(PESELColumn, nameColumn, secondNameColumn, surnameColumn, positionColumn,
                specializationColumn, streetColumn, cityColumn, postcodeColumn, telephoneColumn, wageColumn, dateOfEmployeeColumn);

        root.setCenter(table);
     }



}
