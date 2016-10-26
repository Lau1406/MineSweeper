package mainGame;

import java.awt.*;

import javax.swing.*;

/**
 * Created by L.J. Keijzer on 3-4-2016.
 */
public class SharedPreferences {


    int cellsWidthAmount = Constants.START_CELLS_WIDTH;  //Amount of cells
    int cellsHeightAmount = Constants.START_CELLS_HEIGHT;
    int cellWidth = Constants.START_CELL_SIZE;   // Width of a cell
    int cellHeight = Constants.START_CELL_SIZE;  // Height of a cell
    int gameTimeCounter = 0;
    int amountBombs = Constants.START_AMOUNT_BOMBS;  // The amount of bombs in the game
    int discoveredBombs = 0;
    int timeLeft = Constants.NO_TIME_LIMIT; //timeLeft left, -1 if no time limit

    boolean isFirstClick = true;  //To prevent that the first click is on a bomb
    boolean isFinished = false;


    ImageIcon bombImageIcon = new ImageIcon(Constants.BOMB_IMAGE.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon bombClickedImageIcon = new ImageIcon(Constants.BOMB_CLICKED_IMAGE.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon flagImageIcon = new ImageIcon(Constants.FLAG_IMAGE.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon notBombImageIcon = new ImageIcon(Constants.NOT_BOMB_IMAGE.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon notPressedImageIcon = new ImageIcon(Constants.NOT_PRESSED_IMAGE
            .getImage().getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));

    ImageIcon imageIcon0 = new ImageIcon(Constants.IMAGE_0.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon1 = new ImageIcon(Constants.IMAGE_1.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon2 = new ImageIcon(Constants.IMAGE_2.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon3 = new ImageIcon(Constants.IMAGE_3.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon4 = new ImageIcon(Constants.IMAGE_4.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon5 = new ImageIcon(Constants.IMAGE_5.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon6 = new ImageIcon(Constants.IMAGE_6.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon7 = new ImageIcon(Constants.IMAGE_7.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
    ImageIcon imageIcon8 = new ImageIcon(Constants.IMAGE_8.getImage()
            .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));


    public void resizeImageIcons() {
        bombImageIcon = new ImageIcon(Constants.BOMB_IMAGE.getImage().getScaledInstance
                (cellWidth, cellHeight, Image.SCALE_SMOOTH));
        ImageIcon bombClickedImageIcon = new ImageIcon(Constants.BOMB_CLICKED_IMAGE.getImage()
                .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));
        flagImageIcon = new ImageIcon(Constants.FLAG_IMAGE.getImage().getScaledInstance
                (cellWidth, cellHeight, Image.SCALE_SMOOTH));
        notBombImageIcon = new ImageIcon(Constants.NOT_BOMB_IMAGE.getImage().getScaledInstance
                (cellWidth, cellHeight, Image.SCALE_SMOOTH));
        notPressedImageIcon = new ImageIcon(Constants.NOT_PRESSED_IMAGE.getImage()
                .getScaledInstance(cellWidth, cellHeight, Image.SCALE_SMOOTH));

        imageIcon0 = new ImageIcon(Constants.IMAGE_0.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon1 = new ImageIcon(Constants.IMAGE_1.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon2 = new ImageIcon(Constants.IMAGE_2.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon3 = new ImageIcon(Constants.IMAGE_3.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon4 = new ImageIcon(Constants.IMAGE_4.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon5 = new ImageIcon(Constants.IMAGE_5.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon6 = new ImageIcon(Constants.IMAGE_6.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon7 = new ImageIcon(Constants.IMAGE_7.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
        imageIcon8 = new ImageIcon(Constants.IMAGE_8.getImage().getScaledInstance(cellWidth,
                cellHeight, Image.SCALE_SMOOTH));
    }


}
