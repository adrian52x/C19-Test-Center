package Covid19project.Repository.TestCenterRepositiry;

import Covid19project.Model.Data.TestCenter;
import Covid19project.Model.Data.User;

import java.util.List;

public interface ITestCenterRepo {

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
