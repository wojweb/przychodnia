package sample.recepcjonista;

import sample.admin.EmployeeProperty;
import sample.admin.EmployeeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientView {
    private String PESEL;
    private String name;
    private String secondName;
    private String surname;
    private boolean insured;
    private String street;
    private String city;
    private String postcode;
    private String telephone;

    @Override
    public boolean equals(Object obj) {
        return PESEL.equals(((PatientView) obj).getPESEL());
    }

    PatientView(PatientProperty patientProperty){
        PESEL = patientProperty.getPESEL();
        name = patientProperty.getName();
        if(patientProperty.getSecondName().equals("-"))
            secondName = null;
        else
            secondName = patientProperty.getSecondName();
        surname = patientProperty.getSurname();
        if(patientProperty.getInsured().equals("TAK"))
            insured = true;
        else
            insured = false;

        street = patientProperty.getStreet();
        city = patientProperty.getCity();
        postcode = patientProperty.getPostcode();
        telephone = patientProperty.getTelephone();
    }

    public PatientView(String PESEL, String name, String secondName, String surname,
                       boolean insured, String street, String city, String postcode, String telephone) {
        this.PESEL = PESEL;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.insured = insured;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.telephone = telephone;
    }

    public PatientView(String PESEL, String name, String surname,
                       boolean insured, String street, String city, String postcode, String telephone) {
        this.PESEL = PESEL;
        this.name = name;
        this.surname = surname;
        this.insured = insured;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.telephone = telephone;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isInsured() {
        return insured;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getTelephone() {
        return telephone;
    }
}
