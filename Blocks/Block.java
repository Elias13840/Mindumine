public class Block {
     String id;
     String name;
     int width;
     int height;
     int textureId;
     int backgroundColor;
     int borderColor;
     float weight;
     boolean isSolid;
     boolean isTransparent;
     boolean hasGravity;
     int hardness;
     int harvestLevel;
     String optimalToolType; // Tipo de herramienta ideal: "pickaxe", "axe"
     Item dropItem;
     int maxStack;
     boolean isOre;
     boolean isCraftable;
     int buildTime;
     // Método para calcular el daño según la herramienta usada
     public int calculateDamage(Tool tool) {
         int baseDamage = 2; // Daño base sin herramienta
         
         // Si la herramienta es la ideal y su nivel es suficiente
         if (tool != null && tool.toolType.equals(optimalToolType) && tool.harvestLevel >= harvestLevel) {
             return (int) (baseDamage * tool.damageMultiplier);
         } 
         // Si la herramienta no es ideal o su nivel es bajo
         else if (tool != null && tool.harvestLevel >= harvestLevel) {
             return baseDamage;
         } 
         // Sin herramienta o herramienta insuficiente
         else {
             return 1;
         }
     }
     // Método para verificar si el bloque se rompe
     public boolean breakBlock(Tool tool) {
         hardness -= calculateDamage(tool);
         return hardness <= 0;
     }
}
