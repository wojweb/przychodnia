package sample.recepcjonista;

import java.util.ArrayList;

public class FakeReceptionistConnection implements ReceptionistConnectionInterface {
    ArrayList<PatientView> patients;


    FakeReceptionistConnection(){
        patients = new ArrayList<>();
        for(int i =1; i <= 5; i++)
            patients.add(new PatientView("PESEL " + i, "imie " + i, "nazwisko" + i,
                    true, "ulica " + i, "miasto " + i, "3223", "3231"+i));
    }

    @Override
    public ArrayList<PatientView> getPatients() {
        return patients;
    }

    @Override
    public boolean addPatient(PatientView patientView) {
        return patients.add(patientView);
    }

    @Override
    public boolean changePatient(PatientView newPatientView) {
        if(patients.contains(newPatientView)){
            int n = patients.indexOf(newPatientView);
            patients.set(n, newPatientView);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deletePatient(PatientView patientView) {
        return patients.remove(patientView);
    }
}
