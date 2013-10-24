package com.lmiky.love.memeber.pojo;

import java.util.Date;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 会员
 * @author lmiky
 * @date 2013-10-24
 */
public class Member extends BasePojo {
	private String name;
	private Integer sex; 
	private Date birthday;
	private Integer height;  
	private Integer weight;  
	private Long nation;  
	private Integer maritalStatus;
	private Long faith;   
	private Integer bloodGroup;   
	private String phone;   
	private Long hometownCountry;
	private Long hometownProvince; 
	private Long hometownCity; 
	private Long residenceCountry; 
	private Long residenceProvince;
	private Long residenceCity;
	private Integer education;
	private String graduateSchool;  
	private Integer companyProperty;  
	private Long companyProfession;
	private Long jobPosition;  
	private Long jobStatus;;
	private Long income;  
	private String IDCard;  
	private String sign;
	private String interest;
	private String oftenHaunts;  
	private Long instruction;   
	private String photograph;
	private Date registerTime = new Date();;

}
