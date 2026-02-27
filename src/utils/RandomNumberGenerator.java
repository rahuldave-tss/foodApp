package utils;

import java.util.HashSet;
import java.util.Random;

import static utils.GlobalConstants.randomNumberCapacity;

public class RandomNumberGenerator {
    static HashSet<Integer> generatedNumbers=new HashSet<>();

    public static int generateRandomNumber() {
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(randomNumberCapacity);
            if(!generatedNumbers.contains(randomNumber)){
                generatedNumbers.add(randomNumber);
                return randomNumber;
            }
        }
    }

}
