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
	
	@Persistent
    private String player1;
	
	@Transient
    private List lstplayer1;
	
	@Transient
    private List lstplayer2;
	
	@Persistent
    private String player2;
	
	@Persistent
    private Float score1;
	
	@Persistent
    private Float score2;
	
	@Persistent
    private Float coefficient;
	
	@Persistent
    private Date jour;
	
	@Persistent
    private Float point1;
	
	@Persistent
    private Float point2;

	
	public List getLstplayer1() {
		return lstplayer1;
	}

	public void setLstplayer1(List lstplayer1) {
		this.lstplayer1 = lstplayer1;
	}

	public List getLstplayer2() {
		return lstplayer2;
	}

	public void setLstplayer2(List lstplayer2) {
		this.lstplayer2 = lstplayer2;
	}

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

	public Float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
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
	
}
