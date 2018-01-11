package sample.admin;

import javafx.beans.property.SimpleStringProperty;

public class UserProperty {
    private SimpleStringProperty pesel;
    private SimpleStringProperty typ;

    UserProperty(UserView user){
        pesel = new SimpleStringProperty(user.getPESEL());
        typ = new SimpleStringProperty(user.getTyp().toString());
    }

    public UserProperty(SimpleStringProperty pesel, SimpleStringProperty typ) {
        this.pesel = pesel;
        this.typ = typ;
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
}
