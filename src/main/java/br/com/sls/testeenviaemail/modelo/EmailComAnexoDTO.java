package br.com.sls.testeenviaemail.modelo;

import java.util.List;

public class EmailComAnexoDTO extends EmailDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404480331904498577L;

	private List<PropriedadeArquivo> propriedadeArquivo;

	public EmailComAnexoDTO(String destinatario, String assunto, String texto, String nomeArquivo, String tipoArquivo,
			String base64, List<PropriedadeArquivo> propriedadeArquivo) {
		super(destinatario, assunto, texto);
		this.propriedadeArquivo = propriedadeArquivo;
		this.propriedadeArquivo.add(new PropriedadeArquivo(nomeArquivo, tipoArquivo, base64));
	}

	public EmailComAnexoDTO(String destinatario, String assunto, String texto) {
		super(destinatario, assunto, texto);
	}

	public List<PropriedadeArquivo> getPropriedadeArquivo() {
		return propriedadeArquivo;
	}

	public void setPropriedadeArquivo(List<PropriedadeArquivo> propriedadeArquivo) {
		this.propriedadeArquivo = propriedadeArquivo;
	}
}
