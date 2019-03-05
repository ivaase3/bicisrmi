package servidor;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bicimad.Bicicleta;
import bicimad.Interfaz;
import bicimad.Punto;


public class Servidor implements Interfaz{


	
	public static ArrayList<Punto> puntos;
	static Vista vista;

	public static void main(String[] args) {
	
			// creacion de bicis y puntos
			int id = 0;

			puntos = new ArrayList<>();
			
			
			
			System.out.println("creando los datos");
			for (int i = 0; i < Constantes.N_PUNTOS; i++) {
				Punto punto = new Punto("calle " + i, Constantes.N_BICIS_INICIO);
				for (int j = 0; j < Constantes.N_BICIS_INICIO; j++) {
					Bicicleta bicicleta = new Bicicleta(id);
					punto.getPunto().add(bicicleta);
					punto.setnDisponibles(punto.getnDisponibles() + 1);
					id++;
				}
				puntos.add(punto);
			}

			// Inicializacion
			System.out.println("Esperando mensajes");
			vista = new Vista();
			iniciaDatosVentana(vista,puntos);
			
			
			System.out.println("Conexion iniciada");
			Registry reg = null;
			
			try {
				reg = LocateRegistry.createRegistry(Constantes.PUERTO);
				System.out.println("Creando el objeto servidor e inscribiendolo en el registro…");
				Servidor servidor = new Servidor();
				
				reg.rebind("Bici", (Interfaz) UnicastRemoteObject.exportObject(servidor, 0));
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			
		

	}
	public static void iniciaDatosVentana(Vista vista, ArrayList<Punto> puntos) {
		vista.tfPunto1.setText(String.valueOf(puntos.get(0).getTotal()));
		vista.tfPunto2.setText(String.valueOf(puntos.get(1).getTotal()));
		vista.tfPunto3.setText(String.valueOf(puntos.get(2).getTotal()));
		vista.tfPunto4.setText(String.valueOf(puntos.get(3).getTotal()));
	}
	
	
	public static void rfrscar(Vista vista, ArrayList<Punto> puntos){
		vista.tfPunto1.setText(String.valueOf(puntos.get(0).getnDisponibles()));
		vista.tfPunto2.setText(String.valueOf(puntos.get(1).getnDisponibles()));
		vista.tfPunto3.setText(String.valueOf(puntos.get(2).getnDisponibles()));
		vista.tfPunto4.setText(String.valueOf(puntos.get(3).getnDisponibles()));
	}
	
	
	@Override
	public Object cambio(String accion) throws RemoteException {
		switch (accion) {
		case "refrescar":
			rfrscar(vista,puntos);
			int[] numerosr = new int[]{
					puntos.get(0).getnDisponibles(), 
					puntos.get(1).getnDisponibles(),
					puntos.get(2).getnDisponibles(),
					puntos.get(3).getnDisponibles()
			};
			return numerosr;
		case "coger1":
		
			if (puntos.get(0).getnDisponibles() > 0) {
				puntos.get(0).setnDisponibles(puntos.get(0).getnDisponibles() - 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "coger2":
			if (puntos.get(1).getnDisponibles() > 0) {
				puntos.get(1).setnDisponibles(puntos.get(1).getnDisponibles() - 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "coger3":
			if (puntos.get(2).getnDisponibles() > 0) {
				puntos.get(2).setnDisponibles(puntos.get(2).getnDisponibles() - 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "coger4":
			if (puntos.get(3).getnDisponibles() > 0) {
				puntos.get(3).setnDisponibles(puntos.get(3).getnDisponibles() - 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "dejar1":
			if (puntos.get(0).getnDisponibles() < Constantes.N_MAXIMO_BICIS) {
				puntos.get(0).setnDisponibles(puntos.get(0).getnDisponibles() + 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "dejar2":
			if (puntos.get(1).getnDisponibles() < Constantes.N_MAXIMO_BICIS) {
				puntos.get(1).setnDisponibles(puntos.get(1).getnDisponibles() + 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "dejar3":
			if (puntos.get(2).getnDisponibles() < Constantes.N_MAXIMO_BICIS) {
				puntos.get(2).setnDisponibles(puntos.get(2).getnDisponibles() + 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		case "dejar4":
			if (puntos.get(3).getnDisponibles() < Constantes.N_MAXIMO_BICIS) {
				puntos.get(3).setnDisponibles(puntos.get(3).getnDisponibles() + 1);
				rfrscar(vista,puntos);
				int[] numeros = new int[]{
						puntos.get(0).getnDisponibles(), 
						puntos.get(1).getnDisponibles(),
						puntos.get(2).getnDisponibles(),
						puntos.get(3).getnDisponibles()
				};
				return numeros;
			} else {
				return "error";
			}
//			break;
		}		
		return "error";
	}
}



