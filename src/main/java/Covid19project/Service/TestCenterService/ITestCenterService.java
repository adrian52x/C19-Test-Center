package Covid19project.Service.TestCenterService;

import Covid19project.Model.Data.TestCenter;
import Covid19project.Model.Data.User;

import java.util.List;

public interface ITestCenterService {
    //Create
    TestCenter addTestCenter(TestCenter testCenter);

    //READ
    List<TestCenter> fetchAllCenters();
    TestCenter findTestCenterById(int testCenterId);
    TestCenter findTestCenterByName(String name);


    //Update
    User updateTestCenter(int testCenterId, TestCenter testCenter);

    //Delete
    Boolean deleteTestCenter(int testCenterId);
}
