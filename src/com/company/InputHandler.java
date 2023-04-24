package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InputHandler extends KeyAdapter {

    private final Map<Integer, InputAction> inputActionMap;

    public InputHandler(Player player)
    {
        inputActionMap = new HashMap<>();

        var vg = new Vector2.Generator();
        addActionToMap(KeyEvent.VK_W, new InputAction(() -> player.setMovingDirection(vg.up()), 0));
        addActionToMap(KeyEvent.VK_A, new InputAction(() -> player.setMovingDirection(vg.left()), 0));
        addActionToMap(KeyEvent.VK_S, new InputAction(() -> player.setMovingDirection(vg.down()), 0));
        addActionToMap(KeyEvent.VK_D, new InputAction(() -> player.setMovingDirection(vg.right()), 0));
        addActionToMap(KeyEvent.VK_R, new InputAction(() -> GameLogic.getInstance().changeCurrentScreen(new GameScreen()), 1));
    }

    public void addActionToMap(int code, InputAction action){
        inputActionMap.put(code, action);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(!inputActionMap.containsKey(code)){
            return;
        }
        inputActionMap.get(code).execute();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(!inputActionMap.containsKey(code)){
            return;
        }
        inputActionMap.get(code).release();
    }

    public class InputAction{
        private boolean isCurrentlyHeld;
        private final Runnable action;
        private final int gameStateRequirement;

        public InputAction(Runnable action, int gameStateRequirement){
            this.action = action;
            this.gameStateRequirement = gameStateRequirement;
        }

        public void release(){
            isCurrentlyHeld = false;
        }

        public void execute() {
            if(isCurrentlyHeld){
                return;
            }

            var g = GameLogic.getInstance().getCurrentScreen().getMyID();
            if(g != gameStateRequirement && g != -1){
                return;
            }
            isCurrentlyHeld = true;
            action.run();
        }
    }
}
