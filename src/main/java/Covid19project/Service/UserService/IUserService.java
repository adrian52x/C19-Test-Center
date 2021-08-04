package Covid19project.Service.UserService;

import Covid19project.Model.Data.Role;
import Covid19project.Model.Data.User;

import java.util.List;


public interface IUserService {
    //Create
    User addNewUser(User user);
    User addNewUserRole(User user);
    //READ
    List<User> fetchAllUsers();
    User findUserByCpr(Integer cpr);
    User findUserByRole(String roleName);
    User findUserByFirstName(String firstName);

    //Update
    User updateUser(int cpr, User user);

    //Delete
    Boolean deleteUser(int cpr);

    // ROLES
    List<Role> fetchAllRoles();
    Role updateRole(int cpr, Role role);
    Boolean deleteRole(int cpr);

}
