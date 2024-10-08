package Objects;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_HealPot extends Entity {

    GamePanel gamePanel;
    public int value = 5;

    public OBJ_HealPot(GamePanel gamePanel){

        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Blood Pot";
        type = type_consumable_player;
        itemsImage = setupItemImages("Objects/Healing");
        description = "[" + name + "]\nHeal " + value + "HP to your life.";
        price = 15;
        stackable = true;
    }
    public void use(Entity entity){
        entity.life += value;
        if(entity.life > entity.maxLife){
            entity.life = entity.maxLife;
        }
        if(gamePanel.gameState == gamePanel.battleState){
            gamePanel.ui.addMessage("Healing");
        }
        else{
            gamePanel.ui.addMessage("Your life is restored by "+value+".");
        }
        entity.state = entity.healingState;
    }
}