package com.eshop.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eshop.dao.UserDetailDAO;
import com.eshop.model.UserDetail;


public class UserDetailTest {
	
	static UserDetailDAO userDetailDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	    
	    context.scan("com.eshop");
	    context.refresh();
	    
	    userDetailDAO=(UserDetailDAO)context.getBean("userDetailDAO");
	}
	
	@Ignore
	@Test
	public void registerUserTest() 
	{
		UserDetail user=new UserDetail();
		
		user.setUsername("sravan");
		user.setPassword("sravan3012");
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setCustomerName("Sravan");
		user.setCustomerAddr("Chandrapur");
		
		assertTrue("Problem in adding User:",userDetailDAO.registerUser(user));
	}

    @Ignore
	@Test
	public void updateUserTest()
	{
		UserDetail user=userDetailDAO.getUser("bindu");
		user.setPassword("bindu1709");
		assertTrue("Problem in Updating the User",userDetailDAO.UpdateUser(user));
		
	}
}

