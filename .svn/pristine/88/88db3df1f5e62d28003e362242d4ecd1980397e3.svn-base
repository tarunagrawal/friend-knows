package com.system.you.review.request.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.system.you.review.database.IConstants;

public class ExternalReviewer {
	
	@Id
	@GenericGenerator(name=IConstants.GENERATOR_NAME, strategy=IConstants.GENERATOR_STRATEGY)
	@GeneratedValue(generator=IConstants.GENERATOR_NAME)
	@Column(name=IConstants.ITable.IExternalReviewer.ID)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Basic
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	@Basic
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	@Basic
	public String getHashLink() {
		return hashLink;
	}
	public void setHashLink(String hashLink) {
		this.hashLink = hashLink;
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

	@OneToOne (fetch=FetchType.LAZY)
	@JoinColumn(name=IConstants.ITable.IExternalReviewer.REVIEWER_REQUEST_ID)
	public Reviewer getReviewerRequest() {
		return reviewerRequest;
	}
	public void setReviewerRequest(Reviewer reviewerRequest) {
		this.reviewerRequest = reviewerRequest;
	}

	@Basic
	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Basic
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	private String id ; 
	private String mailId;
	private int contactNo ;
	private String hashLink;
	private String providerUserId;
	private String name;
	private Reviewer reviewerRequest ;
	private Date createDateTime;
	private Date updateDateTime;

}
