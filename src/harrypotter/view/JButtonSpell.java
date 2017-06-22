package harrypotter.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

import javax.swing.JButton;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

public class JButtonSpell extends JButton
{
	private Spell spell;
	public JButtonSpell(Spell s)
	{
		super();
		spell = s;
		this.setBorderPainted(false);
//		this.setOpaque(false);
		setFont(new Font("SignPainter",Font.BOLD,20));
		if(spell instanceof HealingSpell)
			this.setToolTipText(("Healing Spell, Cost:" + spell.getCost() + ", Default Cooldown:"
					+ spell.getDefaultCooldown() + ", CoolDown:" + spell.getCoolDown() + ", Healing Amount:" + ((HealingSpell) spell).getHealingAmount())+ ".");
		if(spell instanceof DamagingSpell)
			setToolTipText("Damaging Spell" + ", Cost:" + spell.getCost() + ", Default Cooldown:"
			+ spell.getDefaultCooldown() + ", CoolDown:" + spell.getCoolDown()+ ", Damage Amount:" + ((DamagingSpell) spell).getDamageAmount()+".");
			
		if(spell instanceof RelocatingSpell)
			setToolTipText("Relocating Spell" + ", Cost:" + spell.getCost() + ", Default Cooldown:"
					+ spell.getDefaultCooldown() + ", CoolDown:" + spell.getCoolDown() + ", Range:"+ ((RelocatingSpell) spell).getRange()+".");
	}
	public Spell getSpell() {
		return spell;
	}
	public void setSpell(Spell spell) {
		this.spell = spell;
	}
//	public void setText(String arg0) {
//	    super.setText(arg0);
//	    FontMetrics metrics = getFontMetrics(getFont()); 
//	    int width = metrics.stringWidth( getText() );
//	    int height = metrics.getHeight();
//	    Dimension newDimension =  new Dimension(width+40,height+10);
//	    setPreferredSize(newDimension);
//	    setBounds(new Rectangle(
//	                   getLocation(), getPreferredSize()));
//	}
}
