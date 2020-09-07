package com.fxtahe.fxblog.config.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxtahe.fxblog.config.annotation.OperationLog;
import com.fxtahe.fxblog.entity.LogInfo;
import com.fxtahe.fxblog.security.UserAuthenticationHelper;
import com.fxtahe.fxblog.service.OperationLogService;
import com.fxtahe.fxblog.util.Const;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志切面处理类
 * </p>
 *
 * @author fxtahe
 * @since 2020/8/19
 */
@Aspect
@Component
public class OperationLogAop {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private OperationLogService operationLogService;

    /**
     * 定义以{@link com.fxtahe.fxblog.config.annotation.OperationLog} 注解切面
     */
    @Pointcut("@annotation(com.fxtahe.fxblog.config.annotation.OperationLog)")
    public void OperationLogCut() {
    }


    /**
     * 定义所有{@link org.springframework.stereotype.Controller} 注解异常切面
     */
    @Pointcut("execution(public * com.fxtahe.fxblog.controller.*.*(..))")
    public void OperationExceptionLogCut() {
    }

    @AfterReturning(value = "OperationLogCut()", returning = "ret")
    public void saveOperationLog(JoinPoint joinPoint, Object ret) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);


        try {

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog operationLog = method.getAnnotation(OperationLog.class);

            String methodName = method.getName();
            String className = joinPoint.getTarget().getClass().getName();
            String requestURI = request.getRequestURI();

            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> paraMap = parameterMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()[0]));

            LogInfo logInfo = new LogInfo();
            logInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logInfo.setRequestIp(getIpAddr(request));
            logInfo.setLogLevel(Const.LOG_LEVEL_INFO);
            if (operationLog != null) {
                logInfo.setOperationDesc(operationLog.operationDesc());
                logInfo.setOperationModule(operationLog.operationModule());
                logInfo.setOperationType(operationLog.operationType());
            }
            logInfo.setRequestParameter(objectMapper.writeValueAsString(paraMap));
            logInfo.setRequestResult(objectMapper.writeValueAsString(ret));
            logInfo.setUserId(UserAuthenticationHelper.getCurrentUserId());
            logInfo.setUserName(UserAuthenticationHelper.getCurrentUserName());
            logInfo.setUserType(UserAuthenticationHelper.getCurrentUserAuthorities().contains(Const.ADMIN_AUTH) ? Const.ADMIN_AUTH : Const.AUTHOR_TYPE);
            logInfo.setRequestUri(requestURI);
            logInfo.setRequestMethod(className.concat(".").concat(methodName));
            logInfo.setCreateDate(LocalDateTime.now());

            operationLogService.save(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterThrowing(pointcut = "OperationExceptionLogCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);


        try {

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationLog operationLog = method.getAnnotation(OperationLog.class);

            String methodName = method.getName();
            String className = joinPoint.getTarget().getClass().getName();
            String requestURI = request.getRequestURI();


            LogInfo logInfo = new LogInfo();
            logInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            logInfo.setRequestIp(getIpAddr(request));
            logInfo.setLogLevel(Const.LOG_LEVEL_ERROR);

            if (operationLog != null) {
                logInfo.setOperationDesc(operationLog.operationDesc());
                logInfo.setOperationModule(operationLog.operationModule());
                logInfo.setOperationType(operationLog.operationType());
            }

            Object currentPrincipal = UserAuthenticationHelper.getCurrentPrincipal();
            if(!"anonymousUser".equals(currentPrincipal)){
                logInfo.setUserId(UserAuthenticationHelper.getCurrentUserId());
                logInfo.setUserName(UserAuthenticationHelper.getCurrentUserName());
                logInfo.setUserType(UserAuthenticationHelper.getCurrentUserAuthorities().contains(Const.ADMIN_AUTH) ? Const.ADMIN_AUTH : Const.AUTHOR_TYPE);
            }else{
                logInfo.setUserName(Const.GUEST);
                logInfo.setUserType(Const.GUEST);
            }

            logInfo.setExcName(e.getClass().getName());
            logInfo.setExcMessage(e.getClass().getName().concat(":")
                    .concat(e.getMessage()).concat("\n\t")
                    .concat(Arrays.stream(e.getStackTrace())
                            .map(StackTraceElement::toString).collect(Collectors.joining("\n"))));
            logInfo.setRequestUri(requestURI);
            logInfo.setRequestMethod(className.concat(".").concat(methodName));
            logInfo.setCreateDate(LocalDateTime.now());

            operationLogService.save(logInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String localIp = "127.0.0.1";
            String localIpv6 = "0:0:0:0:0:0:0:1";
            if (ipAddress.equals(localIp) || ipAddress.equals(localIpv6)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        String ipSeparate = ",";
        int ipLength = 15;
        if (ipAddress != null && ipAddress.length() > ipLength) {
            if (ipAddress.indexOf(ipSeparate) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(ipSeparate));
            }
        }
        return ipAddress;
    }
}
