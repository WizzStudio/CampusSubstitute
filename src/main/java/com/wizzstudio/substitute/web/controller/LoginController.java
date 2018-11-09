package com.wizzstudio.substitute.web.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.wizzstudio.substitute.constants.Constant;
import com.wizzstudio.substitute.dto.WxInfo;
import com.wizzstudio.substitute.enums.GenderEnum;
import com.wizzstudio.substitute.pojo.User;
import com.wizzstudio.substitute.util.CookieUtil;
import com.wizzstudio.substitute.util.RandomUtil;
import com.wizzstudio.substitute.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Controller
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private WxMaService wxService;

    /**
     * 用户注册
     * @param loginData
     * @return
     */
    @PostMapping("/user/login")
    public ResponseEntity login(@NotNull @RequestBody WxInfo loginData, HttpServletResponse response) {
        try {
            WxMaJscode2SessionResult sessionResult = wxService.getUserService().getSessionInfo(loginData.getCode());
            User user = userService.findUserByOpenId(sessionResult.getOpenid());
            if (user == null) {
                WxMaUserInfo wxUserInfo = wxService.getUserService().getUserInfo(sessionResult.getSessionKey(), loginData.getEncryptedData(), loginData.getIv());
                user = User.newBuilder()
                        .setId(RandomUtil.getSixRandom())
                        .setUserName(wxUserInfo.getNickName())
                        .setOpenid(wxUserInfo.getOpenId())
                        .setAvatar(wxUserInfo.getAvatarUrl())
                        .build();
                switch (Integer.valueOf(wxUserInfo.getGender())){
                    //性别 0：未知、1：男、2：女
                    case 0:
                        user.setGender(GenderEnum.NO_LIMITED);
                        break;
                    case 1:
                        user.setGender(GenderEnum.MALE);
                        break;
                    case 2:
                        user.setGender(GenderEnum.FEMALE);
                        break;
                    default:
                        return ResultUtil.error("用户信息有误");
                }
                userService.addNewUser(user);
                String cookie = CookieUtil.tokenGenerate();
                redisUtil.storeNewCookie(cookie, user.getId());
                CookieUtil.setCookie(response, Constant.TOKEN, cookie, Constant.TOKEN_EXPIRED);
                log.info("Add a new account for " + user.getOpenid());
            }
            return ResultUtil.success(user);
        } catch (WxErrorException e) {
            return ResultUtil.error();
        }
    }

}