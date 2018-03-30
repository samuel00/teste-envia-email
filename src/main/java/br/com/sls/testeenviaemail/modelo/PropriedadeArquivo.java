package br.com.sls.testeenviaemail.modelo;

import java.io.Serializable;

public class PropriedadeArquivo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7727014218025684L;
	
	private String nomeArquivo;
	
	private String tipoArquivo;
	
	private String base64;
	
	public PropriedadeArquivo(){}

	public PropriedadeArquivo(String nomeArquivo, String tipoArquivo, String base64) {
		this.nomeArquivo = nomeArquivo;
		this.tipoArquivo = tipoArquivo;
		this.base64 = base64;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}


}
