package com.pitang.sms.service;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pitang.treinamento.model.UserModel;
import com.pitang.treinamento.repository.UserRepository;
import com.pitang.treinamento.service.UserService;
import com.pitang.treinamento.service.impl.UserServiceImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	
	@MockBean
    private UserRepository userRepository;
	
	@Autowired
    private UserService userService;
	
	private UserModel userModel;
	
	
	@TestConfiguration
    static class FuncionarioServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
	
	@Before
    public void before(){
		userModel = createUser();
    	
    	Mockito.when(userRepository.findAll()).thenReturn(createUsers());
    }
	
	@Test
	public void testeFindUserByUsername() {
		UserModel expected = createUser();
		
		UserModel result = userService.findUserByUsername("teste");
		assertNull(result);
	}
	
	@Test
	public void testeEmail() {
		UserModel expected = createUser();
		
		UserModel result = userService.findUserByUsername("");
	}
	
	private UserModel createUser() {
		UserModel user = new UserModel();
		user.setId(1l);
		user.setEmail("anderson.rocha@hotmail.com");
		user.setPassword("senha123");
		return user;
	}
	
	private List<UserModel> createUsers() {
		List<UserModel> users = new ArrayList<UserModel>();
		UserModel user = new UserModel();
		user.setId(1l);
		user.setEmail("anderson.rocha@hotmail.com");
		user.setPassword("senha123");
		users.add(user);
		return users;
	}
	
}
