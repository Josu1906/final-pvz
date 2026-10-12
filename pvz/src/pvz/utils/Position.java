package pvz.utils;

import java.util.Objects;

public class Position {
	
	private int row;
	private int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int row() {
		// TODO Auto-generated method stub
		return row;
	}
	
	public int col() {
		// TODO Auto-generated method stub
		return col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}

	public boolean isHorizontallyAligned(Position position) {
		return this.row == position.row();
	}

	public boolean isVerticallyAligned(Position position) {
		return this.col == position.col();	}
}