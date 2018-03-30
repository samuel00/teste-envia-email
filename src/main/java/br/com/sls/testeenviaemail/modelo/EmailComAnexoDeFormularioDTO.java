package br.com.sls.testeenviaemail.modelo;

import org.springframework.web.multipart.MultipartFile;

public class EmailComAnexoDeFormularioDTO extends EmailDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540531425509484090L;
	
	private MultipartFile[] arquivo;
	
	public EmailComAnexoDeFormularioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public EmailComAnexoDeFormularioDTO (String destinatario, String assunto, String texto) {
		super(destinatario,assunto,texto);
	}

	public MultipartFile[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(MultipartFile[] arquivo) {
		this.arquivo = arquivo;
	}

}
