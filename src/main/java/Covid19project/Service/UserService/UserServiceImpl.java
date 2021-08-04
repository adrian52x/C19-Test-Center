package Covid19project.Service.UserService;

import Covid19project.Model.Data.Role;
import Covid19project.Repository.UserRepository.IUserRepo;
import Covid19project.Model.Data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService  {

    @Autowired
    IUserRepo iUserRepo;

    @Override
    public User addNewUser(User user) {
        return iUserRepo.addNewUser(user);
    }

    @Override
    public User addNewUserRole(User user) {
        return iUserRepo.addNewUserRole(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return iUserRepo.fetchAllUsers();
    }

    @Override
    public User findUserByCpr(Integer cpr) {
        return iUserRepo.findUserByCpr(cpr);
    }



    @Override
    public User findUserByRole(String roleName) {
        return iUserRepo.findUserByRole(roleName);
    }

    @Override
    public User findUserByFirstName(String firstName) {
        return iUserRepo.findUserByFirstName(firstName);
    }

    @Override
    public User updateUser(int cpr, User user) {
        return iUserRepo.updateUser(cpr,user);
    }

    @Override
    public Boolean deleteUser(int cpr) {
        return iUserRepo.deleteUser(cpr);
    }



    // ROLES

    @Override
    public List<Role> fetchAllRoles() {
        return iUserRepo.fetchAllRoles();
    }

    @Override
    public Role updateRole(int cpr, Role role) {
        return iUserRepo.updateRole(cpr, role);
    }

    @Override
    public Boolean deleteRole(int cpr) {
        return iUserRepo.deleteRole(cpr);
    }
}
