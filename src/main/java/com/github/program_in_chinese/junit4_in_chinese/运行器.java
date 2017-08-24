
package com.github.program_in_chinese.junit4_in_chinese;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.Test.None;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * 扩展 BlockJUnit4ClassRunner 令其能解析 {@link com.github.program_in_chinese.junit4_in_chinese.测试}.
 * 当 @Test 与 @测试 同时声明时，仅 @测试 有效.
 *
 * @author Azige
 */
public class 运行器 extends BlockJUnit4ClassRunner {

  public 运行器(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected List<FrameworkMethod> computeTestMethods() {
    Set<FrameworkMethod> collectedMethods = new HashSet<FrameworkMethod>();
    collectedMethods.addAll(getTestClass().getAnnotatedMethods(Test.class));
    collectedMethods.addAll(getTestClass().getAnnotatedMethods(测试.class));
    return Collections.unmodifiableList(new ArrayList<FrameworkMethod>(collectedMethods));
  }

  @Override
  protected void validateTestMethods(List<Throwable> errors) {
    validatePublicVoidNoArgMethods(Test.class, false, errors);
    validatePublicVoidNoArgMethods(测试.class, false, errors);
  }

  @Override
  protected Statement possiblyExpectingExceptions(FrameworkMethod method, Object test,
      Statement next) {
    Class<? extends Throwable> 期待异常类 = getExpectedException(method);
    return 期待异常类 != null ? new ExpectException(next, 期待异常类) : next;
  }

  @Override
  protected Statement withPotentialTimeout(FrameworkMethod method, Object test, Statement next) {
    long timeout = getTimeout(method);
    if (timeout <= 0) {
      return next;
    }
    return FailOnTimeout.builder().withTimeout(timeout, TimeUnit.MILLISECONDS).build(next);
  }

  private Class<? extends Throwable> getExpectedException(FrameworkMethod method) {
    测试 测试注解 = method.getAnnotation(测试.class);
    // 在测试注解存在时完全忽略Test
    if (测试注解 != null) {
      return 测试注解.期望异常() != None.class ? 测试注解.期望异常() : null;
    }

    Test test注解 = method.getAnnotation(Test.class);
    return test注解 != null && test注解.expected() != None.class ? test注解.expected() : null;
  }

  private long getTimeout(FrameworkMethod method) {
    测试 测试注解 = method.getAnnotation(测试.class);
    // 在测试注解存在时完全忽略Test
    if (测试注解 != null) {
      return 测试注解.期望异常() != None.class ? 测试注解.超时() : 0;
    }

    Test test注解 = method.getAnnotation(Test.class);
    return test注解 != null && test注解.expected() != None.class ? test注解.timeout() : 0;
  }
}
