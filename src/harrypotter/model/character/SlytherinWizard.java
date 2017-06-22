package harrypotter.model.character;

import java.io.IOException;
import java.util.ArrayList;

import harrypotter.exceptions.InCooldownException;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.Direction;

public class SlytherinWizard extends Wizard implements Champion
{
	private Direction traitDirection;
	
	public SlytherinWizard(String n)
	{
		super(n,850,550);
		super.setHp(getDefaultHp());
		super.setIp(getDefaultIp());
		super.setTraitCooldown(0);
		
	}
	public void setTraitDirection(Direction traitDirection)
	{
		this.traitDirection = traitDirection;
	}
	public Direction getTraitDirection()
	{
		return traitDirection;
	}
	public void useTrait() throws Exception
	{	
		if(getListener() != null)
			if(getTraitDirection() != null)
					getListener().onSlytherinTrait(getTraitDirection());
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
