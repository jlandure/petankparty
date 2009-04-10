package org.petank.client.model;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Bareme implements Serializable {

	private static final long serialVersionUID = -8984845986760026206L;
	
	@Persistent
    private Float minimum;
	
	@Persistent
	private Float maximum;
	
	@Persistent
    private Float victoireNormale;
	
	@Persistent
    private Float defaiteNormale;
	
	@Persistent
    private Float victoireAnormale;
	
	@Persistent
    private Float defaiteAnormale;

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
	
}
