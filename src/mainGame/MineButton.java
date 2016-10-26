package mainGame;

import java.awt.*;

import javax.swing.*;

/**
 * Created by L.J. Keijzer on 3-4-2016.
 */
public class MineButton extends JButton {
    private boolean mIsExploded = false;// True if this is the cell that exploded
    private boolean mIsBomb = false;    // True if contains a bomb
    private boolean mIsOpened = false;  // True if left clicked
    private boolean mIsFlagged = false; // True if right clicked
    private boolean mIsDone = false;    // True if mIsFlagged && mIsBomb && mIsOpened ||
    // !mIsFlagged && !mIsBomb && mIsOpened

    private int mNeighborBombs;

    public MineButton() {
        setBorderPainted(false);
        setPreferredSize(new Dimension(Constants.START_CELL_SIZE, Constants.START_CELL_SIZE));
    }


    // Setters
    public void setIsExploded(boolean exploded) {
        mIsExploded = exploded;
    }

    public void setNeighborBombs(int neighborBombs) {
        mNeighborBombs = neighborBombs;
    }

    public void setIsBomb(boolean isBomb) {
        mIsBomb = isBomb;
    }

    public void setIsOpened(boolean opened) {
        mIsOpened = opened;
    }

    public void setIsFlagged(boolean flagged) {
        mIsFlagged = flagged;
    }

    //TODO: proper validation check
    public void setIsDone(boolean done) {
        mIsDone = done;
    }


    // Getters
    public boolean isExploded() {
        return mIsExploded;
    }

    public boolean isOpened() {
        return mIsOpened;
    }

    public int neighborBombs() {
        return mNeighborBombs;
    }

    public boolean isBomb() {
        return mIsBomb;
    }

    public boolean isFlagged() {
        return mIsFlagged;
    }

    public boolean isDone() {
        return mIsDone;
    }
}
