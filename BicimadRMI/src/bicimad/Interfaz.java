package bicimad;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Interfaz extends Remote{

	
	public Object cambio(String accion)throws RemoteException; 
}
