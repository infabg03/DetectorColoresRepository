package alberto.boedo.wirtz.DetectorColores;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Componentes.LabeledCombo;

public class LabeledComboTest {

	// SWT.DROP_DOWN

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));
		
		

		LabeledCombo combo = new LabeledCombo(shell, "Paises", SWT.DROP_DOWN);

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
