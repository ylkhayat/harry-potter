package harrypotter.model.character;

import java.util.ArrayList;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.magic.Spell;

public class RavenclawWizard extends Wizard implements Champion
{
	public RavenclawWizard(String n)
	{
		super(n,750,700);
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		super.setTraitCooldown(0);
		
	}
	public void useTrait() throws InCooldownException
	{
		if(getListener() != null)
			if(getTraitCooldown() <= 0)
				getListener().onRavenclawTrait();
			else
				throw new InCooldownException(getTraitCooldown());
	}
	public void restore() {
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		super.setTraitCooldown(0);
		//getSpells().clear();
		ArrayList<Spell> spells = getSpells();
		Spell temp;
		for (int i = 0; i < spells.size(); i++)
		{
			temp = spells.get(i);
			temp.setCoolDown(0);
		}
		getInventory().clear();	
	}

}
