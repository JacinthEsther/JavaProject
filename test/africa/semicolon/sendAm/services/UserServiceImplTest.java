package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.exceptions.SendAmAppException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;

    @BeforeEach
    public void testSetUp(){
        userService = new UserServiceImpl();
    }
    @Test
    void afterRegister_repositoryContainOneElement(){
        RegisterUserRequest registerForm = createRegisterForm();

        userService.register(registerForm);

        assertEquals(1,userService.getRepository().count());
    }

    private RegisterUserRequest createRegisterForm() {
        RegisterUserRequest registerForm = new RegisterUserRequest();
        registerForm.setFirstName("Lotachi");
        registerForm.setLastName("Senior Dev");
        registerForm.setEmailAddress("@Lotachi.gmail.com");
        registerForm.setAddress("semiColon");
        registerForm.setPhoneNumber("09098987876");
        return registerForm;
    }

    @Test
    public void duplicateEmail_throws_exception(){
        RegisterUserRequest lotaForm = createRegisterForm();

        userService.register(lotaForm);

        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));
    }

    @Test
    public void duplicateEmailWithDifferentCase_ThrowsException(){
        RegisterUserRequest lotaForm = createRegisterForm();

        userService.register(lotaForm);
        lotaForm.setEmailAddress("@lotachi.gmail.com");

        assertThrows(SendAmAppException.class, ()-> userService.register(lotaForm));
    }

    @Test
    public void registrationReturnsCorrectResponseTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        RegisterUserResponse response = userService.register(lotaForm);
        assertEquals("Lotachi Senior Dev", response.getFullName());
        assertEquals("@lotachi.gmail.com",response.getEmail());
    }

    @Test
    public void findRegisteredUserByEmailTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);

        FindUserResponse result = userService.findUserByEmail(lotaForm.getEmailAddress().toLowerCase());

        assertEquals("Lotachi Senior Dev", result.getFullName());
        assertEquals("@lotachi.gmail.com",result.getEmail());
    }

    @Test
    public void findingUnregisteredUserEmailThrowsExceptionTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);

       assertThrows(UserNotFoundException.class,()->userService.findUserByEmail("aj@gmail.com"));
    }

    @Test
    public void findByUserEmailIsNotCaseSensitiveTest(){
        RegisterUserRequest lotaForm = createRegisterForm();
        userService.register(lotaForm);

        FindUserResponse response = userService.findUserByEmail("@LotaChi.gmail.com");

        assertEquals("Lotachi Senior Dev", response.getFullName());
        assertEquals("@lotachi.gmail.com",response.getEmail());
    }
}