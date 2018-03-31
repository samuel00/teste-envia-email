package br.com.sls.testeenviaemail.comum;

import java.util.ArrayList;

import br.com.sls.testeenviaemail.modelo.EmailComAnexoDTO;
import br.com.sls.testeenviaemail.modelo.EmailDTO;
import br.com.sls.testeenviaemail.modelo.PropriedadeArquivo;

public class CriadorDeEmail {

	protected EmailComAnexoDTO emailComAnexoDTO;
	private EmailDTO emailDTO;
	private static final String ASSUNTO = "TESTE AUTOMATIZADO DE EMAIL COM JSON";
	private static final String DESTINATARIO = "ifpa.santana@gmail.com";
	private static final String TEXTO = "Parabéns seu teste funcionou";
	private static final String NULL = null;

	public CriadorDeEmail criarEmailCompleto() {
		this.emailDTO = new EmailDTO(DESTINATARIO, ASSUNTO, TEXTO);
		return this;
	}

	public CriadorDeEmail criarEmailSemAssunto() {
		this.emailDTO = new EmailDTO(DESTINATARIO, NULL, TEXTO);
		return this;
	}

	public CriadorDeEmail criarEmailSemDestinatario() {
		this.emailDTO = new EmailDTO(NULL, ASSUNTO, TEXTO);
		return this;
	}

	public CriadorDeEmail criarEmailSemTexto() {
		this.emailDTO = new EmailDTO(DESTINATARIO, ASSUNTO, NULL);
		return this;
	}

	public CriadorDeEmail naoCriarArquivo() {
		this.emailComAnexoDTO = new EmailComAnexoDTO(this.emailDTO.getDestinatario(), this.emailDTO.getAssunto(),
				this.emailDTO.getTexto());
		return this;
	}

	public CriadorDeEmail criarArquivo() {
		this.emailComAnexoDTO = new EmailComAnexoDTO(this.emailDTO.getDestinatario(), this.emailDTO.getAssunto(),
				this.emailDTO.getTexto(), PropriedadeAnexo.geNomeArquivo(), PropriedadeAnexo.getTipoArquivo(),
				PropriedadeAnexo.getBase64(), new ArrayList<PropriedadeArquivo>());
		return this;
	}
	
	public EmailComAnexoDTO concluirEmailComAnexo(){
		return this.emailComAnexoDTO;
	}
	
	public EmailDTO concluirEmailSemAnexo(){
		return this.emailDTO;
	}

}
