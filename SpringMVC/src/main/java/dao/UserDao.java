package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import model.User;

public class UserDao {
	private HibernateTemplate hibernatetemplate;

	public HibernateTemplate getHibernatetemplate() {
		return hibernatetemplate;
	}

	public void setHibernatetemplate(HibernateTemplate hibernatetemplate) {
		this.hibernatetemplate = hibernatetemplate;
	}
	
	
	@Transactional
	public void insertOrUpdateUser(User u)
	{
		this.hibernatetemplate.saveOrUpdate(u);
	}
	
	@Transactional
	public void deleteUser(int id)
	{
		User u=getUserById(id);
		this.hibernatetemplate.delete(u);
	}
	
	public User getUserById(int id)
	{
		return this.hibernatetemplate.get(User.class, id);
	}
	
	public List<User> getAllUsers()
	{
		return this.hibernatetemplate.loadAll(User.class);
	}
}
