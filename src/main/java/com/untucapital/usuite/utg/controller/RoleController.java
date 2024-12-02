package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Role;
import com.untucapital.usuite.utg.model.Sales;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "roles")
public class RoleController extends AbstractController<Role> {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected AbstractService<Role> getService() {
        return roleService;
    }

    @PostMapping("/addRoles")
    public void add(@RequestBody Role role) {
        roleService.saveRole(role);
    }


}
