package com.tq.mindset.mapper;

import com.tq.mindset.domain.User;
import com.tq.mindset.dto.viewDto.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, BlogMapper.class})
public interface UserMapper {

    UserDto userToDto(User user);

    @InheritInverseConfiguration
    User dtoToUser(UserDto userDto);

    @AfterMapping
    default void linkBlogsAndProjects(@MappingTarget User user) {
        if (user.getBlogs() != null) {
            user.getBlogs().forEach(blog -> blog.setUser(user));
        }
    }
}
