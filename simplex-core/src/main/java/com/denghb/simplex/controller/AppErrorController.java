package com.denghb.simplex.controller;

import com.denghb.simplex.base.JSONModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public JSONModel error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return JSONModel.buildFailure(status.value(), status.getReasonPhrase());
    }

    /**
     * 全局错误处理
     */
    @ExceptionHandler
    @ResponseBody
    public JSONModel<String> error(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);

        HttpStatus status = getStatus(request);
        if (e instanceof MethodArgumentNotValidException) {
            // 进来了肯定有错
            MethodArgumentNotValidException mane = (MethodArgumentNotValidException) e;
            List<ObjectError> errors = mane.getBindingResult().getAllErrors();

            return JSONModel.buildFailure(status.value(), errors.get(0).getDefaultMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            return JSONModel.buildFailure(status.value(), "参数不匹配");
        }
        // 未知错误
        return JSONModel.buildFailure("failure");
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