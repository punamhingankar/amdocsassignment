package com.amdocs.media.assignement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	public void sendAsynMessage(String message , String topic){
		this.kafkaTemplate.send(topic,message);
	}
	
}
