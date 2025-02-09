package com.br.fiap.postech.ht_video_notificador.infra.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.fiap.postech.ht_video_notificador.domain.repository.INotificacaoQueueAdapterOUT;

@Service
public class NotificacaoQueueAdapterOUT {//implements INotificacaoQueueAdapterOUT{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Value("${queue2.name}")
//	private String filaVideosPendentes;
//	
//	@Autowired
//	private RabbitTemplate rabbitTemplate;
//	
//	@Override
//	public void publishVideoComErro(String videoJson) {
//		rabbitTemplate.convertAndSend(filaVideosPendentes, videoJson);
//		logger.info("Publicação na fila VideosPendentes executada.");
//	}
}
