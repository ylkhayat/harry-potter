package harrypotter.model.tournament;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;

import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class FirstTask extends Task {
	private ArrayList<Point> markedCells;
	private ArrayList<Champion> winners;
	
	public FirstTask(ArrayList<Champion> champions) throws IOException
	{
		super(champions);
		if(champions != null)
		{
			Collections.shuffle(champions);
			winners = new ArrayList<Champion>();
			setAllowedMoves(1);
			setTraitActivated(false);
			markedCells = new ArrayList<Point>();
			setCurrentChamp(getChampions().get(0));
			generateMap();
			markCells();
		}
	}
	public ArrayList<Point> getMarkedCells() {
		return markedCells;
	}

	public void setMarkedCells(ArrayList<Point> markedCells) {
		this.markedCells = markedCells;
	}

	public ArrayList<Champion> getWinners()
	{
		return winners;
	}

	public void setWinners(ArrayList<Champion> winners) {
		this.winners = winners;
	}

	public void generateMap() throws IOException {
		LinkedList<Integer> availablePoints = super.getAvailablePoints();			//Map unused and shuffled points.
		availablePoints.remove(availablePoints.indexOf(44));
		ArrayList<Champion> champions = getChampions();
		ArrayList<Potion> potions = getPotions();
		Point[] constPositions = {new Point(9, 0), new Point(9, 9),new Point(0, 9), new Point(0, 0)};
		Cell[][] map = getMap();
		int randPosIndex;
		int removePos;
		for(int counter = 0;counter < champions.size(); counter++)			//Champions adder, cases that not all champions may be existing handled.
		{
				Wizard temp = (Wizard) champions.get(counter);
				temp.setLocation(constPositions[counter%(constPositions.length)]);
				map[constPositions[counter%(constPositions.length)].x][constPositions[counter%(constPositions.length)].y] = new ChampionCell(temp);
				removePos = availablePoints.indexOf(constPositions[counter%constPositions.length].x*10+constPositions[counter%constPositions.length].y);
				if(removePos != -1)
					availablePoints.remove(removePos);
		}
		for(int k = 0; k < 10; k++)			//Potions adder.
		{
			randPosIndex = availablePoints.removeLast();
			map[randPosIndex/10][randPosIndex%10] = new CollectibleCell(potions.get((int) (Math.random()*potions.size())));
		}
		Random rand = new Random();
		for (int obstacleCounter = 0; obstacleCounter < 40;obstacleCounter++)			//Obstacles adder.
		{
			randPosIndex = availablePoints.removeLast();
			map[randPosIndex/10][randPosIndex%10] = new ObstacleCell(new PhysicalObstacle((Integer) rand.nextInt(101) + 200));
		}
		while(!availablePoints.isEmpty())
		{
			randPosIndex = availablePoints.removeLast();
			map[randPosIndex/10][randPosIndex%10] = new EmptyCell();
		}
		map[4][4] = new EmptyCell();
	}
	public void markCells()
	{
		Champion currChamp = (Wizard) getCurrentChamp();
		markedCells = new ArrayList<Point>();
		Point champPos = ((Wizard) currChamp).getLocation();
		int xCor = champPos.x;
		int yCor = champPos.y;
		markedCells.add(champPos);
		Point right = new Point(xCor , (yCor+1));
		if(islegalMove(right))
			markedCells.add(right);
		Point left = new Point(xCor , (yCor-1));
		if(islegalMove(left))
			markedCells.add(left);
		Point top = new Point((xCor -1), yCor);
		if(islegalMove(top))
			markedCells.add(top);
		Point bot = new Point((xCor +1) , yCor);
		if(islegalMove(bot))
			markedCells.add(bot);
		int rand;
		while(markedCells.size() > 2)
		{
			rand = (int)(Math.random()*markedCells.size());
			markedCells.remove(markedCells.get(rand));	
		}
	}
	public void fire()
	{
		Champion current;
		Cell firstPos;
		Cell secondPos;
		if(markedCells.size() > 1)
		{
			firstPos = getMap()[markedCells.get(0).x][markedCells.get(0).y];
			secondPos = getMap()[markedCells.get(1).x][markedCells.get(1).y];
			if(firstPos instanceof ChampionCell)
			{
				current = ((ChampionCell) firstPos).getChamp();
				if(current.getHp() <= 150)
					current.setHp(0);
				else
					current.setHp(current.getHp()-150);
			}
			if(secondPos instanceof ChampionCell)
			{
				current = ((ChampionCell) secondPos).getChamp();
				if(current.getHp() <= 150)
					current.setHp(0);
				else
					current.setHp(current.getHp()-150);
			}
		}
	}
	public void onSlytherinTrait(Direction d) throws Exception
	{
		Champion currentChamp = getCurrentChamp();
		if(currentChamp.getTraitCooldown() == 0)
		{	
			setTraitActivated(true);
			currentChamp.setTraitCooldown(4);
		}
		super.onSlytherinTrait(d);
	}
	public void onHufflepuffTrait()
	{
		Champion currentChamp = getCurrentChamp();
		super.onHufflepuffTrait();
		currentChamp.setTraitCooldown(3);
	}
	public Object onRavenclawTrait()
	{
		Champion currentChamp = getCurrentChamp();
		setTraitActivated(true);
		currentChamp.setTraitCooldown(5);
		return markedCells;
	}
	public void castDamagingSpell(DamagingSpell s, Direction d) throws Exception
	{ 
		if(s.getCoolDown() == 0)
		{
			if(getCurrentChamp().getIp() >= s.getCost())
			{
				super.castDamagingSpell(s, d);
				finalizeAction();
			}
			else
				throw new NotEnoughIPException(s.getCost(), s.getCost() - getCurrentChamp().getIp());
		}
		else
			throw new InCooldownException(s.getCoolDown());
	}
	public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r) throws Exception
	{
		if(s.getCoolDown() == 0)
		{
			if(getCurrentChamp().getIp() >= s.getCost())
			{
				super.castRelocatingSpell(s, d, t, r);
				finalizeAction();
			}
			else
				throw new NotEnoughIPException(s.getCost(), s.getCost() - getCurrentChamp().getIp());
		}
		else
			throw new InCooldownException(s.getCoolDown());
	}
	public void castHealingSpell(HealingSpell s) throws Exception
	{
		if(s.getCoolDown() == 0)
		{	
			if(getCurrentChamp().getIp() >= s.getCost())
			{
				super.castHealingSpell(s);
				finalizeAction();
			}
			else
				throw new NotEnoughIPException(s.getCost(), s.getCost() - getCurrentChamp().getIp());
		}
		else
			throw new InCooldownException(s.getCoolDown());
	}
	public void finalizeAction() throws Exception
	{
		Cell[][] map = getMap();
		Champion currChamp = getCurrentChamp();
		if(getCurrentChamp().getLocation().equals(new Point(4,4)))
		{
			winners.add(currChamp);
			map[4][4] = new EmptyCell();
			getChampions().remove(currChamp);
			setAllowedMoves(1);
		}
		if(!(getCurrentChamp() instanceof HufflepuffWizard) || !isTraitActivated())
			fire();
		super.finalizeAction();
		if(getChampions().isEmpty())
		{	
			if(getListener() != null)
					getListener().onFinishingFirstTask(winners);
			return;
		}
		getMarkedCells().clear();
		markCells();
	}
	public void moveForward() throws Exception
	{
		Point beforeMove = getCurrentChamp().getLocation();
		super.moveForward();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveBackward() throws Exception
	{
		Point beforeMove = getCurrentChamp().getLocation();
		super.moveBackward();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveLeft() throws Exception
	{
		Point beforeMove = getCurrentChamp().getLocation();
		super.moveLeft();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveRight() throws Exception
	{
		Point beforeMove = getCurrentChamp().getLocation();
		super.moveRight();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public int[] getObs()
	{
		int[] obstaclePos = {2,3,4,6,12,17,18,20,22,25,27,28,30,34,42,43,46,47,49,50,51,52,54,57,60,61,64,67,69,70,73,76,77,82,83,85,95,96,97,98};
		return obstaclePos;
	}
	public int[] getColl()
	{
		int[] collectiblePos = {7,13,37,39,40,53,56,59,86,94};
		return collectiblePos;
	}
	public void showBag()
	{
		System.out.println(getCurrentChamp().getName() + " bag:");
		if(getCurrentChamp().getInventory().size() > 0)
			for (int i = 0; i < getCurrentChamp().getInventory().size(); i++) {
				System.out.println((i+1)+". Name: " + getCurrentChamp().getInventory().get(i).getName() + ".");
			}
		else
			System.out.println("Empty.");
	}
	public void printMap()
	{
		Cell[][] map = getMap();
		System.out.println("-------------------------------------------------------------------------------------------");
		for (int i = 0; i < map.length; i++)
		{
			System.out.print("|");
			for (int j = 0; j < map[i].length; j++)
			{
				if(i*10+j == 44)
					System.out.print("   <>   |");
				else
					if(map[i][j] instanceof EmptyCell)
						System.out.print("        |");
					else
						if(map[i][j] instanceof ObstacleCell)
							System.out.print("ObstCell|");
						else
							if(map[i][j]  instanceof CollectibleCell)
								System.out.print("   ()   |");
							else
								if(map[i][j] instanceof ChampionCell)
									System.out.print(((ChampionCell)map[i][j]).getChamp().getName()+"|");
			}
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------");
		}
	}
	public void displayWinners()
	{
		Champion x;
		for (int i = 0; i < getWinners().size(); i++)
		{
			x = getWinners().get(i);
			System.out.println((i+1)+". Name: "+ x.getName()+".");
		}
	}
	public void displayChamps()
	{
		Champion x;
		for (int i = 0; i < getChampions().size(); i++)
		{
			x = getChampions().get(i);
			System.out.println((i+1)+". Name: "+ x.getName()+", Hp: "+x.getHp()+", Ip: "+x.getIp()+", Location: "+x.getLocation());
		}
		System.out.println("Now Playing.. " + getCurrentChamp().getName());
	}
	public void displaySpells()
	{
		Champion x = getCurrentChamp();
		for (int i = 0; i < x.getSpells().size(); i++)
		{
			System.out.println("Name: "+ x.getSpells().get(i).getName()+", CoolDown: "+x.getSpells().get(i).getCoolDown()+", Cost: "+x.getSpells().get(i).getCost());
		}
	}
	public void displayMarked()
	{
		ArrayList<Point> x = getMarkedCells();
		Point y;
		for (int i = 0; i < x.size(); i++)
		{
			y = x.get(i);
			System.out.println((i+1)+ ". Location: "+y.x+", "+y.y+".");
		}
	}
	public void displayRavenclawTrait()
	{
		System.out.println(getMarkedCells().toString());
	}
}
