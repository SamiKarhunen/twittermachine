package com.example.twitter;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class databaseConnection  {
	
	public void Register(String username, String password) throws MySQLIntegrityConstraintViolationException
	{
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		Configuration configuration = new Configuration().configure();
		configuration.addClass(User.class);
		configuration.addResource("com/example/twitter/User.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		
		Session sessio = sf.openSession();
		sessio.beginTransaction();
		sessio.save(user);
		sessio.getTransaction().commit();
		sessio.close();
	}
	
	@SuppressWarnings("rawtypes")
	public List Login(String username, String password)
	{
		
		Configuration configuration = new Configuration().configure();
		configuration.addClass(User.class);
		configuration.addResource("com/example/twitter/User.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		
		
		Session sessio = sf.openSession();
		sessio.beginTransaction();
		Query query = sessio.createQuery("SELECT password FROM User WHERE username =:username").setParameter("username", username);
		
		List answer = query.list();
		System.out.println(answer);
		sessio.getTransaction().commit();
		sessio.close();
		return answer;
	}
	
	public void AddKeywords(String username, String keyword)
	{
		Keywords kw = new Keywords();
		kw.setUsername(username);
		kw.setKeyword(keyword);
		
		Configuration configuration = new Configuration().configure();
		configuration.addResource("com/example/twitter/User.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		
		Session sessio = sf.openSession();
		sessio.beginTransaction();
		sessio.save(kw);
		sessio.getTransaction().commit();
		sessio.close();
	}
	
	@SuppressWarnings("rawtypes")
	public List Getkeywords(String username)
	{
		
		Configuration configuration = new Configuration().configure();
		configuration.addClass(User.class);
		configuration.addResource("com/example/twitter/User.hbm.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sf = configuration.buildSessionFactory(builder.build());
		
		
		Session sessio = sf.openSession();
		sessio.beginTransaction();
		Query query = sessio.createQuery("SELECT keyword FROM Keywords WHERE username =:username").setParameter("username", username);
		
		List answer = query.list();
		System.out.println(answer);
		sessio.getTransaction().commit();
		sessio.close();
		return answer;
	}
	}


