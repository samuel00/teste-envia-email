package br.com.sls.testeenviaemail;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.jayway.restassured.path.json.JsonPath;

import br.com.sls.testeenviaemail.comum.PropriedadeAnexo;
import br.com.sls.testeenviaemail.modelo.EmailComAnexoDTO;
import br.com.sls.testeenviaemail.modelo.PropriedadeArquivo;

public class TestaEnvioDeEmailComAnexoComJson {

	private static final String URL_RECURSO = "/envia-email/comanexo";
	protected EmailComAnexoDTO emailCompletoDTO;
	protected EmailComAnexoDTO emailSemAssuntoDTO;
	protected EmailComAnexoDTO emailSemDestinatarioDTO;
	protected EmailComAnexoDTO emailSemTextoDTO;
	protected EmailComAnexoDTO emailSemArquivoDTO;
	protected PropriedadeArquivo propriedadeArquivo;

	protected String urlRecurso;

	@Before
	public void setUp() {
		this.emailCompletoDTO = new EmailComAnexoDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL COM JSON",
				"Parabéns seu teste funcionou", PropriedadeAnexo.geNomeArquivo(), PropriedadeAnexo.getTipoArquivo(),
				PropriedadeAnexo.getBase64(), new ArrayList<PropriedadeArquivo>());
		this.emailSemAssuntoDTO = new EmailComAnexoDTO("ifpa.santana@gmail.com", null, "Parabéns seu teste funcionou",
				PropriedadeAnexo.geNomeArquivo(), PropriedadeAnexo.getTipoArquivo(), PropriedadeAnexo.getBase64(),
				new ArrayList<PropriedadeArquivo>());
		this.emailSemDestinatarioDTO = new EmailComAnexoDTO(null, "TESTE AUTOMATIZADO DE EMAIL COM JSON",
				"Parabéns seu teste funcionou", PropriedadeAnexo.geNomeArquivo(), PropriedadeAnexo.getTipoArquivo(),
				PropriedadeAnexo.getBase64(), new ArrayList<PropriedadeArquivo>());
		this.emailSemTextoDTO = new EmailComAnexoDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL COM JSON",
				null, PropriedadeAnexo.geNomeArquivo(), PropriedadeAnexo.getTipoArquivo(), PropriedadeAnexo.getBase64(),
				new ArrayList<PropriedadeArquivo>());
		this.emailSemArquivoDTO = new EmailComAnexoDTO("ifpa.santana@gmail.com", "TESTE AUTOMATIZADO DE EMAIL COM JSON",
				"Parabéns seu teste funcionou");
		this.urlRecurso = URL_RECURSO;
	}
	
	private void enviaRequisicao(EmailComAnexoDTO emailComAnexoDTO, int StatusCodeEsperado) {
		given().header("Accept", "application/json").contentType("application/json").body(emailComAnexoDTO).expect()
		.statusCode(StatusCodeEsperado).when().post(URL_RECURSO).andReturn().jsonPath();
	}

	@Test
	public void testaEnvioDeEmailCompleto() {
		enviaRequisicao(emailCompletoDTO,HttpStatus.OK.value());
	}	

	@Test
	public void testaEnvioDeEmailSemAssunto() {
		enviaRequisicao(emailSemAssuntoDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemDestinatario() {
		enviaRequisicao(emailSemDestinatarioDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemTexto() {
		enviaRequisicao(emailSemTextoDTO,HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testaEnvioDeEmailSemAnexo() {
		enviaRequisicao(emailSemArquivoDTO,HttpStatus.BAD_REQUEST.value());
	}

}
