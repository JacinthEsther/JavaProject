package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.model.User;
import africa.semicolon.sendAm.data.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private List <User> users = new ArrayList<>();



    @Override
    public User save(User aUser, String email) {
        aUser.setEmail(email);
        users.add(aUser);
        return aUser;
    }

    @Override
    public User findByFullName(String fullName) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for(User user: users){
            if(Objects.equals(user.getEmail(), email)) return user;
        }
        return null;
    }

    @Override
    public void delete(User user) {
       users.remove(user);
    }

    @Override
    public void delete(String email) {
        User user = findByEmail(email);
    users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public int count() {
        return users.size();
    }

}
