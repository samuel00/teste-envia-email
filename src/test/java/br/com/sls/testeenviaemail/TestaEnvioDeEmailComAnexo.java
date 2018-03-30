package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.sls.testeenviaemail.modelo.EmailDTO;

public class TestaEnvioDeEmailComAnexo {

	private static final String URL_RECURSO = "/envia-email/multipart/comanexo";
	private EmailDTO emailDTO;
	private File arquivo1;
	private File arquivo2;

	@Before
	public void setUp() {
		emailDTO = new EmailDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL",
				"Parabéns seu teste funcionou");
		arquivo1 = new File("/home/samuel/activity-diagram-api-manager.png");
		arquivo2 = new File("/home/samuel/diagramas-api-manager.asta");
	}

	@Test
	public void testarEnvioMultiPart() {
		given().multiPart("arquivo", arquivo1).multiPart("arquivo", arquivo2)
				.formParam("assunto", emailDTO.getAssunto()).formParam("destinatario", emailDTO.getDestinatario())
				.formParam("texto", emailDTO.getTexto()).expect().statusCode(HttpStatus.OK.value()).when()
				.post(URL_RECURSO).andReturn().jsonPath();
	}

	@Test
	public void testarEnvioMultiPartSemAssunto() {
		given().multiPart("arquivo", arquivo1).multiPart("arquivo", arquivo2)
				.formParam("destinatario", emailDTO.getDestinatario()).formParam("texto", emailDTO.getTexto()).expect()
				.statusCode(HttpStatus.BAD_REQUEST.value()).when().post(URL_RECURSO).andReturn().jsonPath();
	}

	@Test
	public void testarEnvioMultiPartSemDestinatario() {
		given().multiPart("arquivo", arquivo1).multiPart("arquivo", arquivo2)
				.formParam("assunto", emailDTO.getAssunto()).formParam("texto", emailDTO.getTexto()).expect()
				.statusCode(HttpStatus.BAD_REQUEST.value()).when().post(URL_RECURSO).andReturn().jsonPath();

	}

	@Test
	public void testarEnvioMultiPartSemTexto() {
		given().multiPart("arquivo", arquivo1).multiPart("arquivo", arquivo2)
				.formParam("assunto", emailDTO.getAssunto()).formParam("destinatario", emailDTO.getDestinatario())
				.expect().statusCode(HttpStatus.BAD_REQUEST.value()).when().post(URL_RECURSO).andReturn().jsonPath();

	}

	@Test
	public void testarEnvioMultiPartSemAnexo() {
		given().multiPart(arquivo1)
				.formParam("assunto", emailDTO.getAssunto()).formParam("destinatario", emailDTO.getDestinatario())
				.formParam("texto", emailDTO.getTexto()).expect().statusCode(HttpStatus.BAD_REQUEST.value()).when()
				.post(URL_RECURSO).andReturn().jsonPath();
	}

}
