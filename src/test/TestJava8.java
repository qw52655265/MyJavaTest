package test;

import java.util.Arrays;

/**
 * JAVA8 新特性测试
 * @author yang
 * @date 2018年4月3日 上午10:21:06
 * @since 1.0
 */
public class TestJava8 {
	
	public static void main(String[] args) {
		testArrayForEach();
	}
	
	/**
	 * java8 ForEach
	 * 
	 * @author yang
	 * @date 2018年4月3日 上午10:21:11
	 */
	public static void testArrayForEach() {
		Arrays.asList(1,2,3,4,5).forEach(item -> {
			System.out.println(item);
		});
	}

}
