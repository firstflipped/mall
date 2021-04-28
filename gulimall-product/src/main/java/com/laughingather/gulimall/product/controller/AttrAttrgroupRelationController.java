package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import com.laughingather.gulimall.product.entity.query.AttrAttrGroupQuery;
import com.laughingather.gulimall.product.service.AttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 属性&属性分组关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @GetMapping("/list/attr/{attrGroupId}")
    public MyResult<List<AttrEntity>> listAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrEntity> attrEntities = attrAttrgroupRelationService.listAttrsByAttrGroupId(attrGroupId);
        return MyResult.success(attrEntities);
    }

    @GetMapping("/page/noAttr/{attrGroupId}")
    public MyResult<MyPage<AttrEntity>> pageNoAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId,
                                                                 @ModelAttribute AttrAttrGroupQuery attrAttrGroupQuery) {
        MyPage<AttrEntity> listNoAttrsByAttrGroupId = attrAttrgroupRelationService.pageNoAttrsByAttrGroupId(attrGroupId, attrAttrGroupQuery);
        return MyResult.success(listNoAttrsByAttrGroupId);
    }

    @PostMapping
    public MyResult saveBatchAttrAttrgroupRelation(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrgroupRelationService.saveBatchAttrAttrgroupRelation(attrGroupRelationDTOs);
        return MyResult.success();
    }

    @DeleteMapping("/byAttrIdAndAttrGroupId")
    public MyResult deleteAttrAttrgroupRelationByAttrIdAndAttrGroupId(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrgroupRelationService.deleteAttrAttrgroupRelationByAttrIdAndAttrGroupId(attrGroupRelationDTOs);
        return MyResult.success();
    }

}
