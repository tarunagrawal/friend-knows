package com.system.you.review.web.controller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.core.service.exception.ReviewerAlreadyExist;
import com.system.you.review.item.bean.helper.impl.RequestBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.web.beans.form.ReviewForwardFormBean;
import com.system.you.review.web.beans.response.RequestContext;
import com.system.you.review.web.beans.view.RequestViewBean;
import com.system.you.review.web.domain.impl.SessionUtils;

@Service
public class RequestForwardHelper extends ControllerHelper {

	public RequestContext<ReviewForwardFormBean, RequestViewBean> submit(
			ReviewForwardFormBean formBean) {
		RequestContext<ReviewForwardFormBean, RequestViewBean> requestContext = new RequestContext<ReviewForwardFormBean, RequestViewBean>(
				formBean);
		validate(formBean, requestContext);
		if (!requestContext.containsMessage()) {
			Request fwdRequest = null;
			try {
				fwdRequest = requestService.forwardRequest(formBean);
				if (fwdRequest != null) {
					// retrieving the request again as it should have all the
					// nested object
					fwdRequest = requestService.get(fwdRequest.getId());
					RequestViewBean viewBean = getViewBean(fwdRequest);
					requestContext.setViewBean(viewBean);
				} else {
					addSystemErrorMessage(requestContext);
				}
			} catch (ReviewerAlreadyExist ex) {
				addFriendAlreadyExistMessage(requestContext, ex);
			} catch (Exception ex) {
				addSystemErrorMessage(requestContext);
				logger.error("error while forwarding request", ex);
			}
		}
		return requestContext;
	}

	private RequestViewBean getViewBean(Request forwardRequest) {
		return requestBeanHelper.dataToView(forwardRequest);
	}

	private void addFriendAlreadyExistMessage(
			RequestContext<ReviewForwardFormBean, RequestViewBean> requestContext,
			ReviewerAlreadyExist ex) {
		requestContext.addMessage("friend.already.exist",
				getMessage("friend.already.exist", new Object[] { getFriendName(ex) }));
	}

	private String getFriendName(ReviewerAlreadyExist ex) {
		return SessionUtils.getRequestor().getFacebookFriend(ex.getProviderId()).getName();
	}

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestBeanHelper requestBeanHelper;

	private static Logger logger = LoggerFactory.getLogger(RequestForwardHelper.class);

	private static String FWD_REQUEST = "fwdRequest";
}
