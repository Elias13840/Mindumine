public class PlayerControl {
     private PlayerCharacter player;
     public PlayerControl(PlayerCharacter player) {
         this.player = player;
     }
     public void moveLeft() {
         player.positionX -= player.movementSpeed;
         player.isMoving = true;
     }
     public void moveRight() {
         player.positionX += player.movementSpeed;
         player.isMoving = true;
     }
     public void moveUp() {
         player.positionY -= player.movementSpeed;
         player.isMoving = true;
     }
     public void moveDown() {
         player.positionY += player.movementSpeed;
         player.isMoving = true;
     }
     public void stopMoving() {
         player.isMoving = false;
     }
     public void equipTool(int slot) {
         if (player.inventory[slot] instanceof Tool) {
             player.equippedTool = (Tool) player.inventory[slot];
         }
     }
     public void takeDamage(int damage) {
         int finalDamage = damage - player.armor;
         if (finalDamage < 0) finalDamage = 0;
         player.currentHealth -= finalDamage;
         if (player.currentHealth <= 0) player.currentHealth = 0;
     }
     public void heal(int amount) {
         player.currentHealth += amount;
         if (player.currentHealth > player.maxHealth) player.currentHealth = player.maxHealth;
     }
     }
