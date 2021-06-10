package com.zwl.fluentmybatisdemo;

import com.zwl.fluentmybatisdemo.entity.ReceivingAddress;
import com.zwl.fluentmybatisdemo.mapper.ReceivingAddressMapper;
import com.zwl.fluentmybatisdemo.mapper.UserMapper;
import java.util.Date;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import com.zwl.fluentmybatisdemo.dao.UserDao;
import com.zwl.fluentmybatisdemo.entity.HelloWorldEntity;
import com.zwl.fluentmybatisdemo.entity.User;
import com.zwl.fluentmybatisdemo.mapper.HelloWorldMapper;
import java.net.URL;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@Slf4j
class FluentMybatisDemoApplicationTests {

  @Autowired
  HelloWorldMapper mapper;

  @Autowired
  UserMapper userMapper;


  @Autowired
  ReceivingAddressMapper receivingAddressMapper;

  @Autowired
  UserDao userDao;

  private static final String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";

  @Test
  public void userTest() {
    User user = new User();
    user.setAvatar("value1");
    user.setAccount("value2");
    user.setPassword("123456");
    user.setUserName("admin");
    user.setBirthday(new Date());
    user.setEMail("123456@qq.com");
    user.setPhone("123456789");
    user.setBonusPoints(1000L);
    user.setStatus("online");
    user.setGmtCreate(new Date());
    user.setGmtModified(new Date());

    int i = userMapper.insert(user);
    System.out.println(i);

    List<User> users = userDao.selectUsers(1);
    System.out.println(users);
  }

  /**
   * 测试子查询
   */
  @Test
  public void subQuery() {
    ReceivingAddress address = new ReceivingAddress();
    address.setUserId(1L);
    address.setProvince("广东省");
    address.setCity("深圳市");
    address.setDistrict("南山区");
    address.setDetailAddress("粤海街道");
    address.setGmtCreate(new Date());
    address.setGmtModified(new Date());

    System.out.println(receivingAddressMapper.insert(address));
    List<ReceivingAddress> addresses = receivingAddressMapper
        .listEntity(receivingAddressMapper.query().where.apply("1=1").end());
    System.out.println(
    );

    List<User> userList = userDao.subQueryByAddress(addresses.get(0).getId());
    log.info("in 子查询：{}", userList);

    log.info("exists 子查询：{}", userDao.exists("粤海"));

  }

  /**
   * 自动生成entity、dao
   */
  @Test
  public void generate() {
    FileGenerator.build(Empty.class);
  }

  @Tables(
      url = url, username = "root", password = "123456",
      srcDir = "src/main/java/com/zwl/fluentmybatisdemo",
      basePack = "com.zwl.fluentmybatisdemo.base",
      daoDir = "src/main/java/com/zwl/fluentmybatisdemo",
      tables = {@Table(value = {"tb_login_history", "tb_user", "tb_user_info"})}
  )
  class Empty {

  }

  @Test
  void test() {
    /**
     * 为了演示方便，先删除数据
     */
    mapper.delete(mapper.query()
        .where.id().eq(1L).end());
    /**
     * 插入数据
     */
    HelloWorldEntity entity = new HelloWorldEntity();
    entity.setId(1L);
    entity.setSayHello("hello world");
    entity.setYourName("fluent mybatis");
    entity.setIsDeleted(false);
    mapper.insert(entity);
    /**
     * 查询 id = 1 的数据
     */
    HelloWorldEntity result1 = mapper.findOne(mapper.query()
        .where.id().eq(1L).end());
    /**
     * 控制台直接打印出查询结果
     */
    System.out.println("1. HelloWorldEntity:" + result1.toString());
    /**
     * 更新id = 1的记录
     */
    mapper.updateBy(mapper.updater()
        .update.sayHello().is("say hello, say hello!")
        .set.yourName().is("fluent mybatis is powerful!").end()
        .where.id().eq(1L).end()
    );
    /**
     * 查询 id = 1 的数据
     */
    HelloWorldEntity result2 = mapper.findOne(mapper.query()
        .where.sayHello().like("hello")
        .and.isDeleted().eq(false).end()
        .limit(1)
    );
    /**
     * 控制台直接打印出查询结果
     */
    System.out.println("2. HelloWorldEntity:" + result2.toString());

  }

}
