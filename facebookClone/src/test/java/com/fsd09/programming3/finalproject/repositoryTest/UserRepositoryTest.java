package com.fsd09.programming3.finalproject.repositoryTest;

import com.fsd09.programming3.finalproject.entity.User;
import com.fsd09.programming3.finalproject.mapper.UserResultMapper;
import com.fsd09.programming3.finalproject.repository.UserRepository;
import com.fsd09.programming3.finalproject.result.UserResult;
import com.fsd09.programming3.finalproject.util.IDGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 *
 */
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserResultMapper userResultMapper;

    @Test
    @Transactional
    void insertAndCheckUserRepository() {
        //given
        User user = new User()
                .builder()
                .userId(IDGenerator.generateUserId())
                .userName("test123")
                .password("test123")
                .email("test123@gmail.com")
                .commentList(new ArrayList<>())
                .postList(new ArrayList<>())
                .build();
        User save = userRepository.save(user);
        //when
        User test123 = userRepository.findByUserNameIgnoreCase("test123").orElseThrow(() -> new UsernameNotFoundException("user can not be found"));
        UserResult userResult = userResultMapper.apply(test123);
        //then
        log.info(userResult.toString());
        assertThat(userResult).isNotNull();
    }


}
