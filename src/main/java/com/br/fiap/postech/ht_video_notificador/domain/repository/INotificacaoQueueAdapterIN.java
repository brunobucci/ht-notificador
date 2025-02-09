package com.br.fiap.postech.ht_video_notificador.domain.repository;

import org.springframework.messaging.handler.annotation.Payload;

public interface INotificacaoQueueAdapterIN {
	void receive(@Payload String message);
}
