package alberto.boedo.wirtz.DetectorColores;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import Filtros.FiltradoEstatico;
import Helpers.WindowCenterHelper;

public class FiltroEstaticoTest {
	static List<String> listaFotos;

	public static void main(String[] args) {
		final Shell shell;
		final Display display = new Display();
		shell = new Shell(display, SWT.MIN);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(800, 600);
		
		Label labelFoto = new Label(shell, SWT.NONE);
		listaFotos= FiltradoEstatico.conversor("kjakja","ijhjsiaji");
		
		labelFoto.setImage(new Image(display, listaFotos.get(1)));
		

		// Centrar la ventana
		WindowCenterHelper.centrarVentana(display, shell);

		shell.open();

		// Con esto hacemos que la shell no se desvanezca en tiempo de ejecucion
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
