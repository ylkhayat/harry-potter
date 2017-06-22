package harrypotter.model.world;

public class ObstacleCell extends Cell
{
		private Obstacle obstacle;
		
		public ObstacleCell(Obstacle o)
		{
			super(false);
			obstacle = o;
		}
		public Obstacle getObstacle() {
			return obstacle;
		}
}
