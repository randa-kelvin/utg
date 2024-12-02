package com.untucapital.usuite.utg.controller;//package com.untucapital.usuite.utg.controller;
//
//import com.untucapital.usuite.utg.model.Role;
//import com.untucapital.usuite.utg.model.User;
//import com.untucapital.usuite.utg.service.AbstractService;
//import com.untucapital.usuite.utg.service.RoleService;
//import com.untucapital.usuite.utg.service.UserRoleService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path = "userroles")
//public class UserRoleController extends AbstractController<User> {
//
//    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);
//
//    private final UserRoleService userRoleService;
//
//    public UserRoleController(UserRoleService userRoleService) {
//        this.userRoleService = userRoleService;
//    }
//
//    @Override
//    protected AbstractService<User> getService() {
//        return roleService;
//    }
//
//}
