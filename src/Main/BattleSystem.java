package Main;

import java.util.ArrayList;

import Entities.Entity;
import Monster.MON_BloodySlime;
import Monster.MON_Boss;
import Monster.MON_GateKeeper;
import Monster.MON_GreenDragon;
import Monster.MON_Spider;
import Monster.MonsterFactory;
import Objects.*;

public class BattleSystem {
    
    GamePanel gamePanel;

    public ArrayList<Entity> listofMonster = new ArrayList<>();
    boolean checker = false;
    public int indexBattle = 0;
    public int orderTurn = 0; // 0 for player
    public int interactNum = 0;  // 0 for stuff 1, stuff 2, stuff 3
    public int interactType = 0; // 0 for selection, 1 for choosing equipment, 2 for choosing enemy
    public int selectAction = 0;
    public int choosingEquipAction = 0;
    public int choosingEnemyAction = 0;
    public int numberOfInteractNum = interactNum;

    public BattleSystem(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    // SET MONSTER
    public void resetMonster(){
        if(this.checker == false){
                    listofMonster = new ArrayList<>();
                    addMonster(indexBattle);
                    checker = true;
        }
    }

        // Add monster due to the place (indexBattle)
        public void addMonster(int index){
            //Battle 1 in map 0
            if(index == 1){
                listofMonster.add(MonsterFactory.createMonster("Pumpkin", gamePanel));
                listofMonster.add(MonsterFactory.createMonster("Pumpkin", gamePanel));
            }
            //Battle 2
            if(index == 2){
                listofMonster.add(MonsterFactory.createMonster("Reaper", gamePanel));
                listofMonster.add(MonsterFactory.createMonster("Pumpkin", gamePanel));
                listofMonster.add(MonsterFactory.createMonster("Reaper", gamePanel));
            }
            //Battle 3
            if(index == 3){
                listofMonster.add(MonsterFactory.createMonster("GhostRider", gamePanel));
                listofMonster.add(MonsterFactory.createMonster("Pumpkin", gamePanel));
            }
            //Battle 2 in map 1
            if(index == 4) {
                listofMonster.add(MonsterFactory.createMonster("Bloody Slime", gamePanel));
    
            }
            if(index == 5) {
                listofMonster.add(MonsterFactory.createMonster("Spider", gamePanel));
    
            }
            if(index == 6) {
                listofMonster.add( MonsterFactory.createMonster("Gate Keeper", gamePanel));
    
            }
            //Battle 3 in map 1 for boss
            if(index == 10) {
                listofMonster.add(MonsterFactory.createMonster("Boss", gamePanel));
            }
            if(index == 9) {
                listofMonster.add(MonsterFactory.createMonster("Green Dragon", gamePanel));
            }
            if(index == 7) {
                listofMonster.add(MonsterFactory.createMonster("Robot", gamePanel));
            }
            if(index == 8) {
                listofMonster.add(MonsterFactory.createMonster("Robot", gamePanel));
            }
        }

    // RESET THE BATTLE COMMAND NUM
    public void resetNum(){
        interactType = 0;
        interactNum = 0;
        selectAction = 0;
        choosingEquipAction = 0;
        choosingEnemyAction = 0;
    }

    
    // Checking if the battle end or not
    public boolean checkBattleEnd(){
        if(gamePanel.player.dying == true){
            return true;
        }
        else{
            for(int i = 0; i<listofMonster.size();i++){
                if(listofMonster.get(i).dying == false){
                    return false;
                }
            }
            return true;
        }
    }

    // Count the number of interaction
    public int numberOfInteract(){
        int t = 0;
        if(interactType == 1){
            
        for(int i = 0; i<gamePanel.player.inventory.size(); i++){
            if((selectAction == 0 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_sword || gamePanel.player.inventory.get(i).type == gamePanel.player.type_dagger))
                  || (selectAction == 1 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_shield))
                  || (selectAction == 2 && (gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_player || gamePanel.player.inventory.get(i).type == gamePanel.player.type_consumable_enemy))){
                t++;
                }
            }
        }
        else if (interactType == 2){
            if(selectAction == 2 && gamePanel.player.inventory.get(choosingEquipAction).type == gamePanel.player.type_consumable_player){
                t = 1;
            }
            else{
                for(int i = 0; i<listofMonster.size(); i++){
                    if(listofMonster.get(i).dying == false)
                    t++;
                }
            }
        }
        return t;
    }

    
    // Monster Turn
    public void monsterTurn(){
        if((orderTurn - 1) >= listofMonster.size()) {
            orderTurn = 0;
            interactType = 0;
            selectAction = 0;
            choosingEquipAction = 0;
            choosingEnemyAction = 0;
        }
        else{
            if(listofMonster.get(orderTurn - 1).preState == listofMonster.get(orderTurn - 1).stunState){
                listofMonster.get(orderTurn - 1).preState = listofMonster.get(orderTurn - 1).normalState;
                orderTurn++;
            }
            else{
                
                if(listofMonster.get(orderTurn-1).dying == false){
                    if(listofMonster.get(orderTurn - 1).preState == listofMonster.get(orderTurn - 1).bleedState){
                        listofMonster.get(orderTurn - 1).life--;
                        listofMonster.get(orderTurn - 1).preState = listofMonster.get(orderTurn - 1).normalState;
                    }
                    listofMonster.get(orderTurn - 1).damage(gamePanel.player);
                }
                else{
                orderTurn++;
                }
            }
            if(gamePanel.player.life <= 0) gamePanel.player.dying = true;
        }
    }
}
