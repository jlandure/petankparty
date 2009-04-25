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
    
    @Transient
    private Integer partiesJoues = 0;
    
    @Transient
    private Integer partiesGagnes = 0;
    
    @Transient
    private Integer partiesPerdus = 0;
    
    @Transient
    private Integer totalPoints = 0;
    
    @Transient
    private Integer fannyGagnes = 0;
    
    @Transient
    private Integer fannyPerdus = 0;
    
    @Transient
    private Integer victoireNormale = 0;
    
    @Transient
    private Integer victoireAnormale = 0;
    
    @Transient
    private Integer defaiteNormale = 0;
    
    @Transient
    private Integer defaiteAnormale = 0;
    
    @Transient
    private Integer nbMatchOfficiel = 0;
    
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

	public Integer getPartiesJoues() {
		return partiesJoues;
	}

	public void setPartiesJoues(Integer partiesJoues) {
		this.partiesJoues = partiesJoues;
	}

	public Integer getPartiesGagnes() {
		return partiesGagnes;
	}

	public void setPartiesGagnes(Integer partiesGagnes) {
		this.partiesGagnes = partiesGagnes;
	}

	public Integer getPartiesPerdus() {
		return partiesPerdus;
	}

	public void setPartiesPerdus(Integer partiesPerdus) {
		this.partiesPerdus = partiesPerdus;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getFannyGagnes() {
		return fannyGagnes;
	}

	public void setFannyGagnes(Integer fannyGagnes) {
		this.fannyGagnes = fannyGagnes;
	}

	public Integer getFannyPerdus() {
		return fannyPerdus;
	}

	public void setFannyPerdus(Integer fannyPerdus) {
		this.fannyPerdus = fannyPerdus;
	}

	public Integer getVictoireNormale() {
		return victoireNormale;
	}

	public void setVictoireNormale(Integer victoireNormale) {
		this.victoireNormale = victoireNormale;
	}

	public Integer getVictoireAnormale() {
		return victoireAnormale;
	}

	public void setVictoireAnormale(Integer victoireAnormale) {
		this.victoireAnormale = victoireAnormale;
	}

	public Integer getDefaiteNormale() {
		return defaiteNormale;
	}

	public void setDefaiteNormale(Integer defaiteNormale) {
		this.defaiteNormale = defaiteNormale;
	}

	public Integer getDefaiteAnormale() {
		return defaiteAnormale;
	}

	public void setDefaiteAnormale(Integer defaiteAnormale) {
		this.defaiteAnormale = defaiteAnormale;
	}

	public Integer getNbMatchOfficiel() {
		return nbMatchOfficiel;
	}

	public void setNbMatchOfficiel(Integer nbMatchOfficiel) {
		this.nbMatchOfficiel = nbMatchOfficiel;
	}

}
