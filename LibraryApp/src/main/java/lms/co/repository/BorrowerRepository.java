package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.Borrower;

@Component
public class BorrowerRepository {
    HashMap<String, Borrower> users = new HashMap<>();

    public void addUser(Borrower user){

        users.put(String.valueOf(user.getUserid()), user);
    }

    public Borrower getUser(String id){
        return users.get(id);
    }

    public List<Borrower> getAllUsers(){
        return new ArrayList<>(users.values());
    }

}
