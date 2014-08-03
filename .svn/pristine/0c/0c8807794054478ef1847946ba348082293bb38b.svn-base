package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.item.bean.helper.impl.RequestBeanHelper;
import com.system.you.review.request.bean.Request;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.web.beans.response.RequestContext;

@Service
public class EditRequestDescriptionHelper extends ControllerHelper {

	public RequestContext<String[], String> editDescription(String requestId,
			String description) {
		RequestContext<String[], String> requestContext = getResponseBean(
				requestId, description);
		validateInput(requestContext);
		if (!requestContext.containsMessage()) {
			try {
				Request request = requestService.editDescription(requestId,
						description);
				if (request != null) {
					requestContext.setViewBean(requestBeanHelper
							.shortDescription(request.getDescription()));
				} else {
					addSystemErrorMessage(requestContext);
				}
			} catch (Exception ex) {
				addSystemErrorMessage(requestContext);
			}
		}
		return requestContext;
	}

	private RequestContext<String[], String> getResponseBean(String requestId,
			String description) {
		return new RequestContext<String[], String>(new String[] { requestId,
				description }, "");
	}

	private void validateInput(RequestContext<String[], String> responseBean) {
		String requestId = responseBean.getFormBean()[0];
		String description = responseBean.getFormBean()[0];
		if (StringUtils.isBlank(requestId)) {
			responseBean.addMessage("requestId",
					getMessage("request.id.field.missing", null));
		}
		if (StringUtils.isBlank(description)) {
			responseBean.addMessage("description",
					getMessage("description.field.missing", null));
		}
	}

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestBeanHelper requestBeanHelper;

}
