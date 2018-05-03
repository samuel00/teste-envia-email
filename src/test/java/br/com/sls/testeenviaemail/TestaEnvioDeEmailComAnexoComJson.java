package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.sls.testeenviaemail.comum.CriadorDeEmail;
import br.com.sls.testeenviaemail.comum.Propriedade;
import br.com.sls.testeenviaemail.modelo.EmailComAnexoDTO;

public class TestaEnvioDeEmailComAnexoComJson {

	private static final String URL_RECURSO = "/comanexo";
	protected String urlRecurso;

	@Before
	public void setUp() {
		baseURI = Propriedade.configuracoes.getProperty("base.url.teste");
		this.urlRecurso = URL_RECURSO;
	}
	
	private void enviaRequisicao(EmailComAnexoDTO emailComAnexoDTO, int StatusCodeEsperado) {
		given().header("Accept", "application/json").contentType("application/json").body(emailComAnexoDTO).expect()
		.statusCode(StatusCodeEsperado).when().post(URL_RECURSO).andReturn().jsonPath();
	}

	@Test
	public void testaEnvioDeEmailCompleto() {
		EmailComAnexoDTO emailCompletoDTO = new CriadorDeEmail().criarEmailCompleto().criarArquivo().concluirEmailComAnexo();
		enviaRequisicao(emailCompletoDTO,HttpStatus.OK.value());
	}	

	@Test
	public void testaEnvioDeEmailSemAssunto() {
		EmailComAnexoDTO emailSemAssuntoDTO = new CriadorDeEmail().criarEmailSemAssunto().criarArquivo().concluirEmailComAnexo();
		enviaRequisicao(emailSemAssuntoDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemDestinatario() {
		EmailComAnexoDTO emailSemDestinatarioDTO = new CriadorDeEmail().criarEmailSemDestinatario().criarArquivo().concluirEmailComAnexo();
		enviaRequisicao(emailSemDestinatarioDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemTexto() {
		EmailComAnexoDTO emailSemTextoDTO = new CriadorDeEmail().criarEmailSemTexto().criarArquivo().concluirEmailComAnexo();
		enviaRequisicao(emailSemTextoDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemAnexo() {
		EmailComAnexoDTO emailSemArquivoDTO = new CriadorDeEmail().criarEmailSemTexto().naoCriarArquivo().concluirEmailComAnexo();
		enviaRequisicao(emailSemArquivoDTO,HttpStatus.BAD_REQUEST.value());
	}

}
