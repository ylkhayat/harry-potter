package harrypotter.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import harrypotter.model.magic.*;
import harrypotter.model.world.*;

import org.junit.Test;

public class M1V5 {

	public static final int timeoutValue = 1000;

	@Test(timeout = timeoutValue)
	public void testClassIsAbstractMystery() {

		testClassIsAbstract(Mystery.class);

	}

	@Test(timeout = timeoutValue)
	public void testClassIsSubClassVengance() throws Exception {

		testClassIsSubClass(Vengance.class, Mystery.class);

	}

	@Test(timeout = timeoutValue)
	public void testClassIsSubClassRestoration() throws Exception {

		testClassIsSubClass(Restoration.class, Mystery.class);

	}

	@Test(timeout = timeoutValue)
	public void testInstanceVariableDamageInVenganceClassGetter()
			throws Exception {

		testGetterMethodExistsInClass(Vengance.class, "getDamage", int.class);

	}

	@Test(timeout = timeoutValue)
	public void testInstanceVariableNameInMysteryClassGetter() throws Exception {

		testGetterMethodExistsInClass(Mystery.class, "getName", String.class);

	}

	@Test(timeout = timeoutValue)
	public void testInstanceVariableDamageInVenganceClass() throws Exception {

		testInstanceVariablesArePresent(Vengance.class, "damage", true);
		testInstanceVariablesType(Vengance.class, "damage", int.class);
		testInstanceVariablesArePrivate(Vengance.class, "damage");

	}

	@Test(timeout = timeoutValue)
	public void testConstructorMystery() throws Exception {

		Class<?>[] inputs = { String.class };
		testConstructorExists(Mystery.class, inputs);

	}

	@Test(timeout = timeoutValue)
	public void testConstructorVengance() throws Exception {

		Class<?>[] inputs = { String.class, int.class };
		testConstructorExists(Vengance.class, inputs);

	}

	@Test(timeout = timeoutValue)
	public void testConstructorRestoration() throws Exception {

		Class<?>[] inputs = { String.class };
		testConstructorExists(Restoration.class, inputs);

	}

	@Test(timeout = timeoutValue)
	public void testClassIsSubClassMysteryCell() throws Exception {

		testClassIsSubClass(MysteryCell.class, Cell.class);

	}

	@Test(timeout = timeoutValue)
	public void testInstanceVariableMysteryInMysteryCellClass()
			throws Exception {

		testInstanceVariablesArePresent(MysteryCell.class, "mystery", true);
		testInstanceVariablesType(MysteryCell.class, "mystery", Mystery.class);
		testInstanceVariablesArePrivate(MysteryCell.class, "mystery");

	}

	@Test(timeout = timeoutValue)
	public void testConstructorMysteryCell() throws Exception {

		Class<?>[] inputs = { Mystery.class };
		testConstructorExists(MysteryCell.class, inputs);

	}

	@Test(timeout = timeoutValue)
	public void testConstructorVengancenitialization() throws Exception {

		testConstructorVengance();

		for (int it = 0; it < 10; it++) {

			Constructor<?> constructor = Class.forName(
					"harrypotter.model.magic.Vengance").getConstructor(
					String.class, int.class);

			int damage = (int) (Math.random() * 300) + 50;
			int name = (int) (Math.random() * 100) + 50;

			Object myObj = constructor.newInstance("Vengance" + name, damage);

			testInstanceVariableDamageInVenganceClassGetter();

			Method myObjMethod = myObj.getClass().getMethod("getDamage");
			int h = (int) myObjMethod.invoke(myObj);

			assertEquals(
					"The constructor of the Vengance class should initialize the instance variable \"damage\" correctly",
					damage, h);

			Method myObjMethod2 = myObj.getClass().getMethod("getName");
			String h2 = (String) myObjMethod2.invoke(myObj);
			assertEquals(
					"The constructor of the Vengance class should initialize the instance variable \"name\" correctly",
					"Vengance" + name, h2);

		}
	}

	// /////////////////////////helper methods

	@SuppressWarnings("rawtypes")
	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from "
				+ aClass.getSimpleName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
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
							+ aClass.getSimpleName() + " class.",

					thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getSimpleName() + " class.",

					thrown);

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
					+ " instance variable in class " + aClass.getSimpleName(),
					thrown);
		} else {
			assertTrue("There should not be " + varName
					+ " instance variable in class " + aClass.getSimpleName()
					+ ", it should be inherited from the super class", thrown);
		}
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class "
						+ aClass.getSimpleName()
						+ " should not be accessed outside that class", 2,
				f.getModifiers());
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesType(Class aClass, String varName,
			Class varType) throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class "
						+ aClass.getSimpleName() + " should be of type "
						+ varType.getSimpleName(), varType, f.getType());
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
						+ aClass.getSimpleName() + " is a READ variable.",
				found);
		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getSimpleName() + " class.", m.getReturnType()
				.isAssignableFrom(returnedType));
	}
}
