package repos;

import models.User;

import java.util.HashMap;
import java.util.Map;

import static utils.GlobalConstants.adminPassword;
import static utils.GlobalConstants.adminUsername;

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
        User admin=new User(adminUsername,adminPassword, UserType.ADMIN);
        userList.put(1, admin);
    }

    public User getUserById(int id){
        return userList.getOrDefault(id,null);
    }


}
