package listeners;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
This class is used to control the keys that need to be pressed in the game screen
 */
public class MousesListener implements MousesCommand, MouseListener, MouseMotionListener {
    private boolean ifClicked, ifPressed, ifReleased, ifEntered, ifExit;
    private Point mousePoint;

    @Override
    public void mouseClicked(MouseEvent e) {
        ifClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ifPressed = true;
        ifReleased = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ifReleased = true;
        ifPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ifEntered = true;
        ifExit = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ifExit = true;
        ifEntered = false;
    }

    @Override
    public boolean hasClickedButton1() {
        return ifClicked;
    }

    @Override
    public boolean hasPressedButton1() {
        return ifPressed;
    }

    @Override
    public boolean hasReleasedButton1() {
        if (ifReleased) {
            ifReleased = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEnteredButton1() {
        return ifEntered;
    }

    @Override
    public boolean hasExitButton1() {
        return ifExit;
    }

    @Override
    public Point getMousePoint() {
        return mousePoint;
    }

    @Override
    public void resetMousePressed() {
        ifClicked = false;
        ifPressed = false;
        ifReleased = false;
        ifEntered = false;
        ifExit = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }
}