package com.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sc.entity.Massage;
import com.sc.entity.XtUserNum;
import com.sc.service.XtUserNumService;

import oracle.jdbc.driver.Message;

@Controller
@RequestMapping("/xtuserctrl")
public class XtUserController {

	@Autowired
	XtUserNumService xtUserNumService;
	
	@RequestMapping("/selectuser.do")
	public ModelAndView selectUser(ModelAndView mav,
			@RequestParam(defaultValue="1")Integer pageNum, 
			@RequestParam(defaultValue="10")Integer pageSize,
			XtUserNum usernum){
		System.out.println("进入查询用户分页方法了");
		
		PageInfo<XtUserNum> page = xtUserNumService.selectUser(pageNum, pageSize, usernum);
		System.out.println(page.getList());
		mav.addObject("p", page);
		mav.setViewName("yzh_xt/admin-list");
		
		return mav;
		
	}
	@RequestMapping("/goadduser.do")
	public ModelAndView goadduser(ModelAndView mav){
		System.out.println("进入添加页面");
		mav.setViewName("yzh_xt/admin-add");
		return mav;
	}
	
	@RequestMapping("/adduser.do")
	@ResponseBody
	public Massage adduser(ModelAndView mav,XtUserNum usernum){
		System.out.println("进入添加管理员"+usernum);
		xtUserNumService.addUser(usernum);
		return new Massage("1","success","成功");
	}
	
	@RequestMapping("/deleteuser.do")
	@ResponseBody
	public Massage deleteuser(ModelAndView mav,XtUserNum usernum){
		System.out.println("进入删除管理员"+usernum);
		xtUserNumService.deleteUser(usernum.getUserId());
		return new Massage("1","success","成功");
	}
}
