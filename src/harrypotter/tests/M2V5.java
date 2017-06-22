package harrypotter.tests;

import static org.junit.Assert.assertEquals;
import harrypotter.model.character.*;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

public class M2V5 {

	public static final int timeoutValue = 1000;

	@Test(timeout = timeoutValue)
	public void testMoveLeftToRestorationMysteryCellHp() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y - 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveLeft();

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Restoration mystery, his/her hp should be restored to its default value ",
					c.getDefaultHp(), c.getHp());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveLeftToRestorationMysteryCellIp() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y - 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveLeft();

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Restoration mystery, his/her Ip should be restored to its default value ",
					c.getDefaultIp(), c.getIp());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveLeftToRestorationMysteryCellSpells() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y - 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveLeft();

			ArrayList<Spell> afterS = c.getSpells();

			for (int i = 0; i < afterS.size(); i++) {
				int coolSpell = afterS.get(i).getCoolDown();
				assertEquals(
						"Whenever a champion moves left to a Mystery Cell containing Restoration mystery, the coolDown of all his/her spell should be restored to their default values",
						0, coolSpell);
			}
		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveLeftToRestorationMysteryCellTrait() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y - 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveLeft();

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Restoration mystery, his/her traitCollDown should be restored to its default value ",
					0, c.getTraitCooldown());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveLeftToVenganceMysteryCell() throws Exception {

		for (int it = 0; it < 20; it++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			int damage = (((int) (Math.random() * 100)) + 50);

			Vengance vg = new Vengance("Vengance", damage);
			MysteryCell mys = new MysteryCell(vg);
			task1.getMap()[2][1] = mys;

			h.setHp((((int) (Math.random() * 5)) * 50 + 200));
			r.setHp((((int) (Math.random() * 5)) * 50 + 200));
			s.setHp((((int) (Math.random() * 5)) * 50 + 200));

			task1.setCurrentChamp(g);
			int initialHp = ((Wizard) (task1.getCurrentChamp())).getHp();
			int initialHpH = h.getHp();
			int initialHpR = r.getHp();
			int initialHpS = s.getHp();
			task1.moveLeft();

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Vengance mystery, his/her hp should not be affected ",
					initialHp, g.getHp());

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Vengance mystery, the hp of all the other champions should be affected ",
					initialHpH - damage, h.getHp());

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Vengance mystery, the hp of all the other champions should be affected ",
					initialHpR - damage, r.getHp());

			assertEquals(
					"Whenever a champion moves left to a Mystery Cell containing Vengance mystery, the hp of all the other champions should be affected ",
					initialHpS - damage, s.getHp());

		}
	}

	// Mostafa: redundant, should be removed.
	// @Test(timeout = timeoutValue)
	// public void testMoveRightToRestorationMysteryCell() throws Exception {
	//
	// ArrayList<Champion> e = new ArrayList<>();
	// GryffindorWizard g = new GryffindorWizard("gryff");
	// HufflepuffWizard h = new HufflepuffWizard("huff");
	// RavenclawWizard r = new RavenclawWizard("raven");
	// SlytherinWizard s = new SlytherinWizard("slyth");
	//
	// e.add(g);
	// e.add(h);
	// e.add(r);
	// e.add(s);
	//
	// FirstTask task1 = new FirstTask(e);
	//
	// task1.getChampions().clear();
	// task1.getChampions().add(g);
	// task1.getChampions().add(h);
	// task1.getChampions().add(r);
	// task1.getChampions().add(s);
	//
	// Cell[][] taskMap = task1.getMap();
	// for (int i = 0; i < taskMap.length; i++) {
	// for (int j = 0; j < taskMap[i].length; j++) {
	// taskMap[i][j] = new EmptyCell();
	// }
	// }
	//
	// task1.getMap()[2][2] = new ChampionCell(g);
	// g.setLocation(new Point(2, 2));
	// task1.getMap()[3][3] = new ChampionCell(h);
	// h.setLocation(new Point(3, 3));
	// task1.getMap()[5][5] = new ChampionCell(r);
	// r.setLocation(new Point(5, 5));
	// task1.getMap()[6][6] = new ChampionCell(s);
	// s.setLocation(new Point(6, 6));
	//
	// Restoration rm = new Restoration("Restoration");
	// MysteryCell mys = new MysteryCell(rm);
	// task1.getMap()[2][3] = mys;
	//
	// task1.setCurrentChamp(g);
	// g.setHp(700);
	// g.setTraitCooldown(2);
	// g.setIp(200);
	//
	// ArrayList<Spell> initialS = g.getSpells();
	// initialS.clear();
	// initialS.add(new DamagingSpell("", 10, 2, 5));
	// initialS.add(new HealingSpell("", 10, 2, 5));
	// initialS.add(new DamagingSpell("", 10, 2, 5));
	// initialS.add(new RelocatingSpell("", 10, 2, 5));
	//
	// for (int i = 0; i < initialS.size(); i++)
	// initialS.get(i).setCoolDown(70);
	//
	// task1.moveRight();
	//
	// assertEquals(
	// "Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her hp should be restored to its default value ",
	// 900, g.getHp());
	//
	// assertEquals(
	// "Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her Ip should be restored to its default value ",
	// 500, g.getIp());
	//
	// assertEquals(
	// "Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her traitCollDown should be restored to its default value ",
	// 0, g.getTraitCooldown());
	//
	// ArrayList<Spell> afterS = g.getSpells();
	// for (int i = 0; i < afterS.size(); i++) {
	// int coolSpell = afterS.get(i).getCoolDown();
	// assertEquals(
	// "Whenever a champion moves right to a Mystery Cell containing Restoration mystery, the coolDown of all his/her spell should be restored to their default values",
	// 0, coolSpell);
	// }
	//
	// }

	@Test(timeout = timeoutValue)
	public void testMoveRightToRestorationMysteryCellHp() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y + 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveRight();

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her hp should be restored to its default value ",
					c.getDefaultHp(), c.getHp());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveRightToRestorationMysteryCellIp() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();

		for (int champ = 0; champ < 4; champ++) {

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y + 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveRight();

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her Ip should be restored to its default value ",
					c.getDefaultIp(), c.getIp());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveRightToRestorationMysteryCellSpells() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y + 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveRight();

			ArrayList<Spell> afterS = c.getSpells();

			for (int i = 0; i < afterS.size(); i++) {
				int coolSpell = afterS.get(i).getCoolDown();
				assertEquals(
						"Whenever a champion moves right to a Mystery Cell containing Restoration mystery, the coolDown of all his/her spell should be restored to their default values",
						0, coolSpell);
			}
		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveRightToRestorationMysteryCellTrait() throws Exception {

		for (int champ = 0; champ < 4; champ++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			Restoration rm = new Restoration("Restoration");
			MysteryCell mys = new MysteryCell(rm);

			Wizard c = (Wizard) e.get(champ);

			task1.getMap()[c.getLocation().x][c.getLocation().y + 1] = mys;

			task1.setCurrentChamp(e.get(champ));
			c.setHp(700);
			c.setTraitCooldown(2);
			c.setIp(200);

			ArrayList<Spell> initialS = c.getSpells();
			initialS.clear();
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new HealingSpell("", 10, 2, 5));
			initialS.add(new DamagingSpell("", 10, 2, 5));
			initialS.add(new RelocatingSpell("", 10, 2, 5));

			for (int i = 0; i < initialS.size(); i++)
				initialS.get(i).setCoolDown(70);

			task1.moveRight();

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Restoration mystery, his/her traitCollDown should be restored to its default value ",
					0, c.getTraitCooldown());

		}
	}

	@Test(timeout = timeoutValue)
	public void testMoveRightToVenganceMysteryCell() throws Exception {

		for (int it = 0; it < 20; it++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			int damage = (((int) (Math.random() * 100)) + 50);

			Vengance vg = new Vengance("Vengance", damage);
			MysteryCell mys = new MysteryCell(vg);
			task1.getMap()[2][3] = mys;

			h.setHp((((int) (Math.random() * 5)) * 50 + 200));
			r.setHp((((int) (Math.random() * 5)) * 50 + 200));
			s.setHp((((int) (Math.random() * 5)) * 50 + 200));

			task1.setCurrentChamp(g);
			int initialHp = ((Wizard) (task1.getCurrentChamp())).getHp();
			int initialHpH = h.getHp();
			int initialHpR = r.getHp();
			int initialHpS = s.getHp();

			task1.moveRight();

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Vengance mystery, his/her hp should not be affected ",
					initialHp, g.getHp());

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Vengance mystery, the hp of all the other champions should be affected ",
					initialHpH - damage, h.getHp());

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Vengance mystery, the hp of all the other champions should be affected ",
					initialHpR - damage, r.getHp());

			assertEquals(
					"Whenever a champion moves right to a Mystery Cell containing Vengance mystery, the hp of all other the champions should be affected ",
					initialHpS - damage, s.getHp());

		}
	}

}
