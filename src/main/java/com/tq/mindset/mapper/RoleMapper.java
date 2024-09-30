package com.tq.mindset.mapper;

import com.tq.mindset.domain.Role;
import com.tq.mindset.dto.createDto.CreateRoleDto;
import com.tq.mindset.dto.updateDto.UpdateRoleDto;
import com.tq.mindset.dto.viewDto.RoleDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role createDtoToRole(CreateRoleDto createRoleDto);

    void updateRoleFromDto(UpdateRoleDto updateRoleDto, @MappingTarget Role role);

    RoleDto roleToDto(Role role);

    @InheritInverseConfiguration
    Role dtoToRole(RoleDto roleDto);
}
