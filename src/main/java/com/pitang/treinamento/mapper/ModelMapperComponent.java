package com.pitang.treinamento.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.pitang.treinamento.dto.UserDto;
import com.pitang.treinamento.dto.UserProfileDto;
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
                    	map().setEmail(source.getEmail());
                    	map().setUserName(source.getUserName());
                    	map().setFirstName(source.getFirstName());
                    	map().setLastName(source.getLastName());
                    	when(Conditions.isNotNull()).using(ModelConverter.convertStatus).map(source.isStatus()).setStatus(null);
                    	map().getUserProfileDto().setId(source.getUserProfile().getId());
                    	map().getUserProfileDto().setAddress1(source.getUserProfile().getAddress1());
                    	map().getUserProfileDto().setAddress2(source.getUserProfile().getAddress2());
                    	map().getUserProfileDto().setCity(source.getUserProfile().getCity());
                    	map().getUserProfileDto().setCountry(source.getUserProfile().getCountry());
                    	using(ModelConverter.fromDateToString).map(source.getUserProfile().getDateOfBirth()).getUserProfileDto().setDateOfBirth(null);
                    	map().getUserProfileDto().setPhoneNumber(source.getUserProfile().getPhoneNumber());
                    	map().getUserProfileDto().setState(source.getUserProfile().getState());
                    	map().getUserProfileDto().setStreet(source.getUserProfile().getStreet());
                    	map().getUserProfileDto().setZipCode(source.getUserProfile().getZipCode());
                    	map().setPassword(null);
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
                    	skip().setUserProfile(null);
                    }
                });
        
        
        modelMapper.addMappings(
                new PropertyMap<UserProfileDto, UserProfile>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setAddress1(source.getAddress1());
                    	map().setAddress2(source.getAddress2());
                    	map().setCity(source.getCity());
                    	map().setCountry(source.getCountry());
                    	using(ModelConverter.fromStringToDate).map(source.getDateOfBirth()).setDateOfBirth(null);
                    	map().setPhoneNumber(source.getPhoneNumber());
                    	map().setState(source.getState());
                    	map().setStreet(source.getStreet());
                    	map().setZipCode(source.getZipCode());
                    	skip().setUser(null);
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserProfile, UserProfileDto>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setAddress1(source.getAddress1());
                    	map().setAddress2(source.getAddress2());
                    	map().setCity(source.getCity());
                    	map().setCountry(source.getCountry());
                    	using(ModelConverter.fromDateToString).map(source.getDateOfBirth()).setDateOfBirth(null);
                    	map().setPhoneNumber(source.getPhoneNumber());
                    	map().setState(source.getState());
                    	map().setStreet(source.getStreet());
                    	map().setZipCode(source.getZipCode());
                    }
                });
	}
}