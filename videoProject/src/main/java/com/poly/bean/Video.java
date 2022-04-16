package com.poly.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;






@Entity
@Table(name = "Videos")
@NamedNativeQueries({
@NamedNativeQuery(name = "Report.random", query = "SELECT * FROM Videos ORDER BY newid()", resultClass = Video.class) })
@NamedQueries({
@NamedQuery(name = "Video.findByKeyword",
query = "SELECT DISTINCT o.video FROM Favorite o"
+ " WHERE o.video.title LIKE :keyword"),
@NamedQuery(name = "Video.findByUser",
query = "SELECT o.video FROM Favorite o"
+ " WHERE o.user.id=:id"),
@NamedQuery(name = "Video.findInRange",
query = "SELECT DISTINCT o.video FROM Favorite o"
+ " WHERE o.likeDate BETWEEN :min AND :max"),
@NamedQuery(name = "Video.findInMonths",
query = "SELECT DISTINCT o.video FROM Favorite o"
+ " WHERE MONTH(o.likeDate) IN (:months)"),
@NamedQuery(name = "Video.findFavoriteVideo",
query = "SELECT DISTINCT o.video FROM Favorite o" +
 " WHERE o.video.id=:vidid and o.user.id = :userid"),
@NamedQuery(name = "Video.insertFavoriteVideo",
query = "SELECT DISTINCT o.video FROM Favorite o" +
 " WHERE o.user.id=:id"),
@NamedQuery(name = "Video.findByTitle",
query ="SELECT o FROM Video o WHERE o.title LIKE :keyword")
})
public class Video {
	@Id
	String id;
	String title;
	String poster;
	String description;
	Integer views = 0;
	Boolean active = true;

	@OneToMany(mappedBy = "video")

	List<Favorite> favorites;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

}
