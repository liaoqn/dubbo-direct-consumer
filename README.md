# dubbo-direct-consumer
客户端直连 Dubbo 服务，测试使用，或者用来验证线上服务。

使用方式
=====
具体步骤如下：

1、继承基类 BaseDubboDirectConsumer<T>

	public class DemoServiceDirectConsumer extends BaseDubboDirectConsumer<DemoService> {

	}

2、实现 getUrl() 方法。该方法需要返回 Dubbo 服务的实际地址，注意不是注册中心（Zookeeper）地址

	@Override
	protected String getUrl() {
		// dubbo服务地址
		return "dubbo://127.0.0.1:20880/com.alibaba.dubbo.demo.DemoService";
	}

3、通过父类中的 service 变量得到需要调用的 Dubbo 服务，通过 service 变量直接调用服务方法

	public void sayHelloTest() {
		// 直接通过父类中的 service 变量得到 DemoService 服务
		String hello = service.sayHello("world"); // call remote method
	}

具体可以参考[https://github.com/liaoqn/dubbo-direct-consumer/blob/master/src/test/java/com/github/dubbo/direct/DemoServiceDirectConsumer.java](https://github.com/liaoqn/dubbo-direct-consumer/blob/master/src/test/java/com/github/dubbo/direct/DemoServiceDirectConsumer.java "DemoServiceDirectConsumer.java")


