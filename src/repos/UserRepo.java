package repos;

import models.Admin;
import models.DeliveryPartner;
import models.Role;
import models.User;

import java.util.HashMap;
import java.util.Map;

import static utils.GlobalConstants.*;

public class UserRepo {
    //id -> user
    Map<Integer,User> userList;
    DPRepo dpRepo;

    public UserRepo(DPRepo dpRepo) {
        this.userList = new HashMap<>();
        this.dpRepo=dpRepo;
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


}
