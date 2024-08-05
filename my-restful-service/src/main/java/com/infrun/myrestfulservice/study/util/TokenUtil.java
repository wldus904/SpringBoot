package com.infrun.myrestfulservice.study.util;

import com.infrun.myrestfulservice.study.constant.AuthConstant;
import io.jsonwebtoken.lang.Strings;
import org.apache.commons.lang3.StringUtils;

public class TokenUtil {
    public static String parsingToken(String authHeader) {
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(AuthConstant.TOKEN_TYPE)) {
            return Strings.EMPTY;
        }
        String[] tokenSplit = authHeader.split(" ");
        return tokenSplit[1];
    }
}
