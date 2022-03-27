package com.bcp.test.dto.user;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.bcp.test.model.security.User;


@Mapper(
	    componentModel = "spring",
	    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
	    unmappedTargetPolicy = ReportingPolicy.IGNORE,
	    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
	)
public interface UserMapper {
	
	public UserDto toDto(User user);

}
