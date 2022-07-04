package com.sinosoft.baiduai.controller;

import com.sinosoft.baiduai.model.IdCardInfoModel;
import com.sinosoft.baiduai.service.BaiduAiService;
import com.sinosoft.baiduai.util.GlobalUtil;
import com.sinosoft.baiduai.util.ResultUtil;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 用于验证auth是否正确授权及应用程序是否正常运行
 *
 * @author hiYuzu
 * @version V1.0
 * @date 2022/5/16 15:22
 */
@RestController
@RequestMapping("/baiduai/idcard/Ipa7JtwL")
public class BaiduAiController {

    private static final Logger LOG = LoggerFactory.getLogger(BaiduAiController.class);

    @Resource
    private BaiduAiService baiduAiService;

    @PostMapping("parseIdCard")
    public ResultUtil<?> uploadIdCard(@RequestHeader(name = GlobalUtil.AUTH_HEADER) String auth, MultipartFile file) throws JSONException {
        if (!GlobalUtil.IIG_AUTH.equals(auth)) {
            String msg = "未授权访问！";
            LOG.warn(msg);
            return ResultUtil.error(msg);
        }
        try {
            byte[] data = file.getBytes();
            IdCardInfoModel idCardInfoModel = baiduAiService.getIdCardInfoEntity(data);
            LOG.info("接口被调用：" + idCardInfoModel.getName());
            return ResultUtil.ok(idCardInfoModel);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();
            LOG.error(msg);
            return ResultUtil.error(msg);
        }
    }

    /**
     * 用于验证auth是否正确授权
     *
     * @param auth RequestHeader 授权码
     * @return boolean auth是否正确授权
     */
    @GetMapping("/testAuth")
    public boolean isAlive(@RequestHeader(name = GlobalUtil.AUTH_HEADER) String auth) {
        return GlobalUtil.IIG_AUTH.equals(auth);
    }
}
