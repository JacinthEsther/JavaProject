package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserRepositoryImplTest {
    private UserRepository userRepository;

    @BeforeEach
  private void setUp(){
        userRepository = new UserRepositoryImpl();
    }
    @Test
    void saveUserTest(){
        User user = new User();
        User saveUser = userRepository.save(user, "e-agboniro@gmail.com");
        assertEquals("e-agboniro@gmail.com",saveUser.getEmail());
        assertEquals(1,userRepository.count());
    }

    @Test
    void findUserByEmail() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userRepository.save(user1, "e-agboniro@gmail.com");
        userRepository.save(user2, "j-agboniro@gmail.com");
        userRepository.save(user3, "p-agboniro@gmail.com");

        assertEquals(3, userRepository.count());
        User findUserByEmail = userRepository.findByEmail("e-agboniro@gmail.com");
        assertEquals(user1, findUserByEmail);
    }
       @Test
       void deleteUserByEmail(){
           User user1 = new User();
           User user2 = new User();
           User user3 = new User();

           userRepository.save(user1, "e-agboniro@gmail.com");
           userRepository.save(user2, "j-agboniro@gmail.com");
           userRepository.save(user3, "p-agboniro@gmail.com");

           assertEquals(3, userRepository.count());
           userRepository.delete("e-agboniro@gmail.com");
           assertEquals(2, userRepository.count());
       }
       @Test
       void deleteUserByUser(){
           User user1 = new User();
           User user2 = new User();
           User user3 = new User();

           userRepository.save(user1, "e-agboniro@gmail.com");
           userRepository.save(user2, "j-agboniro@gmail.com");
           userRepository.save(user3, "p-agboniro@gmail.com");

           assertEquals(3, userRepository.count());
           userRepository.delete(user2);
           assertEquals(2, userRepository.count());
       }

         @Test
       void findByEmailWoks_afterADeleteTest(){
           User user1 = new User();
           User user2 = new User();
           User user3 = new User();

           userRepository.save(user1, "e-agboniro@gmail.com");
           userRepository.save(user2, "j-agboniro@gmail.com");
           userRepository.save(user3, "p-agboniro@gmail.com");

           assertEquals(3, userRepository.count());
           userRepository.delete(user2);
     User findUserByEmail = userRepository.findByEmail("j-agboniro@gmail.com");
     assertNull(findUserByEmail);
           assertEquals(2, userRepository.count());
       }

     @Test
       void findAllTest(){
           User user1 = new User();
           User user2 = new User();
           User user3 = new User();

           userRepository.save(user1, "e-agboniro@gmail.com");
           userRepository.save(user2, "j-agboniro@gmail.com");
           userRepository.save(user3, "p-agboniro@gmail.com");

          List<User>all = userRepository.findAll();
          assertEquals(3,all.size());
       }





}
