package com.system.you.review.web.controller.helper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.request.exception.RequestCloseException;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.web.beans.response.RequestContext;

@Service
public class CloseRequestHelper extends ControllerHelper {

	public RequestContext<String, String> close(String requestId)
			throws RequestCloseException {
		RequestContext<String,String> responseBean = new RequestContext<String,String>(requestId);
		validate(requestId, responseBean);
		if (!responseBean.containsMessage()) {
			try {
				boolean closed = requestService.close(requestId);
				if (!closed) {
					responseBean.addMessage("requestId",
							getMessage("request.close.error", null));
				}
			} catch (Exception ex) {
				addSystemErrorMessage(responseBean);
				log.error("exception occured while removing request ("
						+ requestId + ")");
			}
		}
		return responseBean;
	}

	private void validate(String requestId, RequestContext<String,String> responseBean) {
		if (StringUtils.isBlank(requestId)) {
			responseBean.addMessage("requestId",
					getMessage("request.id.field.missing ", null));
		}
	}

	@Autowired
	private RequestService requestService;

	private static Logger log = LoggerFactory
			.getLogger(CloseRequestHelper.class);

}
