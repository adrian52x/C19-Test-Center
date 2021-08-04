package Covid19project.Model.Data;

import javax.persistence.*;


public class Role {

    @Id
    private int auth_role_id;
    private String role_name;


    public int getRoleId() {
        return auth_role_id;
    }

    public void setRoleId(int roleId) {
        this.auth_role_id = roleId;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String roleName) {
        this.role_name = roleName;
    }






}
