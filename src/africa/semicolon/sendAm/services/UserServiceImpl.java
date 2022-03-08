package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.model.User;
import africa.semicolon.sendAm.data.repositories.UserRepositoryImpl;
import africa.semicolon.sendAm.data.repositories.UserRepository;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository = new UserRepositoryImpl();
    @Override
    public RegisterUserResponse register(RegisterUserRequest requestForm) {
        requestForm.setEmailAddress(requestForm.getEmailAddress().toLowerCase());
        if(emailExist(requestForm.getEmailAddress())) throw new RegisterFailureException
                ("Email exists");
        User user = new User();
        user.setEmail(requestForm.getEmailAddress());
        user.setAddress(requestForm.getAddress());
        user.setFullName(requestForm.getFirstName()+ " "+ requestForm.getLastName());
        user.setPhoneNumber(requestForm.getPhoneNumber());

      User savedUser =  userRepository.save(user);
      RegisterUserResponse response = new RegisterUserResponse();
      response.setEmail(savedUser.getEmail());
      response.setFullName(savedUser.getFullName());
        return response;
    }

    private boolean emailExist(String emailAddress) {
       User user = userRepository.findByEmail(emailAddress);
        if(user == null) return false;
        return true;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public FindUserResponse findUserByEmail(String email) {
        email = email.toLowerCase();
        User user = userRepository.findByEmail(email);
        if(user== null) throw new UserNotFoundException(email + " not found");
        FindUserResponse response = new FindUserResponse();
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        return response;
    }
}
