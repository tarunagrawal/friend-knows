package com.system.you.review.web.controller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.you.review.request.bean.Request;
import com.system.you.review.request.service.RequestService;
import com.system.you.review.web.beans.form.RequestFormBean;
import com.system.you.review.web.beans.response.RequestContext;

@Service
public class RequestCreateHelper extends ControllerHelper {

	public RequestContext<RequestFormBean, String> form() {
		return new RequestContext<RequestFormBean, String>(new RequestFormBean(), "");
	}

	public RequestContext<RequestFormBean, String> createRequest(RequestFormBean formBean) {
		RequestContext<RequestFormBean, String> respBean = new RequestContext<RequestFormBean,String>(
				formBean);
		try {
			validate(formBean, respBean);
			if (!respBean.containsMessage()) {
				Request reviewRequest = requestService.create(formBean);
				if (reviewRequest == null) {
					addSystemErrorMessage(respBean);
				}
			}
		} catch (Exception ex) {
			addSystemErrorMessage(respBean);
			logger.error("error while creating request", ex);
		}
		return respBean;
	}

	@Autowired
	private RequestService requestService;

	private static Logger logger = LoggerFactory
			.getLogger(RequestCreateHelper.class);
}
