
package com.eshop.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.eshop.model.CartItem;
import com.eshop.model.Category;
import com.eshop.model.OrderDetail;
import com.eshop.model.Product;
import com.eshop.model.Supplier;
import com.eshop.model.UserDetail;



@Configuration
@EnableTransactionManagement
@ComponentScan("com.eshop")
public class DBConfig 
{
	@Bean(name="dataSource")
	public DataSource getH2DataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl( "jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		System.out.println("======DataSource Created=====");
	
		return dataSource;
	}
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProp=new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getH2DataSource());
	
		factory.addProperties(hibernateProp);
		factory.addAnnotatedClass(Category.class);
		factory.addAnnotatedClass(Product.class);
		factory.addAnnotatedClass(Supplier.class);
		factory.addAnnotatedClass(CartItem.class);
		factory.addAnnotatedClass(OrderDetail.class);
		factory.addAnnotatedClass(UserDetail.class);
		
		
		SessionFactory sessionFactory=factory.buildSessionFactory();
		System.out.println("=====SessionFactory object Created=====");
		return sessionFactory;
			
	}
	@Bean(name="txManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("=====Hibernate Transaction Manager=====");
		return new HibernateTransactionManager(sessionFactory);
		
	}

}

