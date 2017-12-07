package com.github.dubbo.direct;

import java.lang.reflect.ParameterizedType;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;

/**
 * 
 * 客户端直连dubbo服务<br/>
 * 测试使用，或者方便验证线上服务
 * 
 * @author liaoqn
 *
 */
public abstract class BaseDubboDirectConsumer<T> {
	private static ApplicationConfig application;
	static {
		// 当前应用配置
		application = new ApplicationConfig();
		application.setName("DubboConsumerTest");
	}

	protected T service = service();

	/**
	 * 返回直连dubbo服务
	 * 
	 * @param clazz
	 * @return
	 */
	protected T service(Class<T> clazz) {
		// 引用远程服务
		// 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		ReferenceConfig<T> reference = new ReferenceConfig<T>();
		reference.setApplication(application);
		String url = getUrl(); // 直连dubbo服务的URL
		// 如果点对点直连，可以用reference.setUrl()指定目标地址，设置url后将绕过注册中心，
		// 其中，协议对应provider.setProtocol()的值，端口对应provider.setPort()的值，
		// 路径对应service.setPath()的值，如果未设置path，缺省path为接口名
		reference.setUrl(url);

		reference.setInterface(clazz);

		return reference.get();
	}

	/**
	 * 返回直连dubbo服务
	 * 
	 * @return
	 */
	protected T service() {
		// 变相获取 T.class
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		return service(entityClass);
	}

	/**
	 * 获取直连dubbo服务的URL, dubbo://10.20.130.230:20880/com.xxx.XxxService
	 * 
	 * @return
	 */
	protected abstract String getUrl();

	public T getService() {
		return service;
	}
}
