package org.glxy.sass.config;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class SaTokenHandlerConfig {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public Result<?> handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response){
        // 打印堆栈，以供调试
        log.error("exception handler ->", nle);

        // 判断场景值
        String message;
        switch (nle.getType()) {
            case NotLoginException.BE_REPLACED:
            case NotLoginException.KICK_OUT:
            case NotLoginException.NOT_TOKEN:
            case NotLoginException.INVALID_TOKEN:
            case NotLoginException.TOKEN_TIMEOUT:
            default:
                message = "401";
                break;
        }

        // 返回给前端
        return Result.error(message);
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {

        // 打印堆栈，以供调试
        log.error("exception handler ->", e);

        // 不同异常返回不同状态码
        ResponseEntity.BodyBuilder body = ResponseEntity.status(HttpStatus.FORBIDDEN);

        String message;
        if (e instanceof NotRoleException) {
            // 角色异常
            NotRoleException ee = (NotRoleException) e;
            message = "401";
        } else if (e instanceof NotPermissionException) {
            // 权限异常
            NotPermissionException ee = (NotPermissionException) e;
            message = "401";
        }  else {
            // 普通异常
            message = "401";
            body = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        // 返回给前端
        return body.body(message);
    }
}
