package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.modelo.EmailDTO;
import br.com.sls.testeenviaemail.modelo.HTTPResponse;

public class TestaEnviaEmailSimples {
	
	private EmailDTO emailCompletoDTO;
	private EmailDTO emailSemAssuntoDTO;
	private EmailDTO emailSemDestinatarioDTO;
	private EmailDTO emailSemTextoDTO;
	protected String urlRecurso;
	
	@Before
	public void setUp() {
		emailCompletoDTO = new EmailDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL",
				"Parabéns seu teste funcionou");
		emailSemAssuntoDTO = new EmailDTO("ifpa.santana@gmail.com", null, "Parabéns seu teste funcionou");
		emailSemDestinatarioDTO = new EmailDTO(null, "TESTE AUTOMATIZADO DE EMAIL", "Parabéns seu teste funcionou");
		emailSemTextoDTO = new EmailDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL", null);
		this.urlRecurso = "envia-email/v2/simples";
	}
	
	
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
	public void testarEnvioEmailSimplesSemAssunto() {
		JsonPath jsonPath = given().header("Accept", "application/json").contentType("application/json")
				.body(emailSemAssuntoDTO).expect().statusCode(400).when().post(this.urlRecurso).andReturn()
				.jsonPath();

		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("O campo assunto não pode ser nulo!", hTTPResponse.getMensagem());

	}

	@Test
	public void testarEnvioEmailSimples() {
		JsonPath jsonPath = given().header("Accept", "application/json").contentType("application/json")
				.body(emailCompletoDTO).expect().statusCode(200).when().post(this.urlRecurso).andReturn()
				.jsonPath();

		HTTPResponse hTTPResponse = jsonPath.getObject("", HTTPResponse.class);
		assertEquals("Requisicao realizada com sucesso!", hTTPResponse.getMensagem());

	}

}
