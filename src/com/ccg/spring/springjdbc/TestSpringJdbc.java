package com.ccg.spring.springjdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ccg.spring.springjdbc.dao.EmployeeDao;
import com.ccg.spring.springjdbc.pojo.Employee;
import com.ccg.spring.springjdbc.service.EmployeeService;
import com.ccg.spring.springjdbc.service.EmployeeServiceXml;
import com.ccg.spring.springjdbc.service.PayService;

public class TestSpringJdbc {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate = null;
	EmployeeDao employeeDao = null;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	EmployeeService employeeService = null;
	PayService payService = null;
	EmployeeServiceXml employeeServiceXml = null;
	{
		ctx = new ClassPathXmlApplicationContext(
				new String[]{"applicationContext.xml","applicationContext-employeedao.xml"});
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		employeeDao = (EmployeeDao) ctx.getBean(EmployeeDao.class); //也可getBean("employeeDao")
		namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) ctx.getBean(NamedParameterJdbcTemplate.class);
		employeeService = (EmployeeService) ctx.getBean("employeeService");
		payService = (PayService) ctx.getBean("payService");
		employeeServiceXml = (EmployeeServiceXml) ctx.getBean("employeeServiceXml");
	}
	
	/**
	 * 测试连接池
	 */
	@Test
	public void testDataSource() throws SQLException{
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource.getConnection()); //返回一个实例，则创建bean成功
	}

	//==================================1、测试Dao，利用jdbcTemplate 和 注解创建bean =============
	/**
	 * 测试dao的bean：EmployeeDao
	 */
	@Test
	public void testEmployeeDao(){ 
		System.out.println(employeeDao.get(5));
		System.out.println(employeeDao.getAccount(3));
		System.out.println(employeeDao.decreaseAccount("Tom3",10));
		System.out.println(employeeDao.addAccount("Tom2",10));
	}
	
	//=================================2、测试事务，注解方式创建===============================================
	/**
	 * 测试单个事务
	 * spring声明式事务：
	 * 		1、配置事务管理器bean: DataSourceTransactionManager
	 * 		2、启动事务注解
	 * 		3、在需要的方法的上面加注释@Transactional
	 */
	@Test
	public void testEmployeeService(){ 
		/*只有一个事务时，transferAccount和transferAccountRequiredNew效果一样
			1、异常是事务捕获到的，则回滚，Tom2少的75回滚，Tom3不会增加75。
			2、异常是事务里面catch,没有抛出给事务，则事务catch不到异常，不会回滚，Tom2少了75不回滚，Tom3不会增加75。
				如果里面catch,并throw给了事务，则事务会回滚。
		*/		
		employeeService.transferAccount("Tom2", "Tom3", 5);
		//employeeService.transferAccountRequiredNew("Tom2", "Tom3", 75);
	}
	/**
	 * 测试事务中调用多个事务--同一个类里面纯属方法调用，不产生里面的事务
	 */
	@Test
	public void testEmployeeService2(){ 
		employeeService.transferManyAccount("Tom2", Arrays.asList("Tom3","Tom4","Tom5"), 20);
	}
	
	/**
	 * 测试事务中调用多个事务--从payService事务中，调用EmployeeService中的多个事务
	 */
	@Test
	public void testPayService(){ 
		payService.transferManyAccount("Tom2", Arrays.asList("Tom3","Tom4","Tom5"), 20);
	}
	
	
	//=================================2、测试事务，xml方式创建===============================================

	/**
	 * 测试xml方式配置事务
	 */
	@Test
	public void testEmployeeServiceXml(){ 
		employeeServiceXml.transferAccount("Tom2", "Tom3", 80);
	}
	
	
	
	
	
	//==================================2、测试JdbcTemplate模板:具名参数 NamedParameterJdbcTemplate =============
	/**
	 * 测试NamedParameterJdbcTemplate:可设置具名参数
	 * 1、创建bean，必须给属性dataSource，因为有构造器
	 * 2、多个参数的时候，不需要对应?和参数
	 * 3、要拼Map，可能麻烦点
	 * 4、可以传入Employee对象来插入数据，利用SqlParameterSource接口，用BeanPropertySqlParameterSource实现类入参
	 */
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "insert into employee(id,last_name,email) values(employee_seq.nextval,:lastName,:email)";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("lastName", "德鲁伊1");
		paramMap.put("email", "druid1@cn");
		
		System.out.println(namedParameterJdbcTemplate.update(sql, paramMap));
	}
	@Test
	public void testNamedParameterJdbcTemplate2(){
		String sql = "insert into employee(id,last_name,email) values(employee_seq.nextval,:lastName,:email)";
		Employee employee = new Employee("德鲁伊2","druid1@cn");
		
		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(employee);
		
		System.out.println(namedParameterJdbcTemplate.update(sql, paramMap));
	}
	
	//==================================3、测试spring的jdbcTemplate============================
	/**
	 * 测试spring的jdbcTemplate：jdbcTemplate是线程安全的
	 * update，insert，delete
	 */
	@Test
	public void testUpdate(){
		String sql = "update t_student set name = ? where id = ?";
		Object[] params = new Object[]{"德鲁伊",5};
		System.out.println(jdbcTemplate.update(sql, params));
	}
	/**
	 * 批量插入
	 * 有employee_seq序列的时候，插入一定要用这个序列，直接插数字，后面序列增到这个数字时会报错，违反唯一约束条件
	 */
	@Test
	public void testBatchUpdate(){
		String sql = "insert into employee values(employee_seq.nextval,?,?)";
		List<Object[]> paramsList = new ArrayList<>();
		paramsList.add(new Object[]{"Tom","Tom@139.com"});
		paramsList.add(new Object[]{"Tom2","Tom2@139.com"});
		paramsList.add(new Object[]{"Tom3","Tom3@139.com"});
		paramsList.add(new Object[]{"Tom4","Tom4@139.com"});
		paramsList.add(new Object[]{"Tom5","Tom5@139.com"});
		int[] batchUpdate = jdbcTemplate.batchUpdate(sql, paramsList);
		System.out.println(batchUpdate);
	}
	
	/**
	 * 查询数据，返回pojo对象的数据
	 * 注意！不是用：queryForObject(String sql, Class<Employee> requiredType, Object... args)
	 * 而是用：queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 		RowMapper：指定对象来映射结果集的行,new BeanPropertyRowMapper<>(Employee.class)
	 * 		结果集的列名需要与对象的属性一致:last_name as lastName。
	 * 不支持级联属性，Employee中的对象Department不能查出，jdbcTemplate是一个小的jdbc框架，不是ORM框架
	 */
	@Test
	public void testQueryForObject(){
		String sql = "select id,last_name as lastName,email from employee where id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 2);
		System.out.println(employee);
	}
	/**
	 * 查到实体类的集合
	 */
	@Test
	public void testQueryForList(){
		String sql = "select id,last_name as lastName,email from employee where id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employee = jdbcTemplate.query(sql, rowMapper,2);
		System.out.println(employee);
	}

	/**
	 * 获取单列的值，或做统计查询
	 * 方法：queryForObject(String sql, Class<Long> requiredType)
	 */
	@Test
	public void testQueryForObject2(){
		//记录数
		String sql = "select count(1) from employee";
		Long count = jdbcTemplate.queryForObject(sql,Long.class);
		System.out.println(count);
		//某列值
		String sql2 = "select account from employee where id = ?";
		Long account = jdbcTemplate.queryForObject(sql2,Long.class ,3);
		System.out.println(account);
		
	}
	
	
}
