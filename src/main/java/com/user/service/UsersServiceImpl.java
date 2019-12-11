package com.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.entity.Users;
import com.user.mapper.UsersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author smalldeng
 * @since 2019-05-27
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper,Users> implements UsersService{
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Users findUserByName(String name) {
        return usersMapper.findUserByName(name);
    }

    @Override
    public String getUserById() {
        return usersMapper.getUserById();
    }

    @Override
    public List<Users> findUserByRoleSid(Long roleSid){
        return usersMapper.findUserByRoleSid(roleSid);
    }

    @Override
    public void delUserByRoleUserSid(Long roleUserSid){
        usersMapper.delUserByRoleUserSid(roleUserSid);
    }

    @Override
    public List<Users> findUsersByOrgSid(int[] orgSid) {
        return usersMapper.findUsersByOrgSid(orgSid);
    }
}
