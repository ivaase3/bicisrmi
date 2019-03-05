package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bicimad.Interfaz;
import bicimad.Punto;


public class Cliente implements ActionListener {
	public static boolean bici = false;
	static VentanaC ventana = new VentanaC();
	static Object objetoEntrada = null;
	static Interfaz interfaz = null;
	static Registry registry;
	
	
	
	public Cliente(VentanaC ventana) {
			
			this.ventana = ventana;

			String host = "localhost";
			int puerto = 5555;
			
			try {
				System.out.println("Localizando registro de objetos remotos...");
//				Registry registry;
				registry = LocateRegistry.getRegistry("localhost", 5555);
				interfaz = (Interfaz) registry.lookup("Bici");
			} catch (RemoteException e) {
			e.printStackTrace();
			} catch (NotBoundException e) {
			e.printStackTrace();
			}
			
			




			ventana.btRefrescar.setActionCommand("refrescar");
			ventana.btRefrescar.addActionListener(this);
			ventana.btCoger1.setActionCommand("coger1");
			ventana.btCoger1.addActionListener(this);
			ventana.btCoger2.setActionCommand("coger2");
			ventana.btCoger2.addActionListener(this);
			ventana.btCoger3.setActionCommand("coger3");
			ventana.btCoger3.addActionListener(this);
			ventana.btCoger4.setActionCommand("coger4");
			ventana.btCoger4.addActionListener(this);
			ventana.btDejar1.setActionCommand("dejar1");
			ventana.btDejar1.addActionListener(this);
			ventana.btDejar2.setActionCommand("dejar2");
			ventana.btDejar2.addActionListener(this);
			ventana.btDejar3.setActionCommand("dejar3");
			ventana.btDejar3.addActionListener(this);
			ventana.btDejar4.setActionCommand("dejar4");
			ventana.btDejar4.addActionListener(this);


			
			


	}

	public static void main(String[] args) {

		new Cliente(ventana);

	}

	public static void refrescar(ArrayList<Punto> puntos, VentanaC ventana, boolean bici) {

		ventana.tfP1.setText(String.valueOf(puntos.get(0).getnDisponibles()));
		ventana.tfP2.setText(String.valueOf(puntos.get(1).getnDisponibles()));
		ventana.tfP3.setText(String.valueOf(puntos.get(2).getnDisponibles()));
		ventana.tfP4.setText(String.valueOf(puntos.get(3).getnDisponibles()));

		if (bici) {
			ventana.btCoger1.setEnabled(false);
			ventana.btCoger2.setEnabled(false);
			ventana.btCoger3.setEnabled(false);
			ventana.btCoger4.setEnabled(false);
			if (puntos.get(0).getnDisponibles() < 10) {
				ventana.btDejar1.setEnabled(true);
			}
			if (puntos.get(1).getnDisponibles() < 10) {
				ventana.btDejar2.setEnabled(true);
			}
			if (puntos.get(2).getnDisponibles() < 10) {
				ventana.btDejar3.setEnabled(true);
			}
			if (puntos.get(3).getnDisponibles() < 10) {
				ventana.btDejar4.setEnabled(true);
			}

		} else {
			ventana.btDejar1.setEnabled(false);
			ventana.btDejar2.setEnabled(false);
			ventana.btDejar3.setEnabled(false);
			ventana.btDejar4.setEnabled(false);
			if (puntos.get(0).getnDisponibles() > 0) {
				ventana.btDejar1.setEnabled(true);
			}
			if (puntos.get(1).getnDisponibles() > 0) {
				ventana.btDejar2.setEnabled(true);
			}
			if (puntos.get(2).getnDisponibles() > 0) {
				ventana.btDejar3.setEnabled(true);
			}
			if (puntos.get(3).getnDisponibles() > 0) {
				ventana.btDejar4.setEnabled(true);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
//			Object objetoEntrada = null;
			String accion = "";
			Interfaz interfaz = (Interfaz) registry.lookup("Bici");
			switch (e.getActionCommand()) {
			case "refrescar":
				accion = "refrescar";
				interfaz.cambio(accion);
				
				
			
				refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
				
				break;
			case "coger1":

				accion = "coger1";
				System.out.println(interfaz.cambio(accion)); 
				
				if (!(objetoEntrada instanceof String)) {
					
					
					
					System.out.println(((ArrayList<Punto>) objetoEntrada).get(0).getnDisponibles()); 
//					System.out.println(((ArrayList<Punto>) puntos).get(0).getnDisponibles()); 
					
					bici = true;
					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "coger2":

				accion="coger2";	
			
				
			
				if (!(objetoEntrada instanceof String)) {
					//TODO le resto uno porque no se por que le mal el objeto 
					((ArrayList<Punto>) objetoEntrada).get(1).setnDisponibles(((ArrayList<Punto>) objetoEntrada).get(1).getnDisponibles() - 1);					
					bici = true;
					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
//			case "coger3":
//
//				perSal.writeObject("coger3");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;
//			case "coger4":
//
//				perSal.writeObject("coger4");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;

//			case "dejar1":
//
//				perSal.writeObject("dejar1");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;
//			case "dejar2":
//
//				perSal.writeObject("dejar2");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;
//			case "dejar3":
//
//				perSal.writeObject("dejar3");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;
//			case "dejar4":
//
//				perSal.writeObject("dejar4");
//				objetoEntrada = perEnt.readObject();
//				if (!(objetoEntrada instanceof String)) {
//					bici = true;
//					refrescar((ArrayList<Punto>) objetoEntrada, ventana, bici);
//
//				} else {
//					String mensaje = (String) objetoEntrada;
//					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//
//				break;
			default:

				break;

			}
		
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
