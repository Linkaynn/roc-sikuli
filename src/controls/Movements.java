package controls;

interface ReverseMovement {
	Movements reverse();
}
public enum Movements implements ReverseMovement {
	UP {
		public Movements reverse() {
			return DOWN;
		}
	},
	DOWN {
		public Movements reverse() {
			return UP;
		}
	},
	LEFT {
		public Movements reverse() {
			return RIGHT;
		}
	},
	RIGHT {
		public Movements reverse() {
			return LEFT;
		}
	}
}
