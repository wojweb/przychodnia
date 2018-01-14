package sample.admin;

import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ConnectionManagerInterface;
import sample.TypUzytkownika;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;


public class AdminGui {
    private int HEIGHT;
    private int WIDTH;
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
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(SPACING);
        menu.setAlignment(Pos.CENTER);
        root.setTop(menu);
        usersButton.setOnAction(e -> userMode());
        financeButton.setOnAction(e -> financesMode());
        employeeButton.setOnAction(e -> employeeMode());

        //Finanse firmy

        Scene scene = new Scene(root);
        stage.setMaximized(true);

        stage.setScene(scene);
    }

    private void userMode(){

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
            if(userProperty != null)
                onChangePasswdButton(userProperty);
        });

        Button deleteUserButton = new Button("Usun uzytkownika");
        deleteUserButton.setOnAction(e -> {
            UserProperty userProperty = table.getSelectionModel().getSelectedItem();
            if(userProperty != null)
                onDeleteUserButton(userProperty);
        });
        int buttonsSize = 130;
        addUserButton.setPrefWidth(buttonsSize);
        changePasswdButton.setPrefWidth(buttonsSize);
        deleteUserButton.setPrefWidth(buttonsSize);






        VBox buttomsBox = new VBox(addUserButton, changePasswdButton, deleteUserButton);
        buttomsBox.setSpacing(SPACING);
        buttomsBox.setPadding(new Insets(10, 10, 10, 10));
        root.setRight(buttomsBox);
    }

    private void onAddUserButton(){
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

    private void onChangePasswdButton(UserProperty userProperty){
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

        TableView<TreatmentProperty> priceListTable = new TableView<>();
        ObservableList<TreatmentProperty> priceListData = FXCollections.observableArrayList();
        ArrayList<TreatmentView> priceList = manager.getTreatments();
        for(TreatmentView view: priceList)
            priceListData.add(new TreatmentProperty(view));

        TableColumn typeOfTreatmentColumn = new TableColumn("Typ badania");
        typeOfTreatmentColumn.setCellValueFactory(new PropertyValueFactory<TreatmentProperty, String>("typeOfTreatment"));
        TableColumn priceColumn = new TableColumn("Cena");
        priceColumn.setCellValueFactory(new PropertyValueFactory<TreatmentProperty, String>("price"));
        TableColumn refundableColumn = new TableColumn("Refundowane");
        refundableColumn.setCellValueFactory(new PropertyValueFactory<TreatmentProperty, String>("refundable"));

        priceListTable.setItems(priceListData);
        priceListTable.getColumns().addAll(typeOfTreatmentColumn, priceColumn, refundableColumn);

        table.setPrefWidth(root.getWidth()/2);
        priceListTable.setPrefWidth(root.getWidth()/2);
        HBox tables = new HBox(table, priceListTable);
        tables.setSpacing(SPACING);
        root.setCenter(tables);

        Button addTreatmentButton = new Button("Dodaj badanie");
        addTreatmentButton.setOnAction(e -> onAddTreatmentButton());
        Button changeTreatmentButton = new Button("Zmień");
        changeTreatmentButton.setOnAction(e -> {
            TreatmentProperty treatmentProperty = priceListTable.getSelectionModel().getSelectedItem();
            onChangeTreatmentButton(treatmentProperty);
        });
        Button deleteTreatmentButton = new Button("Usuń badanie");
        deleteTreatmentButton.setOnAction(e -> {
            TreatmentProperty treatmentProperty = priceListTable.getSelectionModel().getSelectedItem();
            onDeleteTreatmentButton(treatmentProperty);
        });
        int buttonsSize = 130;
        addTreatmentButton.setPrefWidth(buttonsSize);
        changeTreatmentButton.setPrefWidth(buttonsSize);
        deleteTreatmentButton.setPrefWidth(buttonsSize);
        VBox vBox = new VBox(addTreatmentButton, changeTreatmentButton, deleteTreatmentButton);
        vBox.setSpacing(SPACING);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        root.setRight(vBox);
     }

    private void onAddTreatmentButton(){
        Stage addTreatmentStage = new Stage();
        addTreatmentStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Wprowadz dane nowego badania");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(SPACING);
        gridPane.setHgap(SPACING);

        int i = 1;
        Label typeOfTreatmentLabel = new Label("Typ Badania");
        TextField typeOfTreatmentTextField = new TextField();
        gridPane.addRow(i++, typeOfTreatmentLabel,typeOfTreatmentTextField);

        Label priceLabel = new Label("Cena");
        TextField priceTextField = new TextField();
        gridPane.addRow(i++, priceLabel, priceTextField);

        Label refundableLabel = new Label("Refundowany");
        CheckBox refundableCheckBox = new CheckBox();
        gridPane.addRow(i++, refundableLabel, refundableCheckBox);


        Button addButton = new Button("Dodaj");
        addButton.setOnAction(e -> {
            try{
                TreatmentView treatmentView = new TreatmentView(typeOfTreatmentTextField.getText(),
                        Integer.parseInt(priceTextField.getText()), refundableCheckBox.isSelected());
                boolean state = false;
                state = manager.addTreatment(treatmentView);
                if(state){
                    financesMode();
                    addTreatmentStage.close();
                }else
                    infoLabel.setText("nieprawidlowe dane");


            }catch (NumberFormatException exception){
                infoLabel.setText("nieprawidłowe dane");
            }

        });

        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> {
            userMode();
            addTreatmentStage.close();
        });
        gridPane.addRow(i++, addButton, cancelButton);


        VBox AllVBox = new VBox(infoLabel, gridPane);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);

        Scene scene = new Scene(AllVBox, 300, 400);
        addTreatmentStage.setTitle("Nowe badanie");
        addTreatmentStage.setScene(scene);
        addTreatmentStage.show();
    }
    private void onChangeTreatmentButton(TreatmentProperty treatmentProperty){
        Stage addTreatmentStage = new Stage();
        addTreatmentStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Zmień odpowiednie pola");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(SPACING);
        gridPane.setHgap(SPACING);

        int i = 1;
        Label typeOfTreatmentLabel = new Label("Typ Badania");
        Label typeOfTreatmentCompleted = new Label(treatmentProperty.getTypeOfTreatment());
        gridPane.addRow(i++, typeOfTreatmentLabel,typeOfTreatmentCompleted);

        Label priceLabel = new Label("Cena");
        TextField priceTextField = new TextField(treatmentProperty.getPrice());
        gridPane.addRow(i++, priceLabel, priceTextField);

        Label refundableLabel = new Label("Refundowany");
        CheckBox refundableCheckBox = new CheckBox();
        if(treatmentProperty.getRefundable().equals("TAK"))
            refundableCheckBox.setSelected(true);
        gridPane.addRow(i++, refundableLabel, refundableCheckBox);


        Button addButton = new Button("Zmień");
        addButton.setOnAction(e -> {
            try{
                TreatmentView treatmentView = new TreatmentView(treatmentProperty.getTypeOfTreatment(),
                        Integer.parseInt(priceTextField.getText()), refundableCheckBox.isSelected());
                boolean state = false;
                state = manager.changeTreatment(treatmentView);
                if(state){
                    financesMode();
                    addTreatmentStage.close();
                }else
                    infoLabel.setText("nieprawidlowe dane");


            }catch (NumberFormatException exception){
                infoLabel.setText("nieprawidłowe dane");
            }

        });

        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> {
            financesMode();
            addTreatmentStage.close();
        });
        gridPane.addRow(i++, addButton, cancelButton);


        VBox AllVBox = new VBox(infoLabel, gridPane);
        AllVBox.setAlignment(Pos.CENTER);
        AllVBox.setSpacing(SPACING);

        Scene scene = new Scene(AllVBox, 300, 400);
        addTreatmentStage.setTitle("Zmiana badań");
        addTreatmentStage.setScene(scene);
        addTreatmentStage.show();
    }
    private void onDeleteTreatmentButton(TreatmentProperty treatmentProperty){
        manager.deleteTreatment(new TreatmentView(treatmentProperty));
        financesMode();
    }

    private void employeeMode(){
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

         Button addEmployeeButton = new Button("Dodaj pracownika");
         addEmployeeButton.setOnAction(e -> {
             onAddEmployeeButton();
         });

         Button changeEmployeeButton = new Button("Zmień dane");
         changeEmployeeButton.setOnAction(e -> {
             EmployeeProperty employeeProperty = table.getSelectionModel().getSelectedItem();
             if(employeeProperty != null)
                 onChangeEmployeeButton(employeeProperty);
         });

         Button deleteEmployeeButton = new Button("Usun uzytkownika");
         deleteEmployeeButton.setOnAction(e -> {
             EmployeeProperty employeeProperty = table.getSelectionModel().getSelectedItem();
             if(employeeProperty != null)
                onDeleteEmployeeButton(employeeProperty);
         });
        int buttonsSize = 130;
        addEmployeeButton.setPrefWidth(buttonsSize);
        changeEmployeeButton.setPrefWidth(buttonsSize);
        deleteEmployeeButton.setPrefWidth(buttonsSize);
         VBox vBox = new VBox(addEmployeeButton, changeEmployeeButton, deleteEmployeeButton);
         vBox.setSpacing(SPACING);
         vBox.setPadding(new Insets(10, 10, 10, 10));
         root.setRight(vBox);


     }

    private void onAddEmployeeButton(){
        int employeeWidth = 400;
        int employeeHeight = 600;
       Stage addEmployeeStage = new Stage();
       addEmployeeStage.setAlwaysOnTop(true);
       Label infoLabel = new Label("Wprowadz dane nowego pracownika");

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

       Label positionLabel = new Label("Stanowisko");
       TextField positionTextField = new TextField();
       gridPane.addRow(i++, positionLabel, positionTextField);

       Label specializationLabel = new Label("Specjalizacja");
       TextField specializationTextField = new TextField();
       gridPane.addRow(i++, specializationLabel, specializationTextField);

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

       Label wageLabel = new Label("Zarobki");
       TextField wageTextField = new TextField();
       gridPane.addRow(i++, wageLabel, wageTextField);

       Label dateOfEmployeeLabel = new Label("Data zatrudnienia");
       TextField dateOfEmployeeTextField = new TextField();
       dateOfEmployeeTextField.setPromptText("dd-MM-yyyy");
       gridPane.addRow(i++, dateOfEmployeeLabel, dateOfEmployeeTextField);

       Button addButton = new Button("Dodaj");
       addButton.setOnAction(e -> {
           try{
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
               Date date = dateFormat.parse(dateOfEmployeeTextField.getText());
               EmployeeView employeeView = new EmployeeView(peselTextField.getText(), nameTextField.getText(),
                       secondNameTextField.getText(), surnameTextField.getText(), positionTextField.getText(),
                       specializationTextField.getText(), streetTextField.getText(), cityTextField.getText(),
                       postcodeTextField.getText(), telephoneTextField.getText(), Integer.parseInt(wageTextField.getText()), date);
               if(manager.addEmployee(employeeView)){
                   addEmployeeStage.close();
                   employeeMode();
               }else {
                   infoLabel.setText("Nieprawidlowe dane");
               }

           }catch (ParseException parseExeption){
               infoLabel.setText("Zly format daty");
           }
       });

       Button cancelButton = new Button("Anuluj");
       cancelButton.setOnAction(e -> addEmployeeStage.close());
       gridPane.addRow(i++, addButton, cancelButton);

       VBox vBox = new VBox(infoLabel, gridPane);
       vBox.setAlignment(Pos.CENTER);
       vBox.setSpacing(SPACING);

       Scene scene = new Scene(vBox, employeeWidth, employeeHeight);
       addEmployeeStage.setTitle("Dodaj pracownika");
       addEmployeeStage.setScene(scene);
       addEmployeeStage.show();
    }

    private void onChangeEmployeeButton(EmployeeProperty employeeProperty){
        int employeeWidth = 400;
        int employeeHeight = 600;
        Stage addEmployeeStage = new Stage();
        addEmployeeStage.setAlwaysOnTop(true);
        Label infoLabel = new Label("Zmień odpowiednie dane pracownika");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(SPACING);
        gridPane.setVgap(SPACING);

        int i = 1;
        Label peselLabel = new Label("PESEL");
        Label peselCompletedLabel = new Label(employeeProperty.getPESEL());
        gridPane.addRow(i++, peselLabel, peselCompletedLabel);

        Label nameLabel = new Label("Imie");
        TextField nameTextField = new TextField(employeeProperty.getName());
        gridPane.addRow(i++, nameLabel, nameTextField);

        Label secondNameLabel = new Label("Drugie imie");
        TextField secondNameTextField = new TextField(employeeProperty.getSecondName());
        gridPane.addRow(i++, secondNameLabel, secondNameTextField);

        Label surnameLabel = new Label("Nazwisko");
        TextField surnameTextField = new TextField(employeeProperty.getSurname());
        gridPane.addRow(i++, surnameLabel, surnameTextField);

        Label positionLabel = new Label("Stanowisko");
        TextField positionTextField = new TextField(employeeProperty.getPosition());
        gridPane.addRow(i++, positionLabel, positionTextField);

        Label specializationLabel = new Label("Specjalizacja");
        TextField specializationTextField = new TextField(employeeProperty.getSpecialization());
        gridPane.addRow(i++, specializationLabel, specializationTextField);

        Label streetLabel = new Label("Ulica");
        TextField streetTextField = new TextField(employeeProperty.getStreet());
        gridPane.addRow(i++, streetLabel, streetTextField);

        Label cityLabel = new Label("Miasto");
        TextField cityTextField = new TextField(employeeProperty.getCity());
        gridPane.addRow(i++, cityLabel, cityTextField);

        Label postcodeLabel = new Label("Kod pocztowy");
        TextField postcodeTextField = new TextField(employeeProperty.getPostcode());
        gridPane.addRow(i++, postcodeLabel, postcodeTextField);

        Label telephoneLabel = new Label("Telefon");
        TextField telephoneTextField = new TextField(employeeProperty.getTelephone());
        gridPane.addRow(i++, telephoneLabel, telephoneTextField);

        Label wageLabel = new Label("Zarobki");
        TextField wageTextField = new TextField(employeeProperty.getWage());
        gridPane.addRow(i++, wageLabel, wageTextField);

        Label dateOfEmployeeLabel = new Label("Data zatrudnienia");
        TextField dateOfEmployeeTextField = new TextField(employeeProperty.getDateOfEmployee());
        gridPane.addRow(i++, dateOfEmployeeLabel, dateOfEmployeeTextField);

        Button addButton = new Button("Zmień");
        addButton.setOnAction(e -> {
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(dateOfEmployeeTextField.getText());
                EmployeeView employeeView = new EmployeeView(employeeProperty.getPESEL(), nameTextField.getText(),
                        secondNameTextField.getText(), surnameTextField.getText(), positionTextField.getText(),
                        specializationTextField.getText(), streetTextField.getText(), cityTextField.getText(),
                        postcodeTextField.getText(), telephoneTextField.getText(), Integer.parseInt(wageTextField.getText()), date);
                if(manager.changeEmployee(employeeView)){
                    addEmployeeStage.close();
                    employeeMode();
                }else {
                    infoLabel.setText("Nieprawidlowe dane");
                }

            }catch (ParseException parseExeption){
                infoLabel.setText("Zly format daty");
            }
        });

        Button cancelButton = new Button("Anuluj");
        cancelButton.setOnAction(e -> addEmployeeStage.close());
        gridPane.addRow(i++, addButton, cancelButton);

        VBox vBox = new VBox(infoLabel, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(SPACING);

        Scene scene = new Scene(vBox, employeeWidth, employeeHeight);
        addEmployeeStage.setTitle("Dodaj pracownika");
        addEmployeeStage.setScene(scene);
        addEmployeeStage.show();

    }

    private void onDeleteEmployeeButton(EmployeeProperty employeeProperty){
        manager.deleteEmployee(new EmployeeView(employeeProperty));
        employeeMode();
    }



}
