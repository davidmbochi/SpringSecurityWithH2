package com.david.SpringSecurityWithH2.dao;

import com.david.SpringSecurityWithH2.entity.Role;

public interface RoleDao {
    Role findRoleByRoleName(String theRoleName);
}
