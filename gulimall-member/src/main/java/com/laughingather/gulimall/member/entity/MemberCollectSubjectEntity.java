package com.laughingather.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员收藏的专题活动
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@Data
@TableName("ums_member_collect_subject")
public class MemberCollectSubjectEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 活动专题id
     */
    private Long subjectId;

    /**
     * 活动专题名称
     */
    private String subjectName;

    /**
     * 活动专题图片
     */
    private String subjectImg;

    /**
     * 活动url
     */
    private String subjectUrl;

}
