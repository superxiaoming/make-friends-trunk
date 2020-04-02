//package com.example.makefriends.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * @program: visualization
// * @description:
// * @author: YinShm
// * @date: 2019-12-10 22:31
// **/
//
//@Component
//@Aspect
//public class MyAop {
//    @Pointcut("execution(* com.example.makefriends.controller.*.*(..))")
//    public void pc1(){}
//
//    @Before(value = "pc1()")
//    public void before(JoinPoint joinPoint){
//        System.out.println(joinPoint.getSignature().getName() + "方法开始执行...");
//    }
//
//    @After(value = "pc1()")
//    public void after(JoinPoint joinPoint){
//        System.out.println(joinPoint.getSignature().getName() + "方法执行完毕...");
//    }
//
//    @AfterReturning(value = "pc1()", returning = "result")
//    public void afterReturning(JoinPoint joinPoint, Object result){
//        System.out.println(joinPoint.getSignature().getName() + "方法返回值是： " + result);
//    }
//
//    @AfterThrowing(value = "pc1()", throwing = "e")
//    public void afterThrowing(JoinPoint joinPoint, Exception e){
//        System.out.println(joinPoint.getSignature().getName() + "方法抛异常，异常是： " + e);
//    }
//
//    @Around("pc1()")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        return proceedingJoinPoint.proceed();
//    }
//}
