package com.example.task_management_system_ampada.services;

import com.example.task_management_system_ampada.exceptions.UserNotFoundException;
import com.example.task_management_system_ampada.models.User;
import com.example.task_management_system_ampada.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;


public class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JWTService();
        service = new UserServiceImpl(repository, jwtService);
    }

    @Test
    void testFindUserById_UserFounded_returnUser() {
        Optional<User> user = Optional.of(new User("user", "pass"));
        given(repository.findById(user.get().getId())).willReturn(user);
        Optional<User> exceptedUser = service.findUserById(user.get().getId());
        Assertions.assertEquals(user, exceptedUser);
    }

    @Test
    void testFindUserById_UserNotFound_throwUserNotFoundException() {
        User user = new User("user", "pass");
        given(repository.findById(user.getId())).willReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.findUserById(user.getId());
        });
    }

    @Test
    void testFindUserByUsername_UserFounded_returnUser() {
        User user = new User("user", "pass");
        given(repository.findUserByUsername(user.getUsername())).willReturn(user);
        User exceptedUser = service.findUserByUsername(user.getUsername());
        Assertions.assertEquals(user, exceptedUser);
    }

    @Test
    void testFindUserByUsername_UserNotFound_throwUserNotFoundException() {
        User user = new User("user", "pass");
        given(repository.findUserByUsername(user.getUsername())).willReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.findUserByUsername(user.getUsername());
        });
    }

    @Test
    void testFindAllUsers_UsersFounded_returnUsers() {
        User user1 = new User("user1", "pass1");
        User user2 = new User("user1", "pass1");
        List<User> users = List.of(user1, user2);
        given(repository.findAll()).willReturn(users);
        List<User> exceptedUsers = service.findAllUsers();
        Assertions.assertEquals(users, exceptedUsers);
    }

    @Test
    void testFindAllUsers_UsersNotFound_throwUserNotFoundException() {
        given(repository.findAll()).willReturn(List.of());
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.findAllUsers();
        });
    }

    @Test
    void testSaveUser_UserSaved_returnUser() {
        User user = new User("user1", "pass1");
        given(repository.save(user)).willReturn(user);
        User exceptedUser = service.saveUser(user);
        Assertions.assertEquals(exceptedUser, user);
    }

    @Test
    void testDeleteUserById_UserDeleted_return() {
        User user = new User("user1", "pass1");
        repository.save(user);
        given(repository.existsById(user.getId())).willReturn(true);
        willDoNothing().given(repository).deleteById(user.getId());
        service.deleteUserById(user.getId());
        verify(repository).deleteById(user.getId());
    }

    @Test
    void testUpdateUser_UserUpdated_returnUser() {
        Optional<User> user = Optional.of(new User("user", "pass"));
        given(repository.findById(user.get().getId())).willReturn(user);
        given(repository.save(user.get())).willReturn(user.get());
        User exceptedUser = service.updateUser(user.get().getId(), user.get());
        Assertions.assertNotNull(exceptedUser);
        verify(repository).save(any(User.class));
    }

    @Test
    void testUpdateUser_UserNotFound_throwUserNotFoundException() {
        Optional<User> user = Optional.of(new User("user", "pass"));
        given(repository.findById(user.get().getId())).willReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            service.updateUser(user.get().getId(), user.get());
        });
    }
}
