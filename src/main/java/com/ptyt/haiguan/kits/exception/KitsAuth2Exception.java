package com.ptyt.haiguan.kits.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author: yq
 * @date: 2019/11/15 11:22
 * @description:
 */
@JsonSerialize(using = KitsAuth2ExceptionSerializer.class)
public class KitsAuth2Exception extends OAuth2Exception {
    @Getter
    private String errorCode;

    public KitsAuth2Exception(String msg) {
        super(msg);
    }

    public KitsAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}