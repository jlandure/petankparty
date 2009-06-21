package org.petank.client.model;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PetankPlace implements Serializable {

	private static final long serialVersionUID = -2856460312230934297L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
    private String name;
	
	@Persistent
    private String petankName;
	
	@Persistent
    private Number lat;
	
	@Persistent
    private Number lng;
	
	@Persistent
    private String content;
	
    @Persistent(mappedBy = "place")
    private List<Match> listMatchs;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPetankName() {
		return petankName;
	}

	public void setPetankName(String petankName) {
		this.petankName = petankName;
	}

	public Number getLat() {
		return lat;
	}

	public void setLat(Number lat) {
		this.lat = lat;
	}

	public Number getLng() {
		return lng;
	}

	public void setLng(Number lng) {
		this.lng = lng;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Match> getListMatchs() {
		return listMatchs;
	}

	public void setListMatchs(List<Match> listMatchs) {
		this.listMatchs = listMatchs;
	}

}