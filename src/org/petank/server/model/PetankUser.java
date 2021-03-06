package org.petank.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class PetankUser implements Serializable {

	private static final long serialVersionUID = 4469370239146268223L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    public Long id;
	
	@Persistent
	public String name;
	
	@Persistent
	public Long idGroup;
	
	@Persistent
	public String petankName;
	
	@Persistent
	public String email;
	
    @Persistent
    public Date dateInscription;
    
    @Persistent
    public Float points;
    
    @Persistent
    public Float progression;
    
    @Persistent
    public Float debutSaisonPoints;
    
    @Persistent
    public String commentaire;
    
    @Persistent
    public Integer partiesJoues = 0;
    
    @Persistent
    public Integer partiesGagnes = 0;
    
    @Persistent
    public Integer partiesPerdus = 0;
    
    @Persistent
    public Integer totalPoints = 0;
    
    @Persistent
    public Integer fannyGagnes = 0;
    
    @Persistent
    public Integer fannyPerdus = 0;
    
    @Persistent
    public Integer victoireNormale = 0;
    
    @Persistent
    public Integer victoireAnormale = 0;
    
    @Persistent
    public Integer defaiteNormale = 0;
    
    @Persistent
    public Integer defaiteAnormale = 0;
    
    @Persistent
    public Date dayBefore;
    
    @Persistent
    public Integer placeDayBefore = 0;
    
    @Persistent
    public Float pointsDayBefore;
    
    @Persistent
    public Integer nbMatchOfficiel = 0;
    
    @Persistent
    public Integer classement = 0;
    
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

	public Integer getPlaceDayBefore() {
		return placeDayBefore;
	}

	public void setPlaceDayBefore(Integer placeDayBefore) {
		this.placeDayBefore = placeDayBefore;
	}

	public Float getPointsDayBefore() {
		return pointsDayBefore;
	}

	public void setPointsDayBefore(Float pointsDayBefore) {
		this.pointsDayBefore = pointsDayBefore;
	}

	public Date getDayBefore() {
		return dayBefore;
	}

	public void setDayBefore(Date dayBefore) {
		this.dayBefore = dayBefore;
	}

	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public Integer getClassement() {
		return classement;
	}

	public void setClassement(Integer classement) {
		this.classement = classement;
	}
	
}
