package harrypotter.model.character;

import harrypotter.model.world.Direction;

public interface WizardListener {
	
	public void onGryffindorTrait();
	public void onSlytherinTrait(Direction d) throws Exception;
	public void onHufflepuffTrait();
	public Object onRavenclawTrait();
}
