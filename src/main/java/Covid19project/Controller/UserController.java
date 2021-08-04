package Covid19project.Controller;


import Covid19project.Files.UserExcelExporter;
import Covid19project.Model.Data.TestCenter;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    ITestCenterService iTestCenterService;
    @Autowired
    IAppointmentService iAppointmentService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IAddressService iAddressService;



    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/")
    public String hello(@CurrentSecurityContext(expression="authentication.name") String currentUser, Model model) {
        List<TestCenter> testCentersList = iTestCenterService.fetchAllCenters();
        model.addAttribute("iAddressService", iAddressService);
        model.addAttribute("myTestCenters", testCentersList);

        System.out.println("currently logged in : "+ currentUser);
        return "index";
    }


    @GetMapping("/profile")
    public String getProfile() {

        return "profile/profile";
    }


    @PostMapping("/myappointments")
    public String showUserAppointment(@RequestParam(value = "userCpr", required = false) Integer cpr, @CurrentSecurityContext(expression="authentication.name") String currentUser,   Model model)
    {
        model.addAttribute("iTestCenterService", iTestCenterService);

        System.out.println("[USER APPOINTMENT]: inserted cpr  : "+ cpr);


        if(cpr == Integer.parseInt(currentUser)) {
            try {
                model.addAttribute("userCpr", iAppointmentService.findAppointmentByCpr(cpr));
                return "profile/myappointments";
            } catch (EmptyResultDataAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("wrong input");
            return "errors/wrong_input_profile";
        }

        return "errors/no_appointments";
    }



    @PostMapping("/mydetails")
    public String showUserDetails(@RequestParam(value = "userCprDetails", required = false) Integer cpr, @CurrentSecurityContext(expression="authentication.name") String currentUser, Model model)
    {
        model.addAttribute("iAddressService", iAddressService);

        System.out.println("[USER DETAILS]: inserted cpr is : "+ cpr);

        if(cpr == Integer.parseInt(currentUser)) {
            model.addAttribute("userCprDetails", iUserService.findUserByCpr(cpr));
        }
        else {
            System.out.println("wrong input");
            return "errors/wrong_input_profile";
        }
        return "profile/mydetails";
    }

    @GetMapping("/deleteMyAppointment/{apptID}")
    public String deleteAppointment(@PathVariable int apptID) {
        iAppointmentService.deleteAppointment(apptID);
        return "redirect:/profile";
    }


    // EXPORT USERS LIST

    @GetMapping("/admin/export/users")
    public void exportToExcelUsers(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=[C19]users_List_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = iUserService.fetchAllUsers();

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }



}





