package com.user.controller;

import com.base.controller.BaseController;
import com.user.entity.Users;
import com.user.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController extends BaseController<UsersService,Users> {
    public Logger logger= LoggerFactory.getLogger(getClass());
    @Resource
    public UsersService usersService;

    /**
     * 根据用户名称获取用户信息
     * @return
     */
    @RequestMapping("/findUserByName")
    public String findUserByName(){
        Users users=usersService.findUserByName("smalldeng");
        return users.getName();
    }
    @RequestMapping("/findUserByRoleSid")
    public List<Users> findUserByRoleSid(@Param("roleSid") Long roleSid){
        List<Users> users=usersService.findUserByRoleSid(roleSid);
        return users;
    }
    @RequestMapping("/delUserByRoleUserSid")
    public void delUserByRoleUserSid(@Param("roleUserSid") Long roleUserSid){
        usersService.delUserByRoleUserSid(roleUserSid);
    }
}
