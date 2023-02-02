package com.laughingather.gulimall.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.laughingather.gulimall.admin.entity.query.PlatformLogQuery;
import com.laughingather.gulimall.admin.entity.vo.PlatformLogExcelVO;
import com.laughingather.gulimall.admin.service.PlatformLogService;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.common.entity.PlatformLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * 平台日志路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-24 21:29:30
 */
@RestController
@RequestMapping("/admin/log")
@Tag(name = "日志管理模块")
public class PlatformLogController {

    @Resource
    private PlatformLogService platformLogService;

    @GetMapping("/page")
    @Operation(summary = "分页查询日志列表")
    @PreAuthorize("hasAuthority('admin:log:view')")
    public MyResult<MyPage<PlatformLog>> listPlatformLogsWithPage(@ModelAttribute PlatformLogQuery platformLogQuery) {
        MyPage<PlatformLog> platformLogPage = platformLogService.listPlatformLogsWithPage(platformLogQuery);
        return MyResult.success(platformLogPage);
    }


    @GetMapping("/export")
    @Operation(summary = "导出日志文件")
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
