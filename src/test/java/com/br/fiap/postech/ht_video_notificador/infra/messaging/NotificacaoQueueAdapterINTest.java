package com.br.fiap.postech.ht_video_notificador.infra.messaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;
import com.br.fiap.postech.ht_video_notificador.domain.entity.StatusEdicao;
import com.br.fiap.postech.ht_video_notificador.domain.usecase.IEnviarEmailUsecase;
import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class NotificacaoQueueAdapterINTest {
	@InjectMocks
    private NotificacaoQueueAdapterIN notificacaoQueueAdapterIN;

    @Mock
    private IEnviarEmailUsecase enviarEmailUsecase;

    @Mock
    private Gson gson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceive() {
        // Arrange
        String message = "{\"id\":\"1\",\"codigoEdicao\":\"123\",\"nomeVideo\":\"Video Teste\",\"tentativasDeEdicao\":\"3\",\"statusEdicao\":\"FINALIZADA\"}";
        HashMap mensagemMap = new HashMap<>();
        mensagemMap.put("id", "1");
        mensagemMap.put("codigoEdicao", "123");
        mensagemMap.put("nomeVideo", "Video Teste");
        mensagemMap.put("tentativasDeEdicao", "3");
        mensagemMap.put("statusEdicao", "FINALIZADA");

        when(gson.fromJson(message, HashMap.class)).thenReturn(mensagemMap);

        // Act
        notificacaoQueueAdapterIN.receive(message);

        // Assert
        ArgumentCaptor<VideoDto> videoDtoCaptor = ArgumentCaptor.forClass(VideoDto.class);
        verify(enviarEmailUsecase, times(1)).executar(videoDtoCaptor.capture());

        VideoDto capturedVideoDto = videoDtoCaptor.getValue();
        assertEquals("1", capturedVideoDto.getId());
        assertEquals("123", capturedVideoDto.getCodigoEdicao());
        assertEquals("Video Teste", capturedVideoDto.getNome());
        assertEquals("3", capturedVideoDto.getTentativasDeEdicao());
        assertEquals(StatusEdicao.FINALIZADA, capturedVideoDto.getStatusEdicao());
    }

}
