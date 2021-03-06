package sample.admin;

import java.util.ArrayList;

public interface AdminConnectionInterface {

    //obsluga uzytkownikow
    ArrayList<UserView> getUsers();

    //Wez dane z usera i wrzuc je do tabeli, pamietaj, aby zahashowac haslo, algorytm MD5
    //https://www.mkyong.com/java/java-md5-hashing-example/

    boolean addUser(UserView user);
    boolean changePasswd(UserView userWithNewPasswd);
    boolean deleteUser(UserView user);


    //obsluga pracownikow
    //dawaj mi wszystkich
    ArrayList<EmployeeView> getEmployees();


    //dodaj do bazy danych
    boolean addEmployee(EmployeeView Employee);

    //sprawdz po peselu i zamień wszystkie pola
    boolean changeEmployee(EmployeeView Employee);

    //wez pesel i skasuj odpowiedni rekord
    boolean deleteEmployee(EmployeeView employee);


    //informacje o finansach na rozne lata
    ArrayList<FinanceView> getFinances();

    ArrayList<TreatmentView> getTreatments();
    boolean addTreatment(TreatmentView treatment);
    boolean deleteTreatment(TreatmentView treatment);
    boolean changeTreatment(TreatmentView newTreatment);



}
