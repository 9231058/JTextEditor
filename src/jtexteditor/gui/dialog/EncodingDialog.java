package jtexteditor.gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class EncodingDialog extends JDialog {

	private static final long serialVersionUID = 5674464397185962137L;
	private static ArrayList<Charset> charsets;
	private final JPanel contentPanel = new JPanel();
	private Charset selectedCharset = StandardCharsets.UTF_8;

	public EncodingDialog() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		JComboBox<Charset> comboBox = new JComboBox<>();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, comboBox, 10,
				SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, comboBox, 10,
				SpringLayout.WEST, contentPanel);
		contentPanel.add(comboBox);
		if (charsets == null) {
			charsets = new ArrayList<>();
			charsets.add(StandardCharsets.UTF_8);
			charsets.add(StandardCharsets.US_ASCII);
			charsets.add(StandardCharsets.ISO_8859_1);
			charsets.add(StandardCharsets.UTF_16);
			charsets.add(StandardCharsets.UTF_16BE);
			charsets.add(StandardCharsets.UTF_16LE);
		}
		for (int i = 0; i < charsets.size(); i++) {
			comboBox.addItem(charsets.get(i));
		}
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					selectedCharset = (Charset) event.getItem();
				}
			}
		});

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						selectedCharset = StandardCharsets.UTF_8;
						dispose();
					}
				});
			}
		}
	}

	public Charset getSelectedCharset() {
		return selectedCharset;
	}
}
