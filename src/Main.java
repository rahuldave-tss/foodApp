import models.FoodOrderApp;
import repos.UserRepo;

public class Main {
    public static void main(String[] args) {
        //FoodOrderApp - Facade
        FoodOrderApp app=new FoodOrderApp();
        app.start();
    }
}
