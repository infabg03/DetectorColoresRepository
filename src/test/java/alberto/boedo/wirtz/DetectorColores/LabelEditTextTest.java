package alberto.boedo.wirtz.DetectorColores;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Componentes.LabeledEditText;

public class LabelEditTextTest {
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());
		

		LabeledEditText labelEditText = new LabeledEditText(shell, SWT.NONE, "Introduce login", "Login: ",false, 50);
		LabeledEditText labelEditText2 = new LabeledEditText(shell, SWT.NONE, "Introduce password", "Password: ",true, 50);
		
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
