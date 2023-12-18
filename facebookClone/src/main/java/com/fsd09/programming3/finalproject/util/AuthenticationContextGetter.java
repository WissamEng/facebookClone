package com.fsd09.programming3.finalproject.util;

import cn.hutool.core.bean.BeanUtil;
import com.fsd09.programming3.finalproject.config.UserDetailsImp;
import com.fsd09.programming3.finalproject.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * use this bean to get the user which has been verified by security via username and password
 * the user is stored in SecurityContextHolder
 */
@Service
public class AuthenticationContextGetter {
    public User getCurrentAuthenticatedUser() throws UserPrincipalNotFoundException {
        //get Authentication from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (BeanUtil.isEmpty(authentication) || !authentication.isAuthenticated()){
            //if authentication is null or is not authenticated, throw exception which will be handled by GlobalExceptionHandler
            throw new UserPrincipalNotFoundException("login information can not be found");
        }
        //get principal, which should be a UserDetailsImp instance
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImp){
            UserDetailsImp userDetails = (UserDetailsImp) principal;
            //return user who is login
            return userDetails.getUser();
         }
        throw new UserPrincipalNotFoundException("login information can not be found");
    }
}
