package com.stokkur.exam.demoAccounts.service.util;

import com.stokkur.exam.demoAccounts.dto.RoleDto;
import com.stokkur.exam.demoAccounts.model.Role;

public class RoleDtoMapper {

    private RoleDtoMapper(){ }

    /**
     * Mapper for convert Repository entity to RoleDto
     * @param entity
     * @return RoleDto
     */
    public static RoleDto toDto(Role entity){
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
