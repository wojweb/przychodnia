package sample.admin;

import sample.TypUzytkownika;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FakeAdminConnection implements AdminConnectionInterface {
    ArrayList<UserView> users;
    ArrayList<FinanceView> finances;
    ArrayList<EmployeeView> employees;
    ArrayList<TreatmentView> treatments;

    public FakeAdminConnection() {
        users = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            users.add(new UserView("PESEL: " + i, TypUzytkownika.Lekarz));
        }
        finances = new ArrayList<>();
        for(int i = 0; i < 10; i ++)
            finances.add(new FinanceView(2007 + i, 135234 + (432*i), 32123 + 34 * i - 5 * i ^2));
        employees = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try{
            date = dateFormat.parse("16-01-1997");
        }catch (Exception e){
            System.out.println("Nie udalo mi się :(");
            System.out.println(e.getMessage());
        }

        employees.add(new EmployeeView("SUPERPESEL", "Szymon", null, "Wojtaszek",
                "Programista", null, "Piaskowa 12", "Czerwieńsk", "66-016",
                "604998662", 43, date));


        treatments = new ArrayList<>();
        treatments.add(new TreatmentView("Badanie wzroku", 400, false));
        treatments.add(new TreatmentView("zmartwychwstanie", 1000000000, false));
        treatments.add(new TreatmentView("pranie mózgu", 5, true));
    }

    @Override
    public ArrayList<UserView> getUsers() {
        return users;
    }

    @Override
    public boolean addUser(UserView user) {
        System.out.println("Dodaje: " + user.getPESEL());
        users.add(user);

        return true;
    }

    @Override
    public boolean changePasswd(UserView userWithNewPasswd) {
        System.out.println("Zmieniam haslo uzytkownika: " + userWithNewPasswd.getPESEL() + " na " + userWithNewPasswd.getPasswd());
        if(users.contains(userWithNewPasswd)) {
            users.get(users.indexOf(userWithNewPasswd)).setPasswd(userWithNewPasswd.getPasswd());
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteUser(UserView user) {
        System.out.println("Usuwam: " + user.getPESEL());
        users.remove(user);
        return true;
    }

    @Override
    public ArrayList<EmployeeView> getEmployees() {
        return employees;
    }

    @Override
    public boolean addEmployee(EmployeeView employee) {
        return employees.add(employee);
    }

    @Override
    public boolean changeEmployee(EmployeeView employee) {
        int index = employees.indexOf(employee);
        if(index < 0)
            return false;
        else {
            employees.set(index, employee);
            return true;
        }
    }

    @Override
    public boolean deleteEmployee(EmployeeView employee) {
        return employees.remove(employee);
    }

    @Override
    public ArrayList<FinanceView> getFinances() {
        return finances;
    }

    @Override
    public ArrayList<TreatmentView> getTreatments() {
        return treatments;
    }

    @Override
    public boolean addTreatment(TreatmentView treatment) {
        return treatments.add(treatment);
    }

    @Override
    public boolean deleteTreatment(TreatmentView treatment) {
        return treatments.remove(treatment);
    }

    @Override
    public boolean changeTreatment(TreatmentView newTreatment) {
        if(treatments.contains(newTreatment)){
            int n = treatments.indexOf(newTreatment);
            treatments.set(n, newTreatment);
            return true;
        }else
            return false;
    }
}
