package mainGame;

import java.awt.event.MouseAdapter;

/**
 * Created by L.J. Keijzer on 4-4-2016.
 */
public class CustomMouseListener extends MouseAdapter {
    private final int coordinateX;
    private final int coordinateY;

    public CustomMouseListener(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
}
