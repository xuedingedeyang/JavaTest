package com.yang.huarongdao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

public class Person extends JButton implements FocusListener{
	int number;
	Color c = new Color(255, 245, 170);
	Font font = new Font("����", Font.BOLD, 12);
	public Person (int number,String s){
		super(s);
		setBackground(c);
		setFont(font);
		this.number = number;
		c = getBackground();
		addFocusListener(this);
	}
	@Override
	public void focusGained(FocusEvent e) {
		setBackground(Color.RED);
	}
	@Override
	public void focusLost(FocusEvent e) {
		setBackground(c);
	}
}