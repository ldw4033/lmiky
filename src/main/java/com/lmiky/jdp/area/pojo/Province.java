package com.lmiky.jdp.area.pojo;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 省份
 * @author lmiky
 * @date 2013-10-21
 */
public class Province extends BaseAreaPojo {
	private static final long serialVersionUID = 7914575913111694327L;
	private Country country;
	
	/**
	 * @return the country
	 */
	@ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name="country", updatable = false) 
	public Country getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
}
