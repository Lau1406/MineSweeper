package bootScreen;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mainGame.Constants;
import mainGame.MineSweeper;

/**
 * Created by L.J. Keijzer on 6-4-2016.
 */
public class BootScreen {
    private JPanel mJPanelMain;
    private JPanel mJPanelPresets;
    private JFrame mJFrameMain;
    private JSpinner mJSpinnerCellsWidth;
    private JSpinner mJSpinnerCellsHeight;
    private JSpinner mJSpinnerTimeLimit;
    private JSpinner mJSpinnerAmountOfBombs;
    private JCheckBox mJCheckBoxTimeLimit;
    private JLabel mJLabelCellWidthTitle;
    private JLabel mJLabelCellHeightTitle;
    private JLabel mJLabelAmountOfBombs;
    private JButton mJButtonStart;
    private JButton mJButtonBeginner;
    private JButton mJButtonIntermediate;
    private JButton mJButtonExpert;
    private GridBagConstraints c;

//    private GameRunnable mGameRunnable;

    private int mCellsWidthAmount;
    private int mCellsHeightAmount;
    private int mAmountOfBombs;
    private int mTimeLimit;
//    private Dimension mTextfieldDimension;


    public BootScreen() {
        mCellsWidthAmount = Constants.START_CELLS_WIDTH;
        mCellsHeightAmount = Constants.START_CELLS_HEIGHT;
        mAmountOfBombs = Constants.START_AMOUNT_BOMBS;
        mTimeLimit = Constants.NO_TIME_LIMIT;


        c = new GridBagConstraints();
        mJFrameMain = new JFrame("Mine Sweeper");
        mJPanelMain = new JPanel(new GridBagLayout());
        mJPanelPresets = new JPanel(new GridLayout(1, 3));
        mJSpinnerCellsWidth = new JSpinner(new SpinnerNumberModel(Constants.START_CELLS_WIDTH, 4,
                50, 1));
        mJSpinnerCellsHeight = new JSpinner(new SpinnerNumberModel(Constants.START_CELLS_HEIGHT, 4,
                50, 1));
        mJSpinnerTimeLimit = new JSpinner(new SpinnerNumberModel(90, 1, 999, 1));
        mJCheckBoxTimeLimit = new JCheckBox(Constants.TIME_LIMIT_TITLE);
        mJLabelCellWidthTitle = new JLabel(Constants.CELL_WIDTH_TITLE);
        mJLabelCellHeightTitle = new JLabel(Constants.CELL_HEIGHT_TITLE);
//        mTextfieldDimension = new Dimension(150, 20);
        mJButtonStart = new JButton("Start");
        mJButtonBeginner = new JButton("Beginner");
        mJButtonIntermediate = new JButton("Intermediate");
        mJButtonExpert = new JButton("Expert");
        mJLabelAmountOfBombs = new JLabel("Amount of bombs");
        mJSpinnerAmountOfBombs = new JSpinner(new SpinnerNumberModel(Constants.START_AMOUNT_BOMBS, 1,
                (mCellsWidthAmount * mCellsHeightAmount) - 9, 1));


        mJFrameMain.setLayout(new GridBagLayout());
        mJFrameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mJSpinnerTimeLimit.setVisible(false);


        // Spinner changeListeners
        mJSpinnerCellsWidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mCellsWidthAmount = (Integer) mJSpinnerCellsWidth.getValue();
                updateBombsSpinnerRange();
            }
        });

        mJSpinnerCellsHeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mCellsHeightAmount = (Integer) mJSpinnerCellsHeight.getValue();
                updateBombsSpinnerRange();
            }
        });

        mJSpinnerTimeLimit.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mTimeLimit = (Integer) mJSpinnerTimeLimit.getValue();
            }
        });

        mJSpinnerAmountOfBombs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mAmountOfBombs = (Integer) mJSpinnerAmountOfBombs.getValue();
            }
        });

        mJCheckBoxTimeLimit.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mJCheckBoxTimeLimit.isSelected()) {
                    mJSpinnerTimeLimit.setVisible(true);
                    mJSpinnerTimeLimit.setEnabled(true);
                    mTimeLimit = (Integer) mJSpinnerTimeLimit.getValue();
                } else {
                    mJSpinnerTimeLimit.setVisible(false);
                    mJSpinnerTimeLimit.setEnabled(false);
                    mTimeLimit = Constants.NO_TIME_LIMIT;
                }
            }
        });


        // Button listeners
        mJButtonStart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MineSweeper(mCellsWidthAmount, mCellsHeightAmount, mAmountOfBombs, mTimeLimit);
            }
        });

        mJButtonBeginner.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mJSpinnerCellsWidth.setValue(8);
                mJSpinnerCellsHeight.setValue(8);
                mJSpinnerAmountOfBombs.setValue(10);
            }
        });

        mJButtonIntermediate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mJSpinnerCellsWidth.setValue(16);
                mJSpinnerCellsHeight.setValue(16);
                mJSpinnerAmountOfBombs.setValue(40);
            }
        });

        mJButtonExpert.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mJSpinnerCellsWidth.setValue(16);
                mJSpinnerCellsHeight.setValue(30);
                mJSpinnerAmountOfBombs.setValue(99);
            }
        });


        c.gridx = 0;
        c.gridy = 1;
        mJFrameMain.add(mJPanelMain, c);

        mJPanelPresets.add(mJButtonBeginner);
        mJPanelPresets.add(mJButtonIntermediate);
        mJPanelPresets.add(mJButtonExpert);

        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        mJPanelMain.add(mJPanelPresets, c);

        c.gridwidth = 1;
        c.ipadx = 3;
        c.ipady = 3;
        c.anchor = GridBagConstraints.LINE_START;


        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        mJPanelMain.add(mJSpinnerTimeLimit, c);

        c.gridx = 1;
        c.gridy = 2;
        mJPanelMain.add(mJSpinnerCellsHeight, c);

        c.gridx = 1;
        c.gridy = 3;
        mJPanelMain.add(mJSpinnerCellsWidth, c);

        c.gridx = 1;
        c.gridy = 4;
        mJPanelMain.add(mJSpinnerAmountOfBombs, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;

        mJPanelMain.add(mJCheckBoxTimeLimit, c);

        c.gridx = 0;
        c.gridy = 2;
        mJPanelMain.add(mJLabelCellHeightTitle, c);

        c.gridx = 0;
        c.gridy = 3;
        mJPanelMain.add(mJLabelCellWidthTitle, c);

        c.gridx = 0;
        c.gridy = 4;
        mJPanelMain.add(mJLabelAmountOfBombs, c);


        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        mJPanelMain.add(mJButtonStart, c);


        mJFrameMain.setSize(400, 250);
        mJFrameMain.setLocation(
                (int) (Constants.SCREEN_WIDTH / 2 - mJFrameMain.getWidth() / 2),
                (int) (Constants.SCREEN_HEIGHT / 2 - mJFrameMain.getHeight() / 2));

        mJFrameMain.setVisible(true);


    }

    private void updateBombsSpinnerRange(){
        int maxBombs = (mCellsHeightAmount * mCellsWidthAmount) - 9;
        SpinnerNumberModel tempSpinnerModel = (SpinnerNumberModel) mJSpinnerAmountOfBombs
                .getModel();
        tempSpinnerModel.setMaximum(maxBombs);
        mJSpinnerAmountOfBombs.setModel(tempSpinnerModel);
        if ((Integer) mJSpinnerAmountOfBombs.getValue() > maxBombs) {
            mJSpinnerAmountOfBombs.setValue(maxBombs);
        }
    }

    public static void main(String[] args) {
        new BootScreen();
    }
}
