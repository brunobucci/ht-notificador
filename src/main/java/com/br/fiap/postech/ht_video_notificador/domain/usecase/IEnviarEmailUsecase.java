package com.br.fiap.postech.ht_video_notificador.domain.usecase;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;

public interface IEnviarEmailUsecase {
	void executar(VideoDto videoDto);
}
