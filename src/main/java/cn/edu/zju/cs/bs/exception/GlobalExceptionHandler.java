package cn.edu.zju.cs.bs.exception;

import cn.edu.zju.cs.bs.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        e.printStackTrace();
        return new Result(0, StringUtils.hasLength(e.getMessage())? e.getMessage() : "执行失败", null);
    }

}
