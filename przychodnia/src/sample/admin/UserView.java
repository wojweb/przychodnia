package sample.admin;

import sample.TypUzytkownika;

public class UserView {
    private String PESEL;
    private String passwd;
    private TypUzytkownika typ;

    @Override
    public boolean equals(Object obj) {
        return PESEL.equals(((UserView) obj).getPESEL());
    }

    public UserView(String PESEL, TypUzytkownika typ) {
        this.PESEL = PESEL;
        this.typ = typ;
    }


    public UserView(String PESEL, String passwd, TypUzytkownika typ) {
        this.PESEL = PESEL;
        this.passwd = passwd;
        this.typ = typ;
    }

    public UserView(UserProperty userProperty){
        this.PESEL = userProperty.getPesel();
        this.typ = TypUzytkownika.valueOf(userProperty.getTyp());
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
