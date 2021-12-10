package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import com.laughingather.gulimall.product.entity.query.AttrAttrGroupQuery;
import com.laughingather.gulimall.product.service.AttrAttrGroupRelationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 属性&属性分组关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/attr-attr-group-relation")
public class AttrAttrGroupRelationController {

    @Resource
    private AttrAttrGroupRelationService attrAttrGroupRelationService;

    @GetMapping("/list/attr/{attrGroupId}")
    public MyResult<List<AttrEntity>> listAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrEntity> attrEntities = attrAttrGroupRelationService.listAttrsByAttrGroupId(attrGroupId);
        return MyResult.success(attrEntities);
    }

    @GetMapping("/page/no-attr/{attrGroupId}")
    public MyResult<MyPage<AttrEntity>> pageNoAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId,
                                                                 @ModelAttribute AttrAttrGroupQuery attrAttrGroupQuery) {
        MyPage<AttrEntity> listNoAttrsByAttrGroupId = attrAttrGroupRelationService.pageNoAttrsByAttrGroupId(attrGroupId, attrAttrGroupQuery);
        return MyResult.success(listNoAttrsByAttrGroupId);
    }

    @PostMapping
    public MyResult saveBatchAttrAttrGroupRelation(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrGroupRelationService.saveBatchAttrAttrGroupRelation(attrGroupRelationDTOs);
        return MyResult.success();
    }

    @DeleteMapping("/aid-and-gid")
    public MyResult deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrGroupRelationService.deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(attrGroupRelationDTOs);
        return MyResult.success();
    }

}
