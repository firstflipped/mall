package com.flipped.mall.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.flipped.mall.admin.entity.query.PlatformLogQuery;
import com.flipped.mall.admin.entity.vo.PlatformLogExcelVO;
import com.flipped.mall.admin.service.PlatformLogService;
import com.flipped.mall.common.entity.PlatformLogEntity;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 日志管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-24 21:29:30
 */
@RestController
@RequestMapping("/admin/log")
public class PlatformLogController {

    @Resource
    private PlatformLogService platformLogService;

    /**
     * 分页查询日志列表
     *
     * @param platformLogQuery 日志列表查询条件
     * @return MyResult<MyPage < PlatformLog>> 分页日志列表
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:log:view')")
    public MyResult<MyPage<PlatformLogEntity>> listPlatformLogsWithPage(@ModelAttribute PlatformLogQuery platformLogQuery) {
        MyPage<PlatformLogEntity> platformLogPage = platformLogService.listPlatformLogsWithPage(platformLogQuery);
        return MyResult.success(platformLogPage);
    }


    /**
     * 导出日志列表
     *
     * @param response         javax.servlet.http.HttpServletResponse
     * @param platformLogQuery 日志列表查询条件
     * @download 日志导出文件
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('admin:log:export')")
    public void exportPlatformLogs(HttpServletResponse response, @ModelAttribute PlatformLogQuery platformLogQuery) throws IOException {
        // 设置返回头信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");

        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = URLEncoder.encode("日志", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), PlatformLogExcelVO.class)
                .sheet("日志")
                .doWrite(platformLogService.listPlatformLogsWithExport(platformLogQuery));
    }


}
