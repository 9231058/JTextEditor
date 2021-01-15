package jtexteditor.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ColorDialog extends JDialog {

	private static final long serialVersionUID = 8964698413706415575L;
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JButton fgColorButton;
	private JButton bgColorButton;
	private Color fgColor = Color.ORANGE;
	private Color bgColor = Color.BLACK;

	public ColorDialog() {
		setBounds(100, 100, 300, 350);
		setModal(true);
		setResizable(false);
		contentPanel = new JPanel();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPanel.setLayout(sl_contentPane);

		fgColorButton = new JButton("Set Foreground Color");
		sl_contentPane.putConstraint(SpringLayout.WEST, fgColorButton, 10,
				SpringLayout.WEST, contentPanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, fgColorButton, 10,
				SpringLayout.NORTH, contentPanel);
		contentPanel.add(fgColorButton);
		fgColorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fgColor = JColorChooser.showDialog(ColorDialog.this,
						"Foreground Color", fgColor);
			}
		});

		bgColorButton = new JButton("Set Background Color");
		sl_contentPane.putConstraint(SpringLayout.NORTH, bgColorButton, 10,
				SpringLayout.SOUTH, fgColorButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, bgColorButton, 0,
				SpringLayout.WEST, fgColorButton);
		contentPanel.add(bgColorButton);
		bgColorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bgColor = JColorChooser.showDialog(ColorDialog.this,
						"Foreground Color", bgColor);
			}
		});

		buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton okButton = new JButton("Ok");
		buttonPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fgColor = Color.ORANGE;
				bgColor = Color.BLACK;
				dispose();
			}
		});
	}

	public Color getFgColor() {
		return fgColor;
	}

	public Color getBgColor() {
		return bgColor;
	}

}
