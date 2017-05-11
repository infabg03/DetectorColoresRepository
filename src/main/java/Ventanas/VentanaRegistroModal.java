package Ventanas;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import Componentes.BotonImagen;
import Componentes.BotonTexto;
import Componentes.LabeledEditText;
import Helpers.WindowCenterHelper;
import controlador.GestorEventos;

public class VentanaRegistroModal extends Dialog {
	private Display display;
	private Shell shell;
	private final String rutaImagen = "src/resources/return.png";
	boolean edicion = false;
	String passwd;

	public VentanaRegistroModal(Shell parent, boolean edicion) {
		super(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		display = parent.getDisplay();
		createContents();
		this.edicion = edicion;
	}

	public VentanaRegistroModal(Shell parent, boolean edicion, String passwd) {
		super(parent, SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		display = parent.getDisplay();
		this.edicion = edicion;
		this.passwd = passwd;
		createContents();

	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void createContents() {
		shell = new Shell(getParent(), getStyle());

		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				e.doit = false;
			}
		});

		shell.setSize(418, 600);

		WindowCenterHelper.centrarVentana(display, shell);

		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		shell.setLayout(gridLayout);

		Composite compo1 = new Composite(shell, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout(2, true);
		gridLayout2.marginWidth = 5;
		gridLayout2.marginHeight = 5;
		gridLayout2.verticalSpacing = 0;
		gridLayout2.horizontalSpacing = 0;
		compo1.setLayout(gridLayout2);

		final LabeledEditText nombreTxt = new LabeledEditText(compo1, SWT.LEFT, "", "Nombre", false, 45);

		Button btnImg = new BotonImagen().getBotonImagen(display, compo1, rutaImagen);
		GridData gridData = new GridData(SWT.CENTER, SWT.FILL, true, false);
		gridData.widthHint = SWT.DEFAULT;
		gridData.heightHint = SWT.DEFAULT;
		btnImg.setLayoutData(gridData);
		btnImg.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();

			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		

		final LabeledEditText apellidosTxt = new LabeledEditText(shell, SWT.NONE, "", "Apellidos", false, 50);
		final LabeledEditText emailTxt = new LabeledEditText(shell, SWT.NONE, "", "Email", false, 50);
		final LabeledEditText passwdTxt = new LabeledEditText(shell, SWT.NONE, "", "Password", true, 50);
		final LabeledEditText telefonoTxt = new LabeledEditText(shell, SWT.NONE, "", "Teléfono", false, 50);
		Label padre = new Label(shell, SWT.NONE);

		Button botonRegistro = new BotonTexto().devuelveBotonTexto(shell, SWT.NONE, "Confirmar");
		
		
		List<String> lista = new ArrayList<String>();
		lista.add(nombreTxt.getText());
		lista.add(apellidosTxt.getText());
		lista.add(emailTxt.getText());
		lista.add(passwdTxt.getText());
		lista.add(telefonoTxt.getText());
		
		botonRegistro.setLayoutData(new GridData(SWT.BEGINNING, SWT.END, true, true));
		botonRegistro.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {

				if (!edicion){
					List<String> lista = new ArrayList<String>();
					lista.add(nombreTxt.getText());
					lista.add(apellidosTxt.getText());
					lista.add(emailTxt.getText());
					lista.add(passwdTxt.getText());
					lista.add(telefonoTxt.getText());
					GestorEventos.insertaUsuario(lista);
				}
				else{
					List<String> lista = new ArrayList<String>();
					lista.add(nombreTxt.getText());
					lista.add(apellidosTxt.getText());
					lista.add(emailTxt.getText());
					lista.add(passwdTxt.getText());
					lista.add(telefonoTxt.getText());
					GestorEventos.modificoUsuario(lista, passwd);
					}
				
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
