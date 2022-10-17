package com.skooch;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skooch.dao.UserDao;
import com.skooch.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testSave() {
        User user = new User();
        user.setName("skooch");
        user.setAge(16);
        user.setPassword("123456");
        user.setTel("123456789");
        userDao.insert(user);
    }

    @Test
    void testDelete() {
        userDao.deleteById(1567028853750669313L);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("Skooch666");
        userDao.updateById(user);
    }

    @Test
    void testGetById() {
        User user = userDao.selectById(2L);
        System.out.println(user);
    }

    @Test
    void testGetAll() {
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testGetByPage() {
        IPage page = new Page(1, 5);
        userDao.selectPage(page, null);
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getPages() = " + page.getPages());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.getRecords() = " + page.getRecords());
    }

}
