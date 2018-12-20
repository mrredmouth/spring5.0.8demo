package com.ccg.spring.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ccg.spring.base.utils.MySpringUtils;


/**
 * 用监听器实现spring容器在项目启动的时候初始化
 * @author Administrator
 *
 */
public class SpringContextListener implements ServletContextListener {

	private static final Log logger = LogFactory.getLog(SpringContextListener.class);


	/** tomcat上下文环境  */
	private ServletContext context = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("SpringContextListener监听器启动开始!");
		context = sce.getServletContext();
		
		// 初始化Spring上下文
		logger.debug("初始化Spring容器开始!");
		MySpringUtils.setSC(context);
		logger.debug("初始化Spring容器结束!");


		logger.debug("初始化应用程序变量开始!");
		initContextVar(context);
		logger.debug("初始化应用程序变量结束!");
		
		logger.debug("SpringContextListener监听器启动结束!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("SpringContextListener监听器关闭!");
	}


	/**
	 * 初始化应用环境变量
	 * @param cnt
	 */
	private void initContextVar(ServletContext cnt) {
		// 系统参数初始化
	}
}
