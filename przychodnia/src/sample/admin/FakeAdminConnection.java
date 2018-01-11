package sample.admin;

import sample.TypUzytkownika;

import java.util.ArrayList;

public class FakeAdminConnection implements AdminConnectionInterface {
    ArrayList<UserView> users;

    public FakeAdminConnection() {
        users = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            users.add(new UserView("PESEL: " + i, TypUzytkownika.Lekarz));
        }

    }

    @Override
    public UserView[] getUsers() {
        return users.toArray(new UserView[users.size()]);
    }

    @Override
    public boolean addUser(UserView user) {
        users.add(user);

        return false;
    }

    @Override
    public boolean changePasswd(UserView userWithNewPasswd) {
        return false;
    }

    @Override
    public boolean deleteUser(UserView user) {
        return users.remove(user);

    }

    @Override
    public EmployeeView[] getEmployees() {
        return new EmployeeView[0];
    }

    @Override
    public boolean addEmployee(EmployeeView Employee) {
        return false;
    }

    @Override
    public boolean changeEmployeeName(EmployeeView Employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(EmployeeView employee) {
        return false;
    }

    @Override
    public FinanceView getFinances() {
        return null;
    }
}
