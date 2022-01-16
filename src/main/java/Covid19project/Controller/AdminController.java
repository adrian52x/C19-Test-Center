package Covid19project.Controller;

import Covid19project.Model.Data.*;
import Covid19project.Service.AddressService.IAddressService;
import Covid19project.Service.AppointmentService.IAppointmentService;
import Covid19project.Service.TestCenterService.ITestCenterService;
import Covid19project.Service.UserService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IAddressService iAddressService;
    @Autowired
    ITestCenterService iTestCenterService;
    @Autowired
    IAppointmentService iAppointmentService;

    // DIPSLAY ADMIN MENU
    @GetMapping("/admin")
    public String getManagement(){
        return "admin/management";
    }

    // DISPLAY ALL USERS
    @GetMapping("/admin/manageUser")
    public String displayUsers(Model model, Integer keyword) {
        List<User> userList = iUserService.fetchAllUsers();
        model.addAttribute("iAddressService", iAddressService);

        if(keyword == null){
            model.addAttribute("myUsers", userList);
        }
        else {

            model.addAttribute("myUsers",iUserService.findUserByCpr(keyword));
        }
        return "admin/User/manageUser";
    }

    // NEW USER FORM
    @GetMapping("/showNewUserForm")
    public String newUserForm(Model model){
        model.addAttribute("iUserService", iUserService);
        model.addAttribute("newUser", new User());
        return "admin/User/newUser";
    }
    // CREATE USER
    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute User user) {
        iUserService.addNewUser(user);
        iUserService.addNewUserRole(user);
        return "redirect:/admin/manageUser";

    }

    // UPDATE USER
    @GetMapping("/updateUser/{cpr}")
    public String updateUser(@PathVariable int cpr, Model model){
        model.addAttribute("iAddressService", iAddressService);
        //get user by cpr
        User user = iUserService.findUserByCpr(cpr);

        //set user as a mode attribute to pre populate the form
        model.addAttribute("updatedUser",user);

        return "admin/User/updateUser";
    }

    //SAVE USER
    @PostMapping("/saveUser/{cpr}")
    public String saveUser(@PathVariable int cpr, User user) {
        iUserService.updateUser(cpr, user);
        return "redirect:/admin/manageUser";

    }

    // DELETE USER

   /* @GetMapping("/deleteUser/{cpr}")
    public String deleteUser(@PathVariable int cpr) {
        iUserService.deleteUser(cpr);
        return "redirect:/admin/manageUser";
    }*/








}
