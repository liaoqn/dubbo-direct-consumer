package com.github.dubbo.direct;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.dubbo.demo.DemoService;
import com.github.dubbo.direct.BaseDubboDirectConsumer;

/**
 * 
 * 直连DemoService服务测试类
 * @author liaoqn
 *
 */
public class DemoServiceDirectConsumer extends BaseDubboDirectConsumer<DemoService> {

	@Test
	public void sayHelloTest() {
		// 直接通过父类中的 service 变量得到 DemoService 服务
		String hello = service.sayHello("world"); // call remote method
        System.out.println("DemoService Response: " + hello); // get result
        Assert.assertTrue(hello.startsWith("Hello world"));
	}
	
	@Override
	protected String getUrl() {
		// dubbo服务地址
		return "dubbo://127.0.0.1:20880/com.alibaba.dubbo.demo.DemoService";
	}
	
}
