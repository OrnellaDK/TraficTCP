package serveur;

import java.io.IOException;

class MainServeur {
	private final static int PORT = 1234;

	public static void main(String[] args) {
		try {
			(new Serveur(PORT)).run();
		}
		catch (IOException e) {
			System.err.println("Pb lors de la création du serveur : " +  e);			
		}
	}
}
