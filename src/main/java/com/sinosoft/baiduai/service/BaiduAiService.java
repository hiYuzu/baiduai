package com.sinosoft.baiduai.service;


import com.baidu.aip.ocr.AipOcr;
import com.sinosoft.baiduai.model.IdCardInfoModel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author hiYuzu
 * @version V1.0
 * @date 2022/5/16 17:46
 */


@Service
public class BaiduAiService {
    @Value("${baidu.appId}")
    public String appId;
    @Value("${baidu.apiKey}")
    public String apiKey;
    @Value("${baidu.secretKey}")
    public String secretKey;
    @Resource
    private AipOcr aipOcr;

    public IdCardInfoModel getIdCardInfoEntity(byte[] data) throws RuntimeException {
        // 调用接口
        JSONObject jsonObject = aipOcr.idcard(data, "front", null);
        String imageStatus = jsonObject.getString("image_status");
        switch (imageStatus) {
            case "normal": {
                //识别正常
                IdCardInfoModel idCardInfoModel = new IdCardInfoModel();
                JSONObject wordsResult = jsonObject.getJSONObject("words_result");
                String name = wordsResult.getJSONObject("姓名").getString("words");
                idCardInfoModel.setName(name);
                String address = wordsResult.getJSONObject("住址").getString("words");
                idCardInfoModel.setAddress(address);
                String idNumber = wordsResult.getJSONObject("公民身份号码").getString("words");
                idCardInfoModel.setIdNumber(idNumber);
                String birthday = wordsResult.getJSONObject("出生").getString("words");
                idCardInfoModel.setBirthday(birthday);
                String sex = wordsResult.getJSONObject("性别").getString("words");
                idCardInfoModel.setSex(sex);
                String nation = wordsResult.getJSONObject("民族").getString("words");
                idCardInfoModel.setNation(nation);
                return idCardInfoModel;
            }
            case "reversed_side": {
                throw new RuntimeException("未摆正身份证");
            }
            case "non_idcard": {
                throw new RuntimeException("上传的图片中不包含身份证");
            }
            case "blurred": {
                throw new RuntimeException("身份证模糊");
            }
            case "over_exposure": {
                throw new RuntimeException("身份证关键字段反光或过曝");
            }
            default:
                throw new RuntimeException("未知错误");
        }
    }
}


