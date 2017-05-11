package Ventanas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import Componentes.BotonTexto;
import Componentes.LabeledCombo;
import Componentes.LabeledEditText;
import Helpers.WindowCenterHelper;
import controlador.GestorEventos;

public class VentanaPrincipal implements Runnable {

	static Shell shell;
	
	public static void main(String[] args) {
		final Display display = new Display();
		shell = new Shell(display, SWT.MIN);
		shell.setLayout(new GridLayout(1, true));
		shell.setSize(500, 300);

		// Centrar la ventana
		WindowCenterHelper.centrarVentana(display, shell);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.widthHint = SWT.DEFAULT;
		gridData.heightHint = SWT.DEFAULT;
		composite.setLayoutData(gridData);

		final LabeledEditText login = new LabeledEditText(composite, SWT.NONE, "", "Login", false, 45);
		LabeledCombo combo = new LabeledCombo(composite, "Idioma", SWT.READ_ONLY | SWT.CHECK);
		combo.add("Español");
		combo.add("English");

		final LabeledEditText passwd = new LabeledEditText(shell, SWT.NONE, "", "Password", true, 50);

		Button registro = new BotonTexto().devuelveBotonTexto(shell, SWT.NONE, "Login");
		registro.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				if (GestorEventos.correctLogin(login.getText(), passwd.getText())) {
					System.out.println("encontrado");
					// Thread tVentanaFotos = new Thread(new VentanaFotos());
					Thread tVentanaSelectora = new Thread(new VentanaSelectora(login.getText()));
					display.dispose();
					tVentanaSelectora.run();
				} else {
					// create a dialog with ok and cancel buttons and a question
					// icon
					MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK );
					dialog.setText("Informacion de acceso");
					dialog.setMessage("El login o password son incorrectos");

					// open dialog and await user selection
					dialog.open();
					login.setText("");
					passwd.setText("");
				}

			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		Link link = new Link(shell, SWT.NONE);
		link.setText("¿No estas registrado? <A>Registrate ahora</A>");
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				display.asyncExec(new Runnable() {

					public void run() {
						VentanaRegistroModal secondWindow = new VentanaRegistroModal(VentanaPrincipal.this.shell, false);
						secondWindow.open();

					}
				});
			}
		});

		shell.open();

		// con esto hacemos que la shell no se desvanezca en tiempo de ejecucion
		while (!shell.isDisposed()) {
			try {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			} catch (Exception e) {

			}

		}
		display.dispose();
	}


	public void run() {
		main(null);
	}

}
