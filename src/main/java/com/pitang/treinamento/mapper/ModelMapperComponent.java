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
                    	map().setUserName(source.getUserName());
                    	map().setFirstName(source.getFirstName());
                    	map().setLastName(source.getLastName());
                    	when(Conditions.isNotNull()).using(ModelConverter.convertStatus).map(source.isStatus()).setStatus(null);
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserDto, UserModel>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setEmail(source.getEmail());
                    	map().setUserName(source.getUserName());
                    	map().setFirstName(source.getFirstName());
                    	map().setLastName(source.getLastName());
                    	when(Conditions.isNotNull()).using(ModelConverter.convertStatus).map(source.getStatus()).setStatus(false);
                    }
                });
        
	}
}