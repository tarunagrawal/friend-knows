package com.system.you.review.request.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.you.review.core.service.exception.AttemptToRemoveOnlyReviewerException;
import com.system.you.review.core.service.exception.LongForwardChainException;
import com.system.you.review.core.service.exception.ReviewerAlreadyExist;
import com.system.you.review.core.service.exception.ServiceException;
import com.system.you.review.item.bean.Item;
import com.system.you.review.item.bean.helper.impl.RequestBeanHelper;
import com.system.you.review.item.bean.helper.impl.ReviewerBeanHelper;
import com.system.you.review.item.service.ItemService;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.bean.Request.Status;
import com.system.you.review.request.bean.Reviewer;
import com.system.you.review.request.dao.ExternalReviewerDAO;
import com.system.you.review.request.dao.RequestDAO;
import com.system.you.review.request.dao.ReviewerDAO;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.request.service.ServiceSupport;
import com.system.you.review.user.bean.ReviewUser;
import com.system.you.review.user.service.ReviewUserService;
import com.system.you.review.web.beans.form.RequestFormBean;
import com.system.you.review.web.beans.form.ReviewForwardFormBean;
import com.system.you.review.web.beans.form.ReviewerFormBean;

@Service
public class RequestServiceImpl extends ServiceSupport implements
		RequestService {

	@Override
	@Transactional(readOnly = true)
	public List<Request> getAllRequestForUser(ReviewUser user)
			throws ServiceException {
		try {
			List<Request> requests = requestDAO.get(user);
			if (requests != null && !requests.isEmpty()) {
				Collections.sort(requests);
				return requests;
			}
		} catch (Exception ex) {
			logErrorAndThrowException(
					"Error occured while retrieving requests of user "
							+ user.getName(), ex);
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Request create(RequestFormBean formBean) throws ServiceException {
		Request dbBean = null;
		try {
			dbBean = requestBeanHelper.formToData(formBean);
			create(dbBean);
		} catch (Exception ex) {
			logErrorAndThrowException("error occured while creating request",
					ex);
		}
		return dbBean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Request forwardRequest(ReviewForwardFormBean formBean)
			throws ServiceException {
		String reviewerId = formBean.getReviewerRequestId();
		try {
			Reviewer reviewer = reviewerDAO.getReviewer(reviewerId);
			if (reviewer != null) {
				// check security for reviewer request, make sure that current
				// user has update right to it
				hasRight(reviewer);
				Request rootRequest = reviewer.getRequest();
				// validate request for duplicate reviewer in entire chain and
				// depth of the chain
				validateDuplicateReviewersInChain(rootRequest, formBean);
				// Fetch forward request
				Request dbBean = forwardRequest(getCurrentUser(), rootRequest);
				// if not exist create a new request
				if (dbBean == null) {
					// validate chain depth (only configured level of depth is
					// permitted
					validateChainDepth(rootRequest);
					dbBean = createForwardRequest(formBean, rootRequest,
							reviewer);
					return dbBean;
				}
				// otherwise add reviewers to existing request
				hasRight(dbBean);
				Collection<Reviewer> reviewers = addReviewers(
						dbBean.getRequestID(), formBean.getFriends());
				// If collection is empty that means reviewers were not added.
				if (reviewers == null || reviewers.isEmpty()) {
					dbBean = null;
				}
				return dbBean;
			}
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error occured while forwarding request ", ex);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Request forwardRequest(ReviewUser user, Request request) {
		return requestDAO.get(user, request);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean close(String requestId) {
		boolean closed = false;
		Request request = requestDAO.get(requestId);
		if (request != null) {
			hasRight(request);
			if (requestDAO.close(requestId)) {
				for (Reviewer reviewer : request.getReviewers()) {
					reviewerDAO.close(reviewer.getRequestID());
				}
				Set<Request> children = request.getChildren();
				if (children != null && children.size() > 0) {
					for (Request child : children) {
						close(child.getRequestID());
					}
				}
				closed = true;
			}
		}
		return closed;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Reviewer removeFwdReviewer(String reviewerId, String requestId,
			String fwdReviewerId) throws ServiceException {
		try {
			Reviewer reviewer = reviewerDAO.getReviewer(fwdReviewerId);
			if (reviewer != null) {
				Request request = reviewer.getRequest();
				hasRight(request);
				if (request.getRequestID().equals(requestId)) {
					if (request.getStatus() == Status.PROPAGATED) {
						int totalReviewer = request.getReviewers().size();
						if (totalReviewer == 1) {
							// if only one reviewer is left then close the
							// request
							close(requestId);
							Reviewer parentReviewer = reviewerDAO
									.getReviewer(reviewerId);
							Status newStatus = Status.INITIATED;
							Status existing = parentReviewer.getStatus();
							if (existing == Status.PROPAGATED) {
								newStatus = Status.INITIATED;
							} else if (existing == Status.ASWERED_FORWARED) {
								newStatus = Status.ANSWERED;
							}
							parentReviewer.setStatus(newStatus);
							parentReviewer.setUpdateDateTime(new Date());
							reviewerDAO.update(parentReviewer);
							return null;
						} else {
							reviewer = reviewerDAO.close(fwdReviewerId);
							if (reviewer != null) {
								request.setUpdateDateTime(new Date());
								Set<Reviewer> reviewers = request
										.getReviewers();
								if (reviewers != null) {
									Iterator<Reviewer> iterator = reviewers
											.iterator();
									while (iterator.hasNext()) {
										Reviewer bean = iterator.next();
										if (bean.getId().equalsIgnoreCase(
												fwdReviewerId)) {
											iterator.remove();
											break;
										}
									}
								}
								requestDAO.save(request);
								return reviewer;
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logErrorAndThrowException("error while removing reviewer "
					+ reviewerId + " for request " + requestId, ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Reviewer removeReviewer(String requestId, String reviewerId)
			throws ServiceException {
		try {
			Reviewer reviewer = reviewerDAO.getReviewer(reviewerId);
			if (reviewer != null) {
				Request request = reviewer.getRequest();
				hasRight(request);
				// validate that last reviewer is not getting deleted
				validateLastReviewer(requestId);
				if (request.getRequestID().equals(requestId)) {
					reviewer = reviewerDAO.close(reviewerId);
					if (reviewer != null) {
						request.setUpdateDateTime(new Date());
						requestDAO.save(request);
						return reviewer;
					}
				}
			}
		} catch (Exception ex) {
			logErrorAndThrowException("error while removing reviewer "
					+ reviewerId + " for request " + requestId, ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Request get(String requestId) {
		return requestDAO.get(requestId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Collection<Reviewer> addReviewers(String requestId, String reviewers)
			throws ServiceException, ReviewerAlreadyExist {
		try {
			Request request = get(requestId);
			if (request != null) {
				hasRight(request);
				Collection<ReviewerFormBean> formBeans = reviewerBeanHelper
						.stringToForms(reviewers);
				validateDuplicate(requestId, formBeans);
				Collection<Reviewer> reviewerDBs = reviewerBeanHelper
						.formToData(formBeans);
				for (Reviewer reviewer : reviewerDBs) {
					reviewer.setRequest(request);
					reviewerDAO.addReviewer(reviewer);
					request.getReviewers().add(reviewer);
				}
				request.setUpdateDateTime(new Date());
				requestDAO.save(request);
				return reviewerDBs;
			}
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error while adding reviewers for request " + requestId, ex);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Request editDescription(String requestId, String description)
			throws ServiceException {
		try {
			Request request = get(requestId);
			if (request != null) {
				hasRight(request);
				request.setDescription(description);
				requestDAO.save(request);
				return request;
			}
		} catch (Exception e) {
			logErrorAndThrowException(
					"Error occurred while updating the description for "
							+ requestId, e);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public int totalRequest(ReviewUser user) throws ServiceException {
		try {
			return requestDAO.totalRequest(user);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"Error occurred while retirving request count for user "
							+ user.getName(), ex);
		}
		return -1;
	}

	private void validateLastReviewer(String requestId)
			throws AttemptToRemoveOnlyReviewerException, ServiceException {
		int totalReviewer = 0;
		try {
			totalReviewer = reviewerDAO.getReviewerCount(requestId);
		} catch (Exception ex) {
			logErrorAndThrowException(
					"error while retrieving count for reviewers for request "
							+ requestId, ex);
		}
		if (totalReviewer == 1) {
			throw new AttemptToRemoveOnlyReviewerException(
					"attempt to remove only existing reviewer", null);
		}
	}

	private void validateDuplicate(String requestId,
			Iterable<ReviewerFormBean> formBeans) throws ReviewerAlreadyExist {
		for (ReviewerFormBean reviewerFormBean : formBeans) {
			if (reviewerDAO.alreadyExist(requestId,
					reviewerFormBean.getReviewerProviderId())) {
				throw new ReviewerAlreadyExist(
						reviewerFormBean.getReviewerProviderId());
			}
		}
	}

	private RequestFormBean createRequestFormBean(
			ReviewForwardFormBean formBean, Request parentRequest) {
		RequestFormBean bean = new RequestFormBean();
		bean.setCategory(parentRequest.getItem().getCategory().getId());
		bean.setDescription(parentRequest.getDescription());
		bean.setFriends(formBean.getFriends());
		bean.setItem(parentRequest.getItem().getId());
		return bean;
	}

	private Request create(Request dbBean) throws Exception{
		Item item = dbBean.getItem();
		if (!alreadyExist(item)) {
			itemService.createItem(item);
		}
		//setting right item :
		//This is kind of hack
		dbBean.setItem(itemService.getItem(item.getId()));
		requestDAO.save(dbBean);
		Collection<Reviewer> reviewers = dbBean.getReviewers();
		for (Reviewer reviewer : reviewers) {
			reviewer.setRequest(dbBean);
			reviewerDAO.addReviewer(reviewer);
		}
		return dbBean;
	}

	private void logErrorAndThrowException(String message, Exception ex)
			throws ServiceException {
		logger.error(message, ex);
		if (ex instanceof ServiceException) {
			throw (ServiceException) ex;
		}

		throw new ServiceException(message, ex);
	}

	private void validateChainDepth(Request rootRequest)
			throws LongForwardChainException {
		int depth = getChainDepth(rootRequest);
		if (depth > 3) {
			throw new LongForwardChainException(
					"Request chain can not be too longer than 3 level");
		}
	}

	private void validateDuplicateReviewersInChain(Request rootRequest,
			ReviewForwardFormBean formBean) throws ReviewerAlreadyExist {
		Collection<ReviewerFormBean> newReviewers = reviewerBeanHelper
				.stringToForms(formBean.getFriends());
		validateDuplicateReviewers(rootRequest, newReviewers);
	}

	private void validateDuplicateReviewers(Request rootRequest,
			Collection<ReviewerFormBean> newReviewers)
			throws ReviewerAlreadyExist {
		if (rootRequest != null) {
			validateDuplicate(rootRequest.getId(), newReviewers);
			for (Request child : rootRequest.getChildren()) {
				validateDuplicateReviewers(child, newReviewers);
			}
		}
	}

	private int getChainDepth(Request request) {
		int depth = 0;
		while (request != null) {
			++depth;
			request = request.getParentRequest();
		}
		return depth;
	}

	private Request createForwardRequest(ReviewForwardFormBean formBean,
			Request parentRequest, Reviewer reviewer) throws Exception{
		// create a new forwarded request
		RequestFormBean reqFromBean = createRequestFormBean(formBean,
				parentRequest);
		Request dbBean = requestBeanHelper.formToData(reqFromBean);
		dbBean.setStatus(Status.PROPAGATED);
		dbBean.setParentRequest(parentRequest);
		dbBean.setItem(parentRequest.getItem());
		dbBean = create(dbBean);
		if (dbBean != null) {
			Status existingStatus = reviewer.getStatus();
			Status newStatus = Status.PROPAGATED;
			if (existingStatus == Status.ANSWERED) {
				newStatus = Status.ASWERED_FORWARED;
			}
			reviewer.setStatus(newStatus);
			reviewerDAO.update(reviewer);
		}
		return dbBean;
	}

	@Autowired
	private RequestDAO requestDAO;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ReviewUserService reviewUserService;

	@Autowired
	private ReviewerDAO reviewerDAO;

	@Autowired
	private ExternalReviewerDAO externalReviewerDAO;

	@Autowired
	private RequestBeanHelper requestBeanHelper;

	@Autowired
	private ReviewerBeanHelper reviewerBeanHelper;

	private static Logger logger = LoggerFactory
			.getLogger(RequestServiceImpl.class);

}
