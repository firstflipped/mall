package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.AttrEntity;
import com.laughingather.gulimall.product.entity.dto.AttrGroupRelationDTO;
import com.laughingather.gulimall.product.entity.query.AttrAttrGroupQuery;
import com.laughingather.gulimall.product.service.AttrAttrGroupRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "属性&属性分组关联模块")
public class AttrAttrGroupRelationController {

    @Resource
    private AttrAttrGroupRelationService attrAttrGroupRelationService;

    @GetMapping("/{attrGroupId}/attrs")
    @ApiOperation(value = "根据属性分组id查询关联的属性列表")
    @ApiImplicitParam(name = "attrGroupId", value = "属性分组id", dataTypeClass = Long.class)
    public MyResult<List<AttrEntity>> listAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId) {
        List<AttrEntity> attrEntities = attrAttrGroupRelationService.listAttrsByAttrGroupId(attrGroupId);
        return MyResult.success(attrEntities);
    }

    @GetMapping("/{attrGroupId}/page/no-attrs")
    @ApiOperation(value = "根据属性分组id分页查询未关联的属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attrGroupId", value = "属性分组id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "attrAttrGroupQuery", value = "属性过滤条件", dataTypeClass = AttrAttrGroupQuery.class)
    })
    public MyResult<MyPage<AttrEntity>> pageNoAttrsByAttrGroupId(@PathVariable("attrGroupId") Long attrGroupId,
                                                                 @ModelAttribute AttrAttrGroupQuery attrAttrGroupQuery) {
        MyPage<AttrEntity> listNoAttrsByAttrGroupId = attrAttrGroupRelationService.pageNoAttrsByAttrGroupId(attrGroupId, attrAttrGroupQuery);
        return MyResult.success(listNoAttrsByAttrGroupId);
    }

    @PostMapping
    @ApiOperation(value = "批量保存属性&属性分组关联关系")
    public MyResult saveBatchAttrAttrGroupRelation(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrGroupRelationService.saveBatchAttrAttrGroupRelation(attrGroupRelationDTOs);
        return MyResult.success();
    }

    @DeleteMapping("/aid-and-gid")
    @ApiOperation(value = "根据属性id和属性分组id批量删除属性&属性分组关联关系")
    public MyResult deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(@RequestBody AttrGroupRelationDTO[] attrGroupRelationDTOs) {
        attrAttrGroupRelationService.deleteAttrAttrGroupRelationByAttrIdAndAttrGroupId(attrGroupRelationDTOs);
        return MyResult.success();
    }

}
