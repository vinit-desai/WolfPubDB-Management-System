import java.util.*;

public class WolfCity{

    public static void main(String[] args){        
        System.out.println(
            "------------------------------------" + "\n" +
            "Welcome to WolfCity Publishing House" + "\n" +
            "------------------------------------"
        );
        
        User user = new User();
        user.select_position();
    }
}