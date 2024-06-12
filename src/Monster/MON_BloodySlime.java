package Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Main.GamePanel;

public class MON_BloodySlime extends Entity implements MonsterInt<Graphics2D, GamePanel>{

    GamePanel gamePanel;

    public MON_BloodySlime(GamePanel gamePanel) {
        super(gamePanel);

        this.gamePanel = gamePanel;

        type = type_monster;
        name = "Deadly Slime";
        direction = "down";
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 5;
        exp = 2;
        mana = 0;
        maxMana = 2;
        state = normalState;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        Defeat = false;
        getImage();
    }
    public void getImage(){
        
        up1 = setupMonsterImages("Monster/BloodSlime/BloodySlime",gamePanel.tileSize + 100,gamePanel.tileSize + 100);
        down1 = setupMonsterImages("Monster/BloodSlime/BloodySlime",gamePanel.tileSize + 100,gamePanel.tileSize + 100);
        left1 = setupMonsterImages("Monster/BloodSlime/BloodySlime",gamePanel.tileSize + 100,gamePanel.tileSize + 100);
        right1 = setupMonsterImages("Monster/BloodSlime/BloodySlime",gamePanel.tileSize + 100,gamePanel.tileSize + 100);
    }   
    @Override
    public void setAction(){}
    @Override
    public void damage(Entity entity){
        
        entity.state = entity.getDamageState;
            int damage = attack - entity.defense;
            if(damage <= 0){
                damage = 0;
                gamePanel.ui.orderTurn++;
            }
            else{
                entity.life -= damage;
                gamePanel.ui.addMessage(damage + " damage!");
            }
    }
    
    @Override
    public void draw(Graphics2D g2,GamePanel gamePanel){
        BufferedImage image = up1;
    
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
    
        if(Defeat == false) {
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
