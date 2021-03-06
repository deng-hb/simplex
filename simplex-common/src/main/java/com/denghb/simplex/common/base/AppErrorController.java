package com.denghb.simplex.common.base;

import com.denghb.simplex.common.base.BizException;
import com.denghb.simplex.common.base.JSONModel;
import com.denghb.simplex.common.base.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@ControllerAdvice
@ApiIgnore
public class AppErrorController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    /**
     * Supports the HTML Error View
     */
    //@RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView("error");
    }

    /**
     * Supports other formats like JSON, XML
     */
    @RequestMapping(name = "系统异常", value = ERROR_PATH)
    @ResponseBody
    public JSONModel error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return JSONModel.buildFailure(status.getReasonPhrase());
    }

    /**
     * 全局错误处理
     */
    @ExceptionHandler
    @ResponseBody
    public JSONModel<String> error(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);

        if (e instanceof BizException) {
            return JSONModel.buildFailure(e.getMessage());
        } else if (e instanceof AuthException) {
            return JSONModel.buildFailure(2, e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 进来了肯定有错
            MethodArgumentNotValidException mane = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = mane.getBindingResult().getAllErrors();

            return JSONModel.buildFailure(errors.get(0).getDefaultMessage());
        } else if (e instanceof IllegalArgumentException) {
            return JSONModel.buildFailure("参数异常");
        } else if (e instanceof HttpMessageNotReadableException || e instanceof MissingServletRequestParameterException) {
            return JSONModel.buildFailure("参数不匹配");
        } else if (e instanceof HttpRequestMethodNotSupportedException || e instanceof HttpMediaTypeNotSupportedException) {
            return JSONModel.buildFailure("请求方法错误");
        } else if (e instanceof DuplicateKeyException) {
            return JSONModel.buildFailure("请求数据重复");
        } else if (e instanceof BadSqlGrammarException) {
            return JSONModel.buildFailure("数据库脚本错误");
        } else if (e instanceof NullPointerException) {
            return JSONModel.buildFailure("空异常");
        }
        // 未知错误
        return JSONModel.buildFailure("服务器忙，请稍后重试");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}