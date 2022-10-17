package com.skooch;

import com.skooch.dao.UserDao;
import com.skooch.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mybatisplus03DmlApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void testSave() {
		User user = new User();
		user.setName("skooch");
		user.setPassword("123456");
		user.setAge(12);
		user.setTel("1234567890");
		userDao.insert(user);
	}

	@Test
	void testDeleteList(){
		List<Long> list = new ArrayList<>();
		list.add(1567338357418541058L);
		list.add(1567338696247046145L);
		list.add(1567339339162570753L);
		userDao.deleteBatchIds(list);
	}

	@Test
	void testGetByIds(){
		//查询指定多条数据
		List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(3L);
		list.add(4L);
		userDao.selectBatchIds(list);
		List<User> userList = userDao.selectAll();
		System.out.println(userList);
	}

	@Test
	void testDelete(){
		userDao.deleteById(1L);
	}

	@Test
	void testUpdate(){
		//1.先通过要修改的数据id将当前数据查询出来
		User user = userDao.selectById(3L);     //version=3
		User user2 = userDao.selectById(3L);    //version=3
		user2.setName("Jock aaa");
		userDao.updateById(user2);              //version=>4
		user.setName("Jock bbb");
		userDao.updateById(user);               //verion=3?条件还成立吗？
	}

}
