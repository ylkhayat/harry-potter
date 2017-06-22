package harrypotter.model.character;

import java.util.ArrayList;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.magic.Spell;

public class HufflepuffWizard extends Wizard implements Champion
{

	public HufflepuffWizard(String n) {
		super(n,1000,450);
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		super.setTraitCooldown(0);

	}
	public void useTrait() throws InCooldownException
	{
		if(getListener() != null)
			if(getTraitCooldown() <= 0)
				getListener().onHufflepuffTrait();
			else
				throw new InCooldownException(getTraitCooldown());
	}
	public void restore() {
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		super.setTraitCooldown(0);
		ArrayList<Spell> spells = getSpells();
		Spell temp;
		for (int i = 0; i < spells.size(); i++)
		{
			temp = spells.get(i);
			temp.setCoolDown(0);
		}
		//getSpells().clear();
		getInventory().clear();
		
	}
}
