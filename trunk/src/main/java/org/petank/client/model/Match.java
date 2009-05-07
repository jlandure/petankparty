package org.petank.client.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Transient;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Match implements Serializable {

	private static final long serialVersionUID = -8984845986760026206L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	/**
	 * tous les joueurs de l'équipe 1 concaténé
	 */
	@Persistent
    private String player1;
	
	/**
	 * tous les joueurs de l'équipe 1 concaténé
	 */
	@Persistent
    private String player2;
	
	@Persistent
    private Float score1;
	
	@Persistent
    private Float score2;
	
	@Persistent
    private Date jour;
	
	@Persistent
    private Float point1;
	
	@Persistent
    private Float point2;
	
	/**
	 * tous les joueurs de l'équipe concaténé avec leurs points
	 */
	@Persistent
    private String playersWithPoints;
	
	/**
	 * normal / anormal
	 */
	@Transient
    private TypeVictoire typeVictoire;
	
	/**
	 * officiel / non officiel
	 */
	@Transient
    private TypeMatch typeMatch;
	
	@Persistent
    private Bareme bareme;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public Float getScore1() {
		return score1;
	}

	public void setScore1(Float score1) {
		this.score1 = score1;
	}

	public Float getScore2() {
		return score2;
	}

	public void setScore2(Float score2) {
		this.score2 = score2;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public Float getPoint1() {
		return point1;
	}

	public void setPoint1(Float point1) {
		this.point1 = point1;
	}

	public Float getPoint2() {
		return point2;
	}

	public void setPoint2(Float point2) {
		this.point2 = point2;
	}

	public String getPlayersWithPoints() {
		return playersWithPoints;
	}

	public void setPlayersWithPoints(String playersWithPoints) {
		this.playersWithPoints = playersWithPoints;
	}

	public TypeVictoire getTypeVictoire() {
		return typeVictoire;
	}

	public void setTypeVictoire(TypeVictoire typeVictoire) {
		this.typeVictoire = typeVictoire;
	}

	public Bareme getBareme() {
		return bareme;
	}

	public void setBareme(Bareme bareme) {
		this.bareme = bareme;
	}

	public TypeMatch getTypeMatch() {
		return typeMatch;
	}

	public void setTypeMatch(TypeMatch typeMatch) {
		this.typeMatch = typeMatch;
	}
	
	public boolean isOfficiel() {
		return this.typeMatch.equals(TypeMatch.OFFICIEL);
	}

}
