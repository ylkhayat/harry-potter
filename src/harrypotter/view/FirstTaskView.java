package harrypotter.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import harrypotter.model.magic.Potion;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.character.Champion;
import harrypotter.model.world.Cell;

public class FirstTaskView extends TaskView
{
    private JPanel markCell1;
    private JPanel markCell2;
	public FirstTaskView()
	{
		super();
		markCell1 = new JPanel();
		markCell2 = new JPanel();
	}
	public JPanel getMarkCell1() {
		return markCell1;
	}
	public void setMarkCell1(JPanel markCell1) {
		this.markCell1 = markCell1;
	}
	public JPanel getMarkCell2() {
		return markCell2;
	}
	public void setMarkCell2(JPanel markCell2) {
		this.markCell2 = markCell2;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
