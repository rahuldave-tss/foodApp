package repos;

import models.User;
import models.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    //id -> user
    Map<Integer,User> userList;
    private DPRepo dpRepo;

    public UserRepo() {
        this.userList = new HashMap<>();
        dpRepo=new DPRepo();
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
        User admin=new User("Rahul","admin123", UserType.ADMIN);
        userList.put(1, admin);
    }

    public User getUserById(int id){
        return userList.getOrDefault(id,null);
    }


}
