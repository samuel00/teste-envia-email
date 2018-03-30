package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.modelo.EmailComAnexoDTO;
import br.com.sls.testeenviaemail.modelo.EmailDTO;
import br.com.sls.testeenviaemail.modelo.HTTPResponse;
import br.com.sls.testeenviaemail.modelo.PropriedadeArquivo;

public class TestaEnviaEmailComJson {
	
	protected EmailComAnexoDTO emailCompletoDTO;
	protected EmailComAnexoDTO emailSemAssuntoDTO;
	protected EmailComAnexoDTO emailSemDestinatarioDTO;
	protected EmailComAnexoDTO emailSemTextoDTO;
	protected PropriedadeArquivo propriedadeArquivo;
	protected String urlRecurso;
	
	
	@Test
	public void testarEnvioEmailSimplesSemDestinatario() {
		JsonPath jsonPath = given().header("Accept", "application/json").contentType("application/json")
				.body(emailSemDestinatarioDTO).expect().statusCode(400).when().post(this.urlRecurso).andReturn()
				.jsonPath();

		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo destinatario não pode ser nulo!", hTTPResponse.getMensagem());

	}

	@Test
	public void testarEnvioEmailSimplesSemTexto() {
		JsonPath jsonPath = given().header("Accept", "application/json").contentType("application/json")
				.body(emailSemTextoDTO).expect().statusCode(400).when().post(this.urlRecurso).andReturn()
				.jsonPath();

		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo texto não pode ser nulo!", hTTPResponse.getMensagem());

	}

	@Test
	public void testarEnvioEmailSimples() {
		JsonPath jsonPath = given().header("Accept", "application/json").contentType("application/json")
				.body(emailCompletoDTO).expect().statusCode(200).when().post(this.urlRecurso).andReturn()
				.jsonPath();

		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("Requisicao realizada com sucesso!", hTTPResponse.getMensagem());

	}

	// {"mensagem":"Requisicao realizada com
	// sucesso!","status":"OK","statusCode":200}


}
