package com.br.fiap.postech.ht_video_notificador.domain.repository;

public interface INotificacaoQueueAdapterOUT {
	void publishVideoComErro(String videoJson);
}
