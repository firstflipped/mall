package com.flipped.mall.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 采购信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wms_purchase")
public class PurchaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 采购单id
     */
    @TableId
    private Long id;

    /**
     * 采购人id
     */
    private Long assigneeId;

    /**
     * 采购人姓名
     */
    private String assigneeName;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 总金额
     */
    private BigDecimal amount;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 更新日期
     */
    private LocalDateTime updateTime;

}
