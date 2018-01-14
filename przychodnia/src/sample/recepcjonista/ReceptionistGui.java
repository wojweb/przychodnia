package sample.recepcjonista;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.TypUzytkownika;
import sample.admin.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ReceptionistGui {

    private int SPACING = 10;
    private BorderPane root;
    ReceptionistConnectionInterface manager;

    public ReceptionistGui(Stage stage){
        root = new BorderPane();
        manager = new FakeReceptionistConnection();

        Label infolabel = new Label("Menu recepcjonisty:");
        infolabel.setFont(new Font("Arial", 20));
        Button patientButton = new Button("");
        Button financeButton = new Button("Pacjenici");
        Button employeeButton = new Button("Pracownicy");

        HBox menu = new HBox(infolabel, patientButton);
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(SPACING);
        menu.setAlignment(Pos.CENTER);
        root.setTop(menu);
        patientButton.setOnAction(e -> patientMode());
//        financeButton.setOnAction(e -> financesMode());
//        employeeButton.setOnAction(e -> employeeMode());

        //Finanse firmy

        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
    }

    private void patientMode() {

        TableView<PatientProperty> table = new TableView<PatientProperty>();

        ObservableList<PatientProperty> data = FXCollections.observableArrayList();
        ArrayList<PatientView> patients = manager.getPatients();

        for (PatientView patient : patients) {
            data.add(new PatientProperty(patient));
        }

        TableColumn PESELColumn = new TableColumn("PESEL");
        PESELColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("PESEL"));
        TableColumn nameColumn = new TableColumn("Imie");
        nameColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("name"));
        TableColumn secondNameColumn = new TableColumn("Drugie imie");
        secondNameColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("secondName"));
        TableColumn surnameColumn = new TableColumn("Nazwisko");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("surname"));
        TableColumn insuredColumn = new TableColumn("Ubezpieczony");
        insuredColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("insured"));
        TableColumn streetColumn = new TableColumn("Ulica");
        streetColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("street"));
        TableColumn cityColumn = new TableColumn("Miasto");
        cityColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("city"));
        TableColumn postcodeColumn = new TableColumn("Kod pocztowy");
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("postcode"));
        TableColumn telephoneColumn = new TableColumn("Telefon");
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<PatientProperty, String>("telephone"));

        table.setItems(data);
        table.getColumns().addAll(PESELColumn, nameColumn, secondNameColumn, surnameColumn, insuredColumn,
                streetColumn, cityColumn, postcodeColumn, telephoneColumn);

        root.setCenter(table);

        Button addPatientButton = new Button("Dodaj pacjenta");
        addPatientButton.setOnAction(e -> {
            onAddPatientButton();
        });

        Button changePatientButton = new Button("Zmień dane");
        changePatientButton.setOnAction(e -> {
            PatientProperty patientProperty = table.getSelectionModel().getSelectedItem();
            if(patientProperty != null)
                onChangePatientButton(patientProperty);
        });

        Button deletePatientButton = new Button("Usun uzytkownika");
        deletePatientButton.setOnAction(e -> {
            PatientProperty patientProperty = table.getSelectionModel().getSelectedItem();
            if(patientProperty != null)
                onDeletePatientButton(patientProperty);
        });
        int buttonsSize = 200;
        addPatientButton.setMaxWidth(buttonsSize);
        changePatientButton.setMaxWidth(buttonsSize);
        deletePatientButton.setMaxWidth(buttonsSize);
        VBox vBox = new VBox(addPatientButton, changePatientButton, deletePatientButton);
        vBox.setSpacing(SPACING);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        root.setRight(vBox);


    }
/**
    private void onAddPatientButton(){
        Stage addUserStage = new Stage();
        addUserStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Wprowadz dane nowego uzytkownika");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(SPACING);
        gridPane.setHgap(SPACING);

        int i = 1;
        Label peselLabel = new Label("PESEL");
        TextField peselTextField = new TextField("");
        gridPane.addRow(i++, peselLabel,peselTextField);

        Label typeLabel = new Label("Typ");
        ComboBox<TypUzytkownika> typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll(TypUzytkownika.values());
        gridPane.addRow(i++, typeLabel, typeComboBox);

        Label passwdLabel = new Label("Hasło");
        PasswordField passwdField = new PasswordField();
        gridPane.addRow(i++, passwdLabel, passwdField);

        Label repeatPasswdLabel = new Label("Powtórz hasło");
        PasswordField repeatPasswdField = new PasswordField();
        gridPane.addRow(i++, repeatPasswdLabel, repeatPasswdField);


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
        gridPane.addRow(i++, addButton, cancelButton);


        VBox AllVBox = new VBox(infoLabel, gridPane);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);

        Scene scene = new Scene(AllVBox, 300, 400);
        addUserStage.setTitle("Nowy użytkownik");
        addUserStage.setScene(scene);
        addUserStage.show();
    }

    private void onChangePatientButton(UserProperty userProperty){
        Stage changePasswdStage = new Stage();
        changePasswdStage.setAlwaysOnTop(true);

        Label peselLabel =  new Label("Użytkownik: " + userProperty.getPesel());
        Label infoLabel = new Label("Wprowadz nowe haslo");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(SPACING);
        gridPane.setHgap(SPACING);

        int i = 1;
        Label passwdLabel = new Label("Nowe hasło");
        PasswordField passwdField = new PasswordField();
        gridPane.addRow(i++, passwdLabel, passwdField);

        Label repeatPasswdLabel = new Label("Powtorz nowe haslo");
        PasswordField repeatPasswdField = new PasswordField();
        gridPane.addRow(i++, repeatPasswdLabel, repeatPasswdField);

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
        gridPane.addRow(i++, changeButton, cancelButton);

        VBox AllVBox = new VBox(peselLabel, infoLabel, gridPane);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);

        Scene scene = new Scene(AllVBox, 300, 400);
        changePasswdStage.setScene(scene);
        changePasswdStage.show();


    }

    private void onDeleteUserButton(UserProperty userProperty){
        manager.deleteUser(new UserView(userProperty));
        userMode();
    }

    /**

    private void financesMode(){
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

    private void employeeMode(){
        TableView<EmployeeProperty> table = new TableView<>();
        ObservableList<EmployeeProperty> data = FXCollections.observableArrayList();

        ArrayList<EmployeeView> employees = manager.getEmployees();
        for(EmployeeView view : employees)
            data.add(new EmployeeProperty(view));



    }
*/
    private void onAddPatientButton(){
        int patientWidth = 400;
        int patientHeight = 600;
        Stage addPatientStage = new Stage();
        addPatientStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Wprowadz dane nowego pacjenta");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(SPACING);
        gridPane.setVgap(SPACING);

        int i = 1;
        Label peselLabel = new Label("PESEL");
        TextField peselTextField = new TextField();
        gridPane.addRow(i++, peselLabel, peselTextField);
        Label nameLabel = new Label("Imie");
        TextField nameTextField = new TextField();
        gridPane.addRow(i++, nameLabel, nameTextField);

        Label secondNameLabel = new Label("Drugie imie");
        TextField secondNameTextField = new TextField();
        gridPane.addRow(i++, secondNameLabel, secondNameTextField);

        Label surnameLabel = new Label("Nazwisko");
        TextField surnameTextField = new TextField();
        gridPane.addRow(i++, surnameLabel, surnameTextField);

        Label insuredLabel = new Label("Ubezpieczony");
        CheckBox insuredCheckBox = new CheckBox();
        insuredCheckBox.setSelected(false);
        gridPane.addRow(i++, insuredLabel, insuredCheckBox);


        Label streetLabel = new Label("Ulica");
        TextField streetTextField = new TextField();
        gridPane.addRow(i++, streetLabel, streetTextField);

        Label cityLabel = new Label("Miasto");
        TextField cityTextField = new TextField();
        gridPane.addRow(i++, cityLabel, cityTextField);

        Label postcodeLabel = new Label("Kod pocztowy");
        TextField postcodeTextField = new TextField();
        gridPane.addRow(i++, postcodeLabel, postcodeTextField);

        Label telephoneLabel = new Label("Telefon");
        TextField telephoneTextField = new TextField();
        gridPane.addRow(i++, telephoneLabel, telephoneTextField);

        Button addButton = new Button("Dodaj");
        addButton.setOnAction(e -> {

            String secondName;
            if(secondNameTextField.getText().isEmpty())
                secondName = null;
            else
                secondName = secondNameTextField.getText();
            PatientView patientView = new PatientView(peselTextField.getText(), nameTextField.getText(),
                    secondName, surnameTextField.getText(), insuredCheckBox.isSelected(),
                    streetTextField.getText(), cityTextField.getText(), postcodeTextField.getText(),
                    telephoneTextField.getText());
            if(manager.addPatient(patientView)){
                addPatientStage.close();
                patientMode();
            }else {
                infoLabel.setText("Nieprawidlowe dane");
            }
        });


        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> addPatientStage.close());
        gridPane.addRow(i++, addButton, cancelButton);

        VBox vBox = new VBox(infoLabel, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(SPACING);

        Scene scene = new Scene(vBox, patientWidth, patientHeight);
        addPatientStage.setTitle("Dodaj pacjenta");
        addPatientStage.setScene(scene);
        addPatientStage.show();
    }

    private void onChangePatientButton(PatientProperty patientProperty){
        int patientWidth = 400;
        int patientHeight = 600;
        Stage changePatientStage = new Stage();
        changePatientStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Zmień odpowiednie dane pacjenta");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(SPACING);
        gridPane.setVgap(SPACING);

        int i = 1;
        Label peselLabel = new Label("PESEL");
        Label peselCompletedLabel = new Label(patientProperty.getPESEL());
        gridPane.addRow(i++, peselLabel, peselCompletedLabel);

        Label nameLabel = new Label("Imie");
        TextField nameTextField = new TextField(patientProperty.getName());
        gridPane.addRow(i++, nameLabel, nameTextField);

        Label secondNameLabel = new Label("Drugie imie");
        TextField secondNameTextField = new TextField();
        if(!patientProperty.getSecondName().equals("-"))
            secondNameTextField.setText(patientProperty.getSecondName());
        gridPane.addRow(i++, secondNameLabel, secondNameTextField);

        Label surnameLabel = new Label("Nazwisko");
        TextField surnameTextField = new TextField(patientProperty.getSurname());
        gridPane.addRow(i++, surnameLabel, surnameTextField);

        Label insuredLabel = new Label("Ubezpieczony");
        CheckBox insuredCheckBox = new CheckBox();
        insuredCheckBox.setSelected((new PatientView(patientProperty)).isInsured());
        gridPane.addRow(i++, insuredLabel, insuredCheckBox);

        Label streetLabel = new Label("Ulica");
        TextField streetTextField = new TextField(patientProperty.getStreet());
        gridPane.addRow(i++, streetLabel, streetTextField);

        Label cityLabel = new Label("Miasto");
        TextField cityTextField = new TextField(patientProperty.getCity());
        gridPane.addRow(i++, cityLabel, cityTextField);

        Label postcodeLabel = new Label("Kod pocztowy");
        TextField postcodeTextField = new TextField(patientProperty.getPostcode());
        gridPane.addRow(i++, postcodeLabel, postcodeTextField);

        Label telephoneLabel = new Label("Telefon");
        TextField telephoneTextField = new TextField(patientProperty.getTelephone());
        gridPane.addRow(i++, telephoneLabel, telephoneTextField);


        Button addButton = new Button("Zmień");
        addButton.setOnAction(e -> {

            String secondName;
            if(secondNameTextField.getText().isEmpty())
                secondName = null;
            else
                secondName = secondNameTextField.getText();
            PatientView patientView = new PatientView(patientProperty.getPESEL(), nameTextField.getText(),
                    secondName, surnameTextField.getText(), insuredCheckBox.isSelected(),
                    streetTextField.getText(), cityTextField.getText(),
                    postcodeTextField.getText(), telephoneTextField.getText());
            if(manager.changePatient(patientView)){
                changePatientStage.close();
                patientMode();
            }else {
                infoLabel.setText("Nieprawidlowe dane");
            }

        });

        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> changePatientStage.close());
        gridPane.addRow(i++, addButton, cancelButton);

        VBox vBox = new VBox(infoLabel, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(SPACING);

        Scene scene = new Scene(vBox, patientWidth, patientHeight);
        changePatientStage.setTitle("Dodaj pracownika");
        changePatientStage.setScene(scene);
        changePatientStage.show();

    }

    private void onDeletePatientButton(PatientProperty patientProperty){
        manager.deletePatient(new PatientView(patientProperty));
        patientMode();
    }


 }
