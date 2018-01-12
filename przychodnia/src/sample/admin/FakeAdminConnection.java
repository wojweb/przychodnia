package sample.admin;

import sample.TypUzytkownika;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FakeAdminConnection implements AdminConnectionInterface {
    ArrayList<UserView> users;
    ArrayList<FinanceView> finances;
    ArrayList<EmployeeView> employees;

    public FakeAdminConnection() {
        users = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            users.add(new UserView("PESEL: " + i, TypUzytkownika.Lekarz));
        }
        finances = new ArrayList<>();
        for(int i = 0; i < 10; i ++)
            finances.add(new FinanceView(2007 + i, 135234 + (432*i), 32123 + 34 * i - 5 * i ^2));
        employees = new ArrayList<>();
        employees.add(new EmployeeView("SUPERPESEL", "Szymon", null, "Wojtaszek",
                "Programista", null, "Piaskowa 12", "Czerwieńsk", "66-016",
                "604998662", 43, "16.01.1997"));

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
    public boolean changeEmployeeName(EmployeeView employee) {
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
}
