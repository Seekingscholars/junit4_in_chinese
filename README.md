[![Build Status](https://travis-ci.org/program-in-chinese/junit4_in_chinese.svg?branch=master)](https://travis-ci.org/program-in-chinese/junit4_in_chinese)

# junit4_in_chinese
JUnit4中文化. 起源: https://github.com/program-in-chinese/overview/issues/10

已公布到Maven库, 在pom.xml添加:
```
<dependency>
  <groupId>com.github.program_in_chinese</groupId>
  <artifactId>junit4_in_chinese</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
中英对应表:

| 英文 | 中文 | 
| ------------- | ------------- |
| Assert.assertEquals | 断言.相等 |
| Assert.assertNotEquals | 断言.不等 |
| Assert.assertTrue | 断言.为真 |
| Assert.assertFalse | 断言.为假 |
| Assert.fail | 断言.失败 |
| @Test.expected | @测试.期望异常 |
| @Test.timeout | @测试.超时 |

当 @Test 与 @测试 同时声明时，仅 @测试 有效.
