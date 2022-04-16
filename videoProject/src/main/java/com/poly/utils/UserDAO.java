package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.poly.bean.User;

public class UserDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	
	public UserDAO() {
		
	}
	@Override
	protected void finalize() throws Throwable {
		em.close();// dong EntiryManager khi DAO bi giai phong
		super.finalize();
	}
	
	public User create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public User update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public User remove(String id) {
		try {
			User entity = this.findById(id);
			em.getTransaction().begin();			
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public User findById(String id) {
		User entity = em.find(User.class, id);
		return entity;
	}
	public List<User> findAll(){
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		return list;
	}
	
	//Đếm số lượng bản ghi trong user
	public double countAll(){
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> list = query.getResultList();
		return list.size();
	}
	
	//Đếm số lượng trang cần hiện
	public List<Integer> countPage(double record,double max) {
		double sum = max/record;
		int newSum = (int)sum;
		if(sum % 2 != 0) {
			newSum = newSum + 1;
		}
		List<Integer> list = new ArrayList<>();
		for(int i = 0;i < newSum;i++) {
			list.add(i+1);
		}
		return list;
	}
	
	//Phạm vi của trang
	public List<User> pageOne(int first,int max) {
		// Câu lệnh truy vấn JPQL
		String jpql = "SELECT o FROM User o";
		// Tạo đối tượng truy vấn
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setFirstResult((first - 1)*max);
		query.setMaxResults(max);
		// Truy vấn
		List<User> list = query.getResultList();
		return list;
	}
}
