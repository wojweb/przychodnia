package sample.admin;

import javafx.beans.property.SimpleStringProperty;

public class UserProperty {
    private SimpleStringProperty pesel;
    private SimpleStringProperty typ;

    UserProperty(UserView user){
        pesel = new SimpleStringProperty(user.getPESEL());
        typ = new SimpleStringProperty(user.getTyp().toString());
    }

    public String getPesel() {
        return pesel.get();
    }

    public SimpleStringProperty peselProperty() {
        return pesel;
    }

    public String getTyp() {
        return typ.get();
    }

    public SimpleStringProperty typProperty() {
        return typ;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public void setTyp(String typ) {
        this.typ.set(typ);
    }
}
