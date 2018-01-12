package sample.admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeProperty {
    private SimpleStringProperty PESEL;
    private SimpleStringProperty name;
    private SimpleStringProperty secondName;
    private SimpleStringProperty surname;
    private SimpleStringProperty position;
    private SimpleStringProperty specialization;
    private SimpleStringProperty street;
    private SimpleStringProperty city;
    private SimpleStringProperty postcode;
    private SimpleStringProperty telephone;
    private SimpleStringProperty wage;
    private SimpleStringProperty dateOfEmployee;

    public EmployeeProperty(EmployeeView view) {
        PESEL = new SimpleStringProperty(view.getPESEL());
        name = new SimpleStringProperty(view.getName());
        if(view.getSecondName() == null)
            secondName  = new SimpleStringProperty("-");
        else
            secondName = new SimpleStringProperty(view.getSecondName());
        surname = new SimpleStringProperty(view.getSurname());
        position = new SimpleStringProperty(view.getPosition());
        if(view.getSpecialization() == null)
            specialization = new SimpleStringProperty("-");
        else
            specialization = new SimpleStringProperty(view.getSpecialization());
        street = new SimpleStringProperty(view.getStreet());
        city = new SimpleStringProperty(view.getCity());
        postcode = new SimpleStringProperty(view.getPostcode());
        telephone = new SimpleStringProperty(view.getTelephone());
        wage = new SimpleStringProperty(Integer.toString(view.getWage()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateOfEmployee = new SimpleStringProperty(dateFormat.format(view.getDateOfEmployee()));   
    }

    public String getPESEL() {
        return PESEL.get();
    }

    public SimpleStringProperty PESELProperty() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL.set(PESEL);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public SimpleStringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public String getSpecialization() {
        return specialization.get();
    }

    public SimpleStringProperty specializationProperty() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization.set(specialization);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostcode() {
        return postcode.get();
    }

    public SimpleStringProperty postcodeProperty() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode.set(postcode);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getWage() {
        return wage.get();
    }

    public SimpleStringProperty wageProperty() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage.set(wage);
    }

    public String getDateOfEmployee() {
        return dateOfEmployee.get();
    }

    public SimpleStringProperty dateOfEmployeeProperty() {
        return dateOfEmployee;
    }

    public void setDateOfEmployee(String dateOfEmployee) {
        this.dateOfEmployee.set(dateOfEmployee);
    }
}
