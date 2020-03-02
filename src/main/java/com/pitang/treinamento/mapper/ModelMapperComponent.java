package com.pitang.treinamento.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.pitang.treinamento.dto.UserDto;
import com.pitang.treinamento.model.UserModel;


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
                    	map().setName(source.getName());
                    	map().setUsername(source.getUsername());
                    	when(Conditions.isNotNull()).map().getUserProfileDto().setId(source.getProfileImageModel().getId());
                    	when(Conditions.isNotNull()).map().getUserProfileDto().setImage(source.getProfileImageModel().getImage());
                    	map().setPassword(null);
                    	skip().setContacts(null);
                    	skip().setMessages(null);
                    	skip().setStories(null);
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserDto, UserModel>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setName(source.getName());
                    	map().setPassword(source.getPassword());
                    	map().setEmail(source.getEmail());
                    	map().setUsername(source.getUsername());
                    	when(Conditions.isNotNull()).map().getProfileImageModel().setId(source.getUserProfileDto().getId());
                    	when(Conditions.isNotNull()).map().getProfileImageModel().setImage(source.getUserProfileDto().getImage());
                    	skip().setContacts(null);
                    	skip().setMessages(null);
                    	skip().setStories(null);
                    }
                });
        
        
	}
}