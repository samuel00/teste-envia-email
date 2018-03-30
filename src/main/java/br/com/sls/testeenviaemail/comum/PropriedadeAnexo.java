package br.com.sls.testeenviaemail.comum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Properties;

public class PropriedadeAnexo {

	public static String geNomeArquivo() {
		return "mario-broz.png";
	}

	public static String getTipoArquivo() {
		return ".pnf";
	}

	public static String getBase64() {
		File file = new File("/home/samuel/Firefox_wallpaper.png");
		InputStream finput;
		try {
			finput = new FileInputStream(file);
			byte[] imageBytes = new byte[(int) file.length()];
			finput.read(imageBytes, 0, imageBytes.length);
			finput.close();
			return Base64.getEncoder().encodeToString(imageBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}
}
