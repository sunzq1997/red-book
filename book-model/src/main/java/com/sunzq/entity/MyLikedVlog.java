package com.sunzq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 点赞短视频关联表

 * </p>
 *
 * @author sunzq
 * @since 2022-02-23
 */
@Getter
@Setter
@TableName("my_liked_vlog")
public class MyLikedVlog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 喜欢的短视频id
     */
    private String vlogId;


}
