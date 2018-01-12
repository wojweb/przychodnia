package sample.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeView {
    private String PESEL;
    private String name;
    private String secondName;
    private String surname;
    private String position;
    private String specialization;
    private String street;
    private String city;
    private String postcode;
    private String telephone;
    private int wage;
    private Date dateOfEmployee;

    @Override
    public boolean equals(Object obj) {
        return PESEL.equals(((EmployeeView) obj).getPESEL());
    }

    public EmployeeView(String PESEL, String name, String secondName, String surname, String position, String specialization,
                        String street, String city, String postcode, String telephone, int wage, Date dateOfEmployee) {
        this.PESEL = PESEL;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.position = position;
        this.specialization = specialization;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.telephone = telephone;
        this.wage = wage;
        this.dateOfEmployee = dateOfEmployee;
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

    public String getPosition() {
        return position;
    }

    public String getSpecialization() {
        return specialization;
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

    public int getWage() {
        return wage;
    }

    public Date getDateOfEmployee() {
        return dateOfEmployee;
    }
}
