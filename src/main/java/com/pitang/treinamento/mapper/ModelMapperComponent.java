package com.pitang.treinamento.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.pitang.treinamento.dto.UserDto;
import com.pitang.treinamento.model.UserModel;
import com.pitang.treinamento.model.UserProfile;


@Component
public class ModelMapperComponent {
	
	public static final ModelMapper modelMapper= new ModelMapper();
	
	@PostConstruct
	private void configureMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        modelMapper.addMappings(
                new PropertyMap<UserModel, UserDto>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setPassword(source.getPassword());
                    	map().setEmail(source.getEmail());
                    	map().setUserName(source.getUserName());
                    	map().setFirstName(source.getFirstName());
                    	map().setLastName(source.getLastName());
                    	when(Conditions.isNotNull()).using(ModelConverter.convertStatus).map(source.isStatus()).setStatus(null);
                    	when(Conditions.isNotNull()).map().setProfileId(source.getUserProfile().getId());
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserDto, UserModel>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setPassword(source.getPassword());
                    	map().setEmail(source.getEmail());
                    	map().setUserName(source.getUserName());
                    	map().setFirstName(source.getFirstName());
                    	map().setLastName(source.getLastName());
                    	when(Conditions.isNotNull()).using(ModelConverter.convertStatusToBoolean).map(source.getStatus()).setStatus(false);
                    	map().setUserProfile(new UserProfile());
                    	map().getUserProfile().setId(source.getProfileId());
                    	
                    	map().getUserProfile().setState(null);
                    	map().getUserProfile().setAddress2(null);
                    	map().getUserProfile().setDateOfBirth(null);
                    	map().getUserProfile().setAddress1(null);
                    	map().getUserProfile().setGender(null);
                    	map().getUserProfile().setPhoneNumber(null);
                    	map().getUserProfile().setStreet(null);
                    	map().getUserProfile().setCountry(null);
                    	map().getUserProfile().setCity(null);
                    	map().getUserProfile().setUser(null);
                    	map().getUserProfile().setZipCode(null);
                    }
                });
        
	}
}