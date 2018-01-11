package sample.admin;

import sample.TypUzytkownika;

public class UserView {
    private String PESEL;
    private String passwd;
    private TypUzytkownika typ;


    public UserView(String PESEL, TypUzytkownika typ) {
        this.PESEL = PESEL;
        this.typ = typ;
    }

    public UserView(String PESEL, String passwd, TypUzytkownika typ) {
        this.PESEL = PESEL;
        this.passwd = passwd;
        this.typ = typ;
    }


    public String getPESEL() {
        return PESEL;
    }


    public TypUzytkownika getTyp() {
        return typ;
    }

    public String getPasswd() {
        return passwd;
    }
}
