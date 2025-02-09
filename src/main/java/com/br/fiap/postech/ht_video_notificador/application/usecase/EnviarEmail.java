package com.br.fiap.postech.ht_video_notificador.application.usecase;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.br.fiap.postech.ht_video_notificador.application.dto.VideoDto;
import com.br.fiap.postech.ht_video_notificador.domain.usecase.IEnviarEmailUsecase;

@Service
public class EnviarEmail implements IEnviarEmailUsecase{

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    //private final INotificacaoQueueAdapterOUT notificacaoQueueAdapterOUT;

//    @Autowired
//    private Gson gson;
    
//    public EnviarEmail(INotificacaoQueueAdapterOUT notificacaoQueueAdapterOUT) {
//        this.notificacaoQueueAdapterOUT = notificacaoQueueAdapterOUT;
//    }
    
	@Override
	public void executar(VideoDto videoDto) {
		try {
			logger.info("Iniciou processo de envio de email.");

			SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("teste@brunobucci.me");
	        message.setTo("marcelo30128@gmail.com"); 
	        message.setSubject("Teste de e-mail"); 
	        StringBuffer sb = new StringBuffer();
	        sb.append("Erro ao processar extração de frames de vídeo.")
	        .append("\n id: ").append(videoDto.getId())
	        .append("\n codigoEdicao: ").append(videoDto.getCodigoEdicao())
	        .append("\n nome: ").append(videoDto.getNome())
	        .append("\n status: ").append(videoDto.getStatusEdicao());
	        message.setText(sb.toString());
	        getJavaMailSender().send(message);
			
			//notificacaoQueueAdapterOUT.publishVideoComErro(toVideoMessage(videoDto));
			
			logger.info("Finalizou processo de envio de email.");
		}
		catch(Exception ex) {
			//TODO - Publicar na fila de video com erro
			//extracaoQueueAdapterOUT.publishVideoProcessado(toVideoMessage(videoDto));
			logger.error("Video publicado na fila videos_com_erro: ", ex);
		}		
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail.brunobucci.me");
		mailSender.setPort(587);
		
		mailSender.setUsername("teste@brunobucci.me");
		mailSender.setPassword(".Z9YPriR*LiUubG8mU");
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return mailSender;
	}
	
//	private String toVideoMessage(VideoDto video){
//        Map message = new HashMap<String, String>();
//        message.put("id",video.getId());
//        message.put("nomeVideo",video.getNome());
//        message.put("codigoEdicao",video.getCodigoEdicao().toString());
//        message.put("statusEdicao",StatusEdicao.COM_ERRO);
//        return gson.toJson(message);
//    }
	
}
