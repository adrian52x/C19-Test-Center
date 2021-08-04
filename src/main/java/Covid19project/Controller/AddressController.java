package Covid19project.Controller;

import Covid19project.Model.Data.Address;
import Covid19project.Service.AddressService.IAddressService;
import Covid19project.Service.AppointmentService.IAppointmentService;
import Covid19project.Service.TestCenterService.ITestCenterService;
import Covid19project.Service.UserService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IAddressService iAddressService;
    @Autowired
    ITestCenterService iTestCenterService;
    @Autowired
    IAppointmentService iAppointmentService;


    //DIPSLAY ALL ADDRESS
    @GetMapping("/admin/manageAddress")
    public String getManageAddress(Model model){
        List<Address> addressList = iAddressService.fetchAllAddresses();
        model.addAttribute("myAddresses", addressList);
        return "admin/Address/manageAddress";
    }

    // DELETE ADDRESS
    @GetMapping("/deleteAddress/{addressid}")
    public String deleteAddress(@PathVariable int addressid) {
        iAddressService.deleteAddress(addressid);
        return "redirect:/admin/manageAddress";
    }

}
