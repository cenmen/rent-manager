package com.liang.rent.auth;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.liang.rent.beans.RentManager;
import com.liang.rent.beans.Thority;
import com.liang.rent.service.IRentManagerService;
import com.liang.rent.service.IThorityService;


public class RentManagerRealm extends AuthorizingRealm {
	@Autowired
	private IRentManagerService rentManagerService;
	@Autowired
	private IThorityService thorityService;

	//执行授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权...");
		RentManager token = (RentManager)SecurityUtils.getSubject().getPrincipal();
		System.out.println(token);
	    int Rm_role_id = token.getRm_role_id();
	    SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
	    //根据用户ID查询角色（role），放入到Authorization里。
	    Thority thority = thorityService.getThor(Rm_role_id);
	    Set<String> permissionSet = new HashSet<String>();
//	    String author = authority.getAuthor();
	    permissionSet.add(thority.getThor());
//	    info.setRoles(roleSet); //添加角色
	    info.setStringPermissions(permissionSet);
	    return info;
	}

	//执行认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg) throws AuthenticationException {
		System.out.println("执行认证......");
		UsernamePasswordToken token = (UsernamePasswordToken) arg;
		String password = new String(token.getPassword());
		RentManager rentManager = rentManagerService.getRentManagerByAccount(token.getUsername(), password);
		boolean isStatus = rentManagerService.getRentManagerIsstatus(token.getUsername());
		//如果该方法返回AuthenticationInfo对象就代表认证成功，如果返回null就代表认证失败
		if (rentManager != null && isStatus) { //登录成功
			return new SimpleAuthenticationInfo(
					rentManager, //priciple，使用当前登录用户对象
					password,  //credentials
					rentManager.getRm_username()); //realmName
		}
		return null;  //返回null代表认证失败
	}
}
