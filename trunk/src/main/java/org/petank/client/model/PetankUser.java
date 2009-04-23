package org.petank.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Transient;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PetankUser implements Serializable {

	private static final long serialVersionUID = 4469370239146268223L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
    private String name;
	
	@Persistent
    private String petankName;
	
	@Persistent
    private String email;
	
    @Persistent
    private Date dateInscription;
    
    @Persistent
    private Float points;
    
    @Persistent
    private Float progression;
    
    @Persistent
    private Float debutSaisonPoints;
    
    @Persistent
    private String commentaire;
    
    @Transient
    private String evolution;
    
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public Float getProgression() {
		return progression;
	}

	public void setProgression(Float progression) {
		this.progression = progression;
	}

	public Float getDebutSaisonPoints() {
		return debutSaisonPoints;
	}

	public void setDebutSaisonPoints(Float debutSaisonPoints) {
		this.debutSaisonPoints = debutSaisonPoints;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getEvolution() {
		return evolution;
	}

	public void setEvolution(String evolution) {
		this.evolution = evolution;
	}
    
}
