package com.base.shiro;

import com.user.entity.Users;
import com.user.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smalldeng on  六月
 */
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UsersService usersService;

    private Logger logger=LogManager.getLogger(UserRealm.class);

    /**
     * 提供用户信息，返回权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String) principals.getPrimaryPrincipal();
        //String userId=testUsersService.findUserIdByName(userName);
        //Set<SysUserRole> roleIdSet=testUsersService.findRoleIdByUid( Integer.parseInt(userId) );
        Set<String> roleSet=new HashSet<>();
        Set<Integer>  pemissionIdSet=new HashSet<>();
        Set<String>  pemissionSet=new HashSet<>();
//        for(SysUserRole roleInfo : roleIdSet) {
//            int roleId=roleInfo.getRoleId();
//            roleSet.add( testUsersService.findRoleByRoleId( roleId  ) );
//            //将拥有角色的所有权限放进Set里面，也就是求Set集合的并集
//            //由于我这边的数据表设计得不太好，所以提取set集合比较麻烦
//            pemissionIdSet.addAll( testUsersService.findPermissionIdByRoleId(  roleId ));
//        }
//        for(int permissionId : pemissionIdSet) {
//            String permission= testUsersService.findPermissionById( permissionId ).getPermission() ;
//            pemissionSet.add(  permission );
//        }
        // 将角色名称组成的Set提供给授权info
        authorizationInfo.setRoles( roleSet );
        // 将权限名称组成的Set提供给info
        authorizationInfo.setStringPermissions(pemissionSet);
        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        logger.info("获取用户信息开始");
        //令牌——基于用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName=token.getUsername();
        logger.info("登录用户名："+userName);
        //根据用户名查询用户
        Users user=usersService.findUserByName(userName);
        if(user==null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException("用户不存在");
        }
        logger.info("数据库密码："+user.getPassWord());
        //让shiro框架去验证账号密码，不带盐值的验证，密码已加密
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user.getName(),
                user.getPassWord(),
                userName
        );
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        /*logger.info("此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中");
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,
                newPassword,
                ByteSource.Util.bytes("small"),
                userName);*/
        return authenticationInfo;
    }
}