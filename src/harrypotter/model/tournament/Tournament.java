package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Direction;
import harrypotter.view.ChampionSelectionListener;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tournament implements TaskListener, ChampionSelectionListener
{
	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask secondTask;
	private ThirdTask thirdTask;
	private Champion winner;
	//private Task currentTask;

	public Tournament() throws Exception
	{
		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");
	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	private void loadSpells(String filePath) throws Exception {
		String currentLine = "";
		FileReader fileReader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[] s = currentLine.split(",");
			if (s[0].equals("DMG")) {
				spells.add(new DamagingSpell(s[1], Integer.parseInt(s[2]),Integer.parseInt(s[4]), Integer.parseInt(s[3])));
				}
			if (s[0].equals("HEL")){
                spells.add(new HealingSpell(s[1],Integer.parseInt(s[2]),Integer.parseInt(s[4]), Integer.parseInt(s[3]) ));
			}
			if (s[0].equals("REL")){
				spells.add(new RelocatingSpell(s[1],Integer.parseInt(s[2]),Integer.parseInt(s[4]), Integer.parseInt(s[3]) ));
			}
		}
		fileReader.close();
	}
	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}
	public void addChampion(Champion c)
	{
		champions.add(c);
	}
	public void removeChampion(String s)
	{
		for (Champion champion : champions)
		{
			if(champion.getName().equals(s))
			{	
				champions.remove(champion);
				break;
			}
		}
	}
	public void beginTournament() throws IOException
	{
		firstTask = new FirstTask(getChampions());
		firstTask.setListener(this);
	}
	public void onFinishingFirstTask(ArrayList<Champion> winners) throws IOException
	{
		if(winners.size() > 0)
		{
			secondTask = new SecondTask(winners);
			secondTask.setListener(this);
		}
	}
	public void onFinishingSecondTask(ArrayList<Champion> winners) throws IOException
	{
		if(winners.size() > 0)
		{
			thirdTask = new ThirdTask(winners);
			thirdTask.setListener(this);
		}
	}
	public void onFinishingThirdTask(Champion currChamp)
	{
		winner = currChamp;
	}
	public Champion getWinner() {
		return winner;
	}

	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		Tournament y = new Tournament();
		y.addChampion(new GryffindorWizard("   GG   "));
		y.addChampion(new SlytherinWizard("   SS   "));
		y.addChampion(new HufflepuffWizard("   HH   "));
		y.addChampion(new RavenclawWizard("   RR   "));
		y.beginTournament();
		FirstTask x = y.getFirstTask();
		Cell[][] map = x.getMap();
//		x.displayChamps();
//		x.displayMarked();
//		x.getChampions().get(0).setHp(0);
//		x.getChampions().get(1).setHp(140000);
//		x.getChampions().get(3).setHp(140000);
//		x.getChampions().get(2).setHp(0);
//		Champion first = x.getChampions().get(1);
//		first.setLocation(new Point(3, 4));
//		map[3][4] = new ChampionCell(first);
//		Champion second = x.getChampions().get(3);
//		second.setLocation(new Point(5, 4));
//		map[5][4] = new ChampionCell(second);
//		Champion third = x.getChampions().get(2);
//		third.setLocation(new Point(4, 5));
//		map[4][5] = new ChampionCell(third);
//		Champion fourth = x.getChampions().get(0);
//		fourth.setLocation(new Point(4, 3));
//		map[4][3] = new ChampionCell(fourth);
		x.printMap();
		x.displayChamps();
		x.displayMarked();
		RelocatingSpell rel = new RelocatingSpell("Rel", 50, 0, 1);
		DamagingSpell dmg = new DamagingSpell("Kill", 50, 0, 50000);
		for (int i = 0; i < x.getChampions().size(); i++)
		{
			x.getChampions().get(i).getSpells().add(rel);
			x.getChampions().get(i).getSpells().add(dmg);
		}
		x.displaySpells();
		x.showBag();
		String word = sc.nextLine();
		while(!word.equals("done"))
		{
			switch(word)
			{
			case "up" : try{
				x.moveForward();
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}break;
			case "down" : try{
				x.moveBackward();
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}break;
			case "right" : try{
				x.moveRight();
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}break;
			case "left" :  try{
				x.moveLeft();
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}break;
			case "trait up" : 
				((SlytherinWizard) x.getCurrentChamp()).setTraitDirection(Direction.FORWARD);
				x.getCurrentChamp().useTrait();break;
			case "trait down" : 
				((SlytherinWizard) x.getCurrentChamp()).setTraitDirection(Direction.BACKWARD);
				x.getCurrentChamp().useTrait();break;
			case "trait right" : 
				((SlytherinWizard) x.getCurrentChamp()).setTraitDirection(Direction.RIGHT);
				x.getCurrentChamp().useTrait();break;
			case "trait left" : 
				((SlytherinWizard) x.getCurrentChamp()).setTraitDirection(Direction.LEFT);
				x.getCurrentChamp().useTrait();break;
			case "dmg up" : 
				x.castDamagingSpell(dmg,Direction.FORWARD);break;
			case "dmg down" : 
				x.castDamagingSpell(dmg,Direction.BACKWARD);break;
			case "dmg right" : 
				x.castDamagingSpell(dmg,Direction.RIGHT);break;
			case "dmg left" : 
				x.castDamagingSpell(dmg,Direction.LEFT);break;
			case "potion" : x.usePotion(((Potion)x.getCurrentChamp().getInventory().get(0)));
			case "trait" : x.getCurrentChamp().useTrait();
			}
			x.printMap();
			x.displayChamps();
			x.displayMarked();
			x.displayWinners();
			x.displaySpells();
			x.showBag();
			word = sc.nextLine();
		}
		SecondTask task = y.getSecondTask();
//		ArrayList<Champion> e = new ArrayList<>();
//		GryffindorWizard g = new GryffindorWizard("gryff");
//		HufflepuffWizard h = new HufflepuffWizard("huff");
//		RavenclawWizard r = new RavenclawWizard("raven");
//		SlytherinWizard s = new SlytherinWizard("slyth");
//		e.add(g);
//		e.add(h);
//		e.add(r);
//		e.add(s);
//
//		for (int i = 0; i < 10; i++)
//			for (int j = 0; j < 10; j++)
//				task.getMap()[i][j] = new EmptyCell();
//		
//		task.setCurrentChamp(g);
//		task.getMap()[7][8] = new ChampionCell(g);
//		task.getMap()[8][8] = new ChampionCell(r);
//
//		((Wizard) task.getCurrentChamp()).setLocation(new Point(7, 8));
//		task.printMap();
//		task.displayChamps();
//		task.displayMarked();
//		RelocatingSpell rell = new RelocatingSpell("Accio", 100, 1, 1);
//		g.getSpells().add(rell);
//		task.castRelocatingSpell(rell, Direction.BACKWARD, Direction.FORWARD, 1);
		for (int i = 0; i < task.getChampions().size(); i++)
		{
			task.getChampions().get(i).getSpells().add(rel);
			task.getChampions().get(i).getSpells().add(dmg);
			task.getChampions().get(i).setHp(100000000);
		}
		task.printMap();
		task.displayChamps();
		task.displayMarked();
		task.displayWinners();
		task.displaySpells();
		String wordd = sc.nextLine();
		while(!wordd.equals("done"))
		{
			switch(wordd)
			{
			case "up" : task.moveForward();break;
			case "down" : task.moveBackward();break;
			case "right" : task.moveRight();break;
			case "left" : task.moveLeft();break;
			case "trait up" : 
				((SlytherinWizard) task.getCurrentChamp()).setTraitDirection(Direction.FORWARD);
				task.getCurrentChamp().useTrait();break;
			case "trait down" : 
				((SlytherinWizard) task.getCurrentChamp()).setTraitDirection(Direction.BACKWARD);
				task.getCurrentChamp().useTrait();break;
			case "trait right" : 
				((SlytherinWizard) task.getCurrentChamp()).setTraitDirection(Direction.RIGHT);
				task.getCurrentChamp().useTrait();break;
			case "trait left" : 
				((SlytherinWizard) task.getCurrentChamp()).setTraitDirection(Direction.LEFT);
				task.getCurrentChamp().useTrait();break;
			case "dmg up" : 
				task.castDamagingSpell(dmg,Direction.FORWARD);break;
			case "dmg down" : 
				task.castDamagingSpell(dmg,Direction.BACKWARD);break;
			case "dmg right" : 
				task.castDamagingSpell(dmg,Direction.RIGHT);break;
			case "dmg left" : 
				task.castDamagingSpell(dmg,Direction.LEFT);break;
			case "potion" : task.usePotion(((Potion)task.getCurrentChamp().getInventory().get(0)));
			case "trait" : task.getCurrentChamp().useTrait();
			}
			task.printMap();
			task.displayChamps();
			task.displayMarked();
			task.displayWinners();
			task.displaySpells();
			wordd = sc.nextLine();
		}
		ThirdTask d = y.getThirdTask();
		d.printMap();
		d.displayChamps();
		String worddd = sc.nextLine();
		while(!worddd.equals("done"))
		{
			switch(wordd)
			{
			case "up" : d.moveForward();break;
			case "down" : d.moveBackward();break;
			case "right" : d.moveRight();break;
			case "left" : d.moveLeft();break;
			case "trait up" : 
				((SlytherinWizard) d.getCurrentChamp()).setTraitDirection(Direction.FORWARD);
				d.getCurrentChamp().useTrait();break;
			case "trait down" : 
				((SlytherinWizard) d.getCurrentChamp()).setTraitDirection(Direction.BACKWARD);
				d.getCurrentChamp().useTrait();break;
			case "trait right" : 
				((SlytherinWizard) d.getCurrentChamp()).setTraitDirection(Direction.RIGHT);
				d.getCurrentChamp().useTrait();break;
			case "trait left" : 
				((SlytherinWizard) d.getCurrentChamp()).setTraitDirection(Direction.LEFT);
				d.getCurrentChamp().useTrait();break;
			case "potion" : d.usePotion(((Potion)d.getCurrentChamp().getInventory().get(0)));
			case "trait" : d.getCurrentChamp().useTrait();
			}
			d.printMap();
			d.displayChamps();
			worddd = sc.next();
		}
	}
}
