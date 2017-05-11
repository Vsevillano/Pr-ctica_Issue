package concesionario2.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionario2.estructura.Color;
import concesionario2.estructura.Fichero;
import concesionario2.estructura.Modelo;
import concesionario2.excepciones.CocheYaExisteException;
import concesionario2.excepciones.ColorNoValidoException;
import concesionario2.excepciones.MatriculaNoValidaException;
import concesionario2.excepciones.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AnnadirCoche extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnnadirCoche dialog = new AnnadirCoche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Color getColor() {
		if (rdbtnPlata.isSelected()) {
			return Color.PLATA;
		} else if (rdbtnRojo.isSelected()) {
			return Color.ROJO;
		} else if (rdbtnAzul.isSelected()) {
			return Color.AZUL;
		} else {
			return null;
		}
	}

	/**
	 * Create the dialog.
	 */
	public AnnadirCoche() {
		setTitle("A\u00F1adir coche");
		textMatricula.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!Fichero.almacen.checkMatricula(textMatricula.getText()))
					textMatricula.setForeground(java.awt.Color.RED);
			}

			@Override
			public void focusGained(FocusEvent e) {
				textMatricula.setForeground(java.awt.Color.BLACK);
			}
		});
		cancelButton.setText("Cerrar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Fichero.almacen.annadir(textMatricula.getText(), getColor(),
							(Modelo) comboModelo.getSelectedItem());
					JOptionPane.showMessageDialog(contentPanel, "Coche a�adido", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					textMatricula.setText("");
					Fichero.almacen.setModificado(true);
				} catch (ColorNoValidoException | ModeloNoValidoException ex) {
					JOptionPane.showMessageDialog(contentPanel, ex.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (MatriculaNoValidaException ex) {
					JOptionPane.showMessageDialog(contentPanel, ex.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);

				} catch (CocheYaExisteException ex) {
					JOptionPane.showMessageDialog(contentPanel, ex.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setText("A\u00F1adir");
		// Ocultamos botones que no son necesarios
		btnAtras.setVisible(false);
		btnAdelante.setVisible(false);
		okButton.setVisible(false);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
