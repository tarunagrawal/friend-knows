package com.system.you.review.web.beans.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

public class RequestContext<T, V> {

	public RequestContext(T formBean) {
		this.messages = new HashMap<String, String>();
		this.formBean = formBean;
	}

	public RequestContext(T formBean, V viewBean) {
		this(formBean);
		this.viewBean = viewBean;
	}

	public RequestContext(T formBean, V viewBean, Model model) {
		this(formBean, viewBean);
		this.model = model;
	}

	public T getFormBean() {
		return formBean;
	}

	public V getViewBean() {
		return viewBean;
	}

	public void setViewBean(V viewBean) {
		this.viewBean = viewBean;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void addMessage(String key, String message) {
		this.messages.put(key, message);
	}

	public boolean containsMessage() {
		if (messages != null && messages.size() > 0) {
			return true;
		}
		return false;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String viewName) {
		this.successView = viewName;
	}

	public String getErrorView() {
		return errorView;
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

	private T formBean;

	private V viewBean;

	private Model model;

	private String successView;

	private String errorView;

	private Map<String, String> messages;

}
