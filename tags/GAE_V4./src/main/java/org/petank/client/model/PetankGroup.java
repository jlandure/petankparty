package org.petank.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PetankGroup implements Serializable {

	private static final long serialVersionUID = -7630021353197386516L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
    private String name;
	
	@Persistent
    private String petankName;
	
	@Persistent
    private String password;
	
    @Persistent
    private Date dateCreation;
    
    @Persistent
    private Boolean matchApplied;
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPetankName() {
		return petankName;
	}

	public void setPetankName(String petankName) {
		this.petankName = petankName;
	}

	public Boolean getMatchApplied() {
		return matchApplied;
	}

	public void setMatchApplied(Boolean matchApplied) {
		this.matchApplied = matchApplied;
	}

}
