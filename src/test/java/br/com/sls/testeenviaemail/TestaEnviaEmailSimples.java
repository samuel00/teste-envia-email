package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.comum.CriadorDeEmail;
import br.com.sls.testeenviaemail.comum.Propriedade;
import br.com.sls.testeenviaemail.modelo.EmailDTO;

public class TestaEnviaEmailSimples {
	
	private static final String URL_RECURSO = "/simples";

	@Before
	public void setUp() {
		baseURI = Propriedade.configuracoes.getProperty("base.url.teste");
	}

	private JsonPath enviaRequisicao(EmailDTO emailDTO, int StatusCodeEsperado) {
		return given().header("Accept", "application/json").contentType("application/json").body(emailDTO).expect()
				.statusCode(StatusCodeEsperado).when().post(URL_RECURSO).andReturn().jsonPath();
	}

	@Test
	public void testarEnvioEmailSimplesSemDestinatario() {
		EmailDTO emailSemDestinatarioDTO = new CriadorDeEmail().criarEmailSemDestinatario().concluirEmailSemAnexo();
		enviaRequisicao(emailSemDestinatarioDTO, HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testarEnvioEmailSimplesSemTexto() {
		EmailDTO emailSemTextoDTO = new CriadorDeEmail().criarEmailSemTexto().concluirEmailSemAnexo();
		enviaRequisicao(emailSemTextoDTO, HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testarEnvioEmailSimplesSemAssunto() {
		EmailDTO emailSemAssuntoDTO = new CriadorDeEmail().criarEmailSemAssunto().concluirEmailSemAnexo();
		enviaRequisicao(emailSemAssuntoDTO, HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testarEnvioEmailSimples() {
		EmailDTO emailCompletoDTO = new CriadorDeEmail().criarEmailCompleto().concluirEmailSemAnexo();
		enviaRequisicao(emailCompletoDTO, HttpStatus.OK.value());
	}

}
