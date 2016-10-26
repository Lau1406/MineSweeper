package mainGame;

import java.awt.*;

import javax.swing.*;

/**
 * Created by L.J. Keijzer on 3-4-2016.
 */
public final class Constants {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static final ImageIcon BOMB_IMAGE = new ImageIcon(Constants.class.getResource("/resources/img/bomb.png"));
    static final ImageIcon BOMB_CLICKED_IMAGE = new ImageIcon(Constants.class.getResource
            ("/resources/img/bomb_clicked.png"));
    static final ImageIcon FLAG_IMAGE = new ImageIcon(Constants.class.getResource("/resources/img/flag.png"));
    static final ImageIcon NOT_BOMB_IMAGE = new ImageIcon(Constants.class.getResource
            ("/resources/img/no_bomb.png"));
    static final ImageIcon NOT_PRESSED_IMAGE = new ImageIcon(Constants.class.getResource
            ("/resources/img/not_pressed.png"));
    static final ImageIcon IMAGE_0 = new ImageIcon(Constants.class.getResource("/resources/img/0" +
            ".png"));
    static final ImageIcon IMAGE_1 = new ImageIcon(Constants.class.getResource("/resources/img/1.png"));
    static final ImageIcon IMAGE_2 = new ImageIcon(Constants.class.getResource("/resources/img/2.png"));
    static final ImageIcon IMAGE_3 = new ImageIcon(Constants.class.getResource("/resources/img/3.png"));
    static final ImageIcon IMAGE_4 = new ImageIcon(Constants.class.getResource("/resources/img/4.png"));
    static final ImageIcon IMAGE_5 = new ImageIcon(Constants.class.getResource("/resources/img/5.png"));
    static final ImageIcon IMAGE_6 = new ImageIcon(Constants.class.getResource("/resources/img/6.png"));
    static final ImageIcon IMAGE_7 = new ImageIcon(Constants.class.getResource("/resources/img/7.png"));
    static final ImageIcon IMAGE_8 = new ImageIcon(Constants.class.getResource("/resources/img/8.png"));

    public static final double SCREEN_WIDTH = screenSize.getWidth();
    public static final double SCREEN_HEIGHT = screenSize.getHeight();

    static final int WIN = 1;
    static final int DEATH_ON_BOMB = 2;
    static final int LOST_TIME_UP = 3;
    static final int START_CELL_SIZE = 20;
    public static final int NO_TIME_LIMIT = -1;
    public static final int START_CELLS_WIDTH = 10;
    public static final int START_CELLS_HEIGHT = 10;
    public static final int START_AMOUNT_BOMBS = 10;

    public static final String TIME_LIMIT_TITLE = "Time limit";
    public static final String CELL_WIDTH_TITLE = "Amount cells width";
    public static final String CELL_HEIGHT_TITLE = "Amount cells height";

}
