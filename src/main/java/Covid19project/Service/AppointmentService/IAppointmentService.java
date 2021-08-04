package Covid19project.Service.AppointmentService;

import Covid19project.Model.Data.Appointment;

import java.util.List;

public interface IAppointmentService {
    //Create
    Appointment addAppointment(Appointment appointment);

    //READ
    List<Appointment> fetchAllAppts();
    Appointment findAppointmentById(int apptID);

    Appointment findAppointmentByCpr(Integer cpr);

    //Update
    Appointment updateAppointment(int apptID, Appointment appointment);

    //Delete
    Boolean deleteAppointment(int apptID);
}
