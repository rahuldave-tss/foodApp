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

    public UserRepo() {
        this.userList = new HashMap<>();
        init();
    }

    public void addUser(User user){
        userList.put(user.getId(),user);
    }

    public Map<Integer, User> getUserList() {
        return userList;
    }

    public void init(){
        User admin=new User("Rahul","admin123", UserType.ADMIN);
        userList.put(admin.getId(), admin);
    }

    public User getUserById(int id){
        return userList.getOrDefault(id,null);
    }


}
