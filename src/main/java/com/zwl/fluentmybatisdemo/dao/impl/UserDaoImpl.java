package com.zwl.fluentmybatisdemo.dao.impl;

import cn.org.atool.fluent.mybatis.If;
import com.zwl.fluentmybatisdemo.dao.UserDao;
import com.zwl.fluentmybatisdemo.dao.base.UserBaseDao;
import com.zwl.fluentmybatisdemo.entity.ReceivingAddress;
import com.zwl.fluentmybatisdemo.entity.User;
import com.zwl.fluentmybatisdemo.helper.UserWrapperHelper.QueryWhere;
import com.zwl.fluentmybatisdemo.wrapper.ReceivingAddressQuery;
import com.zwl.fluentmybatisdemo.wrapper.UserQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @author zhao_wei_long
 * @since 2021/6/10
 **/
@Repository
public class UserDaoImpl extends UserBaseDao implements UserDao {

  /**
   * 查询积分点数大于等于指定值，并且未逻辑删除的所有用户
   *
   * @param minBonusPoints 最少积分
   * @return 符合条件的用户列表
   */
  @Override
  public List<User> selectUsers(int minBonusPoints) {
    UserQuery query = new UserQuery()
        .where.bonusPoints().ge(minBonusPoints)
        .and.isDeleted().eq(false).end();
    return mapper.listEntity(query);
  }

  /**
   * @param minBonusPoints
   * @return
   */
  @Override
  public List<User> selectUserIds(int minBonusPoints) {
    return mapper.query().where.bonusPoints().ge(minBonusPoints).
        and.isDeleted().eq(false).end().execute(mapper::listEntity);
  }

  /**
   * @return
   */
  @Override
  public List<Map<String, Object>> selectAggregate() {
    UserQuery userQuery = this.query()
        .select.apply(fieldMeta -> "status".equals(fieldMeta.getColumn()))
        .bonusPoints().avg
        .bonusPoints().sum.end()
        .groupBy.status().end();

    return mapper.listMaps(userQuery);
  }

  /**
   * @param birthday
   * @param points
   * @param status
   * @return
   */
  @Override
  public List<User> findByBirthdayAndBonusPoints(Date birthday, Long points, String status) {
    UserQuery query = this
        .query()
        .selectAll()
        .where
        .birthday().eq(birthday)
        .and.bonusPoints().ge(points, If::notNull)
        .and.status().eq(status, If::notBlank).end();
    return mapper.listEntity(query);
  }

  @Override
  public List<User> subQuery(Serializable id) {
    UserQuery query = this.query()
        .select
        .end()
        .where
        .id().in(userQuery -> userQuery.selectId().where
            .id().eq(id).end())
        .and.userName().like("user").end();

    return mapper.listEntity(query);
  }

  @Override
  public List<User> subQueryByAddress(Serializable id) {
    UserQuery query = this.query().selectId()
        .where.addressId()
        .in(ReceivingAddressQuery.class,
            receivingAddressQuery -> receivingAddressQuery.selectId().where
                .id().eq(id).end())
        .end();
    return mapper.listEntity(query);
  }

  @Override
  public List<User> exists(String address) {
    UserQuery query = this.query()
        .where
        .exists(ReceivingAddressQuery.class, receivingAddressQuery -> receivingAddressQuery
            .where.detailAddress().like(address)
            .and.id().apply("=user.address_id").end()).end();

    return mapper.listEntity(query);
  }

  @Override
  public List<User> groupByUserName() {
    return mapper.listEntity(query().groupBy.userName().account().end());
  }

  @Override
  public List<User> having() {
    return mapper.listEntity(query().select
        .id().count.end()
        .where
        .status().eq("1").end()
        .groupBy.account().end()
        .having
        .count.id().gt(2).end());
  }


}
