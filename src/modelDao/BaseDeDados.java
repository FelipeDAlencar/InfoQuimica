package modelDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import model.Jogador;

public class BaseDeDados {
	public static ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	public static XStream xml = new XStream(new DomDriver());

	public static void salvarJogador(Jogador jogador) throws IOException {
		File file = new File("Arquivo\\base.xml");

		ArrayList<Jogador> jogadoresTemp = buscarJogadores();

		jogadoresTemp.add(jogador);

		xml.processAnnotations(Jogador.class);
		String rankXml = xml.toXML(jogadoresTemp);

		PrintWriter writer = new PrintWriter(file);
		writer.print(rankXml);
		writer.flush();
		writer.close();
	}

	public static ArrayList<Jogador> buscarJogadores() throws IOException {
		
		File file = new File("Arquivo\\base.xml");

		XStream xml = new XStream(new Dom4JDriver());
		xml.alias("Jogadores", ArrayList.class);

		//jogadores = (ArrayList) xml.fromXML(file);43
		jogadores = (ArrayList<Jogador>)xml.fromXML(file);

		return jogadores;
	}

}
