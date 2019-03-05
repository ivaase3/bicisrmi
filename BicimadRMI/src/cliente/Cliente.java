package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import bicimad.Interfaz;

public class Cliente implements ActionListener {
	public static boolean bici = false;
	static VentanaC ventana = new VentanaC();
	static Object objetoEntrada = null;
	static Interfaz interfaz = null;
	static Registry registry;

	public Cliente(VentanaC ventana) {

		this.ventana = ventana;


		try {
			System.out.println("Bienvenido a la aplicacion"); 
			System.out.println("Localizando registro de objetos remotos...");

			registry = LocateRegistry.getRegistry(Constante.IP, Constante.PUERTO);
			interfaz = (Interfaz) registry.lookup("Bici");
			System.out.println("localizado");
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

	public static void refrescar(int[] numeros, VentanaC ventana, boolean bici) {

		ventana.tfP1.setText(String.valueOf(numeros[0]));
		ventana.tfP2.setText(String.valueOf(numeros[1]));
		ventana.tfP3.setText(String.valueOf(numeros[2]));
		ventana.tfP4.setText(String.valueOf(numeros[3]));

		if (bici) {
			ventana.btCoger1.setEnabled(false);
			ventana.btCoger2.setEnabled(false);
			ventana.btCoger3.setEnabled(false);
			ventana.btCoger4.setEnabled(false);

			if (numeros[0] < Constante.N_MAXIMO_BICIS) {
				ventana.btDejar1.setEnabled(true);
			}
			if (numeros[1] < Constante.N_MAXIMO_BICIS) {
				ventana.btDejar2.setEnabled(true);
			}
			if (numeros[2] < Constante.N_MAXIMO_BICIS) {
				ventana.btDejar3.setEnabled(true);
			}
			if (numeros[3] < Constante.N_MAXIMO_BICIS) {
				ventana.btDejar4.setEnabled(true);
			}

		} else {
			ventana.btDejar1.setEnabled(false);
			ventana.btDejar2.setEnabled(false);
			ventana.btDejar3.setEnabled(false);
			ventana.btDejar4.setEnabled(false);

			if (numeros[0] > 0) {
				ventana.btCoger1.setEnabled(true);
			}
			if (numeros[1] > 0) {
				ventana.btCoger2.setEnabled(true);
			}
			if (numeros[2] > 0) {
				ventana.btCoger3.setEnabled(true);
			}
			if (numeros[3] > 0) {
				ventana.btCoger4.setEnabled(true);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String accion = "";

			switch (e.getActionCommand()) {
			case "refrescar":
				accion = "refrescar";
				objetoEntrada = interfaz.cambio(accion);
				int[] numerosr = (int[]) objetoEntrada;

				refrescar(numerosr, ventana, bici);

				break;
			case "coger1":

				accion = "coger1";

				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[0]);

					bici = true;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "coger2":

				accion = "coger2";
				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[1]);

					bici = true;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "coger3":

				accion = "coger3";
				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[2]);

					bici = true;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "coger4":

				accion = "coger4";
				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[3]);

					bici = true;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "dejar1":

				accion = "dejar1";

				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[0]);

					bici = false;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "dejar2":

				accion = "dejar2";

				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[0]);

					bici = false;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "dejar3":

				accion = "dejar3";

				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[0]);

					bici = false;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}

				break;
			case "dejar4":

				accion = "dejar4";

				objetoEntrada = interfaz.cambio(accion);
				if (!(objetoEntrada instanceof String)) {
					int[] numeros = (int[]) objetoEntrada;

					System.out.println(numeros[0]);

					bici = false;
					refrescar(numeros, ventana, bici);

				} else {
					String mensaje = (String) objetoEntrada;
					JOptionPane.showMessageDialog(null, "mensaje", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				break;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
