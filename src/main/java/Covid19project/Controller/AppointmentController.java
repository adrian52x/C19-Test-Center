package Covid19project.Controller;

import Covid19project.Files.ApptExcelExporter;
import Covid19project.Files.UserExcelExporter;
import Covid19project.Model.Data.Appointment;
import Covid19project.Model.Data.User;
import Covid19project.Service.AddressService.IAddressService;
import Covid19project.Service.AppointmentService.IAppointmentService;
import Covid19project.Service.TestCenterService.ITestCenterService;
import Covid19project.Service.UserService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    ITestCenterService iTestCenterService;
    @Autowired
    IAppointmentService iAppointmentService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IAddressService iAddressService;

    // SHOW BOOKING FORM
    @GetMapping("/booking")
    public String getBooking(Model model){
        model.addAttribute("iTestCenterService", iTestCenterService);
        model.addAttribute("iAppointmentService", iAppointmentService);
        model.addAttribute("iUserService", iUserService);
        //model.addAttribute("appointment", "booking");
        return "booking";
    }

    // DIPSLAY ALL APPOINTMENTS (ADMIN)
    @GetMapping("/admin/manageAppointment")
    public String getManageAppointment(Model model,Integer keyword){
        List<Appointment> appointmentList = iAppointmentService.fetchAllAppts();
        model.addAttribute("iUserService", iUserService);
        model.addAttribute("iTestCenterService", iTestCenterService);

        if(keyword != null){
            model.addAttribute("myAppointments", iAppointmentService.findAppointmentByCpr(keyword));
        }
        else {
            model.addAttribute("myAppointments", appointmentList);
        }

        return "admin/Appointment/manageAppointment";

    }

    // DELETE APPOINTMENT (ADMIN)
    @GetMapping("/deleteAppointment/{apptID}")
    public String deleteAppointment(@PathVariable int apptID) {
        iAppointmentService.deleteAppointment(apptID);
        return "redirect:/admin/manageAppointment";
    }

    // CREATE APPOINTMENT

    @PostMapping("/success")
    public String addNewAppointment(@ModelAttribute Appointment appointment, @CurrentSecurityContext(expression="authentication.name") String currentUser, Model model) {
        model.addAttribute("iTestCenterService", iTestCenterService);

        System.out.println("make an appointment for: "+ appointment.getCprOfUser());



        if (appointment.getCprOfUser() == Integer.parseInt(currentUser)) {
            try {
                iAppointmentService.findAppointmentByCpr(Integer.parseInt(currentUser));
            } catch (EmptyResultDataAccessException e) {

                e.printStackTrace();
                iAppointmentService.addAppointment(appointment);
                return "success";   // After appointment is made, user get redirected to Success page.
            }
        }
        else{
            return "errors/wrong_input_booking";
        }

        return "errors/already_have_appointment";
    }



    @GetMapping("/updateAppointment/{apptID}")
    public String updateAppointment(@PathVariable int apptID, Model model){
        model.addAttribute("iTestCenterService", iTestCenterService);
        //get appointment by id
        Appointment appointment = iAppointmentService.findAppointmentById(apptID);

        //set appointment as a mode attribute to pre populate the form
        model.addAttribute("updatedAppointment",appointment);

        return "admin/Appointment/updateAppointment";
    }


    @PostMapping("/saveAppointment/{apptID}")
    public String saveAppointment(@PathVariable int apptID, Appointment appointment) {
        iAppointmentService.updateAppointment(apptID, appointment);
        return "redirect:/profile";
    }


    // EXPORT USERS LIST

    @GetMapping("/admin/export/appointments")
    public void exportToExcelAppointments(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=[C19]appt_List_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Appointment> listAppointments = iAppointmentService.fetchAllAppts();

        ApptExcelExporter excelExporter = new ApptExcelExporter(listAppointments);

        excelExporter.export(response);
    }




}
