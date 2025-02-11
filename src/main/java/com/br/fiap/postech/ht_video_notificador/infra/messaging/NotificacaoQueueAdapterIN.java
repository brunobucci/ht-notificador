package com.br.fiap.postech.ht_video_notificador.infra.messaging;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;
import com.br.fiap.postech.ht_video_notificador.domain.entity.StatusEdicao;
import com.br.fiap.postech.ht_video_notificador.domain.repository.INotificacaoQueueAdapterIN;
import com.br.fiap.postech.ht_video_notificador.domain.usecase.IEnviarEmailUsecase;
import com.google.gson.Gson;

@Service
public class NotificacaoQueueAdapterIN implements INotificacaoQueueAdapterIN{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Gson gson;
	
	private IEnviarEmailUsecase enviarEmailUsecase;
	
	public NotificacaoQueueAdapterIN(IEnviarEmailUsecase enviarEmailUsecase) {
		this.enviarEmailUsecase = enviarEmailUsecase;
	}
	
	@SuppressWarnings("unchecked")
	@RabbitListener(queues = {"${queue1.name}"})
	@Override
	public void receive(@Payload String message) {
		HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
		VideoDto videoDto = fromMessageToDto(mensagem);
		
		enviarEmailUsecase.executar(videoDto);
		
	}
	
	private static VideoDto fromMessageToDto(Map<String, String> mensagem) {
		return new VideoDto(
				mensagem.get("id"),
				mensagem.get("codigoEdicao"),
				mensagem.get("nomeVideo"), 
				StatusEdicao.valueOf(mensagem.get("statusEdicao")));
	}
}
