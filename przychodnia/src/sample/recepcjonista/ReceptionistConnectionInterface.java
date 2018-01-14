package sample.recepcjonista;

import sample.admin.EmployeeView;

import java.util.ArrayList;

public interface ReceptionistConnectionInterface {

    ArrayList<PatientView> getPatients();
    boolean addPatient(PatientView patientView);
    boolean changePatient(PatientView newPatientView);
    boolean deletePatient(PatientView patientView);



//    ArrayList <AppointmentView> getAppointments(PatientView patientView);
//    ArrayList <AppointmentView> getAppointments(EmployeeView employeeView);
//    ArrayList <EmployeeView> getDoctors();
//    boolean addAppointment(AppointmentView appointmentView);
//    boolean deleteAppointment(AppointmentView appointmentView);


}
