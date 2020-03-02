package com.pitang.treinamento.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pitang.treinamento.exceptions.ExceptionBadRequest;
import com.pitang.treinamento.exceptions.ExceptionConflict;
import com.pitang.treinamento.model.UserModel;
import com.pitang.treinamento.repository.ContactRepository;
import com.pitang.treinamento.repository.HistoryPasswordRepository;
import com.pitang.treinamento.repository.MessageRepository;
import com.pitang.treinamento.repository.ProfileImageRepository;
import com.pitang.treinamento.repository.StoryRepository;
import com.pitang.treinamento.repository.UserRepository;
import com.pitang.treinamento.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private HistoryPasswordRepository historyPasswordRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ProfileImageRepository profileImageRepository;

	@Autowired
	private StoryRepository storyRepository;

	@Override
	public List<UserModel> listUsers() {
		if (userRepository.findAll().size() == 0) {
			return null;
		}
		return userRepository.findAll();
	}

	@Override
	public UserModel findUserByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public UserModel addUser(UserModel user) {
		checkMandatoryFields(user);
		validateUser(user);
		return userRepository.save(user);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		checkMandatoryFields(user);
		validateUserForUpdate(user);
		getRelations(user);
		return userRepository.save(user);
	}

	private void checkMandatoryFields(UserModel user) {
		if (user == null) {
			throw new ExceptionBadRequest("Usuário não pode ser nulo!");
		}
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new ExceptionBadRequest("Necessário informar o Email do usuário.");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new ExceptionBadRequest("Necessário informar a Senha do usuário.");
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			throw new ExceptionBadRequest("Necessário informar o Nome do usuário.");
		}
	}

	private void validateUser(UserModel user) {
		if (!StringUtils.isEmpty(user.getUsername()) && userRepository.findByUsername(user.getUsername()) != null) {
			throw new ExceptionConflict("Nome de usuário informado já existe!");
		}
		if (!StringUtils.isEmpty(user.getEmail()) && userRepository.findByEmail(user.getEmail()) != null) {
			throw new ExceptionConflict("Email informado já existe!");
		}
	}

	private void validateUserForUpdate(UserModel user) {
		if (!StringUtils.isEmpty(user.getUsername()) && user.getId() != null) {
			UserModel userReturned = userRepository.findByUsername(user.getUsername());
			if (userReturned != null && userReturned.getId() != null && !userReturned.getId().equals(user.getId())) {
				throw new ExceptionConflict("Nome de usuário informado já existe!");
			}
		}
		if (!StringUtils.isEmpty(user.getEmail()) && user.getId() != null) {
			UserModel userReturned = userRepository.findByEmail(user.getEmail());
			if (userReturned != null && userReturned.getId() != null && !userReturned.getId().equals(user.getId())) {
				throw new ExceptionConflict("Email informado já existe!");
			}
		}
	}

	private void getRelations(UserModel userModel) {
		//TODO : Implementar relacionamentos
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(getUserById(id));
	}

	@Override
	public UserModel addProfile(UserModel user) {
		UserModel userDb = getUserById(user.getId());
		
		userDb.setProfileImageModel(user.getProfileImageModel());
		
		byte[] bytes = user.getProfileImageModel().getImage();
        File file = new File("c:\\");
 
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return userRepository.save(userDb);
	}
	
	private UserModel getUserById(Long id) {
		Optional<UserModel> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new ExceptionBadRequest("Usuário não encontrado!");
		}
		return user.get();
	}

}
