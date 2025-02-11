package com.br.fiap.postech.ht_video_notificador.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;
import com.br.fiap.postech.ht_video_notificador.domain.entity.StatusEdicao;

@ExtendWith(MockitoExtension.class)
class EnviarEmailTest {

	@InjectMocks
    private EnviarEmail enviarEmail;

    @Mock
    private JavaMailSender javaMailSender;

	//private VideoDto videoDto;

	@BeforeEach
	void setUp() {
		//videoDto = new VideoDto("123456", "video.mp4", "21321sdasd321ad", "1l", StatusEdicao.EXTRAIDA);
    	MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecutar() {
        // Arrange
        VideoDto videoDto = new VideoDto("1", "codigo123", "Video Teste", "3", StatusEdicao.FINALIZADA);

        // Act
        SimpleMailMessage message = enviarEmail.executar(videoDto);

        // Assert
        //verify(javaMailSender, times(1)).send(message);

        assertEquals("teste@brunobucci.me", message.getFrom());
        assertEquals("marcelo30128@gmail.com", message.getTo()[0]);
        assertEquals("Teste de e-mail", message.getSubject());
        assertTrue(message.getText().contains("Erro ao processar extração de frames de vídeo."));
        assertTrue(message.getText().contains("id: 1"));
        assertTrue(message.getText().contains("codigoEdicao: codigo123"));
        assertTrue(message.getText().contains("nome: Video Teste"));
        assertTrue(message.getText().contains("nro. de tentativas: 3"));
        assertTrue(message.getText().contains("status: "+StatusEdicao.FINALIZADA));
    }
	
}
