package org.petank.server.model;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Bareme implements Serializable {

	private static final long serialVersionUID = -8984845986760026206L;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    public Long id;
	
	@Persistent
	public Float minimum;
	
	@Persistent
	public Float maximum;
	
	@Persistent
	public Float victoireNormale;
	
	@Persistent
	public Float defaiteNormale;
	
	@Persistent
	public Float victoireAnormale;
	
	@Persistent
	public Float defaiteAnormale;

	public Float getMinimum() {
		return minimum;
	}

	public void setMinimum(Float minimum) {
		this.minimum = minimum;
	}

	public Float getMaximum() {
		return maximum;
	}

	public void setMaximum(Float maximum) {
		this.maximum = maximum;
	}

	public Float getVictoireNormale() {
		return victoireNormale;
	}

	public void setVictoireNormale(Float victoireNormale) {
		this.victoireNormale = victoireNormale;
	}

	public Float getDefaiteNormale() {
		return defaiteNormale;
	}

	public void setDefaiteNormale(Float defaiteNormale) {
		this.defaiteNormale = defaiteNormale;
	}

	public Float getVictoireAnormale() {
		return victoireAnormale;
	}

	public void setVictoireAnormale(Float victoireAnormale) {
		this.victoireAnormale = victoireAnormale;
	}

	public Float getDefaiteAnormale() {
		return defaiteAnormale;
	}

	public void setDefaiteAnormale(Float defaiteAnormale) {
		this.defaiteAnormale = defaiteAnormale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
