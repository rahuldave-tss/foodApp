package repos;

import models.Admin;
import models.DeliveryPartner;
import models.Role;
import models.User;

import java.util.HashMap;
import java.util.Map;

import static utils.GlobalConstants.*;

public class UserRepo {
    //userName -> user
    Map<String,User> userList;

    public UserRepo() {
        this.userList = new HashMap<>();
        init();
    }

    public void addUser(User user){
        userList.put(user.getUserName(),user);
    }
    public void removeUser(String userName){
        userList.remove(userName);
    }

    public void setUserList(Map<String, User> userList) {
        this.userList = userList;
    }

    public Map<String, User> getUserList() {
        return userList;
    }

    private void init(){
        //Hardcoding an admin user for testing
        Admin admin=new Admin(adminUsername,adminName,adminPassword, adminEmail,adminPhoneNumber);
        userList.put(adminUsername, admin);
        //Adding 2 delivery partners for testing
//        DeliveryPartner dp1=new DeliveryPartner("Jay","jay123", "jay@gmail.com","9876543210");
//        DeliveryPartner dp2=new DeliveryPartner("Munna","munna123", "munna@gmail.com","9876543211");
//        userList.put(dp1.getId(), dp1);
//        userList.put(dp2.getId(), dp2);
//        dpRepo.addPartner(dp1);
//        dpRepo.addPartner(dp2);
    }

    public User getUserById(int id){
        return userList.getOrDefault(id,null);
    }

    public User getUserByUserName(String userName){
        return userList.getOrDefault(userName,null);
    }
}
