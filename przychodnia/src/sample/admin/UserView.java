package sample.admin;

import sample.TypUzytkownika;

public class UserView {
    private String PESEL;
    private TypUzytkownika typ;

    public UserView(String PESEL, TypUzytkownika typ) {
        this.PESEL = PESEL;
        this.typ = typ;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public TypUzytkownika getTyp() {
        return typ;
    }

    public void setTyp(TypUzytkownika typ) {
        this.typ = typ;
    }
}
