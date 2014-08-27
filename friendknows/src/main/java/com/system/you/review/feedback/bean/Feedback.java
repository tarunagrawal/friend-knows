package com.system.you.review.feedback.bean;

import static com.system.you.review.database.IConstants.ITable.IFeeback.ID;
import static com.system.you.review.database.IConstants.ITable.IFeeback.USER_ID;
import static com.system.you.review.database.IConstants.ITable.INames.FEEDBACK_TABLE;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.system.you.review.ApplicationEntity;
import com.system.you.review.database.IConstants;
import com.system.you.review.user.bean.ReviewUser;

@Entity
@Table(name = FEEDBACK_TABLE)
public class Feedback implements ApplicationEntity{

	@Id
	@GenericGenerator(name = IConstants.GENERATOR_NAME, strategy = IConstants.GENERATOR_STRATEGY)
	@GeneratedValue(generator = IConstants.GENERATOR_NAME)
	@Column(name = ID)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Basic
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = USER_ID, nullable = false)
	public ReviewUser getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(ReviewUser reviewUser) {
		this.reviewUser = reviewUser;
	}

	@Override
	@Transient
	public String getOwnerId() {
		return ApplicationEntity.OWNER.SYSTEM.getValue();
	}

	@Override
	@Transient
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	@Basic
	public Date getCreateDateTime() {
		return createDateTime;
	}

	@Override
	@Basic
	public Date getUpdateDateTime() {
		return updateDateTime;
	}
	
	private String id;

	private String category;

	private String description;
	
	private Date createDateTime;
	
	private Date updateDateTime;

	private ReviewUser reviewUser ;
	
}
