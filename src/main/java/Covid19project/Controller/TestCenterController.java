package Covid19project.Controller;

import Covid19project.Model.Data.TestCenter;
import Covid19project.Service.AddressService.IAddressService;
import Covid19project.Service.AppointmentService.IAppointmentService;
import Covid19project.Service.TestCenterService.ITestCenterService;
import Covid19project.Service.UserService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class TestCenterController {

    @Autowired
    ITestCenterService iTestCenterService;
    @Autowired
    IAppointmentService iAppointmentService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IAddressService iAddressService;

    // DIPSLAY TEST CENTERS
    @GetMapping("/admin/manageTestCenter")
    public String getManageTestCenter(Model model){
        List<TestCenter> testCentersList = iTestCenterService.fetchAllCenters();
        model.addAttribute("iAddressService", iAddressService);
        model.addAttribute("myTestCenters", testCentersList);

        return "admin/TestCenter/manageTestCenter";
    }
    // DELETE TEST CENTER
    @GetMapping("/deleteTestCenter/{testcenterId}")
    public String deleteTestCenter(@PathVariable int testcenterId) {

            try {
                iTestCenterService.deleteTestCenter(testcenterId);
                return "redirect:/admin/manageTestCenter";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
            }

        return "errors/cannot_delete_testCenter";
    }
}
