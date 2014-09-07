package com.system.you.review.user.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.system.you.review.database.IConstants;

@SuppressWarnings("serial")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = IConstants.ITable.INames.REVIEW_USER_TABLE)
public class ReviewUser implements SocialUserDetails {

	@Id
	@GenericGenerator(name = IConstants.GENERATOR_NAME, strategy = IConstants.GENERATOR_STRATEGY)
	@GeneratedValue(generator = IConstants.GENERATOR_NAME)
	@Column(name = IConstants.ITable.IReviewUser.ID)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	@Basic
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Basic
	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public List<? extends GrantedAuthority> getAuthorities() {
		return createAuthority();
	}

	
	@Basic
	public String getProviderUserName() {
		return providerUserName;
	}

	public void setProviderUserName(String providerUserName) {
		this.providerUserName = providerUserName;
	}

	@Transient
	public String getPassword() {
		return "";
	}

	@Transient
	public String getUsername() {
		return mailID;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return false;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return false;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Transient
	public boolean isEnabled() {
		return true;
	}

	@Transient
	public String getUserId() {
		return mailID;
	}

	@Transient
	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	private List<SimpleGrantedAuthority> createAuthority() {
		List<SimpleGrantedAuthority> grantedAuthroties = new ArrayList<SimpleGrantedAuthority>(
				1);
		grantedAuthroties.add(new SimpleGrantedAuthority("USER_ROLE"));
		return grantedAuthroties;
	}

	public String toString() {
		return new ToStringCreator(this).append(mailID).append(providerId)
				.append(providerUserId).toString();
	}

	private String id;
	private String mailID;
	private String providerId;
	private String providerUserId;
	private String name;
	private String providerUserName;
	private boolean external ;

}
