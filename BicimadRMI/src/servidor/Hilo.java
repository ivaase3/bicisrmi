package servidor;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bicimad.Interfaz;
import bicimad.Punto;


public class Hilo extends Thread {
	
	int cont;
	Vista vista;
	Registry registro;
	Servidor servidor;

	Hilo(Registry registro, int cont, ArrayList<Punto> puntos, Vista vista, Servidor servidor) {
		
		this.cont = cont;
		this.registro = registro;
		this.vista = vista;
		this.servidor = servidor;
	}

	public void run() {
		vista.setTitle("clientes conectados: " + cont);
		

	}

	

	

}
