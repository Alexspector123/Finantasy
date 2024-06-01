package Object;

import Entities.Entity;
import Main.GamePanel;

public class OBJ_Weapon extends Entity {
    public OBJ_Weapon(GamePanel gamePanel) {
        super(gamePanel);
        type = type_sword;
        name = "Normal Sword";
        down1 = setup("/Object/sword_normal");
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
