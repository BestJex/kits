package com.ptyt.haiguan.kits.controller;

import cn.hutool.core.util.StrUtil;
import com.ptyt.haiguan.kits.vo.R;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yq
 * @date: 2019/11/18 14:26
 * @description:
 */
@RestController
@RequestMapping("/token")
public class KitsEndpoint {

    /**
     * 退出token
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public R logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StrUtil.isBlank(authHeader)) {
            return R.ok(Boolean.FALSE, "退出失败，token 为空");
        }

//        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
//        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
//        if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
//            return R.ok(Boolean.TRUE, "退出失败，token 无效");
//        }
//
//        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
//
//        // 清空access token
//        tokenStore.removeAccessToken(accessToken);
//
//        // 清空 refresh token
//        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//        tokenStore.removeRefreshToken(refreshToken);
        return R.ok(Boolean.TRUE);
    }
}
