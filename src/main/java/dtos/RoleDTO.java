package dtos;

import entities.Role;
import entities.User;

import java.util.List;

public class RoleDTO {
    String roleName;
    List<User> userList;

    public RoleDTO(){

    }

    public RoleDTO (Role role){
        this.roleName = role.getRoleName();
        this.userList = role.getUserList();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleName='" + roleName + '\'' +
                ", userList=" + userList +
                '}';
    }
}
