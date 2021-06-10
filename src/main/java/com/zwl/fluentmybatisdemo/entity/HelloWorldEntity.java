package com.zwl.fluentmybatisdemo.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.IEntity;
import java.sql.Date;
import lombok.Data;

/**
 * @author zhao_wei_long
 * @since 2021/6/9
 **/
@FluentMybatis(table = "hello_world")
@Data
public class HelloWorldEntity implements IEntity {

  @TableId()
  private Long id;

  private String sayHello;

  private String yourName;

  @TableField(insert = "now()", update = "now()")
  private Date gmtCreate;

  @TableField(update = "now()")
  private Date gmtModified;

  @TableField(insert = "0")
  private Boolean isDeleted;
}
