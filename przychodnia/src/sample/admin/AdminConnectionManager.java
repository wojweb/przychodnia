package sample.admin;

import sample.TypUzytkownika;

public class AdminConnectionManager {

    UserView[] getUsers(){
        UserView[] users = new UserView[5];
        for(int i = 0; i < 5; i++){
            users[i] = new UserView(Integer.toString(i), TypUzytkownika.Lekarz);
        }
        return users;

    }



    boolean addUser(String pesel, String passwd){
        System.out.println("nowy uzytkownik " + pesel + " haslo " + passwd);

        return true;
    }

    boolean deteleUser(String pesel){
        System.out.println("kasuje:  " + pesel);
        return true;
    }

    boolean changePasswd(String pesel, String newPasswd){
        System.out.println("Zmnieniam haslo dla " + pesel);
        return true;
    }
}
