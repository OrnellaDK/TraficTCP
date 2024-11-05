package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import java.io.*;
//import java.net.*;

class Client {
	private final static int PORT = 1234;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket socket = null;		
		try {
			// Cree ET connecte une socket pour communiquer avec le service se trouvant sur la
			// machine host au port PORT
			socket = new Socket(HOST, PORT);
			// Informe l'utilisateur que la connection est réalisée
			System.out.println("Connecté au serveur " + socket.getInetAddress() + ":"+ socket.getPort());
			
			// Cree et "connecte" les flux pour lire et ecrire DU TEXTE dans cette socket
			BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
			PrintWriter sout = new PrintWriter (socket.getOutputStream(), true);
			
			// Cree le stream pour lire du texte a partir du clavier 
			// (On pourrait aussi utiliser Scanner)
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			

			String message;
			System.out.println("Tapez une chaîne de caractères");
			System.out.print("--->");
			// lecture au clavier
			message = clavier.readLine();
			
			// envoie au serveur
			sout.println(message);
			// lit la réponse provenant du serveur
			message = sin.readLine();
			// Affiche la ligne envoyee par le serveur
			System.out.println("La chaîne inversée par le serveur\n--->" + message);
			socket.close();
		}
		catch (IOException e) { System.err.println(e); }

		// Refermer dans TOUS les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}
