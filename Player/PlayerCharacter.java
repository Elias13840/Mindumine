public float positionX;
     public float positionY;
     public float width;
     public float height;
     public int textureResId;
     public float movementSpeed;
     public int maxHealth;
     public int currentHealth;
     public int armor;
     public Tool equippedTool;
     public Item[] inventory;
     public int selectedSlot;
     public boolean isMoving;
     public PlayerCharacter() {
         id = "player-main";
         name = "Aventurero";
         positionX = 0.0f;
         positionY = 0.0f;
         width = 64.0f;
         height = 64.0f;
         textureResId = R.drawable.player_texture;
         movementSpeed = 8.0f;
         maxHealth = 20;
         currentHealth = 20;
         armor = 0;
         equippedTool = null;
         inventory = new Item[36];
         selectedSlot = 0;
         isMoving = false;
     }
     public void moveLeft() {
         positionX -= movementSpeed;
         isMoving = true;
     }
     public void moveRight() {
         positionX += movementSpeed;
         isMoving = true;
     }
     public void moveUp() {
         positionY -= movementSpeed;
         isMoving = true;
     }
     public void moveDown() {
         positionY += movementSpeed;
         isMoving = true;
     }
     public void stopMoving() {
         isMoving = false;
     }
     public void equipTool(int slot) {
         if (inventory[slot] instanceof Tool) {
             equippedTool = (Tool) inventory[slot];
         }
     }
     public void takeDamage(int damage) {
         int finalDamage = damage - armor;
         if (finalDamage < 0) finalDamage = 0;
         currentHealth -= finalDamage;
         if (currentHealth <= 0) currentHealth = 0;
     }
     public void heal(int amount) {
         currentHealth += amount;
         if (currentHealth > maxHealth) currentHealth = maxHealth;
     }
}
