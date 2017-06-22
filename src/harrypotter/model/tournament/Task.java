package harrypotter.model.tournament;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.InvalidTraitActionException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.WizardListener;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Mystery;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.magic.Vengance;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.MysteryCell;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;

public abstract class Task implements WizardListener
{
	private ArrayList<Champion> champions;
	private Champion currentChamp;
	private Cell[][] map;
	private int allowedMoves;
	private boolean traitActivated;
	private ArrayList<Potion> potions;
	private LinkedList<Integer> availablePoints;
	private TaskListener listener;
	
	public TaskListener getListener() {
		return listener;
	}
	public void setListener(TaskListener listener) {
		this.listener = listener;
	}
	public Task(ArrayList<Champion> champions) throws IOException
	{
		map = new Cell[10][10];
		availablePoints = new LinkedList<Integer>();
		for (int i = 0; i < 100; i++) 
			availablePoints.add(i);
		Collections.shuffle(availablePoints);
		potions = new ArrayList<Potion>();
		this.champions = champions;
		restoreChamps(champions);
		for (int i = 0; i < champions.size(); i++)
		{
			champions.get(i).setListener(this);
		}
		loadPotions("Potions.csv");
	}
	public void restoreChamps(ArrayList<Champion> champions)
	{
		Champion tempChamp;
		for (int i = 0; i < champions.size(); i++)
		{
			tempChamp = champions.get(i);
			tempChamp.restore();
			tempChamp.getInventory().clear();
		}
	}
	public Champion getCurrentChamp() {
		return currentChamp;
	}
	public void setCurrentChamp(Champion currentChamp) {
		this.currentChamp = currentChamp;
	}
	public int getAllowedMoves() {
		return allowedMoves;
	}
	public void setAllowedMoves(int allowedMoves) {
		this.allowedMoves = allowedMoves;
	}
	public boolean isTraitActivated()
	{
		return traitActivated;
	}
	public void setTraitActivated(boolean traitActivated) {
		this.traitActivated = traitActivated;
	}
	public ArrayList<Champion> getChampions()
	{
		return champions;
	}
	public Cell[][] getMap() {
		return map;
	}
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	abstract void generateMap() throws IOException;
	
	public LinkedList<Integer> getAvailablePoints() {
		return availablePoints;
	}
	public void moveForward() throws Exception
	{
		Champion currChamp = getCurrentChamp();
		Point currPos = currChamp.getLocation();
		Cell champPos;
		if(currChamp instanceof SlytherinWizard && isTraitActivated())
			champPos = map[currPos.x+1][currPos.y];
		else
			champPos = map[currPos.x][currPos.y];
		Point forward = new Point(currPos.x-1,currPos.y);
		if(islegalMove(forward))
		{	
			if((map[forward.x][forward.y] instanceof EmptyCell || map[forward.x][forward.y] instanceof CollectibleCell))
			{	
				if(map[forward.x][forward.y] instanceof CollectibleCell)
					getCurrentChamp().getInventory().add(((CollectibleCell)(map[forward.x][forward.y])).getCollectible());
				map[forward.x][forward.y] = champPos;
				currChamp.setLocation(forward);
				if(currChamp instanceof SlytherinWizard && isTraitActivated())
					map[currPos.x+1][currPos.y] = new EmptyCell();
				else
					map[currPos.x][currPos.y] = new EmptyCell();
			}
			else
				throw new InvalidTargetCellException();
		}
		else
			throw new OutOfBordersException(); 
	}	
	public void moveBackward() throws Exception
	{
		Champion currChamp = getCurrentChamp();
		Point currPos = currChamp.getLocation();
		Cell champPos;
		if(currChamp instanceof SlytherinWizard && isTraitActivated())
			champPos = map[currPos.x-1][currPos.y];
		else
			champPos = map[currPos.x][currPos.y];
		Point backward = new Point(currPos.x+1,currPos.y);
		if(islegalMove(backward))
		{	
			if((map[backward.x][backward.y] instanceof EmptyCell || map[backward.x][backward.y] instanceof CollectibleCell))
			{
				if(map[backward.x][backward.y] instanceof CollectibleCell)
					getCurrentChamp().getInventory().add(((CollectibleCell)(map[backward.x][backward.y])).getCollectible());
				map[backward.x][backward.y] = champPos;
				currChamp.setLocation(backward);
				if(currChamp instanceof SlytherinWizard && isTraitActivated())
					map[currPos.x-1][currPos.y] = new EmptyCell();
				else
					map[currPos.x][currPos.y] = new EmptyCell();
			}
			else
				throw new InvalidTargetCellException();
		}
		else
			throw new OutOfBordersException();
	}
	public void moveLeft() throws Exception
	{
		Champion currChamp = getCurrentChamp();
		Point currPos = currChamp.getLocation();
		Cell champPos;
		if(currChamp instanceof SlytherinWizard && isTraitActivated())
			champPos = map[currPos.x][currPos.y+1];
		else
			champPos = map[currPos.x][currPos.y];
		Point left = new Point(currPos.x,currPos.y-1);
		if(islegalMove(left))
		{
			if((map[left.x][left.y] instanceof EmptyCell || map[left.x][left.y] instanceof CollectibleCell))
			{
				if(map[left.x][left.y] instanceof CollectibleCell)
					getCurrentChamp().getInventory().add(((CollectibleCell)(map[left.x][left.y])).getCollectible());
				map[left.x][left.y] = champPos;
				currChamp.setLocation(left);
				if(currChamp instanceof SlytherinWizard && isTraitActivated())
					map[currPos.x][currPos.y+1] = new EmptyCell();
				else
					map[currPos.x][currPos.y] = new EmptyCell();
			}
//			else 
//				if(map[left.x][left.y] instanceof MysteryCell)
//				{
//					Mystery mys = ((MysteryCell) map[left.x][left.y]).getMystery();
//					if(mys instanceof Vengance)
//					{ 
//						
//						Champion tmp;
//						for (int i = 0; i < getChampions().size(); i++)
//						{
//							tmp = getChampions().get(i);
//							if(!(tmp.equals(getCurrentChamp())))
//								tmp.setHp(tmp.getHp()- ((Vengance)mys).getDamage());
//						}
//					}
//					else
//					{
//						Champion tmp = getCurrentChamp();
//						tmp.restore();
//						tmp.setTraitCooldown(0);
//					}
//				}
				else
					throw new InvalidTargetCellException();
		}
		else
			throw new OutOfBordersException();
	}
	public void moveRight() throws Exception
	{
		Champion currChamp = getCurrentChamp();
		Point currPos = currChamp.getLocation();
		Cell champPos;
		if(currChamp instanceof SlytherinWizard && isTraitActivated())
			champPos = map[currPos.x][currPos.y-1];
		else
			champPos = map[currPos.x][currPos.y];
		Point right = new Point(currPos.x,currPos.y+1);
		if(islegalMove(right))
		{	
			if((map[right.x][right.y] instanceof EmptyCell || map[right.x][right.y] instanceof CollectibleCell))
			{
				if(map[right.x][right.y] instanceof CollectibleCell)
					getCurrentChamp().getInventory().add(((CollectibleCell)(map[right.x][right.y])).getCollectible());
				map[right.x][right.y] = champPos;
				currChamp.setLocation(right);
				if(currChamp instanceof SlytherinWizard && isTraitActivated())
					map[currPos.x][currPos.y-1] = new EmptyCell();
				else
					map[currPos.x][currPos.y] = new EmptyCell();
			}
//			else 
//				if(map[right.x][right.y] instanceof MysteryCell)
//				{
//					Mystery mys = ((MysteryCell) map[right.x][right.y]).getMystery();
//					if(mys instanceof Vengance)
//					{ 
//						
//						Champion tmp;
//						for (int i = 0; i < getChampions().size(); i++)
//						{
//							tmp = getChampions().get(i);
//							if(!(tmp.equals(getCurrentChamp())))
//								tmp.setHp(tmp.getHp()- ((Vengance)mys).getDamage());
//						}
//					}
//					else
//					{
//						Champion tmp = getCurrentChamp();
//						tmp.restore();
//						tmp.setTraitCooldown(0);
//					}
//				}
			else
				throw new InvalidTargetCellException();
		}
		else
			throw new OutOfBordersException();
	}
	private void loadPotions(String filePath) throws IOException
	{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		String[] potionsArray;
		potions = getPotions();
		while ((currentLine = br.readLine()) != null)
		{
			potionsArray = currentLine.split(",");
				potions.add(new Potion(potionsArray[0], Integer.parseInt(potionsArray[1])));
		}
		br.close();
	}
	public void decrementSpellCooldown()
	{
		ArrayList<Spell> spells = getCurrentChamp().getSpells();
		Spell curr;
		for (int i = 0; i <  spells.size(); i++)
		{
			curr = spells.get(i);
			if(curr.getCoolDown() > 0)
			{
				curr.setCoolDown(curr.getCoolDown()-1);
			}
		}
	}
	public void finalizeAction() throws Exception// to be continued
	{
		setAllowedMoves(getAllowedMoves()-1);
		for (int i = 0; i < getChampions().size();)
		{
			if(getChampions().get(i).getHp() == 0)
			{
				map[getChampions().get(i).getLocation().x][getChampions().get(i).getLocation().y] = new EmptyCell();
				getChampions().remove(getChampions().get(i));
			}
			else
				i++;
		}
		if(getAllowedMoves() == 0)
		{	
			if(getCurrentChamp().getTraitCooldown() > 0)
				getCurrentChamp().setTraitCooldown(getCurrentChamp().getTraitCooldown() - 1);
			decrementSpellCooldown();
			setTraitActivated(false);
			endTurn();
			setAllowedMoves(1);
		}
	}
	public Point getTargetPoint(Direction d)
	{
		Champion currChamp = getCurrentChamp();
		Point currPos = currChamp.getLocation();
		int champPosX = currPos.x;
		int champPosY = currPos.y;
		Point temp;
		switch(d)
		{
		case LEFT:
				return new Point(champPosX,champPosY-1);
		case RIGHT:
				return new Point(champPosX,champPosY+1);
		case FORWARD:
				return new Point(champPosX-1,champPosY);
		case BACKWARD:
				return new Point(champPosX+1,champPosY);
		default:
			return currPos;
		}
	}
	public void useSpell(Spell s) throws Exception // to be continued
	{
		getCurrentChamp().setIp(getCurrentChamp().getIp() - s.getCost());
		s.setCoolDown(s.getDefaultCooldown());
	}
	public void castDamagingSpell(DamagingSpell s, Direction d) throws Exception
	{ 
		Cell[][] map = getMap();
		Point target = getTargetPoint(d);
		if(islegalMove(target))
		{
			Object targeted;
			Cell adjCell = map[target.x][target.y];
			if(adjCell instanceof ObstacleCell)
			{
				targeted = (Obstacle)(((ObstacleCell) adjCell).getObstacle());
				((Obstacle) targeted).setHp(((Obstacle) targeted).getHp() - s.getDamageAmount());
				if(((Obstacle) targeted).getHp() <= 0)
					map[target.x][target.y] = new EmptyCell();
			}
			else
				if(adjCell instanceof ChampionCell)
				{
					targeted = (Champion)(((ChampionCell) adjCell).getChamp());
					if(((ChampionCell) adjCell).getChamp() instanceof HufflepuffWizard)
						((Champion) targeted).setHp(((Champion) targeted).getHp() - (s.getDamageAmount()/2));
					else
						((Champion) targeted).setHp(((Champion) targeted).getHp() - s.getDamageAmount());
					if(((Champion) targeted).getHp() <= 0)
					{
						map[target.x][target.y] = new EmptyCell();
						getChampions().remove(targeted);
					}
				}
				else
					throw new InvalidTargetCellException();
		useSpell(s);
		}
		else
			throw new OutOfBordersException();
	}
	public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r) throws Exception
	{
			Cell[][] map = getMap();
			Point targetBefore = getTargetPoint(d);
			Point targetAfter = getTargetPoint(t);
			if(islegalMove(targetBefore) && islegalMove(targetAfter))
			{
				Point champPos = getCurrentChamp().getLocation();
				Cell currentLocation = map[targetBefore.x][targetBefore.y];
				if(currentLocation instanceof CollectibleCell || currentLocation instanceof TreasureCell || currentLocation instanceof EmptyCell || currentLocation instanceof 	 CupCell || currentLocation instanceof WallCell)
					throw new InvalidTargetCellException();
				Cell nextLocation;
				if(r > s.getRange())
					throw new OutOfRangeException(s.getRange());
				switch(t)
				{
				case LEFT: 
					if(targetAfter.y-r+1 >=0 && targetAfter.y-r+1 <= 9)
					{	
						if(!(map[targetAfter.x][targetAfter.y-r+1] instanceof EmptyCell))
							throw new InvalidTargetCellException();
						nextLocation = map[targetAfter.x][targetAfter.y-r+1];
						targetAfter = new Point(targetAfter.x,targetAfter.y-r+1);
					}
					else
						throw new OutOfBordersException();
					break;
				case RIGHT:
					if(targetAfter.y+r-1 >= 0 && targetAfter.y+r-1 <= 9)
					{	
						if(!(map[targetAfter.x][targetAfter.y+r-1] instanceof EmptyCell))
							throw new InvalidTargetCellException();
						nextLocation = map[targetAfter.x][targetAfter.y+r-1];
						targetAfter = new Point(targetAfter.x,targetAfter.y+r-1);
					}
					else
						throw new OutOfBordersException();
					break;
				case FORWARD: 
					if(targetAfter.x-r+1 >= 0 && targetAfter.x-r+1 <= 9)
					{	
						if(!(map[targetAfter.x-r+1][targetAfter.y] instanceof EmptyCell))
							throw new InvalidTargetCellException();
						nextLocation = map[targetAfter.x-r+1][targetAfter.y];
						targetAfter = new Point(targetAfter.x-r+1,targetAfter.y);
					}
					else
						throw new OutOfBordersException();
					break;
				case BACKWARD:
					if(targetAfter.x+r-1 >= 0 && targetAfter.x+r-1 <= 9)
					{	
						if(!(map[targetAfter.x+r-1][targetAfter.y] instanceof EmptyCell))
							throw new InvalidTargetCellException();
						nextLocation = map[targetAfter.x+r-1][targetAfter.y];
						targetAfter = new Point(targetAfter.x+r-1,targetAfter.y);
					}
					else
						throw new OutOfBordersException();
					break;
				default: nextLocation = map[champPos.x][champPos.y];
				}
				if(nextLocation instanceof EmptyCell)
				{
					if(currentLocation instanceof ObstacleCell || currentLocation instanceof ChampionCell)
					{
						map[targetAfter.x][targetAfter.y] = currentLocation;
						map[targetBefore.x][targetBefore.y] = new EmptyCell();
					}
//					else
//						throw new InvalidTargetCellException();
				}
				else
					throw new InvalidTargetCellException();
			useSpell(s);
			}
			else
				throw new OutOfBordersException();
	}
	public void castHealingSpell(HealingSpell s) throws Exception
	{
		Champion current = getCurrentChamp();
		if(current.getHp() + s.getHealingAmount() <= current.getDefaultHp())
			current.setHp(current.getHp() + s.getHealingAmount());
		else
			current.setHp(current.getDefaultHp());
		useSpell(s);
	}
	public void endTurn()
	{
		if(getChampions().size() > 0)
			setCurrentChamp(getChampions().get((getChampions().indexOf(getCurrentChamp())+1)%getChampions().size()));
	}
	public boolean islegalMove(Direction d)
	{
		Point champNextPos = getTargetPoint(d);
		if(champNextPos.x < 0 || champNextPos.x > 9 || champNextPos.y < 0 || champNextPos.y > 9)
			return false;
		return true;
	}
	public boolean islegalMove(Point d)
	{
		if(d.x < 0 || d.x > 9 || d.y < 0 || d.y > 9)
			return false;
		return true;
	}
	public void usePotion(Potion p)
	{
		getCurrentChamp().setIp(getCurrentChamp().getIp() + p.getAmount());
		getCurrentChamp().getInventory().remove(p);
	}
	public void onSlytherinTrait(Direction d) throws Exception
	{
		Champion currentChamp = getCurrentChamp();
		int champPosX = currentChamp.getLocation().x;
		int champPosY = currentChamp.getLocation().y;
		Cell[][] map = getMap();
		switch(d)
		{
		case LEFT:
			if(champPosY-2 >= 0)
			{	
				if((map[champPosX][champPosY-2] instanceof EmptyCell))
				{	
					if(isTraitActivated())
					{	
						currentChamp.setLocation(new Point(champPosX,champPosY-1));
						moveLeft();
					}
					else
						throw new InCooldownException(getCurrentChamp().getTraitCooldown());
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				throw new OutOfBordersException();
			break;
		case RIGHT:
			if(champPosY+2 <= 9)
			{
				if((map[champPosX][champPosY+2] instanceof EmptyCell))
				{
					if(isTraitActivated())
					{
						currentChamp.setLocation(new Point(champPosX,champPosY+1));
						moveRight();
					}
					else
						throw new InCooldownException(getCurrentChamp().getTraitCooldown());
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				throw new OutOfBordersException();
			break;
		case FORWARD:
			if(champPosX-2 >= 0)
			{	
				if((map[champPosX-2][champPosY] instanceof EmptyCell))
				{
					if(isTraitActivated())
					{
						currentChamp.setLocation(new Point(champPosX-1,champPosY));
						moveForward();
					}
					else
						throw new InCooldownException(getCurrentChamp().getTraitCooldown());
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				throw new OutOfBordersException();
					
			break;
		case BACKWARD:
			if(champPosX+2 <= 9)
			{	
				if((map[champPosX+2][champPosY] instanceof EmptyCell))
				{
					if(isTraitActivated())
					{
						currentChamp.setLocation(new Point(champPosX+1,champPosY));
						moveBackward();
					}
					else
						throw new InCooldownException(getCurrentChamp().getTraitCooldown());
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				throw new OutOfBordersException();
			break;
		}
	}
	public void onGryffindorTrait()
	{	
		if(getCurrentChamp().getTraitCooldown() == 0)
		{
			Champion currentChamp = getCurrentChamp();
			setAllowedMoves(2);
			setTraitActivated(true);
			currentChamp.setTraitCooldown(4);
		}
	}
	public void onHufflepuffTrait()
	{
			setTraitActivated(true);
	}
}
