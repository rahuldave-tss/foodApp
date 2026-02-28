package repos;

import models.Admin;
import models.Role;
import models.User;

import java.util.HashMap;
import java.util.Map;

import static utils.GlobalConstants.*;

public class UserRepo {
    //id -> user
    Map<Integer,User> userList;

    public UserRepo() {
        this.userList = new HashMap<>();
        init();
    }

    public void addUser(User user){
        userList.put(user.getId(),user);
    }
    public void removeUser(int id){
        userList.remove(id);
    }

    public void setUserList(Map<Integer, User> userList) {
        this.userList = userList;
    }

    public Map<Integer, User> getUserList() {
        return userList;
    }

    public void init(){
        //Hardcoding an admin user for testing
        Admin admin=new Admin(adminUsername,adminPassword, adminEmail,adminPhoneNumber);
        userList.put(1, admin);
    }

    public User getUserById(int id){
        return userList.getOrDefault(id,null);
    }


}
