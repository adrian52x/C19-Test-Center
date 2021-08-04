package Covid19project.Repository.TestCenterRepositiry;

import Covid19project.Model.Data.TestCenter;
import Covid19project.Model.Data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestCenterRepoImpl implements ITestCenterRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public TestCenter addTestCenter(TestCenter testCenter) {
        return null;
    }

    @Override
    public List<TestCenter> fetchAllCenters() {
        String sql = "SELECT * FROM test_center";
        RowMapper<TestCenter> rowMapper = new BeanPropertyRowMapper<>(TestCenter.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public TestCenter findTestCenterById(int testCenterId) {
        String sql = "SELECT * FROM test_center WHERE testCenterId=?";
        RowMapper<TestCenter> rowMapper = new BeanPropertyRowMapper<>(TestCenter.class);
        TestCenter myTestCenter = jdbcTemplate.queryForObject(sql, rowMapper, testCenterId);
        return myTestCenter;
    }

    @Override
    public TestCenter findTestCenterByName(String name) {
        String sql = "SELECT * FROM test_center WHERE name=?";
        RowMapper<TestCenter> rowMapper = new BeanPropertyRowMapper<>(TestCenter.class);
        TestCenter myTestCenter = jdbcTemplate.queryForObject(sql, rowMapper, name);
        return myTestCenter;
    }

    @Override
    public User updateTestCenter(int testCenterId, TestCenter testCenter) {
        String sql = "UPDATE test_center SET name=?, center_addressid=? WHERE testCenterId=?";
        jdbcTemplate.update(sql, testCenter.getName(), testCenter.getTestCenterId());

        return null;
    }


    @Override
    public Boolean deleteTestCenter(int testCenterId) {
        String sql = "DELETE FROM test_center WHERE testCenterId=?";
        return jdbcTemplate.update(sql, testCenterId) >= 0;
    }
}
