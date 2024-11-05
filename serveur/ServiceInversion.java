package serveur;

import java.io.*;
import java.net.*;

class ServiceInversion implements Runnable {
	private final Socket client;

	ServiceInversion(Socket socket) {
		client = socket;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			String line = in.readLine();	
			System.out.println("Chaine recue du client: "+line); //<----
			String invLine = new String (new StringBuffer(line).reverse());
			// Pause de 10s
			try {
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) {System.out.println("AIE");}
			System.out.println("Ca m'a pris 10s de traitement !");
			out.println(invLine);
		}
		catch (IOException e) {

		}
		//Fin du service d'inversion
		try {client.close();} catch (IOException e2) {}
	}

	public void lancer() {
		new Thread(this).start();	
	}

	@Override
	protected void finalize() throws Throwable {
		client.close(); 
	}

	@Override
	public String toString() {
		return "Inversion de texte";
	}
}
