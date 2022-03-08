package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.repositories.UserRepository;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest requestForm);

    UserRepository getRepository();

    FindUserResponse findUserByEmail(String email);
}
