package serveur;

import java.io.*;
import java.net.*;

class Serveur {
	private final static int PORT = 1234;
	private ServerSocket listen_socket;
	private Socket accept_socket;

	// Cree un serveur TCP - objet de la classe ServerSocket
	Serveur(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	// Le serveur ecoute et accepte les connexions.
	// pour chaque connexion, il cree un ServiceInversion, 
	// qui va la traiter, et le lance
	public void run() {
		System.out.println("Serveur lance sur le port " + PORT);
		try {
			while(true) {
				System.out.println("J'attends un client...");
				accept_socket = listen_socket.accept(); // Appel bloquant !
				System.out.println("Youpi j'en ai un !");
				new ServiceInversion(accept_socket).lancer();
			}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}

	// restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
}
