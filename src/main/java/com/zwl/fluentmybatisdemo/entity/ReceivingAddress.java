package com.zwl.fluentmybatisdemo.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户收货地址(ReceivingAddress)实体类
 *
 * @author makejava
 * @since 2021-06-09 18:00:03
 */
@FluentMybatis(table = "receiving_address")
@Data
public class ReceivingAddress implements IEntity {

  /**
   * 主键id
   */
  @TableId
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 省份
   */
  private String province;
  /**
   * 城市
   */
  private String city;
  /**
   * 区
   */
  private String district;
  /**
   * 详细住址
   */
  private String detailAddress;
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


}
