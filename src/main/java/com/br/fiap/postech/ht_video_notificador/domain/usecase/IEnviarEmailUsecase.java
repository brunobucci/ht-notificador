package com.br.fiap.postech.ht_video_notificador.domain.usecase;

import org.springframework.mail.SimpleMailMessage;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;

public interface IEnviarEmailUsecase {
	SimpleMailMessage executar(VideoDto videoDto);
}
