package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.comum.CriadorDeEmail;
import br.com.sls.testeenviaemail.modelo.EmailDTO;
import br.com.sls.testeenviaemail.modelo.HTTPResponse;

public class TestaEnviaEmailSimples {

	protected String urlRecurso;

	@Before
	public void setUp() {
		this.urlRecurso = "envia-email/simples";
	}

	private JsonPath enviaRequisicao(EmailDTO emailDTO, int StatusCodeEsperado) {
		return given().header("Accept", "application/json").contentType("application/json").body(emailDTO).expect()
				.statusCode(StatusCodeEsperado).when().post(this.urlRecurso).andReturn().jsonPath();
	}

	@Test
	public void testarEnvioEmailSimplesSemDestinatario() {
		EmailDTO emailSemDestinatarioDTO = new CriadorDeEmail().criarEmailSemDestinatario().concluirEmailSemAnexo();
		JsonPath jsonPath = enviaRequisicao(emailSemDestinatarioDTO, HttpStatus.BAD_REQUEST.value());
		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo destinatario não pode ser nulo!", hTTPResponse.getMensagem());
	}

	@Test
	public void testarEnvioEmailSimplesSemTexto() {
		EmailDTO emailSemTextoDTO = new CriadorDeEmail().criarEmailSemTexto().concluirEmailSemAnexo();
		JsonPath jsonPath = enviaRequisicao(emailSemTextoDTO, HttpStatus.BAD_REQUEST.value());
		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo texto não pode ser nulo!", hTTPResponse.getMensagem());
	}

	@Test
	public void testarEnvioEmailSimplesSemAssunto() {
		EmailDTO emailSemAssuntoDTO = new CriadorDeEmail().criarEmailSemAssunto().concluirEmailSemAnexo();
		JsonPath jsonPath = enviaRequisicao(emailSemAssuntoDTO, HttpStatus.BAD_REQUEST.value());
		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo assunto não pode ser nulo!", hTTPResponse.getMensagem());
	}

	@Test
	public void testarEnvioEmailSimples() {
		EmailDTO emailCompletoDTO = new CriadorDeEmail().criarEmailCompleto().concluirEmailSemAnexo();
		JsonPath jsonPath = enviaRequisicao(emailCompletoDTO, HttpStatus.OK.value());
		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("Requisicao realizada com sucesso!", hTTPResponse.getMensagem());
	}

}
