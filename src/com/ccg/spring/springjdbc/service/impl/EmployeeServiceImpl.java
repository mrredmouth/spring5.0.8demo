package com.ccg.spring.springjdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccg.spring.springjdbc.dao.EmployeeDao;
import com.ccg.spring.springjdbc.exception.EmployeeException;
import com.ccg.spring.springjdbc.service.EmployeeService;


@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * 添加事务：则本事务里面的每一步jdbc操作出现异常，则全部回滚。
	 * 	1、propagation:事务的传播行为，默认REQUIRED，定义了7种,常用两种：
	 * 		propagation=Propagation.REQUIRED (默认)：
	 * 			使用调用者的事务，本事务1,2,3中，其中一个事务回滚了,则停止,
	 * 			调用者的事务回滚,之前成功了的事务也回滚。
	 * 		propagation=Propagation.REQUIRES_NEW：
	 * 			调用者的事务暂时挂起，new一个新事务来执行本事务1，成功。
	 * 			则继续new一个新事务执行事务2,异常,则回滚当前new出来的事务，并停止，后面的事务不执行，
	 * 			调用者的事务回滚,之前成功了的事务不回滚。
	 *  2、isolation:事务的隔离级别
	 *  	isolation=Isolation.READ_COMMITTED，常用的隔离级别：READ_COMMITTED
	 *  3、rollbackFor：对哪些异常进行回滚。默认是对所有RuntimeException进行回滚。
	 *  	rollbackFor={EmployeeException.class},对EmployeeException异常回滚
	 *  4、noRollbackFor：对哪些异常不进行回滚。(3,4一般不用，采用默认的)
	 *  	noRollbackFor={EmployeeException.class},表示对EmployeeException异常不回滚,会出现name1钱少了，但没转给name2
	 *  5、readOnly:指定事务是否只读。
	 *  	readOnly = true,事务只读,不更新。则spring不会加锁，可减轻数据库引擎负担。
	 *  6、timeout：指定访问数据库的强制回滚时间
	 *  	timeout=3,超过3秒后，一旦再访问数据库，则强制回滚事务。
	 *  			    如果3秒后没有再访问数据库，事务不捕获超时异常，不回滚。
	 */
	@Transactional(propagation=Propagation.REQUIRED,
					isolation=Isolation.READ_COMMITTED,
					timeout=3)
	public void transferAccount(String name1,String name2,Integer account){
		try {
			employeeDao.decreaseAccount(name1, account);
			Thread.sleep(5000);	//睡3秒，测试timeout
			employeeDao.addAccount(name2, account);
		} catch (EmployeeException e) {		
			/**
			 * 捕获了异常，继续抛给事务去管理。
			 * 如果如果不抛,事务捕获不到异常就不会回滚事务，那么会出现name1钱少了，但是没转给name2
			 */
			throw e;
		} catch (InterruptedException e) {
			//InterruptedException线程终端异常，没法抛出去。
			//假如真出了此异常，而且不超时，事务是不会回滚的。
			//超时的话，事务管理器会捕获TransactionTimedOutException,然后回滚,与InterruptedException无关。
		} 
	}
	/**
	 * 事务的传播行为，被注解的当前事务方法，被其他事务调用的时候，其如何使用
	 * spring定义了7种,常用两种：
	 * propagation=Propagation.REQUIRED (默认)：
	 * 		使用调用者的事务，本事务1,2,3中，其中一个事务回滚了,则停止,
	 * 		调用者的事务回滚,之前成功了的事务也回滚。
	 * propagation=Propagation.REQUIRES_NEW：
	 * 		调用者的事务暂时挂起，new一个新事务来执行本事务1，成功。
	 * 		则继续new一个新事务执行事务2,异常,则回滚当前new出来的事务，并停止，后面的事务不执行，
	 * 		调用者的事务回滚,之前成功了的事务不回滚。
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void transferAccountRequiredNew(String name1,String name2,Integer account){
		employeeDao.decreaseAccount(name1, account);
		employeeDao.addAccount(name2, account);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void transferManyAccount(String name1,List<String> name2List,Integer account){
		for(String name2:name2List){
			/**
			 * 直接调用方法，属于纯粹的方法调用，这个方法本身不产生事务。
			 * 只能在其他的类或者service里面调用此方法，测试事务传播行为：REQUIRES_NEW
			 */
			transferAccountRequiredNew(name1,name2,account);
		}
	}
}
