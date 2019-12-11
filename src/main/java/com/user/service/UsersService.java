package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.entity.Users;
import org.apache.catalina.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author smalldeng
 * @since 2019-05-27
 */
public interface UsersService extends IService<Users> {
    public String getUserById();
    public Users findUserByName(String name);
    public List<Users> findUserByRoleSid(Long roleSid);
    public void delUserByRoleUserSid(Long roleUserSid);
    public List<Users> findUsersByOrgSid(int[] orgSid);
}
