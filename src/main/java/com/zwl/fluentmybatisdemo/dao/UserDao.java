package com.zwl.fluentmybatisdemo.dao;

import com.zwl.fluentmybatisdemo.entity.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhao_wei_long
 * @since 2021/6/10
 **/
public interface UserDao {

  List<User> selectUsers(int minBonusPoints);

  List<User> selectUserIds(int minBonusPoints);

  List<Map<String, Object>> selectAggregate();

  List<User> findByBirthdayAndBonusPoints(Date birthday, Long points, String status);

  List<User> subQuery(Serializable id);

  List<User> subQueryByAddress(Serializable id);

  List<User> exists(String address);

  List<User> groupByUserName();

  List<User> having();
}
