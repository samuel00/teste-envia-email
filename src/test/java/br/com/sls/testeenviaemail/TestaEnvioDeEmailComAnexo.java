package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.comum.CriadorDeEmail;
import br.com.sls.testeenviaemail.comum.Propriedade;
import br.com.sls.testeenviaemail.modelo.EmailDTO;

public class TestaEnvioDeEmailComAnexo {

	private static final String URL_RECURSO = "/multipart/comanexo";
	private static final String CAMINHO_ARQUIVO1 = "/home/samuel/Documentos/tarefas/27_CADASTRO_SOFTWARE_NFCE/mantis/0521535_nfce/prínt+02.png";
	private static final String CAMINHO_ARQUIVO2 = "/home/samuel/Downloads/CFSNFCe_PRT_F1_CadastrodeFornecedor.pdf";
	protected String urlRecurso;
	private EmailDTO emailDTO;
	private File arquivo1;
	private File arquivo2;

	@Before
	public void setUp() {
		emailDTO = new CriadorDeEmail().criarEmailCompleto().concluirEmailSemAnexo();
		baseURI = Propriedade.configuracoes.getProperty("base.url.teste");
		arquivo1 = new File(CAMINHO_ARQUIVO1);
		arquivo2 = new File(CAMINHO_ARQUIVO2);
	}

	@Test
	public void testarEnvioMultiPart() throws UnsupportedEncodingException {
		JsonPath jsonPathEvaluator = given()
				.config(RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8")))
				.multiPart("arquivo", arquivo1).queryParam("assunto", emailDTO.getAssunto())
				.queryParam("destinatario", emailDTO.getDestinatario()).queryParam("texto", emailDTO.getTexto())
				.expect().statusCode(HttpStatus.OK.value()).when().post(URL_RECURSO).andReturn().jsonPath();
		System.out.println(jsonPathEvaluator.get("mensagem"));
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
		given().multiPart(arquivo1).formParam("assunto", emailDTO.getAssunto())
				.formParam("destinatario", emailDTO.getDestinatario()).formParam("texto", emailDTO.getTexto()).expect()
				.statusCode(HttpStatus.BAD_REQUEST.value()).when().post(URL_RECURSO).andReturn().jsonPath();
	}

}
