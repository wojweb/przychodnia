package sample.recepcjonista;

import javafx.beans.property.SimpleStringProperty;


public class PatientProperty {
    private SimpleStringProperty PESEL;
    private SimpleStringProperty name;
    private SimpleStringProperty secondName;
    private SimpleStringProperty surname;
    private SimpleStringProperty insured;
    private SimpleStringProperty street;
    private SimpleStringProperty city;
    private SimpleStringProperty postcode;
    private SimpleStringProperty telephone;

    PatientProperty(PatientView view) {
        PESEL = new SimpleStringProperty(view.getPESEL());
        name = new SimpleStringProperty(view.getName());

        if(view.getSecondName() == null)
            secondName  = new SimpleStringProperty("-");
        else
            secondName = new SimpleStringProperty(view.getSecondName());
        surname = new SimpleStringProperty(view.getSurname());

        if(view.isInsured())
            insured = new SimpleStringProperty("TAK");
        else
            insured = new SimpleStringProperty("NIE");

        street = new SimpleStringProperty(view.getStreet());
        city = new SimpleStringProperty(view.getCity());
        postcode = new SimpleStringProperty(view.getPostcode());
        telephone = new SimpleStringProperty(view.getTelephone());
    }

    public String getPESEL() {
        return PESEL.get();
    }

    public SimpleStringProperty PESELProperty() {
        return PESEL;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public String getInsured() {
        return insured.get();
    }

    public SimpleStringProperty insuredProperty() {
        return insured;
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public String getPostcode() {
        return postcode.get();
    }

    public SimpleStringProperty postcodeProperty() {
        return postcode;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }
}
