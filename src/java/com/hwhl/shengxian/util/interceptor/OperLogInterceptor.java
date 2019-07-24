/*
package com.hwhl.shengxian.util.interceptor;

import com.hwhl.shengxian.util.annotation.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * Description: 拦截器
 *
 * @Author: yang
 * @Date: 2018/7/26
 * @Version: 1.0
 *//*

@Aspect
@Component
public class OperLogInterceptor {

    private HttpServletRequest request = null;


    //这里写的为环绕触发 ，可自行根据业务场景选择@Before @After
    //触发条件为：com.hwhl.shengxian包下面所有类且注解为OperLog的
    @Around("within(com.hwhl.shengxian..*) && @annotation(operLog)")
    public Object doAroundMethod(ProceedingJoinPoint pjd, SystemLog operLog) throws Throwable {
        long startTime=System.currentTimeMillis();//开始时间

        Object[] params = pjd.getArgs();//获取请求参数
        System.out.println("监听到传入参数为:");
        for(Object param:params) {
            System.out.println(param);
        }
        //###################上面代码为方法执行前#####################
        Object result  = pjd.proceed();//执行方法，获取返回参数
        //###################下面代码为方法执行后#####################
        System.out.println("返回参数为:" + result);

        String user = operLog.userIndex()==-1?operLog.user():(String)params[operLog.userIndex()];//操作人


        String operType = operLog.operType();//操作类型
        System.out.println("操作人: " + user +" 操作类型: " + operType);

        long endTime=System.currentTimeMillis();//结束时间
        float excTime=(float)(endTime-startTime)/1000;
        System.out.println("执行时间:"+excTime+"s");
        System.out.println("#######################分隔符##########################");
        return result;

    }





    */
/**
     * @Description: 获取request
     * @author fei.lei
     * @date 2016年11月23日 下午5:10
     * @param
     * @return HttpServletRequest
     *//*

    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

}
*/
