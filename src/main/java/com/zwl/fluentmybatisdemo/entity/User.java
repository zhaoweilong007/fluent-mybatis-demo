package com.zwl.fluentmybatisdemo.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2021-06-09 18:00:03
 */
@FluentMybatis(table = "user")
@Data
public class User implements IEntity {

  /**
   * 主键id
   */
  @TableId
  private Long id;
  /**
   * 头像
   */
  private String avatar;
  /**
   * 账号
   */
  private String account;
  /**
   * 密码
   */
  private String password;
  /**
   * 名字
   */
  private String userName;
  /**
   * 生日
   */
  private Date birthday;
  /**
   * 电子邮件
   */
  private String eMail;
  /**
   * 电话
   */
  private String phone;
  /**
   * 会员积分
   */
  private Long bonusPoints;
  /**
   * 状态(字典)
   */
  private String status;
  /**
   * 创建时间
   */
  private Date gmtCreate;
  /**
   * 更新时间
   */
  private Date gmtModified;
  /**
   * 是否逻辑删除
   */
  private Integer isDeleted;


  private Long addressId;

}
