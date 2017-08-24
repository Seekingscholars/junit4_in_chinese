
package com.github.program_in_chinese.junit4_in_chinese;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test.None;

/**
 * {@link org.junit.Test} 的中文等价替换.
 *
 * @author Azige
 */
// TODO: 翻译 org.junit.Test 的参考文档？
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface 测试 {

  /**
   * 
   * @return
   */
  Class<? extends Throwable> 期望异常() default None.class;

  /**
   * 
   * @return
   */
  long 超时() default 0L;
}
