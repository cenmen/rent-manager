package com.liang.rent.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.rent.beans.RentManager;
import com.liang.rent.service.IRentManagerService;


@RestController
@RequestMapping("/RentManagerCon")
public class RentManagerController {
	@Autowired
	private IRentManagerService rentManagerService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(path="/RentManagerLogin.do", produces="application/json;charset=utf-8")
	public Map login(String rm_account , String rm_password , HttpSession session) {
		Map map =new HashMap();
		//第一步：创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken(rm_account , rm_password);
		//第二步：获取Subject对象，该对象封装了一系列的操作
		Subject subject = SecurityUtils.getSubject();
		//第三步：执行认证
		try {
			subject.login(token);
			session.setAttribute("rm_account" ,rm_account);
			map.put("status", "200");
			map.put("message", "登录成功");
			return map;
		} catch (AuthenticationException e) {
			map.put("status", "500");
			map.put("message", "登录失败或账号没有启用");
		}
		return map;
	}
	
	@RequestMapping(path="/RentManagerLogout.do", produces="application/json;charset=utf-8")
	public Map logout(HttpSession session) {
		//session.removeAttribute("user"); //删除Session的user属性
		//session.invalidate(); //把整个session销毁
		Map map =new HashMap();
		SecurityUtils.getSubject().logout();
		map.put("status", "200");
		map.put("message", "注销成功");
		return map;
	}
	
	@RequestMapping(path="/getAccountInSession.do", produces="application/json;charset=utf-8")
	public Map getAccountInSession() {
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("rm_account");
		System.out.println("in session toString "+String.valueOf(val));
		String rm_account = String.valueOf(val);
		Map map =new HashMap();
		map.put("rm_account", rm_account);
		return map;
	}
	
	@RequestMapping(path="/getAllRentManager.do", produces="application/json;charset=utf-8")
	public List<RentManager> getAllRentManager() {
		List<RentManager> rentManagerList = rentManagerService.getAllRentManager();
		return rentManagerList;
	}
	
	@RequestMapping(path="/getRentManagerById.do")
	public RentManager getRentManagerById(String rm_id) {  
		return rentManagerService.getRentManagerById(rm_id);
	}
	
	@RequestMapping(path="/getRentManagerByAccountForUpdate.do", produces="application/json;charset=utf-8")
	public RentManager getRentManagerByAccountForUpdate(String rm_account) {  
		return rentManagerService.getRentManagerByAccountForUpdate(rm_account);
	}
	
	@RequestMapping(path="/getRentManagerByCondition.do", produces="application/json;charset=utf-8")
	public List<RentManager> getRentManagerByCondition(RentManager rentManager) {
		List<RentManager> rentManagerList = rentManagerService.getRentManagerByCondition(rentManager);
		return rentManagerList;
	}
	
	@RequestMapping(path="/getRentManagerByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentManagerByPageCache")
	public List<RentManager> getRentManagerByPage(int pageNum) {
		List<RentManager> rentManagerList = rentManagerService.getRentManagerByPage(pageNum);
		return rentManagerList;
	}
	
	@RequestMapping(path="/updateRentManager.do")
	public void updateRentManager(RentManager rentManager) {
		rentManagerService.updateRentManager(rentManager);
	}
	
	@RequestMapping(path="/updateRentManagerPwd.do")
	public Map updateRentManagerPwd(String rm_account , String original_pwd , String new_pwd_1 , String new_pwd_2) {
		return rentManagerService.updateRentManagerPwd(rm_account,original_pwd,new_pwd_1,new_pwd_2);
	}
}
