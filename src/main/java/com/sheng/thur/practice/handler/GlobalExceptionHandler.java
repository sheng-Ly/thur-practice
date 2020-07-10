//package com.sheng.thur.practice.handler;
//
//import com.sheng.thur.practice.domain.vo.ResultVo;
//import com.sheng.thur.practice.exception.DaoException;
//import com.sheng.thur.practice.exception.ServiceException;
//import com.sheng.thur.practice.util.StatusCodeEnum;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * Description：@RestControllerAdvice 开启全局异常处理
// *
// * @author sheng
// * @date 2020/7/9 20:41
// * @since JDK 1.8
// */
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    /**
//     * 对于 dao 层异常的处理
//     *
//     * @param e dao 层的异常
//     * @return 异常信息
//     */
//    @ExceptionHandler(DaoException.class)
//    public ResultVo<Object> daoExceptionHandler(DaoException e) {
//        return ResultVo.error(StatusCodeEnum.DAO_ERROR);
//    }
//
//    /**
//     * 对于 service 层异常的处理
//     *
//     * @param e service 层的异常
//     * @return 异常信息
//     */
//    @ExceptionHandler(ServiceException.class)
//    public ResultVo<Object> serviceExceptionHandler(ServiceException e) {
//        return ResultVo.error(StatusCodeEnum.SERVICE_ERROR);
//    }
//
//    /**
//     * 未知异常的处理
//     *
//     * @param e 未知异常
//     * @return 异常信息
//     */
//    @ExceptionHandler(Exception.class)
//    public ResultVo<Object> exceptionHandler(Exception e) {
//        return ResultVo.error(StatusCodeEnum.ERROR);
//    }
//}
