package com.eshop.daoimpl;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.dao.CartDAO;
import com.eshop.model.CartItem;



@Repository("cartDAO")
@Transactional

public class CartDAOImpl implements CartDAO
{
	@Autowired
    SessionFactory sessionFactory;
	
	public boolean addCartItem(CartItem cartItem) {
		try
        {
        sessionFactory.getCurrentSession().save(cartItem);
        return true;
        }
        catch(Exception e)
        {
        return false;
	}
	}

	public boolean deleteCartItem(CartItem cartItem) {
		try
        {
        sessionFactory.getCurrentSession().delete(cartItem);
        return true;
        }
        catch(Exception e)
        {
        return false;
        }
	}

	public boolean updateCartItem(CartItem cartItem) {
		try
        {
        sessionFactory.getCurrentSession().update(cartItem);
        return true;
        }
        catch(Exception e)
        {
        return false;
	}
	}

	public CartItem getCartItem(int cartItemId) 
	{
		Session session=sessionFactory.openSession();
		CartItem cartItem=session.get(CartItem.class,cartItemId);
		session.close();
			
		return cartItem;
	}

	public List<CartItem> listCartItems(String username) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from CartItem where username=:username and paymentStatus='NP'");
	    query.setParameter("username",username);
	    List<CartItem> cartItemList=query.list();
	    session.close();
		return cartItemList;
	}

}
