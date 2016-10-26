package mainGame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

/**
 * Created by L.J. Keijzer on 3-4-2016.
 */
public class MineSweeper{
    private SharedPreferences mSharedPreferences;

    private JPanel mPlayingField;
    private JPanel mJField;
    private JFrame mJFrame;
    private JTextField mTimeTextField;
    private JTextField mTimeCountdownTextField;
    private GridBagConstraints c;
    private Timer timer;
    private MineButton[][] mMineField;

    boolean won = false;    // To prevent calling endGame(Constants.WIN); twice in a row in some
    // circumstances

    public MineSweeper(final int cellsWidthAmount, final int cellsHeightAmount,
                       final int amountOfBombs, int timeLimit) {

        mSharedPreferences = new SharedPreferences();

        mSharedPreferences.cellsWidthAmount = cellsWidthAmount;
        mSharedPreferences.cellsHeightAmount = cellsHeightAmount;
        mSharedPreferences.amountBombs = amountOfBombs;
        mSharedPreferences.timeLeft = timeLimit;

        //setUpMouseListener(); // Cleaner code this way, main logic is in the mouse listener

        c = new GridBagConstraints();
        mMineField = new MineButton[mSharedPreferences.cellsWidthAmount]
                [mSharedPreferences.cellsHeightAmount];
        mJFrame = new JFrame("Mine Sweeper");
        mJField = new JPanel(new GridBagLayout());
        mPlayingField = new JPanel(new GridBagLayout());
        mTimeTextField = new JTextField(Integer.toString(mSharedPreferences.gameTimeCounter));
        mTimeCountdownTextField = new JTextField(Integer.toString(mSharedPreferences.timeLeft));
        if (mSharedPreferences.timeLeft == Constants.NO_TIME_LIMIT) {
            mTimeCountdownTextField.setVisible(false);  // No maximal time
        } else {
            mTimeCountdownTextField.setVisible(true);
        }
        timer = new Timer();

        mTimeTextField.setPreferredSize(new Dimension(40, 20));
        mTimeTextField.setEditable(false);
        mTimeTextField.setHorizontalAlignment(JTextField.CENTER);
        mTimeCountdownTextField.setPreferredSize(new Dimension(40, 20));
        mTimeCountdownTextField.setEditable(false);
        mTimeCountdownTextField.setHorizontalAlignment(JTextField.CENTER);

        mJFrame.setLayout(new GridBagLayout());
        mJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // For now not resizable
        mJFrame.setResizable(false);
//        mJFrame.setMinimumSize(new Dimension(mSharedPreferences.cellWidth * mSharedPreferences
//                .cellsWidthAmount, mSharedPreferences.cellHeight * mSharedPreferences.cellsHeightAmount));



        // Not used until resizable is enabled again
//        mJFrame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                // Keep ratio of the JFrame the same
////                mJFrame.setSize(new Dimension(Math.min(mJFrame.getWidth(), mJFrame.getHeight()),
////                        Math.min(mJFrame.getWidth(), mJFrame.getHeight())));
//
//
//
////                mainGame.mSharedPreferences.cellHeight = mPlayingField.getHeight() / 10;
////                mainGame.mSharedPreferences.cellWidth = mPlayingField.getWidth() / 20;
//
//                // Both width, to keep cells square and playing field is the same width as the
//                // JFrame
//
//                mPlayingField.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
//                mainGame.mSharedPreferences.cellWidth = e.getComponent().getWidth() / mainGame.mSharedPreferences.cellsWidthAmount;
//                mainGame.mSharedPreferences.cellHeight = mainGame.mSharedPreferences.cellWidth;
//                System.out.println(mainGame.mSharedPreferences.cellHeight);
//                System.out.println(mainGame.mSharedPreferences.cellWidth);
//                changeButtonSize();
//            }
//        });
//
//
//        mJFrame.setSize(mainGame.mSharedPreferences.cellsWidthAmount * 40, mainGame.mSharedPreferences.cellsHeightAmount * 40);
//        mPlayingField.setSize(mainGame.mSharedPreferences.cellsWidthAmount * 40, mainGame.mSharedPreferences.cellsHeightAmount * 40);

        setUpPlayingField();    //Make the playing field

        c.gridx = 0;
        c.gridy = 0;
        mJField.add(mTimeTextField, c);

        c.gridx = 1;
        c.gridy = 0;
        mJField.add(mTimeCountdownTextField, c);


        c.gridx = 0;
        c.gridy = 0;
        mJFrame.add(mJField, c);

//        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 1;
        mJFrame.add(mPlayingField, c);
        mJFrame.pack();
        // Set the location of the frame in the center of the screen
        mJFrame.setLocation(
                (int) (Constants.SCREEN_WIDTH / 2 - mJFrame.getWidth() / 2),
                (int) (Constants.SCREEN_HEIGHT / 2 - mJFrame.getHeight() / 2));


//        mJFrame.addComponentListener(new ComponentListener() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                int minSize = Math.min(mJFrame.getWidth(), mJFrame.getHeight());
//                mSharedPreferences.cellWidth = minSize;
//                mSharedPreferences.cellHeight = minSize;
//
//                changeButtonSize();
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//
//            }
//        });


        mJFrame.setVisible(true);



    }


    private void setUpPlayingField() {
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                mMineField[i][j] = new MineButton();
                mMineField[i][j].setIcon(mSharedPreferences.notPressedImageIcon);
                mMineField[i][j].addMouseListener(setUpMouseListener(i, j));
                c.gridx = i;
                c.gridy = j;
                mPlayingField.add(mMineField[i][j], c);
            }
        }
    }

    private void setNeighborBombs() {
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                int tmp = 0;
                for (int x = -1; x < 2; x++) {  // Relative X coordinate
                    for (int y = -1; y < 2; y++) {  // Relative Y coordinate
                        if (!(x == 0 && y == 0)) {
                            if (i + x >= 0 && j + y >= 0
                                    && i + x < mSharedPreferences.cellsWidthAmount
                                    && j + y < mSharedPreferences.cellsHeightAmount) {  //Check
                                // if the cell is a legit cell
                                if (mMineField[i + x][j + y].isBomb()) {
                                    tmp++;
                                }
                            }
                        }
                    }
                }
                mMineField[i][j].setNeighborBombs(tmp);
            }
        }
    }

    private CustomMouseListener setUpMouseListener(final int X, final int Y) {
        return new CustomMouseListener(X, Y) {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                MineButton tempMineButton = (MineButton) e.getSource();
                if (mSharedPreferences.isFirstClick) {
                    setUpBombs(getCoordinateX(), getCoordinateY());

                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (mSharedPreferences.timeLeft > 0) {
                                mSharedPreferences.timeLeft--;
                                mTimeCountdownTextField.setText(Integer.toString(mSharedPreferences.timeLeft));
                                if (mSharedPreferences.timeLeft <= 0) {
                                    endGame(Constants.LOST_TIME_UP);
                                    timer.cancel();
                                }
                            }
                            mSharedPreferences.gameTimeCounter++;
                            mTimeTextField.setText(Integer.toString(mSharedPreferences.gameTimeCounter));
                        }
                    }, 0, 1000);

                    mSharedPreferences.isFirstClick = false;
                }

                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!tempMineButton.isFlagged()) {
                        tempMineButton.setIsOpened(true);
                        if (tempMineButton.isBomb()) {
                            tempMineButton.setIcon(mSharedPreferences.bombClickedImageIcon);
                            tempMineButton.setIsExploded(true);
                            endGame(Constants.DEATH_ON_BOMB);
                        } else {
                            if (tempMineButton.isDone()) {
                                openNeighbors(getCoordinateX(), getCoordinateY(), true);
                            } else {
                                setNoBombButtonIcon(tempMineButton);    //Set the correct icon

                                tempMineButton.setIsDone(true);
                                openNeighbors(getCoordinateX(), getCoordinateY(), false);
                                if (testIfDone() && !won) {
                                    endGame(Constants.WIN);
                                }
                            }
                        }
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (!tempMineButton.isOpened()) {
                        if (tempMineButton.isFlagged()) {
                            tempMineButton.setIcon(mSharedPreferences.notPressedImageIcon);
                            tempMineButton.setIsFlagged(false);
                            tempMineButton.setIsDone(false);
                            mSharedPreferences.discoveredBombs--;
                        } else {
                            mSharedPreferences.discoveredBombs++;
                            if (tempMineButton.isBomb()) {
                                tempMineButton.setIcon(mSharedPreferences.flagImageIcon);
                                tempMineButton.setIsFlagged(true);
                                tempMineButton.setIsDone(true);
                                if (testIfDone()) {
                                    endGame(Constants.WIN);
                                }
                                // This so user can no longer press the button
                            } else {
                                tempMineButton.setIcon(mSharedPreferences.flagImageIcon);
                                tempMineButton.setIsFlagged(true);
                                tempMineButton.setIsDone(false);
                            }
                        }
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    private void setUpBombs(int coordinateX, int coordinateY) {
        if (mSharedPreferences.amountBombs > (mSharedPreferences.cellsHeightAmount *
                mSharedPreferences.cellsWidthAmount) - 9) {
            throw new IllegalArgumentException("The amount of bombs >= the amount of cells - 9");
        }
        boolean[][] tempList = new boolean[mSharedPreferences.cellsWidthAmount][mSharedPreferences
                .cellsHeightAmount];

        // Remove all bombs, clean start, just in case
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                mMineField[i][j].setIsBomb(false);
            }
        }

        // Fill tempList with all true
        for (int i = 0; i < tempList.length; i++) {
            for (int j = 0; j < tempList[0].length; j++) {
                tempList[i][j] = true;
            }
        }

        // Set starting coordinate and surrounding coordinates of tempList to false
        for (int x = -1; x < 2; x++) {  // Relative X coordinate
            for (int y = -1; y < 2; y++) {  // Relative Y coordinate
                if (coordinateX + x >= 0 && coordinateY + y >= 0 && coordinateX +
                        x < mSharedPreferences.cellsWidthAmount && coordinateY + y
                        < mSharedPreferences.cellsHeightAmount) {  //Check if the cell is a
                    tempList[coordinateX + x][coordinateY + y] = false;
                }

            }
        }

        Random randomX = new Random();
        Random randomY = new Random();
        for (int i = 0; i < mSharedPreferences.amountBombs; i++) {
            final int X = randomX.nextInt(mSharedPreferences.cellsWidthAmount);
            final int Y = randomY.nextInt(mSharedPreferences.cellsHeightAmount);
            if (mMineField[X][Y].isBomb()) {    // This cell is already a bomb
                i--;    // didn't place a bomb, go an extra round
            } else if (tempList[X][Y]) {
                mMineField[X][Y].setIsBomb(true);
            } else {    // Not allowed to place a bomb here
                i--;    // didn't place a bomb, go an extra round
            }
        }

        setNeighborBombs();


    }

    // Not used while JFrame.setResizable(false);
    private void changeButtonSize() {
        Dimension tmpDim = new Dimension(mSharedPreferences.cellWidth, mSharedPreferences.cellHeight);
        mJField.setPreferredSize(new Dimension(mSharedPreferences.cellWidth * mSharedPreferences
                .cellsWidthAmount, mSharedPreferences.cellHeight * mSharedPreferences.cellsHeightAmount));
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
//                mMineField[i][j].setPreferredSize(tmpDim);
                mMineField[i][j].setPreferredSize(tmpDim);
            }
        }
    }

    private boolean testIfDone() {
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                if (!mMineField[i][j].isDone() && !mMineField[i][j].isBomb()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void endGame(int reason) {
        // TODO: make better way of preventing multiple end screens
        if (mSharedPreferences.isFinished) {
            return;
        }
        mSharedPreferences.isFinished = true;

        JFrame endGameFrame = new JFrame();
        JPanel endGamePanel = new JPanel(new GridBagLayout());
        JLabel endGameLabel = new JLabel();
        GridBagConstraints c = new GridBagConstraints();

        endGameFrame.setLayout(new GridBagLayout());
        endGameFrame.setSize(300, 150);
        endGameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        endGameFrame.setResizable(false);

        c.gridx = 0;
        c.gridy = 0;
        endGamePanel.add(endGameLabel, c);
        endGameFrame.add(endGamePanel, c);

        endGameFrame.setLocation(
                (int) (Constants.SCREEN_WIDTH / 2 - endGameFrame.getWidth() / 2),
                (int) (Constants.SCREEN_HEIGHT / 2 - endGameFrame.getHeight() / 2));

        removeAllListeners();
        timer.cancel();
        switch (reason) {
            case Constants.DEATH_ON_BOMB:
                openAllBombs();
                endGameLabel.setText("You lost, you clicked on a bomb");
                endGameFrame.setVisible(true);
                break;
            case Constants.WIN:
                won = true;
                endGameLabel.setText("You won!");
                endGameFrame.setVisible(true);
                break;
            case Constants.LOST_TIME_UP:
                openAllBombs();
                endGameLabel.setText("You lost, the time is up");
                endGameFrame.setVisible(true);
            default:
                openAllBombs();
                endGameLabel.setText("Game ended");
                endGameFrame.setVisible(true);
        }

    }

    private void removeAllListeners() {
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                MouseListener[] tempML = mMineField[i][j].getMouseListeners();
                for (MouseListener mouseListener : tempML) {
                    mMineField[i][j].removeMouseListener(mouseListener);
                }

            }
        }
    }

    private void openAllBombs() {
        for (int i = 0; i < mSharedPreferences.cellsWidthAmount; i++) {
            for (int j = 0; j < mSharedPreferences.cellsHeightAmount; j++) {
                if (mMineField[i][j].isBomb() && !mMineField[i][j].isExploded()) {
                    openMine(mMineField[i][j]);
                }
            }
        }
    }

    private void openNeighbors(final int coordinateX, final int coordinateY, final boolean
            openIfHasNieghborBombs) {

        int bombsFound = 0;

        if (openIfHasNieghborBombs) {
//        MouseListener[] tempMLs = mineButton.getMouseListeners();
//        mainGame.CustomMouseListener tempML = (mainGame.CustomMouseListener) tempMLs[tempMLs.length - 1];
//        final int coordinateX = coordinateX;
//        final int coordinateY = coordinateY;

            // Look at all neighbors and see if that neighbor is flagged, if it is flagged, increase
            // bombsFound
            for (int x = -1; x < 2; x++) {  // Relative X coordinate
                for (int y = -1; y < 2; y++) {  // Relative Y coordinate
                    if (!(x == 0 && y == 0)) {
                        if (coordinateX + x >= 0 && coordinateY + y >= 0
                                && coordinateX + x < mSharedPreferences.cellsWidthAmount
                                && coordinateY + y < mSharedPreferences.cellsHeightAmount) {
                            if (mMineField[coordinateX + x][coordinateY + y].isFlagged()) {
                                bombsFound++;
                            }
                        }
                    }
                }
            }
        }

        // >= just in case the user flagged more cells than there are actual bombs
        if ((bombsFound >= mMineField[coordinateX][coordinateY].neighborBombs() &&
                openIfHasNieghborBombs)
                || (mMineField[coordinateX][coordinateY].neighborBombs() == 0)) {
            for (int x = -1; x < 2; x++) {  // Relative X coordinate
                for (int y = -1; y < 2; y++) {  // Relative Y coordinate
                    if (!(x == 0 && y == 0)) {
                        if (coordinateX + x >= 0 && coordinateY + y >= 0
                                && coordinateX + x < mSharedPreferences.cellsWidthAmount
                                && coordinateY + y < mSharedPreferences.cellsHeightAmount) {

                            // Not opened and not flagged, aka: the user has not pressed it yet
                            if (!mMineField[coordinateX + x][coordinateY + y].isOpened()
                                    && !mMineField[coordinateX + x][coordinateY + y].isFlagged()) {
                                openCell(mMineField[coordinateX + x][coordinateY + y]);
                                if (mSharedPreferences.isFinished) {
                                    return;
                                }
                                openNeighbors(coordinateX + x, coordinateY + y, false);
                            }
                        }
                    }
                }
            }
        }
    }

    // To open a cell that is a bomb, meant for the opening of all bombs if the player looses
    private void openMine(MineButton mineButton) {
        if (mineButton.isBomb()) {
            mineButton.setIcon(mSharedPreferences.bombImageIcon);
        }
    }

    private void openCell(MineButton mineButton) {
        if (!mineButton.isDone() && !mineButton.isFlagged()) {
            if (mineButton.isBomb()) {
                mineButton.setIcon(mSharedPreferences.bombImageIcon);
                endGame(Constants.DEATH_ON_BOMB);
            } else {
                setNoBombButtonIcon(mineButton);    // Set correct icon
                mineButton.setIsDone(true);
                mineButton.setIsOpened(true);

                // Get the mouseListener
//                MouseListener[] tempMLs = mineButton.getMouseListeners();
//                if (tempMLs.length > 0) {
//                    CustomMouseListener tempML = (CustomMouseListener) tempMLs[tempMLs.length - 1];
//
//                    // Remove the mouseListener
//                    // This so user can no longer press the button
////                    mineButton.removeMouseListener(tempML);
//                }

                if (testIfDone()) {
                    endGame(Constants.WIN);
                }
            }
        }
    }

    private void setNoBombButtonIcon(MineButton mineButton) {
        switch (mineButton.neighborBombs()) {
            case 0:
                mineButton.setIcon(mSharedPreferences.notBombImageIcon);
                break;
            case 1:
                mineButton.setIcon(mSharedPreferences.imageIcon1);
                break;
            case 2:
                mineButton.setIcon(mSharedPreferences.imageIcon2);
                break;
            case 3:
                mineButton.setIcon(mSharedPreferences.imageIcon3);
                break;
            case 4:
                mineButton.setIcon(mSharedPreferences.imageIcon4);
                break;
            case 5:
                mineButton.setIcon(mSharedPreferences.imageIcon5);
                break;
            case 6:
                mineButton.setIcon(mSharedPreferences.imageIcon6);
                break;
            case 7:
                mineButton.setIcon(mSharedPreferences.imageIcon7);
                break;
            case 8:
                mineButton.setIcon(mSharedPreferences.imageIcon8);
                break;
            default:
                mineButton.setIcon(mSharedPreferences.notBombImageIcon);
                break;
        }
    }
}
