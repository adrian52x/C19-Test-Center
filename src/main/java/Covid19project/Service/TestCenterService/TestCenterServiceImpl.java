package Covid19project.Service.TestCenterService;

import Covid19project.Repository.TestCenterRepositiry.ITestCenterRepo;
import Covid19project.Model.Data.TestCenter;
import Covid19project.Model.Data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCenterServiceImpl implements ITestCenterService{
    @Autowired
    ITestCenterRepo iTestCenterRepo;

    //CREATE
    @Override
    public TestCenter addTestCenter(TestCenter testCenter) {
        return null;
    }

    //READ
    @Override
    public List<TestCenter> fetchAllCenters() {
        return iTestCenterRepo.fetchAllCenters();
    }

    @Override
    public TestCenter findTestCenterById(int testCenterId) {
        return iTestCenterRepo.findTestCenterById(testCenterId);
    }

    @Override
    public TestCenter findTestCenterByName(String name) {
        return iTestCenterRepo.findTestCenterByName(name);
    }

    //UPDATE
    @Override
    public User updateTestCenter(int testCenterId, TestCenter testCenter) {
        return iTestCenterRepo.updateTestCenter(testCenterId,testCenter);
    }

    //DELETE
    @Override
    public Boolean deleteTestCenter(int testCenterId) {
        return iTestCenterRepo.deleteTestCenter(testCenterId);
    }
}
