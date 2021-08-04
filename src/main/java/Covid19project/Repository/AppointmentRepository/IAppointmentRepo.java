package Covid19project.Repository.AppointmentRepository;

import Covid19project.Model.Data.Appointment;
import org.springframework.security.core.annotation.CurrentSecurityContext;

import java.util.List;

public interface IAppointmentRepo {

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
