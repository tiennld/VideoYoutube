package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.poly.bean.Video;

public class VideoDAO {
	private EntityManager em = JpaUtils.getEntityManager();
	
	public VideoDAO() {
		
	}
	@Override
	protected void finalize() throws Throwable {
		em.close();// dong EntiryManager khi DAO bi giai phong
		super.finalize();
	}
	
	public Video create(Video entity) {
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
	public Video update(Video entity) {
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
	public Video remove(String id) {
		try {
			Video entity = this.findById(id);
			em.getTransaction().begin();			
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public Video findById(String id) {
		Video entity = em.find(Video.class, id);
		return entity;
	}
	public List<Video> findAll(){
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		List<Video> list = query.getResultList();
		return list;
	}
	
	//Đếm số lượng bản ghi trong video
	public double countAll(){
		String jpql = "SELECT o FROM Video o";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		List<Video> list = query.getResultList();
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
	public List<Video> pageOne(int first,int max) {
		// Câu lệnh truy vấn JPQL
		String jpql = "SELECT o FROM Video o";
		// Tạo đối tượng truy vấn
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setFirstResult((first - 1)*max);
		query.setMaxResults(max);
		// Truy vấn
		List<Video> list = query.getResultList();
		return list;
	}
}
