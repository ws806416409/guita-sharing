package com.lemon.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String author;

    private String name;

    @TableField("Img")
    private String img;


}
