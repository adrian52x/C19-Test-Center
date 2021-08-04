package Covid19project.Repository.UserRepository;

import Covid19project.Model.Data.Address;
import Covid19project.Model.Data.Role;
import Covid19project.Model.Data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;


@Repository
public class UserRepoImpl implements IUserRepo{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User addNewUser(User user) {
        String sql = "INSERT INTO user(cpr, firstName, surname, addressid,email,password,dateOfBirth) VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getCPR(),user.getFirstName(),user.getSurname(),user.getAddressId(),user.getEmail(),user.getPassword(),user.getDateOfBirth());
        return null;
    }
    @Override
    public User addNewUserRole(User user){
        String sql = "INSERT into auth_user_role(cpr) values(?)";
        jdbcTemplate.update(sql, user.getCPR());
        return null;
    }

    @Override
    public List<User> fetchAllUsers() {
        String sql = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public User findUserByCpr(Integer cpr) {
        String sql = "SELECT * FROM user WHERE cpr=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User myUser = jdbcTemplate.queryForObject(sql, rowMapper, cpr);
        return myUser;
    }

    @Override
    public User findUserByRole(String roleName) {
        return null;
    }

    @Override
    public User findUserByFirstName(String firstName) {
        String sql = "SELECT * FROM user WHERE firstName=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User myUser = jdbcTemplate.queryForObject(sql, rowMapper, firstName);
        return myUser;
    }

    @Override
    public User updateUser(int cpr, User user) {
        String sql = "UPDATE user SET firstName=?, surname=?, addressid=?, email=? WHERE cpr=?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getSurname(), user.getAddressId(), user.getEmail(),cpr);

        return null;
    }

    @Override
    public Boolean deleteUser(int cpr) {
        String sql = "DELETE FROM user WHERE cpr=?";
        return jdbcTemplate.update(sql,cpr) >= 0;
    }



    // ROLEs ************************************

    @Override
    public List<Role> fetchAllRoles() {
        String sql = "SELECT * FROM auth_role";
        RowMapper<Role> rowMapper = new BeanPropertyRowMapper<>(Role.class);
        return  jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Role updateRole(int cpr, Role role) {
        String sql = "UPDATE auth_user_role SET auth_role_id=? WHERE cpr=?";
        jdbcTemplate.update(sql, role.getRoleId());
        return null;
    }

    @Override
    public Boolean deleteRole(int cpr) {
        String sql = "DELETE FROM auth_user_role WHERE cpr=?";
        return jdbcTemplate.update(sql,cpr) >= 0;
    }
}
