package com.skooch;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skooch.dao.UserDao;
import com.skooch.domain.User;
import com.skooch.domain.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Mybatisplus02DqlApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void testGetAll() {

		// QueryWrapper
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//		queryWrapper.lt("age", 18);
		queryWrapper.lambda().lt(User::getAge, 18);
		//LambdaQueryWrapper
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
		// 多条件查询
		lambdaQueryWrapper.lt(User::getAge, 30).gt(User::getAge, 10);

		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	@Test
	void testNullJudgment() {
		UserQuery userQuery = new UserQuery();
		userQuery.setAge(10);
		userQuery.setAge2(30);
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

		lambdaQueryWrapper.lt(null != userQuery.getAge2(), User::getAge, userQuery.getAge2());
		lambdaQueryWrapper.gt(null != userQuery.getAge(), User::getAge, userQuery.getAge());

		lambdaQueryWrapper.select(User::getId, User::getAge, User::getName);

		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	@Test
	// 聚合查询（无法使用Lambda）
	void testAggregateQuery() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// queryWrapper.select("count(*) as count");
		// queryWrapper.select("max(age) as maxAge");
		// queryWrapper.select("min(age) as minAge");
		// queryWrapper.select("sum(age) as sumAge");
		queryWrapper.select("avg(age) as avgAge");
		List<Map<String, Object>> userList = userDao.selectMaps(queryWrapper);
		System.out.println(userList);
	}

	@Test
	// 分组查询（无法使用Lambda）
	void testGroupQuery() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("count(*) as count, tel");
		queryWrapper.groupBy("tel");
		List<Map<String, Object>> userList = userDao.selectMaps(queryWrapper);
		System.out.println(userList);
	}

	@Test
	// 等值查询
	void testEquivalentQuery() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

		lambdaQueryWrapper.eq(User::getName, "Jerry")
				          .eq(User::getPassword, "jerry");

		User loginUser = userDao.selectOne(lambdaQueryWrapper);
		System.out.println(loginUser);
	}

	@Test
	// 范围查询
	void testRangeQuery() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

		lambdaQueryWrapper.between(User::getAge, 10, 30);
		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	@Test
	// 模糊查询
	void testFuzzyQuery() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.likeRight(User::getName, "J");
		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}

	@Test
	// 排序查询
	void testSortQuery() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		/**
		 * condition ：条件，返回boolean，
		 当condition为true，进行排序，如果为false，则不排序
		 * isAsc:是否为升序，true为升序，false为降序
		 * columns：需要操作的列
		 */
		lambdaQueryWrapper.orderBy(true, false, User::getId);
		List<User> userList = userDao.selectList(lambdaQueryWrapper);
		System.out.println(userList);
	}
}
