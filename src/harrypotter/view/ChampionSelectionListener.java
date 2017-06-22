package harrypotter.view;

import java.io.IOException;

import harrypotter.model.character.Champion;

public interface ChampionSelectionListener
{
	public void addChampion(Champion c);
	public void removeChampion(String s);
	public void beginTournament() throws IOException;
}
