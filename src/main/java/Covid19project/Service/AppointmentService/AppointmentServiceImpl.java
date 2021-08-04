package Covid19project.Service.AppointmentService;

import Covid19project.Model.Data.Appointment;
import Covid19project.Repository.AppointmentRepository.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
    @Autowired
    IAppointmentRepo iAppointmentRepo;

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return iAppointmentRepo.addAppointment(appointment);
    }

    @Override
    public List<Appointment> fetchAllAppts() {
       return iAppointmentRepo.fetchAllAppts();
    }

    @Override
    public Appointment findAppointmentById(int apptID) {
        return iAppointmentRepo.findAppointmentById(apptID);
    }

    @Override
    public Appointment findAppointmentByCpr(Integer cpr) {
        return iAppointmentRepo.findAppointmentByCpr(cpr);
    }

    @Override
    public Appointment updateAppointment(int apptID, Appointment appointment) {
      return iAppointmentRepo.updateAppointment(apptID, appointment);
    }

    @Override
    public Boolean deleteAppointment(int apptID) {
       return iAppointmentRepo.deleteAppointment(apptID);
    }
}
