package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.entity.Users;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author smalldeng
 * @since 2019-05-27
 */
public interface UsersMapper extends BaseMapper<Users> {
    public String getUserById();
    public Users findUserByName(String name);
    public List<Users> findUserByRoleSid(Long roleSid);
    public void delUserByRoleUserSid(Long roleUserSid);
}