package user;

import java.util.ArrayList;
import java.util.Iterator;

public class UserRepository {

    ArrayList<User> list = new ArrayList<>();
    private int idx = 1;

    public void addUser(String email, String password){
        list.add(new User(email, password));
    }

    public User findByEmail(String email){

        for (User user : list) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
