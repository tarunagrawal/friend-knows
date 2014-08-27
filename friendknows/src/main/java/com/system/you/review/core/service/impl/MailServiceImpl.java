package com.system.you.review.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.system.you.review.core.mail.MailContent;
import com.system.you.review.core.mail.MailHelper;
import com.system.you.review.core.mail.MailMessage;
import com.system.you.review.core.mail.impl.MailMessageImpl;
import com.system.you.review.core.service.MailService;
import com.system.you.review.request.bean.Request;

@Service
public class MailServiceImpl implements MailService {

	@PostConstruct
	public void postConstructor() {
		this.mailQueue = new ConcurrentLinkedQueue<MailMessage>();
	}

	@Scheduled(fixedDelay = 30000)
	public void processMails() {
		// if queue is empty
		if (mailQueue.isEmpty()) {
			return;
		}
		List<MimeMessagePreparator> mails = sendingMails();
		if (mails != null && !mails.isEmpty()) {
			mailSender.send(mails.toArray(new MimeMessagePreparator[mails
					.size()]));
		}
	}

	//This method should always be called in current requestor's context
	@Override
	public void sendMessage(Request request) {
		MailContent mailContent = mailHelper.getAssignedMailContent(request);
		mailQueue.offer(new MailMessageImpl(mailContent));

	}

	private List<MimeMessagePreparator> sendingMails() {
		List<MimeMessagePreparator> mails = new ArrayList<MimeMessagePreparator>();
		int count = 0;
		while (count < PROCESSING_LIMIT) {
			MailMessage mailMessage = mailQueue.poll();
			if (mailMessage == null) {
				break;
			}
			mails.add(mailMessage.getMessage());
			++count;
		}
		return mails;
	}

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailHelper mailHelper;

	private Queue<MailMessage> mailQueue;

	private static int PROCESSING_LIMIT = 20;
}
