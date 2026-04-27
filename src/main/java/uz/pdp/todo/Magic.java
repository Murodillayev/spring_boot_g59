package uz.pdp.todo;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Magic {

    @Before(value = "execution(* uz.pdp.todo.*.m1(String))")
    public void a1(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        String simpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();

        System.out.println("oldin ishladi: " + args[0].toString());
    }

    @After(value = "execution(String uz.pdp.todo.*.m1(String))")
    public void a2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("keyin ishladi: " + args[0].toString());
    }

    @AfterReturning(value = "execution(String uz.pdp.todo.*.m1(String))")
    public void a3(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("returndan keyin ishladi: " + args[0].toString());
    }

    @AfterThrowing(value = "execution(String uz.pdp.todo.*.m1(String))")
    public void a4(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Xato boldi: " + args[0].toString());
    }

    @Around(value = "execution(String uz.pdp.todo.*.m1(String))")
    public Object a5(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        log.info("Time: {} ms", System.currentTimeMillis() - start);
        return returnValue;
    }


}
