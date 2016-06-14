package com.lmiky.platform.database.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源切片
 *
 * @author lmiky
 * @date 2015年9月7日 下午3:25:54
 */
public class DateSourceAspect {
    /**
     * 缓存
     */
    private static ConcurrentHashMap<String, Boolean> methodIsReadCache = new ConcurrentHashMap<>();

    /**
     * 决策是否只读
     *
     * @param pjp 织入点
     * @return 方法执行结果
     * @throws Throwable
     * @author lmiky
     * @date 2015年9月7日 下午3:45:27
     */
    public Object determineReadOrWriteDB(ProceedingJoinPoint pjp) throws Throwable {
        if (isChoiceReadDB(pjp)) {
            DynamicDataSourceHolder.markRead();
        } else {
            DynamicDataSourceHolder.markWrite();
        }
        try {
            return pjp.proceed();
        } finally {
            DynamicDataSourceHolder.reset();
        }
    }

    /**
     * 判断是否只读方法
     *
     * @param method 织入点
     * @return 当前方法是否只读
     * @author lmiky
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @date 2015年9月7日 下午3:45:10
     */
    private boolean isChoiceReadDB(ProceedingJoinPoint pjp) throws NoSuchMethodException, SecurityException {
    	// 如果之前选择了写库，则现在还选择写库
        if (DynamicDataSourceHolder.isChoiceWrite()) {
            return false;
        }
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Object target = pjp.getTarget();
        String cacheKey = target.getClass().getName() + "." + method.getName();
        Boolean isReadCacheValue = methodIsReadCache.get(cacheKey);
        if (isReadCacheValue != null) {
            return isReadCacheValue.booleanValue();
        }
        Method realMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        Transactional transactionalAnno = AnnotationUtils.findAnnotation(realMethod, Transactional.class);
        boolean isRead = false;
        if (transactionalAnno == null) {
        	isRead = true;
        }else if (transactionalAnno.readOnly()) {
        	isRead = true;
        } else {
        	isRead = false;
        }
        methodIsReadCache.put(cacheKey, isRead);
        return isRead;
    }
}
