package harrypotter.tests;

import harrypotter.model.character.*;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class M1PrivateTest {

	@Test(timeout = 100)
	public void testClassImplementsInterfaceRavenclawWizard() throws Exception {
		testClassImplementsInterface(RavenclawWizard.class, Champion.class);
	}

	@Test(timeout = 100)
	public void testClassImplementsInterfaceSlytherinWizard() throws Exception {
		testClassImplementsInterface(SlytherinWizard.class, Champion.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassCupCell() throws Exception {
		testClassIsSubClass(CupCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassDamagingSpell() throws Exception {
		testClassIsSubClass(DamagingSpell.class, Spell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassFirstTask() throws Exception {
		testClassIsSubClass(FirstTask.class, Task.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassGryffindorWizard() throws Exception {
		testClassIsSubClass(GryffindorWizard.class, Wizard.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassHealingSpell() throws Exception {
		testClassIsSubClass(HealingSpell.class, Spell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassHufflepuffWizard() throws Exception {
		testClassIsSubClass(HufflepuffWizard.class, Wizard.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassObstacleCell() throws Exception {
		testClassIsSubClass(ObstacleCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassPhysicalObstacle() throws Exception {
		testClassIsSubClass(PhysicalObstacle.class, Obstacle.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassSecondTask() throws Exception {
		testClassIsSubClass(SecondTask.class, Task.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassTreasureCell() throws Exception {
		testClassIsSubClass(TreasureCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassWallCell() throws Exception {
		testClassIsSubClass(WallCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassPotionIsSubclassCollectible() throws Exception {
		testClassIsSubClass(Potion.class, Collectible.class);
	}

	@Test(timeout = 100)
	public void testConstructorCollectibleCell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { Collectible.class };
		testConstructorExists(CollectibleCell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorCollectibleCellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.CollectibleCell").getConstructor(
				Collectible.class);
		Object myObj = constructor.newInstance(new Potion("p", 100));

		Method myObjMethod = myObj.getClass().getMethod("getCollectible");
		Collectible col = (Collectible) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the CollectibleCell class should initialize the instance variable \"collectible\" correctly",
				col instanceof Collectible
						&& ((Potion) col).getName().equals("p")
						&& ((Potion) col).getAmount() == 100);
	}

	@Test(timeout = 100)
	public void testConstructorDamagingSpell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(DamagingSpell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorDamagingSpellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.magic.DamagingSpell").getConstructor(
				String.class, int.class, int.class, int.class);
		Object myObj = constructor.newInstance("Amortentia", 30, 40, 50);

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the DamagingSpell class should initialize the instance variable \"name\" correctly",
				"Amortentia", name);

		myObjMethod = myObj.getClass().getMethod("getCost");
		int cost = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the DamagingSpell class should initialize the instance variable \"cost\" correctly",
				30, cost);
		myObjMethod = myObj.getClass().getMethod("getDefaultCooldown");
		int cool = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the DamagingSpell class should initialize the instance variable \"defaultCooldown\" correctly",
				40, cool);
		myObjMethod = myObj.getClass().getMethod("getDamageAmount");
		int damageAmount = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the DamagingSpell class should initialize the instance variable \"damageAmount\" correctly",
				50, damageAmount);
		myObjMethod = myObj.getClass().getMethod("getCoolDown");
		int coolDown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the DamagingSpell class should initialize the instance variable \"coolDown\" correctly",
				0, coolDown);
	}

	@Test(timeout = 100)
	public void testConstructorGryffindorWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(GryffindorWizard.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorHealingSpell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(HealingSpell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorHealingSpellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.magic.HealingSpell").getConstructor(
				String.class, int.class, int.class, int.class);
		Object myObj = constructor.newInstance("Amortentia", 30, 40, 50);

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HealingSpell class should initialize the instance variable \"name\" correctly",
				"Amortentia", name);

		myObjMethod = myObj.getClass().getMethod("getCost");
		int cost = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HealingSpell class should initialize the instance variable \"cost\" correctly",
				30, cost);
		myObjMethod = myObj.getClass().getMethod("getDefaultCooldown");
		int cool = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HealingSpell class should initialize the instance variable \"defaultCooldown\" correctly",
				40, cool);
		myObjMethod = myObj.getClass().getMethod("getHealingAmount");
		int healingAmount = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HealingSpell class should initialize the instance variable \"healingAmount\" correctly",
				50, healingAmount);
		myObjMethod = myObj.getClass().getMethod("getCoolDown");
		int coolDown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HealingSpell class should initialize the instance variable \"coolDown\" correctly",
				0, coolDown);
	}

	@Test(timeout = 100)
	public void testConstructorHufflepuffWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(HufflepuffWizard.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorMerpersonInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.Merperson").getConstructor(int.class,
				int.class);
		Object myObj = constructor.newInstance(50, 100);

		Method myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the Merperson class should initialize the instance variable \"hp\" correctly",
				50, hp);

		myObjMethod = myObj.getClass().getMethod("getDamage");
		int damage = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the Merperson class should initialize the instance variable \"damage\" correctly",
				100, damage);

	}

	@Test(timeout = 100)
	public void testConstructorMerpersonObstacle() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { int.class, int.class };
		testConstructorExists(Merperson.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorObstacle() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { int.class };
		testConstructorExists(Obstacle.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorPotion() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class };
		testConstructorExists(Potion.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorPotionInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.magic.Potion").getConstructor(String.class,
				int.class);
		Object myObj = constructor.newInstance("Amortentia", 30);

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the Potion class should initialize the instance variable \"name\" correctly",
				"Amortentia", name);
		myObjMethod = myObj.getClass().getMethod("getAmount");

		int amount = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the Potion class should initialize the instance variable \"amount\" correctly",
				30, amount);

	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testConstructorThirdTaskInitialization() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		Constructor<?> constructor = Class.forName(
				"harrypotter.model.tournament.ThirdTask").getConstructor(
				e.getClass());

		Object myObj = constructor.newInstance(e);
		Method myObjMethod = myObj.getClass().getMethod("getChampions");
		ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
				.invoke(myObj);
		assertTrue(
				"The constructor of the ThirdTask class should not randomly shuffle the champions list",
				champs.get(0).equals(g) && champs.get(1).equals(h)
						&& champs.get(2).equals(r) && champs.get(3).equals(s));

		myObj = constructor.newInstance(e);
		myObjMethod = myObj.getClass().getMethod("getMap");
		Cell[][] map = (Cell[][]) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the ThirdTask class should initialize the instance variable \"map\" correctly",
				map.length == 10 && map[0].length == 10);

		myObjMethod = myObj.getClass().getMethod("getAllowedMoves");
		int allowedMoves = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the ThirdTask class should initialize the instance variable \"allowedMoves\" correctly",
				1, allowedMoves);

		myObjMethod = myObj.getClass().getMethod("isTraitActivated");
		boolean traitActivated = (boolean) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the ThirdTask class should initialize the instance variable \"traitActivated\" correctly",
				false, traitActivated);

		myObjMethod = myObj.getClass().getMethod("getChampions");

		ArrayList<Potion> correctPotions = new ArrayList<Potion>();
		correctPotions.add(new Potion("Felix Felicis", 1000));
		correctPotions.add(new Potion("Pepperup Potion", 500));
		correctPotions.add(new Potion("Skele-Gro", 200));
		correctPotions.add(new Potion("Amortentia", 100));
		correctPotions.add(new Potion("Senzu", 700));
		correctPotions.add(new Potion("Thunder Bolt", 1700));

		myObjMethod = myObj.getClass().getMethod("getPotions");
		ArrayList<Potion> potions = (ArrayList<Potion>) myObjMethod
				.invoke(myObj);

		assertEquals(
				"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file, after ThirdTask intialization",
				correctPotions.size(), potions.size());

		for (int i = 0; i < correctPotions.size(); i++) {

			assertEquals(
					"Potion name is not loaded correctly from the CSV file after ThirdTask intialization",
					((Potion) correctPotions.get(i)).getName(),
					((Potion) potions.get(i)).getName());

			assertEquals(
					"Potion amount is not loaded correctly from the CSV file after ThirdTask intialization",
					((Potion) correctPotions.get(i)).getAmount(),
					((Potion) potions.get(i)).getAmount());
		}
	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testConstructorTournamentInitialization() throws Exception {

		Constructor<?> constructor = Class.forName(
				"harrypotter.model.tournament.Tournament").getConstructor();
		Object myObj = null;

		try {
			myObj = constructor.newInstance();
		} catch (Exception e) {
			fail("Failed to create a new instance of the Tournment class.\nAn Exception was raised: "
					+ e.toString());
		}

		Method myObjMethod = myObj.getClass().getMethod("getChampions");
		ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
				.invoke(myObj);
		assertTrue(
				"The constructor of the Tournament class should initialize the instance variable \"champions\" ",
				champs != null && champs.size() == 0);

		myObjMethod = myObj.getClass().getMethod("getSpells");
		ArrayList<Spell> loadedSpells = (ArrayList<Spell>) myObjMethod
				.invoke(myObj);
		assertTrue(
				"The constructor of the Tournament class should initialize the instance variable \"spells\" ",
				loadedSpells != null);

		ArrayList<Spell> correctSpells = new ArrayList<Spell>();
		correctSpells.add(new DamagingSpell("Sectumsempra", 150, 5, 300));
		correctSpells.add(new DamagingSpell("Reducto", 100, 2, 100));
		correctSpells
				.add(new DamagingSpell("Piertotum Locomotor", 400, 1, 200));
		correctSpells.add(new DamagingSpell("Oppugno", 50, 2, 100));
		correctSpells.add(new DamagingSpell("Incendio", 150, 4, 250));
		correctSpells.add(new DamagingSpell("Expulso", 200, 5, 300));
		correctSpells.add(new DamagingSpell("Bombarda", 300, 3, 350));
		correctSpells.add(new DamagingSpell("Avada Kedavra", 500, 10, 650));
		correctSpells.add(new DamagingSpell("Crucio", 400, 6, 500));
		correctSpells.add(new DamagingSpell("Igni", 300, 2, 300));
		correctSpells.add(new DamagingSpell("Kamehameha", 200, 7, 400));
		correctSpells.add(new HealingSpell("Cheering Charm", 50, 2, 100));
		correctSpells.add(new HealingSpell("Expecto Patronum", 150, 8, 550));
		correctSpells.add(new HealingSpell("Ferula", 200, 4, 200));
		correctSpells.add(new HealingSpell("Protego Horribilis", 300, 1, 100));
		correctSpells.add(new HealingSpell("Rennervate", 100, 3, 200));
		correctSpells.add(new HealingSpell("Quen", 50, 1, 50));
		correctSpells.add(new RelocatingSpell("Accio", 100, 1, 1));
		correctSpells.add(new RelocatingSpell("Imperio", 400, 10, 10));
		correctSpells.add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		correctSpells.add(new RelocatingSpell("Axii", 200, 3, 3));

		assertEquals(
				"The loaded spells ArrayList in the Tournament class doesn't contain the same number of spells given in the CSV file",
				correctSpells.size(), loadedSpells.size());

		for (int i = 0; i < correctSpells.size(); i++) {
			assertTrue(
					"The loaded spells ArrayList in the Tournament class doesn't contain the same number of "
							+ correctSpells
									.get(i)
									.getClass()
									.getName()
									.toLowerCase()
									.substring(
											0,
											correctSpells.get(i).getClass()
													.getName().length() - 5)
							+ " spells given in the CSV file",
					correctSpells.get(i).getClass().getName()
							.equals(loadedSpells.get(i).getClass().getName()));

			assertEquals(
					"Spell name in the Tournament class is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getName(),
					((Spell) loadedSpells.get(i)).getName());

			assertEquals(
					"Spell cost in the Tournament class is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCost(),
					((Spell) loadedSpells.get(i)).getCost());

			assertEquals(
					"Spell cooldown in the Tournament class is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCoolDown(),
					((Spell) loadedSpells.get(i)).getCoolDown());

			if ((correctSpells.get(i)) instanceof DamagingSpell)
				assertEquals(
						"Spell damage amount in the Tournament class is not loaded correctly from the CSV file",
						((DamagingSpell) correctSpells.get(i))
								.getDamageAmount(),
						((DamagingSpell) loadedSpells.get(i)).getDamageAmount());

			if ((correctSpells.get(i)) instanceof HealingSpell)
				assertEquals(
						"Spell healing amount in the Tournament class is not loaded correctly from the CSV file",
						((HealingSpell) correctSpells.get(i))
								.getHealingAmount(),
						((HealingSpell) loadedSpells.get(i)).getHealingAmount());

			if ((correctSpells.get(i)) instanceof RelocatingSpell)
				assertEquals(
						"Spell range in the Tournament class is not loaded correctly from the CSV file",
						((RelocatingSpell) correctSpells.get(i)).getRange(),
						((RelocatingSpell) loadedSpells.get(i)).getRange());

		}

	}

	@Test(timeout = 100)
	public void testConstructorTreasureCell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { Champion.class };
		testConstructorExists(TreasureCell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorTreasureCellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.TreasureCell").getConstructor(
				Champion.class);
		Object myObj = constructor.newInstance(new GryffindorWizard("Gryff"));

		Method myObjMethod = myObj.getClass().getMethod("getOwner");
		Champion champ = (Champion) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the TreasureCell class should initialize the instance variable \"owner\" correctly",
				champ instanceof GryffindorWizard
						&& ((GryffindorWizard) champ).getName().equals("Gryff"));
	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testFirstConstructorRavenclawWizardInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.character.RavenclawWizard").getConstructor(
				String.class);
		Object myObj = constructor.newInstance("Ravenclaw");

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"name\" correctly",
				"Ravenclaw", name);

		myObjMethod = myObj.getClass().getMethod("getDefaultHp");
		int defaultHp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"defaultHp\" correctly",
				750, defaultHp);

		myObjMethod = myObj.getClass().getMethod("getDefaultIp");
		int defaultIp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"defaultIp\" correctly",
				700, defaultIp);

		myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"hp\" correctly",
				750, hp);

		myObjMethod = myObj.getClass().getMethod("getIp");
		int ip = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"ip\" correctly",
				700, ip);

		myObjMethod = myObj.getClass().getMethod("getSpells");
		ArrayList<Spell> spells = (ArrayList<Spell>) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"spells\" correctly",
				0, spells.size());

		myObjMethod = myObj.getClass().getMethod("getInventory");
		ArrayList<Collectible> inv = (ArrayList<Collectible>) myObjMethod
				.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"inventory\" correctly",
				0, inv.size());

		myObjMethod = myObj.getClass().getMethod("getTraitCooldown");
		int traitCooldown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RavenclawWizard class should initialize the instance variable \"traitCooldown\" correctly",
				0, traitCooldown);

	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testFirstConstructorSlytherinWizardInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.character.SlytherinWizard").getConstructor(
				String.class);
		Object myObj = constructor.newInstance("Slytherin");

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"name\" correctly",
				"Slytherin", name);

		myObjMethod = myObj.getClass().getMethod("getDefaultHp");
		int defaultHp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"defaultHp\" correctly",
				850, defaultHp);

		myObjMethod = myObj.getClass().getMethod("getDefaultIp");
		int defaultIp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"defaultIp\" correctly",
				550, defaultIp);

		myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"hp\" correctly",
				850, hp);

		myObjMethod = myObj.getClass().getMethod("getIp");
		int ip = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"ip\" correctly",
				550, ip);

		myObjMethod = myObj.getClass().getMethod("getSpells");
		ArrayList<Spell> spells = (ArrayList<Spell>) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"spells\" correctly",
				0, spells.size());

		myObjMethod = myObj.getClass().getMethod("getInventory");
		ArrayList<Collectible> inv = (ArrayList<Collectible>) myObjMethod
				.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"inventory\" correctly",
				0, inv.size());

		myObjMethod = myObj.getClass().getMethod("getTraitCooldown");
		int traitCooldown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SlytherinWizard class should initialize the instance variable \"traitCooldown\" correctly",
				0, traitCooldown);

	}

	@Test(timeout = 100)
	public void testFirstConstructorWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(Wizard.class, inputs);
	}

	@Test(timeout = 2000)
	public void testFirstTaskMapCorrectCellsUsed() throws Exception {
		for (int c = 0; c < 100; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					assertTrue(
							"There are incorrect cell types used in the first task map",
							(currentMap[i][j] instanceof ChampionCell)
									|| (currentMap[i][j] instanceof ObstacleCell)
									|| (currentMap[i][j] instanceof CollectibleCell || (currentMap[i][j] instanceof EmptyCell)));
				}
			}

		}
	}

	@Test(timeout = 2000)
	public void testFirstTaskMapObstacleCount() throws Exception {

		for (int c = 0; c < 50; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			int correctObstacles = 40;
			int currentObstacles = 0;
			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell)
						currentObstacles++;
				}
			}

			assertEquals("Number of obstacles in the map is incorrect",
					correctObstacles, currentObstacles);
		}
	}

	@Test(timeout = 1000)
	public void testFirstTaskMapObstacleEggsCellReserved() throws Exception {

		for (int i = 0; i < 500; i++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			assertTrue("The center of the map is not reserved for the eggs",
					currentMap[4][4] instanceof EmptyCell);
		}
	}

	@Test(timeout = 5000)
	public void testFirstTaskMapObstacleHPRandom() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();

		for (int c = 0; c < 1000; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue("Obstacle HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);
						if (!hpValues
								.contains(((ObstacleCell) currentMap[i][j])
										.getObstacle().getHp()))
							hpValues.add(((ObstacleCell) currentMap[i][j])
									.getObstacle().getHp());
					}
				}
			}

			if (c > 100 && hpValues.size() > 10)
				break;

		}
		assertTrue("Obstacle HP randomization is not done correctly",
				hpValues.size() > 10);

	}

	@Test(timeout = 2000)
	public void testFirstTaskMapObstacleRandomLocation() throws Exception {

		int[][] obstaclePositions = new int[10][10];
		int correctObstacles = 40;

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell)
						obstaclePositions[i][j] = 1;
				}
			}
		}

		assertTrue("The obstacle positions are not properly randomized",
				sumArray(obstaclePositions) > correctObstacles);
	}

	@Test(timeout = 2000)
	public void testFirstTaskMapPotionRandomLocation() throws Exception {

		int[][] potionPositions = new int[10][10];
		int correctPotionss = 10;

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell)
						potionPositions[i][j] = 1;
				}
			}
		}

		assertTrue("The potion positions are not properly randomized",
				sumArray(potionPositions) > correctPotionss);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectibleCell() throws Exception {
		testInstanceVariablesArePresent(CollectibleCell.class, "collectible",
				true);
		testInstanceVariablesArePrivate(CollectibleCell.class, "collectible");
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectibleCellGetter() throws Exception {
		testGetterMethodExistsInClass(CollectibleCell.class, "getCollectible",
				Collectible.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectibleCellSetter() throws Exception {
		testSetterMethodExistsInClass(CollectibleCell.class, "setCollectible",
				Collectible.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamageMerpersonObstacle() throws Exception {
		testInstanceVariablesArePresent(Merperson.class, "damage", true);
		testInstanceVariablesArePrivate(Merperson.class, "damage");
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamageMerpersonObstacleGetter()
			throws Exception {
		testGetterMethodExistsInClass(Merperson.class, "getDamage", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamageMerpersonObstacleSetter()
			throws Exception {
		testSetterMethodExistsInClass(Merperson.class, "setDamage", int.class,
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamagingSpellDamageAmount()
			throws Exception {
		testInstanceVariablesArePresent(DamagingSpell.class, "damageAmount",
				true);
		testInstanceVariablesArePrivate(DamagingSpell.class, "damageAmount");
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamagingSpellDamageAmountGetter()
			throws Exception {
		testGetterMethodExistsInClass(DamagingSpell.class, "getDamageAmount",
				int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableDamagingSpellDamageAmountSetter()
			throws Exception {
		testSetterMethodExistsInClass(DamagingSpell.class, "setDamageAmount",
				int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHealingSpellHealingAmount()
			throws Exception {
		testInstanceVariablesArePresent(HealingSpell.class, "healingAmount",
				true);
		testInstanceVariablesArePrivate(HealingSpell.class, "healingAmount");
	}

	@Test(timeout = 100)
	public void testInstanceVariableHealingSpellHealingAmountGetter()
			throws Exception {
		testGetterMethodExistsInClass(HealingSpell.class, "getHealingAmount",
				int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHealingSpellHealingAmountSetter()
			throws Exception {
		testSetterMethodExistsInClass(HealingSpell.class, "setHealingAmount",
				int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHpMerpersonObstacle() throws Exception {
		testInstanceVariablesArePresent(Merperson.class, "hp", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHpPhysicalObstacle() throws Exception {
		testInstanceVariablesArePresent(PhysicalObstacle.class, "hp", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePotion() throws Exception {
		testInstanceVariablesArePresent(Potion.class, "amount", true);
		testInstanceVariablesArePresent(Potion.class, "name", false);
		testInstanceVariablesArePrivate(Potion.class, "amount");
	}

	@Test(timeout = 100)
	public void testInstanceVariablePotionGetter() throws Exception {
		testGetterMethodExistsInClass(Potion.class, "getAmount", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariablePotionSetter() throws Exception {
		testSetterMethodExistsInClass(Potion.class, "setAmount", int.class,
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableRavenclawWizardInherited() throws Exception {
		testInstanceVariablesArePresent(RavenclawWizard.class, "name", false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "defaultHp",
				false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "defaultIp",
				false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "hp", false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "ip", false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "spells", false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "inventory",
				false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "location",
				false);
		testInstanceVariablesArePresent(RavenclawWizard.class, "traitCooldown",
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariablesDamagingSpellInherited() throws Exception {
		testInstanceVariablesArePresent(DamagingSpell.class, "name", false);
		testInstanceVariablesArePresent(DamagingSpell.class, "cost", false);
		testInstanceVariablesArePresent(DamagingSpell.class, "coolDown", false);
		testInstanceVariablesArePresent(DamagingSpell.class, "defultCooldown",
				false);

	}

	@Test(timeout = 100)
	public void testInstanceVariablesFirstTaskInherited() throws Exception {
		testInstanceVariablesArePresent(FirstTask.class, "champions", false);
		testInstanceVariablesArePresent(FirstTask.class, "currentChamp", false);
		testInstanceVariablesArePresent(FirstTask.class, "map", false);
		testInstanceVariablesArePresent(FirstTask.class, "allowedMoves", false);
		testInstanceVariablesArePresent(FirstTask.class, "traitActivated",
				false);
		testInstanceVariablesArePresent(FirstTask.class, "potions", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariablesHealingSpellInherited() throws Exception {
		testInstanceVariablesArePresent(HealingSpell.class, "name", false);
		testInstanceVariablesArePresent(HealingSpell.class, "cost", false);
		testInstanceVariablesArePresent(HealingSpell.class, "coolDown", false);
		testInstanceVariablesArePresent(HealingSpell.class, "defultCooldown",
				false);

	}

	@Test(timeout = 100)
	public void testInstanceVariableSlytherinWizardInherited() throws Exception {
		testInstanceVariablesArePresent(SlytherinWizard.class, "name", false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "defaultHp",
				false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "defaultIp",
				false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "hp", false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "ip", false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "spells", false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "inventory",
				false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "location",
				false);
		testInstanceVariablesArePresent(SlytherinWizard.class, "traitCooldown",
				false);

	}

	@Test(timeout = 100)
	public void testInstanceVariableSlytherinWizardTraitDiretionSetter()
			throws Exception {
		testSetterMethodExistsInClass(SlytherinWizard.class,
				"setTraitDirection", Direction.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCoolDown() throws Exception {
		testInstanceVariablesArePresent(Spell.class, "coolDown", true);
		testInstanceVariablesArePrivate(Spell.class, "coolDown");
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCostGetter() throws Exception {
		testGetterMethodExistsInClass(Spell.class, "getCost", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCostSetter() throws Exception {
		testSetterMethodExistsInClass(Spell.class, "setCost", int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellDefultCooldown() throws Exception {
		testInstanceVariablesArePresent(Spell.class, "defaultCooldown", true);
		testInstanceVariablesArePrivate(Spell.class, "defaultCooldown");
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellNameGetter() throws Exception {
		testGetterMethodExistsInClass(Spell.class, "getName", String.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellNameSetter() throws Exception {
		testSetterMethodExistsInClass(Spell.class, "setName", String.class,
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariablesSecondTaskInherited() throws Exception {
		testInstanceVariablesArePresent(SecondTask.class, "champions", false);
		testInstanceVariablesArePresent(SecondTask.class, "currentChamp", false);
		testInstanceVariablesArePresent(SecondTask.class, "map", false);
		testInstanceVariablesArePresent(SecondTask.class, "allowedMoves", false);
		testInstanceVariablesArePresent(SecondTask.class, "traitActivated",
				false);
		testInstanceVariablesArePresent(SecondTask.class, "potions", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentFirstTask() throws Exception {
		testInstanceVariablesArePresent(Tournament.class, "firstTask", true);
		testInstanceVariablesArePrivate(Tournament.class, "firstTask");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentFirstTaskGetter()
			throws Exception {
		testGetterMethodExistsInClass(Tournament.class, "getFirstTask",
				FirstTask.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentFirstTaskSetter()
			throws Exception {
		testSetterMethodExistsInClass(Tournament.class, "setFirstTask",
				FirstTask.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSecondTask() throws Exception {
		testInstanceVariablesArePresent(Tournament.class, "secondTask", true);
		testInstanceVariablesArePrivate(Tournament.class, "secondTask");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSecondTaskGetter()
			throws Exception {
		testGetterMethodExistsInClass(Tournament.class, "getSecondTask",
				SecondTask.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSecondTaskSetter()
			throws Exception {
		testSetterMethodExistsInClass(Tournament.class, "setSecondTask",
				SecondTask.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentThirdask() throws Exception {
		testInstanceVariablesArePresent(Tournament.class, "thirdTask", true);
		testInstanceVariablesArePrivate(Tournament.class, "thirdTask");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentThirdTaskGetter()
			throws Exception {
		testGetterMethodExistsInClass(Tournament.class, "getThirdTask",
				ThirdTask.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentThirdTaskSetter()
			throws Exception {
		testSetterMethodExistsInClass(Tournament.class, "setThirdTask",
				ThirdTask.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTreasureCell() throws Exception {
		testInstanceVariablesArePresent(TreasureCell.class, "owner", true);
		testInstanceVariablesArePrivate(TreasureCell.class, "owner");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTreasureCellGetter() throws Exception {
		testGetterMethodExistsInClass(TreasureCell.class, "getOwner",
				Champion.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTreasureCellSetter() throws Exception {
		testSetterMethodExistsInClass(TreasureCell.class, "setOwner",
				Champion.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDeafultHpGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getDefaultHp", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDeafultHpSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setDefaultHp", int.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDeafultIpGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getDefaultIp", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDeafultIpSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setDefaultIp", int.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardHpGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getHp", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardHpSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setHp", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardInventory() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "inventory", true);
		testInstanceVariablesArePrivate(Wizard.class, "inventory");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardIpGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getIp", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardIpSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setIp", int.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardLocation() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "location", true);
		testInstanceVariablesArePrivate(Wizard.class, "location");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardNameGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getName", String.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardNameSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setName", String.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardSpells() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "spells", true);
		testInstanceVariablesArePrivate(Wizard.class, "spells");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardTraitCoolDown() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "traitCooldown", true);
		testInstanceVariablesArePrivate(Wizard.class, "traitCooldown");
	}

	@Test(timeout = 1000)
	public void testLoadedSpells() throws Exception {

		Tournament t = null;
		try {
			t = new Tournament();
		} catch (Exception e) {
			fail("Failed to create a new instance of the Tournment class.\nAn Exception was raised: "
					+ e.toString());
		}
		ArrayList<Spell> correctSpells = new ArrayList<Spell>();
		correctSpells.add(new DamagingSpell("Sectumsempra", 150, 5, 300));
		correctSpells.add(new DamagingSpell("Reducto", 100, 2, 100));
		correctSpells
				.add(new DamagingSpell("Piertotum Locomotor", 400, 1, 200));
		correctSpells.add(new DamagingSpell("Oppugno", 50, 2, 100));
		correctSpells.add(new DamagingSpell("Incendio", 150, 4, 250));
		correctSpells.add(new DamagingSpell("Expulso", 200, 5, 300));
		correctSpells.add(new DamagingSpell("Bombarda", 300, 3, 350));
		correctSpells.add(new DamagingSpell("Avada Kedavra", 500, 10, 650));
		correctSpells.add(new DamagingSpell("Crucio", 400, 6, 500));
		correctSpells.add(new DamagingSpell("Igni", 300, 2, 300));
		correctSpells.add(new DamagingSpell("Kamehameha", 200, 7, 400));
		correctSpells.add(new HealingSpell("Cheering Charm", 50, 2, 100));
		correctSpells.add(new HealingSpell("Expecto Patronum", 150, 8, 550));
		correctSpells.add(new HealingSpell("Ferula", 200, 4, 200));
		correctSpells.add(new HealingSpell("Protego Horribilis", 300, 1, 100));
		correctSpells.add(new HealingSpell("Rennervate", 100, 3, 200));
		correctSpells.add(new HealingSpell("Quen", 50, 1, 50));
		correctSpells.add(new RelocatingSpell("Accio", 100, 1, 1));
		correctSpells.add(new RelocatingSpell("Imperio", 400, 10, 10));
		correctSpells.add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		correctSpells.add(new RelocatingSpell("Axii", 200, 3, 3));

		ArrayList<Spell> testSpells = t.getSpells();

		assertEquals(
				"The loaded spells ArrayList doesn't contain the same number of spells given in the CSV file",
				correctSpells.size(), testSpells.size());

		for (int i = 0; i < correctSpells.size(); i++) {
			assertTrue(
					"The loaded spells ArrayList doesn't contain the same number of "
							+ correctSpells
									.get(i)
									.getClass()
									.getName()
									.toLowerCase()
									.substring(
											0,
											correctSpells.get(i).getClass()
													.getName().length() - 5)
							+ " spells given in the CSV file",
					correctSpells.get(i).getClass().getName()
							.equals(testSpells.get(i).getClass().getName()));

			assertEquals(
					"Spell name is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getName(),
					((Spell) testSpells.get(i)).getName());

			assertEquals(
					"Spell cost is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCost(),
					((Spell) testSpells.get(i)).getCost());

			assertEquals(
					"Spell cooldown is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCoolDown(),
					((Spell) testSpells.get(i)).getCoolDown());

			if ((correctSpells.get(i)) instanceof DamagingSpell)
				assertEquals(
						"Spell damage amount is not loaded correctly from the CSV file",
						((DamagingSpell) correctSpells.get(i))
								.getDamageAmount(), ((DamagingSpell) testSpells
								.get(i)).getDamageAmount());

			if ((correctSpells.get(i)) instanceof HealingSpell)
				assertEquals(
						"Spell healing amount is not loaded correctly from the CSV file",
						((HealingSpell) correctSpells.get(i))
								.getHealingAmount(), ((HealingSpell) testSpells
								.get(i)).getHealingAmount());

			if ((correctSpells.get(i)) instanceof RelocatingSpell)
				assertEquals(
						"Spell range is not loaded correctly from the CSV file",
						((RelocatingSpell) correctSpells.get(i)).getRange(),
						((RelocatingSpell) testSpells.get(i)).getRange());

		}
	}

	@Test(timeout = 1000)
	public void testLoadPotionsMethod() throws Exception {

		PrintWriter potionWriter = new PrintWriter("potionsTest.csv");
		potionWriter.println("A,400");
		potionWriter.println("B,100");
		potionWriter.println("D,2200");
		potionWriter.println("C,300");
		potionWriter.close();
		GryffindorWizard c1 = new GryffindorWizard("1");
		HufflepuffWizard c2 = new HufflepuffWizard("2");
		RavenclawWizard c3 = new RavenclawWizard("3");
		SlytherinWizard c4 = new SlytherinWizard("4");
		ArrayList<Champion> tempChampions = new ArrayList<Champion>();
		tempChampions.add(c1);
		tempChampions.add(c2);
		tempChampions.add(c3);
		tempChampions.add(c4);
		ArrayList<Potion> correctPotions = new ArrayList<Potion>();
		correctPotions.add(new Potion("A", 400));
		correctPotions.add(new Potion("B", 100));
		correctPotions.add(new Potion("D", 2200));
		correctPotions.add(new Potion("C", 300));

		FirstTask firstTask = new FirstTask(tempChampions);
		firstTask.getPotions().clear();
		Method method = Task.class.getDeclaredMethod("loadPotions",
				new Class[] { String.class });
		method.setAccessible(true);
		method.invoke(firstTask, "potionsTest.csv");

		ArrayList<Potion> testPotions = firstTask.getPotions();

		assertEquals(
				"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file",
				correctPotions.size(), testPotions.size());

		for (int i = 0; i < correctPotions.size(); i++) {

			assertEquals(
					"Potion name is not loaded correctly from the CSV file",
					((Potion) correctPotions.get(i)).getName(),
					((Potion) testPotions.get(i)).getName());

			assertEquals(
					"Potion amount is not loaded correctly from the CSV file",
					((Potion) correctPotions.get(i)).getAmount(),
					((Potion) testPotions.get(i)).getAmount());
		}
	}

	@Test(timeout = 100)
	public void testMethodInGryffindorWizardTrait() throws Exception {
		testMethodExistsInClass(GryffindorWizard.class, "useTrait", true,
				Void.TYPE);
	}

	@Test(timeout = 100)
	public void testMethodInHufflepuffWizardTrait() throws Exception {
		testMethodExistsInClass(HufflepuffWizard.class, "useTrait", true,
				Void.TYPE);
	}

	@Test(timeout = 1000)
	public void testSecondTaskMapChampionCount() throws Exception {

		for (int c = 0; c < 100; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1" + c);
			HufflepuffWizard c2 = new HufflepuffWizard(c + "2");
			RavenclawWizard c3 = new RavenclawWizard("3" + c);
			SlytherinWizard c4 = new SlytherinWizard("4" + c);
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);

			switch (c / 25) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}

			SecondTask secondTask = new SecondTask(tempChampions);

			tempChampions = secondTask.getChampions();

			int correctChamps = tempChampions.size();
			int currentChamps = 0;
			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i += 9) {
				for (int j = 0; j < currentMap[i].length; j += 9) {
					if (currentMap[i][j] instanceof ChampionCell)
						currentChamps++;
				}
			}

			assertEquals("Number of champions in the map is incorrect",
					correctChamps, currentChamps);
		}
	}

	@Test(timeout = 1000)
	public void testSecondTaskMapChampionLocation() throws Exception {
		for (int i = 0; i < 100; i++) {
			GryffindorWizard c1 = new GryffindorWizard("13" + i);
			HufflepuffWizard c2 = new HufflepuffWizard("22" + i);
			RavenclawWizard c3 = new RavenclawWizard("33" + i);
			SlytherinWizard c4 = new SlytherinWizard("45" + i);
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);

			int quarter = i / 25;
			switch (quarter) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}

			SecondTask secondTask = new SecondTask(tempChampions);
			tempChampions = secondTask.getChampions();

			Cell[][] currentMap = secondTask.getMap();

			assertTrue("Corner cells should contain champions",
					currentMap[9][0] instanceof ChampionCell);

			assertTrue(
					"The first champion position is incorrect.\n"
							+ "Note: the coordinates of the first champion should match the bottom left cell of the 2D-array"
							+ " regardless of his/her coordinates in the map.",
					((Wizard) tempChampions.get(0)).getName().equals(
							((Wizard) ((ChampionCell) currentMap[9][0])
									.getChamp()).getName()));

			if (quarter < 3) {
				assertTrue("Corner cells should contain champions",
						currentMap[9][9] instanceof ChampionCell);

				assertTrue(
						"The second champion position is incorrect.\n"
								+ "Note: the coordinates of the second champion should match the bottom right cell of the 2D-array"
								+ " regardless of his/her coordinates in the map.",
						((Wizard) tempChampions.get(1)).getName().equals(
								((Wizard) ((ChampionCell) currentMap[9][9])
										.getChamp()).getName()));
			}

			if (quarter < 2) {
				assertTrue("Corner cells should contain champions",
						currentMap[0][9] instanceof ChampionCell);

				assertTrue(
						"The third champion position is incorrect.\n"
								+ "Note: the coordinates of the third champion should match the top right cell of the 2D-array"
								+ " regardless of his/her coordinates in the map.",
						((Wizard) tempChampions.get(2)).getName().equals(
								((Wizard) ((ChampionCell) currentMap[0][9])
										.getChamp()).getName()));
			}

			if (quarter < 1) {
				assertTrue("Corner cells should contain champions",
						currentMap[0][0] instanceof ChampionCell);

				assertTrue(
						"The fourth champion position is incorrect.\n"
								+ "Note: the coordinates of the fourth champion should match the top left cell of the 2D-array"
								+ " regardless of his/her coordinates in the map.",
						((Wizard) tempChampions.get(3)).getName().equals(
								((Wizard) ((ChampionCell) currentMap[0][0])
										.getChamp()).getName()));
			}
		}

	}

	@Test(timeout = 2000)
	public void testSecondTaskMapCorrectCellsUsed() throws Exception {
		for (int c = 0; c < 100; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					assertTrue(
							"There are incorrect cell types used in the first task map",
							(currentMap[i][j] instanceof ChampionCell)
									|| (currentMap[i][j] instanceof ObstacleCell)
									|| (currentMap[i][j] instanceof CollectibleCell || (currentMap[i][j] instanceof EmptyCell || (currentMap[i][j] instanceof TreasureCell))));
				}
			}

		}
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapMerpersonRandomLocation() throws Exception {

		int[][] obstaclePositions = new int[10][10];
		int correctObstacles = 40;

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell)
						obstaclePositions[i][j] = 1;
				}
			}
		}

		assertTrue("The Merperson positions are not properly randomized",
				sumArray(obstaclePositions) > correctObstacles);
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapPotionCount() throws Exception {
		for (int c = 0; c < 100; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			int correctPotions = 10;
			int currentPotions = 0;
			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell)
						currentPotions++;
				}
			}

			assertEquals("Number of potions in the map is incorrect",
					correctPotions, currentPotions);
		}
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapPotionRandomLocation() throws Exception {

		int[][] potionPositions = new int[10][10];
		int correctPotionss = 10;

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell)
						potionPositions[i][j] = 1;
				}
			}

		}

		assertTrue("The potion positions are not properly randomized",
				sumArray(potionPositions) > correctPotionss);
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapTreasureRandomLocation() throws Exception {

		int[][] treasurePositions = new int[10][10];

		for (int c = 0; c < 20; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);

			switch (c / 5) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}

			SecondTask secondTask = new SecondTask(tempChampions);
			tempChampions = secondTask.getChampions();

			int correctTreasures = tempChampions.size();
			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof TreasureCell)
						treasurePositions[i][j] = 1;
				}
			}

			if (c + 1 % 50 == 0) {
				assertTrue(
						"The Treasure positions are not properly randomized",
						sumArray(treasurePositions) > correctTreasures);
				treasurePositions = new int[10][10];
			}
		}

	}

	@Test(timeout = 1000)
	public void testThirdTaskGenerateMap() throws Exception {

		for (int c = 0; c < 12; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			Collections.shuffle(tempChampions);
			switch (c / 3) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}
			ThirdTask thirdTask = new ThirdTask(tempChampions);

			int[][] generatedMap = { { 0, 6, 0, 5, 5, 5, 0, 0, 0, 0 },
					{ 0, 5, 0, 5, 7, 5, 6, 0, 5, 0 },
					{ 0, 5, 6, 5, 0, 5, 0, 5, 5, 0 },
					{ 0, 5, 0, 6, 0, 5, 0, 5, 0, 6 },
					{ 6, 5, 5, 5, 5, 5, 0, 5, 0, 5 },
					{ 0, 0, 0, 0, 0, 0, 0, 5, 0, 5 },
					{ 5, 5, 5, 5, 5, 5, 5, 5, 0, 5 },
					{ 0, 5, 6, 0, 0, 5, 0, 6, 0, 5 },
					{ 6, 5, 0, 5, 6, 5, 0, 5, 0, 5 },
					{ 6, 0, 0, 5, 4, 3, 2, 5, 0, 1 } };

			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (generatedMap[i][j] == 0)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should be empty or contain a potion.",
								currentMap[i][j] instanceof EmptyCell
										|| currentMap[i][j] instanceof CollectibleCell);
					if (generatedMap[i][j] >= 1 && generatedMap[i][j] <= 4) {
						if (tempChampions.size() >= generatedMap[i][j])
							assertTrue(
									"Map is not correctly loaded from the CSV file. Cell should contain a champion.",
									currentMap[i][j] instanceof ChampionCell
											&& ((Wizard) ((ChampionCell) currentMap[i][j])
													.getChamp())
													.getName()
													.equals(((Wizard) tempChampions
															.get(generatedMap[i][j] - 1))
															.getName()));
						else
							assertTrue(
									"Map is not correctly loaded from the CSV file. Cell should be empty or contain a potion.",
									currentMap[i][j] instanceof EmptyCell
											|| currentMap[i][j] instanceof CollectibleCell);
					}
					if (generatedMap[i][j] == 5)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain a wall.",
								currentMap[i][j] instanceof WallCell);
					if (generatedMap[i][j] == 6)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain an obstacle.",
								currentMap[i][j] instanceof ObstacleCell);
					if (generatedMap[i][j] == 7)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain the cup.",
								currentMap[i][j] instanceof CupCell);

				}
			}
		}

	}

	@Test(timeout = 5000)
	public void testThirdTaskMapObstacleHPRandom() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();

		for (int c = 0; c < 1000; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			ThirdTask thirdTask = new ThirdTask(tempChampions);

			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue("Obstacle HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);
						if (!hpValues
								.contains(((ObstacleCell) currentMap[i][j])
										.getObstacle().getHp()))
							hpValues.add(((ObstacleCell) currentMap[i][j])
									.getObstacle().getHp());
					}
				}
			}

			if (c > 100 && hpValues.size() > 10)
				break;

		}
		assertTrue("Obstacle HP randomization is not done correctly",
				hpValues.size() > 10);

	}

	@Test(timeout = 5000)
	public void testThirdTaskMapObstacleHPRange() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();

		for (int c = 0; c < 10000; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			ThirdTask thirdTask = new ThirdTask(tempChampions);

			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue(
								"Obstacle used in obstacle cell is not a physical obstacle",
								((ObstacleCell) currentMap[i][j]).getObstacle() instanceof PhysicalObstacle);
						assertTrue("Obstacle HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);
						hpValues.add(((ObstacleCell) currentMap[i][j])
								.getObstacle().getHp());
					}
				}
			}

			if (c > 500 && (hpValues.contains(300) && hpValues.contains(200)))
				break;

		}
		assertTrue("Obstacle HP range is incorrect", hpValues.contains(300)
				&& hpValues.contains(200));
	}

	@Test(timeout = 2000)
	public void testThirdTaskMapPotionRandomLocation() throws Exception {

		int[][] potionPositions = new int[10][10];
		int correctPotionss = 10;
		int correctObstacles = 11;
		int correctWall = 43;

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			Collections.shuffle(tempChampions);
			switch (c / 10) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}
			ThirdTask thirdTask = new ThirdTask(tempChampions);
			int currentObstacles = 0;
			int currentWall = 0;
			int currentCup = 0;
			int currentChamps = 0;
			int correctChamps = tempChampions.size();

			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue(
								"Obstacle used in obstacle cell is not a physical obstacle",
								((ObstacleCell) currentMap[i][j]).getObstacle() instanceof PhysicalObstacle);
						currentObstacles++;
					}

					if (currentMap[i][j] instanceof WallCell)
						currentWall++;

					if (currentMap[i][j] instanceof CupCell)
						currentCup++;

					if (currentMap[i][j] instanceof ChampionCell)
						currentChamps++;

					if (currentMap[i][j] instanceof CollectibleCell)
						potionPositions[i][j] = 1;
				}
			}

			assertEquals("The number of obstacles is incorrect",
					correctObstacles, currentObstacles);

			assertEquals("The number of wall cells is incorrect", correctWall,
					currentWall);

			assertEquals("The number of champions is incorrect", correctChamps,
					currentChamps);

			assertEquals("The number of cup cells is incorrect", 1, currentCup);

		}

		assertTrue("The potion positions are not properly randomized",
				sumArray(potionPositions) > correctPotionss);
	}

	// ############################################# Helper methods
	// ############################################# //

	@SuppressWarnings("rawtypes")
	private void testClassImplementsInterface(Class aClass, Class interfaceName) {
		Class[] interfaces = aClass.getInterfaces();
		boolean inherits = false;
		for (Class i : interfaces) {
			if (i.toString().equals(interfaceName.toString()))
				inherits = true;
		}
		assertTrue(aClass.getName() + " class should implement "
				+ interfaceName.getName() + " interface.", inherits);
	}

	@SuppressWarnings("rawtypes")
	private void testClassIsSubClass(Class subClass, Class superClass) {
		assertEquals(subClass.getName() + " class should inherit from "
				+ superClass.getName() + ".", superClass,
				subClass.getSuperclass());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter"
							+ (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getName() + " class.",

					thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getName() + " class.",

					thrown);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void testGetterMethodExistsInClass(Class aClass, String methodName,
			Class returnedType) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (Exception e) {
			found = false;
		}
		String varName = methodName.substring(3).toLowerCase();
		assertTrue(
				"The " + varName + " instance variable in class "
						+ aClass.getName() + " is a READ variable.", found);
		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + " class.", m.getReturnType()
				.isAssignableFrom(returnedType));
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePresent(Class aClass, String varName,
			boolean implementedVar) throws SecurityException {

		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse("There should be " + varName
					+ " instance variable in class " + aClass.getName(), thrown);
		} else {
			assertTrue("There should not be " + varName
					+ " instance variable in class " + aClass.getName()
					+ ", it should be inherited from the super class", thrown);
		}
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class " + aClass.getName()
						+ " should not be accessed outside that class", 2,
				f.getModifiers());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testMethodExistsInClass(Class aClass, String methodName,
			boolean implementedMethod, Class returnType, Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue(
					"The " + methodName + " method in class "
							+ aClass.getName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes no parameters", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes " + inputsList + " parameter(s)",
					found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(returnType));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testSetterMethodExistsInClass(Class aClass, String methodName,
			Class inputType, boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a WRITE variable.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a READ ONLY variable.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (Exception e) {
			found = false;
		}

		assertTrue(aClass.getName() + " class should have " + methodName
				+ " method that takes one " + inputType.getSimpleName()
				+ " parameter", found);

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(Void.TYPE));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private int sumArray(int[][] input) {
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				sum += input[i][j];
			}
		}
		return sum;
	}

}
