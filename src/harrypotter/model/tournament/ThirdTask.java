package harrypotter.model.tournament;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class ThirdTask extends Task {

	private LinkedList<Integer> potIndex;
	private Point cupPoint;
	private Champion winner;
	
	public ThirdTask(ArrayList<Champion> champions) throws IOException
	{
		super(champions);
		potIndex = new LinkedList<Integer>();
		setAllowedMoves(1);
		setCurrentChamp(getChampions().get(0));
		if(getChampions().get(0) instanceof HufflepuffWizard)
			setTraitActivated(true);
		else
			setTraitActivated(false);
		generateMap();
	}
	public void generateMap() throws IOException {
		
		ArrayList<Potion> potions = getPotions();
		LinkedList<Integer> potIndex = getPotIndex();
		readMap("task3map.csv");
		Cell[][] map = getMap();
		Collections.shuffle(potIndex);
		int randPotionIndex = potIndex.removeLast();
		for(int k = 0; k < 10;)
		{
				map[randPotionIndex/10][randPotionIndex%10] = new CollectibleCell(potions.get((int) (Math.random()*potions.size())));
				k++;
				randPotionIndex = potIndex.removeLast();
		}
	}

	private void readMap(String filePath) throws IOException {
		String currentLine = "";
		LinkedList<Integer> potIndex = getPotIndex();
		FileReader fileReader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		String[] Line;
		Cell[][] map = getMap();
		Champion champ;
		int x;
		Random rand = new Random();
		int j = 0;
		while ((currentLine = br.readLine()) != null)
		{
			Line = currentLine.split(",");
			for (int i = 0; i < Line.length; i++)
			{	
				x = Integer.parseInt(Line[i]);
				switch (x)
				{
				case 5:
					map[j][i] = new WallCell();
					break;
				case 6:
					map[j][i] = new ObstacleCell(new PhysicalObstacle((int) rand.nextInt(101) + 200));
					break;
				case 7:
					map[j][i] = new CupCell();
					cupPoint = new Point(j,i);
					break;
				case 1:
					if (x <= getChampions().size()) {
						champ = getChampions().get(x - 1);
						map[j][i] = new ChampionCell(champ);
						champ.setLocation(new Point(j,i));
						break;
					}
				case 2:
					if (x <= getChampions().size()) {
						champ = getChampions().get(x - 1);
						map[j][i] = new ChampionCell(champ);
						champ.setLocation(new Point(j,i));
						break;
					}
				case 3:
					if (x <= getChampions().size()) {
						champ = getChampions().get(x - 1);
						map[j][i] = new ChampionCell(champ);
						champ.setLocation(new Point(j,i));
						break;
					}
				case 4:
					if (x <= getChampions().size()) {
						champ = getChampions().get(x - 1);
						map[j][i] = new ChampionCell(champ);
						champ.setLocation(new Point(j,i));
						break;
					}
				case 0:
					map[j][i] = new EmptyCell();
					potIndex.add(j*10+i);
				break;
				}
			}
			j++;
		}
		fileReader.close();
	}
	public LinkedList<Integer> getPotIndex() {
		return potIndex;
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
		super.onHufflepuffTrait();
	}
	public Object onRavenclawTrait(){
		Champion currentChamp = getCurrentChamp();
		setTraitActivated(true);
		currentChamp.setTraitCooldown(7);
		return getCupCell(cupPoint);
	}
	public ArrayList<Direction> getCupCell(Point p)
	{
		ArrayList<Direction> directions = new ArrayList<Direction>();
		Point currentPos = getCurrentChamp().getLocation();
		if(currentPos.x > p.x) // cup is forward
			if(currentPos.y > p.y) // cup is left
			{
				directions.add(Direction.LEFT);
				directions.add(Direction.FORWARD);
			}
			else // cup is right
				if(currentPos.y < p.y)
				{
					directions.add(Direction.RIGHT);
					directions.add(Direction.FORWARD);
				}
				else
					directions.add(Direction.FORWARD);
		else 
			if(currentPos.x < p.x)// cup is backward
				if(currentPos.y > p.y) // cup is left
				{
					directions.add(Direction.LEFT);
					directions.add(Direction.BACKWARD);
				}
				else // cup is right
					if(currentPos.y < p.y)
					{
						directions.add(Direction.RIGHT);
						directions.add(Direction.BACKWARD);
					}
					else
						directions.add(Direction.BACKWARD);
			else // cup same line
				if(currentPos.y > p.y) // cup is left
					directions.add(Direction.LEFT);
				else // cup is right
					directions.add(Direction.RIGHT);
		return directions;
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
		if(getCurrentChamp() instanceof HufflepuffWizard)
			setTraitActivated(true);
		if(currCell instanceof CupCell)
		{
			if(getListener() != null)
				getListener().onFinishingThirdTask(currChamp);
			return;
		}
		else
			super.finalizeAction();
		if(getCurrentChamp() instanceof HufflepuffWizard)
			setTraitActivated(true);
	}
	public void moveForward() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point forward = new Point(beforeMove.x-1,beforeMove.y);
		if(islegalMove(forward) && map[forward.x][forward.y] instanceof CupCell)
		{
			map[beforeMove.x][beforeMove.y] = new EmptyCell();
			getCurrentChamp().setLocation(forward);
			winner = getCurrentChamp();
			getChampions().remove(getChampions().indexOf(getCurrentChamp()));
		}
		else
			super.moveForward();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	
	public void moveBackward() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point backward = new Point(beforeMove.x+1,beforeMove.y);
		if(islegalMove(backward) && map[backward.x][backward.y] instanceof CupCell)
		{
			map[beforeMove.x][beforeMove.y] = new EmptyCell();
			getCurrentChamp().setLocation(backward);
			getChampions().remove(getChampions().indexOf(getCurrentChamp()));
		}
		else
			super.moveBackward();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveLeft() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point left = new Point(beforeMove.x,beforeMove.y-1);
		if(islegalMove(left) && map[left.x][left.y] instanceof CupCell)
		{
			map[beforeMove.x][beforeMove.y] = new EmptyCell();
			getCurrentChamp().setLocation(left);
			getChampions().remove(getChampions().indexOf(getCurrentChamp()));
		}
		else
			super.moveLeft();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
	}
	public void moveRight() throws Exception
	{
		Cell[][] map = getMap();
		Point beforeMove = getCurrentChamp().getLocation();
		Point right = new Point(beforeMove.x,beforeMove.y+1);
		if(islegalMove(right) && map[right.x][right.y] instanceof CupCell)
		{
			map[beforeMove.x][beforeMove.y] = new EmptyCell();
			getCurrentChamp().setLocation(right);
			getChampions().remove(getChampions().indexOf(getCurrentChamp()));
		}
		else
			super.moveRight();
		if(!getCurrentChamp().getLocation().equals(beforeMove))
			finalizeAction();
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
						if(map[i][j] instanceof WallCell)
							System.out.print("WallCell|");
						else
							if(map[i][j]  instanceof CollectibleCell)
								System.out.print("ColcCell|");
							else
								if(map[i][j] instanceof ChampionCell)
									System.out.print(((ChampionCell)map[i][j]).getChamp().getName()+"|");
			}
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------------------");
		}
	}
	public void displayRavenclawTrait()
	{
		System.out.println(getCupCell(cupPoint));
	}
	public void displayChamps()
	{
		Champion x;
		for (int i = 0; i < getChampions().size(); i++)
		{
			x = getChampions().get(i);
			System.out.println((i+1)+". Name: "+ x.getName()+", Hp: "+x.getHp()+", Location: "+x.getLocation());
		}
		System.out.println("Now Playing.. " + getCurrentChamp().getName());
	}
	
	public Point getCupPoint() {
		return cupPoint;
	}
	public Champion getWinner() {
		return winner;
	}
	public void setWinner(Champion winner) {
		this.winner = winner;
	}
}
