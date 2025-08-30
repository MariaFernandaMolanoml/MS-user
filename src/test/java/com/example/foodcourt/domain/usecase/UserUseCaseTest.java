package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.constants.Constants;
import com.example.foodcourt.domain.exception.
import com.example.foodcourt.domain.model.Role;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IEncryptPasswordPersistencePort;
import com.example.foodcourt.domain.spi.IRolePersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.
import static org.mockito.Mockito.

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncryptPasswordPersistencePort encryptPasswordPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setEmail("test@example.com");
        testUser.setDocument("123456789");
        testUser.setBirthDate(LocalDate.of(1990, 1, 1));
        testUser.setPassword("plainPassword");
    }

    @Test
    void saveOwner_Success() {

        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocument(testUser.getDocument())).thenReturn(false);

        Role role = new Role();
        role.setId(1L);
        role.setName(Constants.ROLE_OWNER);
        when(rolePersistencePort.findByName(Constants.ROLE_OWNER)).thenReturn(role);

        when(encryptPasswordPersistencePort.encryptPassword(testUser.getPassword())).thenReturn("encrypted");
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(testUser);


        User savedUser = userUseCase.saveOwner(testUser);

        assertNotNull(savedUser);
        assertEquals(Constants.ROLE_OWNER, savedUser.getRole().getName());
        verify(userPersistencePort).saveUser(any(User.class));
    }


    @Test
    void saveOwner_ShouldThrowEmailAlreadyExistsException() {
        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void saveOwner_ShouldThrowIllegalArgumentException_WhenEmailIsNull() {
        testUser.setEmail(null);

        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void saveOwner_ShouldThrowIllegalArgumentException_WhenEmailIsBlank() {
        testUser.setEmail("  ");

        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void saveOwner_ShouldThrowDocumentAlreadyExistsException() {
        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocument(testUser.getDocument())).thenReturn(true);

        assertThrows(DocumentAlreadyExistsException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void saveOwner_ShouldThrowAgeNotValidException_WhenUnder18() {
        testUser.setBirthDate(LocalDate.now().minusYears(17));

        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocument(testUser.getDocument())).thenReturn(false);

        assertThrows(AgeNotValidException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void saveOwner_ShouldThrowRoleNotFoundException() {
        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocument(testUser.getDocument())).thenReturn(false);
        when(rolePersistencePort.findByName(Constants.ROLE_OWNER)).thenReturn(null);

        assertThrows(RoleNotFoundException.class, () -> userUseCase.saveOwner(testUser));
    }

    @Test
    void getUser_Success() {
        when(userPersistencePort.findByDocument("123456789")).thenReturn(testUser);

        User user = userUseCase.getUserByDocument("123456789");

        assertNotNull(user);
        assertEquals("123456789", user.getDocument());
    }

    @Test
    void getUser_ShouldThrowUserNotFoundException() {
        when(userPersistencePort.findByDocument("999")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userUseCase.getUserByDocument("999"));
    }

    @Test
    void saveOwner_ShouldThrowIllegalArgumentException_WhenDocumentIsNull() {

        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);

        testUser.setDocument(null);

        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveOwner(testUser));

        verify(userPersistencePort, never()).existsByDocument(anyString());
        verify(rolePersistencePort, never()).findByName(anyString());
    }

    @Test
    void saveOwner_ShouldThrowIllegalArgumentException_WhenDocumentIsBlank() {
        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);

        testUser.setDocument("   ");

        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveOwner(testUser));

        verify(userPersistencePort, never()).existsByDocument(anyString());
        verify(rolePersistencePort, never()).findByName(anyString());
    }

    @Test
    void saveOwner_ShouldThrowAgeNotValidException_WhenBirthDateIsNull() {
        when(userPersistencePort.existsByEmail(testUser.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByDocument(testUser.getDocument())).thenReturn(false);

        testUser.setBirthDate(null);

        assertThrows(AgeNotValidException.class, () -> userUseCase.saveOwner(testUser));

        verify(rolePersistencePort, never()).findByName(anyString());
    }

}
