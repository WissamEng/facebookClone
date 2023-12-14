package com.fsd09.programming3.finalproject.service;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.CommentResultMapper;
import com.fsd09.programming3.finalproject.mapper.PostResultMapper;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.service.imp.UserServiceImp;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private IUserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImp(userRepository, new UserResultMapper(new CommentResultMapper(), new PostResultMapper(new CommentResultMapper())));
    }

    @Test
    void testInsertANewUser() {
        //given
        User user = new User()
                .builder()
                .userName("test789")
                .password("abcd1234")
                .userId(IDGenerator.generateUserId())
                .email("test789@gmail.com")
                .build();
        //when
        when(userRepository.save(any(User.class))).thenReturn(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        userService.insertNewUser(user);
        //then
        verify(userRepository).save(userArgumentCaptor.capture());
        User captorValue = userArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(user);

    }
}
