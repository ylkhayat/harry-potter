package harrypotter.model.tournament;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
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
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class SecondTask extends Task
{
	private ArrayList<Champion> winners;
	private ArrayList<Point> PositionMerPerson;
	private Point ravenClawTres;

	public SecondTask(ArrayList<Champion> champions) throws IOException
	{
		super(champions);
		winners = new ArrayList<>();
		ravenClawTres = new Point();
		if(champions != null)
		{
			Collections.shuffle(getChampions());
			setAllowedMoves(1);
			setTraitActivated(false);
			setCurrentChamp(getChampions().get(0));
		}
		generateMap();
		merpersonLocation();
	}
	public ArrayList<Champion> getWinners()
	{
		return winners;
	}


	public void setWinners(ArrayList<Champion> winners) {
		this.winners = winners;
	}
	public Point getRavenClawTres()
	{
		return ravenClawTres;
	}
	public void generateMap() throws IOException
	{
		LinkedList<Integer> availablePoints = getAvailablePoints();
		Point[] constPositions = {new Point(9, 0), new Point(9, 9),new Point(0, 9), new Point(0, 0)};
		ArrayList<Champion> champions = getChampions();
		ArrayList<Potion> potions = getPotions();
		Cell[][] map = getMap();
		int randPosIndex;
		Champion temp;
		int removePos;
		for(int counter = 0;counter < champions.size() && !availablePoints.isEmpty(); counter++)			//Champions adder, cases that not all champions may be existing handled.
		{
				temp = champions.get(counter);
				temp.setLocation(constPositions[counter%constPositions.length]);
				map[constPositions[counter%constPositions.length].x][constPositions[counter%constPositions.length].y] = new ChampionCell(temp);
				removePos = availablePoints.indexOf(constPositions[counter%constPositions.length].x*10+constPositions[counter%constPositions.length].y);
				if(removePos != -1)
					availablePoints.remove(removePos);
		}
		int pos = (int) (Math.random()*potions.size());
		for(int k = 0; k < 10 && !availablePoints.isEmpty();)
		{
			if(pos < potions.size())
			{	randPosIndex = availablePoints.removeLast();
				map[randPosIndex/10][randPosIndex%10] = new CollectibleCell(potions.get(pos));
				k++;
			}
			pos = (int) (Math.random()*potions.size());
			
		}
		Random rand = new Random();
		for (int i = 0; i < champions.size() && !availablePoints.isEmpty(); i++)
		{
				randPosIndex = availablePoints.removeLast();
				temp = champions.get(i);
				map[randPosIndex/10][randPosIndex%10] = new TreasureCell(champions.get(i));
				if(temp instanceof RavenclawWizard)
					setRavenClawTres(new Point(randPosIndex/10,randPosIndex%10));
		}
		for (int obstacleCounter = 0; obstacleCounter < 40 && !availablePoints.isEmpty();obstacleCounter++)			//Obstacles adder.
		{
			randPosIndex = availablePoints.removeLast();
			map[randPosIndex/10][randPosIndex%10] = new ObstacleCell(new Merperson((int) rand.nextInt(101)+200,(int)rand.nextInt(201)+100));
		}
		while(!availablePoints.isEmpty())
		{
			randPosIndex = availablePoints.removeLast();
			map[randPosIndex/10][randPosIndex%10] = new EmptyCell();
		}
	}
	public ArrayList<Point> merpersonLocation()
	{
		Champion currChamp = (Wizard) getCurrentChamp();
		PositionMerPerson = new ArrayList<Point>();
		Point champPos = ((Wizard) currChamp).getLocation();
		int xCor = champPos.x;
		int yCor = champPos.y;
		Cell [][] map = getMap();
		PositionMerPerson.add(new Point(xCor, yCor+1));
		PositionMerPerson.add(new Point(xCor, yCor -1));
		PositionMerPerson.add(new Point(xCor-1, yCor));
		PositionMerPerson.add(new Point(xCor +1, yCor));
		ObstacleCell tempObs;
		Point tempPoint;
		for (int i = 0; i < PositionMerPerson.size();)
		{
			tempPoint = new Point(PositionMerPerson.get(i).x,PositionMerPerson.get(i).y);
			if(islegalMove(tempPoint))
			{
				if(map[tempPoint.x][tempPoint.y] instanceof ObstacleCell)
				{	
					tempObs =(ObstacleCell) map[tempPoint.x][tempPoint.y];
					if(!(tempObs.getObstacle() instanceof Merperson))
						PositionMerPerson.remove(i);
					i++;
				}
				else
					PositionMerPerson.remove(i);
			}
			else
				PositionMerPerson.remove(i);
		}
		return PositionMerPerson;
	}
	public void encounterMerPerson()
	{
		PositionMerPerson = merpersonLocation(); 
		Champion current = getCurrentChamp();
		Cell[][] map = getMap();
		Merperson curMerperson;
		for(int i = 0; i<PositionMerPerson.size(); i++)
		{
			if(map[PositionMerPerson.get(i).x][PositionMerPerson.get(i).y] instanceof ObstacleCell)
			{	
				curMerperson = (Merperson) ((ObstacleCell) map[PositionMerPerson.get(i).x][PositionMerPerson.get(i).y]).getObstacle();
				if(current.getHp() <= curMerperson.getDamage())
					current.setHp(0);
				else	
					current.setHp(current.getHp()- curMerperson.getDamage());
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
		currentChamp.setTraitCooldown(6);
	}
	public Object onRavenclawTrait()
	{
		Champion currentChamp = getCurrentChamp();
		setTraitActivated(true);
		currentChamp.setTraitCooldown(7);
		Point ravenClawTres = getRavenClawTres();
		if(ravenClawTres != null)
			return getRavenclawTresDirection(ravenClawTres);
		else
			return null;
	}
	public ArrayList<Direction> getRavenclawTresDirection(Point p)
	{
		ArrayList<Direction> directions = new ArrayList<Direction>();
		if(p != null)
		{
			Point currentPos = getCurrentChamp().getLocation();
			if(currentPos.x > p.x) // tres is forward
				if(currentPos.y > p.y) // tres is left
				{
					directions.add(Direction.LEFT);
					directions.add(Direction.FORWARD);
				}
				else // tres is right
					if(currentPos.y < p.y)
					{
						directions.add(Direction.RIGHT);
						directions.add(Direction.FORWARD);
					}
					else
						directions.add(Direction.FORWARD);
			else 
				if(currentPos.x < p.x)// tres is backward
					if(currentPos.y > p.y) // tres is left
					{
						directions.add(Direction.LEFT);
						directions.add(Direction.BACKWARD);
					}
					else // tres is right
						if(currentPos.y < p.y)
						{
							directions.add(Direction.RIGHT);
							directions.add(Direction.BACKWARD);
						}
						else
							directions.add(Direction.BACKWARD);
				else // tres same line
					if(currentPos.y > p.y) // tres is left
						directions.add(Direction.LEFT);
					else // tres is right
						directions.add(Direction.RIGHT);
		}				
		return directions;
	}
	public void setRavenClawTres(Point ravenClawTres) {
		this.ravenClawTres = ravenClawTres;
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
		Point currChampPos = currChamp.getLocation();
		Cell currCell = map[currChampPos.x][currChampPos.y];
		if(currCell instanceof TreasureCell && ((TreasureCell) currCell).getOwner().equals(currChamp))
		{
			winners.add(currChamp);
			map[currChampPos.x][currChampPos.y] = new EmptyCell();
			getChampions().remove(currChamp);
			setAllowedMoves(1);
		}
		else
		{	
			if(!(getCurrentChamp() instanceof GryffindorWizard) || getAllowedMoves() == 1)
				if(!(getCurrentChamp() instanceof HufflepuffWizard) || !isTraitActivated())
					encounterMerPerson();
		}
		super.finalizeAction();
		if(getChampions().isEmpty())
		{		
				if(getListener() != null)
					getListener().onFinishingSecondTask(winners);
				return;
		}
		getPositionMerPerson().clear();
	}
	public void moveForward() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point forward = new Point(beforeMove.x-1,beforeMove.y);
		if(islegalMove(forward))
		{
			if(map[forward.x][forward.y] instanceof TreasureCell)
			{
				if(((TreasureCell)map[forward.x][forward.y]).getOwner().equals(getCurrentChamp()))
				{
					if(getCurrentChamp() instanceof SlytherinWizard && isTraitActivated())
						map[beforeMove.x+1][beforeMove.y] = new EmptyCell();
					else
						map[beforeMove.x][beforeMove.y] = new EmptyCell();
					getCurrentChamp().setLocation(forward);
					getChampions().remove(getChampions().indexOf(getCurrentChamp()));
				}
				else
					throw new InvalidTargetCellException();
			}	
			else
				super.moveForward();
		}
		else
			throw new OutOfBordersException();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveBackward() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point backward = new Point(beforeMove.x+1,beforeMove.y);
		if(islegalMove(backward))
		{
			if(map[backward.x][backward.y] instanceof TreasureCell)
			{
				if(((TreasureCell)map[backward.x][backward.y]).getOwner().equals(getCurrentChamp()))
				{
					if(getCurrentChamp() instanceof SlytherinWizard && isTraitActivated())
						map[beforeMove.x-1][beforeMove.y] = new EmptyCell();
					else
						map[beforeMove.x][beforeMove.y] = new EmptyCell();
					getCurrentChamp().setLocation(backward);
					getChampions().remove(getChampions().indexOf(getCurrentChamp()));
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				super.moveBackward();
		}
		else
			throw new OutOfBordersException();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveLeft() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point left = new Point(beforeMove.x,beforeMove.y-1);
		if(islegalMove(left))
		{	
			if(map[left.x][left.y] instanceof TreasureCell)
			{
				if(((TreasureCell)map[left.x][left.y]).getOwner().equals(getCurrentChamp()))
				{
					if(getCurrentChamp() instanceof SlytherinWizard && isTraitActivated())
						map[beforeMove.x][beforeMove.y+1] = new EmptyCell();
					else
						map[beforeMove.x][beforeMove.y] = new EmptyCell();
					getCurrentChamp().setLocation(left);
					getChampions().remove(getChampions().indexOf(getCurrentChamp()));
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				super.moveLeft();
		}
		else
			throw new OutOfBordersException();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveRight() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point right = new Point(beforeMove.x,beforeMove.y+1);
		if(islegalMove(right))
		{
			if(map[right.x][right.y] instanceof TreasureCell)
			{
				if(((TreasureCell)map[right.x][right.y]).getOwner().equals(getCurrentChamp()))
				{
					if(getCurrentChamp() instanceof SlytherinWizard && isTraitActivated())
						map[beforeMove.x][beforeMove.y-1] = new EmptyCell();
					else
						map[beforeMove.x][beforeMove.y] = new EmptyCell();
					getCurrentChamp().setLocation(right);
					getChampions().remove(getChampions().indexOf(getCurrentChamp()));
				}
				else
					throw new InvalidTargetCellException();
			}
			else
				super.moveRight();
		}
		else
			throw new OutOfBordersException();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public ArrayList<Point> getPositionMerPerson()
	{
		return PositionMerPerson;
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
				if(map[i][j] instanceof TreasureCell)
					for (int j2 = 0; j2 < getChampions().size(); j2++)
					{
						if(((TreasureCell)map[i][j]).getOwner().equals(getChampions().get(j2)))
							System.out.print("  <"+(j2+1)+""+(j2+1)+">  |");
					}
				else
					if(map[i][j] instanceof EmptyCell)
						System.out.print("        |");
					else
						if(map[i][j] instanceof ObstacleCell)
							System.out.print("ObstCell|");
						else
							if(map[i][j]  instanceof CollectibleCell)
								System.out.print("ColcCell|");
							else
								if(map[i][j] instanceof ChampionCell)
									System.out.print(((ChampionCell)map[i][j]).getChamp().getName()+"|");
								else
									System.out.print("  null  |");
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
		ArrayList<Point> x = getPositionMerPerson();
		Point y;
		for (int i = 0; i < x.size(); i++)
		{
			y = x.get(i);
			System.out.println((i+1)+ ". Location: "+y.x+", "+y.y+".");
		}
	}
}
