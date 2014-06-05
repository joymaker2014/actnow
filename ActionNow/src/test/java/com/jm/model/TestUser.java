package com.jm.model;

import java.util.Calendar;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jm.dao.UserDao;
import com.jm.model.User;
import com.jm.model.codes.Language;
import com.jm.model.codes.Sex;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUser {

	@Test
	@Transactional
	public void testSave() throws Exception {
		// User user = new User();
		// user.setAccount(123);
		// user.setBlackNum(0);
		// user.setCity("BeiJing");
		// user.setCountry("Chaoyang");
		// user.setCredit(123);
		// user.setHeadImageUrl("Http://baidu.com");
		// user.setLanguage(Language.ZH_CN.getDesc());
		// user.setNickname("HaHa");
		// user.setOpenid(UUID.randomUUID().toString());
		// user.setProvince("Beijing");
		// user.setSex(Sex.MALE);
		// user.setSubscribe(true);
		// user.setSubscribeTime(Calendar.getInstance().getTime().toString());
		//
		// ApplicationContext ctx = new ClassPathXmlApplicationContext(
		// "/META-INF/spring/app-context.xml");
		// UserDao userDao = ctx.getBean("userDao", UserDao.class);
		// userDao.save(user);
		System.out.println("*********** Test OK!!!");
	}
}
