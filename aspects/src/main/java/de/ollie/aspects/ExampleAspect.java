package de.ollie.aspects;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExampleAspect {

	@PostConstruct
	public void postConstruct() {
		System.out.println("Constructed!");
	}

	@Before(value = "execution(* de.ollie.aspects.*.*(..))")
	public void before(JoinPoint joinPoint) {
		System.out.println("done something before excuting: " + joinPoint.getSignature());
	}

	@Around("@annotation(de.ollie.aspects.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("started");
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
		return proceed;
	}

}