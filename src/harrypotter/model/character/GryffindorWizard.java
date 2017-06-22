package harrypotter.model.character;

import java.util.ArrayList;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;


public class GryffindorWizard extends Wizard implements Champion
{
	public GryffindorWizard(String n)
	{
		super(n,900,500);
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());

	}

	public void useTrait() throws InCooldownException
	{
		if(getListener() != null)
			System.out.println();
		System.out.println();
			if(getTraitCooldown() <=  0)
				getListener().onGryffindorTrait();
			else
				throw new InCooldownException(getTraitCooldown());
	}
	public void restore() {
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		setTraitCooldown(0);
		ArrayList<Spell> spells = getSpells();
		Spell temp;
		for (int i = 0; i < spells.size(); i++)
		{
			temp = spells.get(i);
			temp.setCoolDown(0);
		}
	}
}
