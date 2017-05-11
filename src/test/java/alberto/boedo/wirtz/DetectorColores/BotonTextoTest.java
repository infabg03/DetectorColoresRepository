package alberto.boedo.wirtz.DetectorColores;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Componentes.BotonTexto;

public class BotonTextoTest {
	private final static Logger log = Logger.getLogger(BotonTextoTest.class);
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		Button botonTexto = new BotonTexto().devuelveBotonTexto(shell, SWT.NONE, "Pulsame");
		
		PropertyConfigurator.configure("src/resources/log4j.properties");
		
		log.error("Soy un error");
		
		//Ejemplo sobrescritura de un metodo de una clase creada por mi
		BotonTexto boton2 = new BotonTexto(){

			@Override
			public Button devuelveBotonTexto(Composite parent, int estilo, String texto) {
				// TODO Auto-generated method stub
				return super.devuelveBotonTexto(parent, estilo, texto);
			}
			
		};
		
		shell.open();
		
		// con esto hacemos que la shell no se desvanezca en tiempo de ejecucion
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
				display.dispose();
	}

}
