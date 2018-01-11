package sample.admin;

public interface AdminConnectionInterface {

    UserView[] getUsers();
    boolean addUser();
    boolean deleteUse();
}
