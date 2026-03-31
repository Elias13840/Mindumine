public class GrassBlock extends Block {
     public GrassBlock() {
         id = "grass-block";
         name = "Pasto";
         width = 32;
         height = 32;
         textureId = 1;
         backgroundColor = 0xFFA9A9A9;
         borderColor = 0xFF000000;
         weight = 1.5f;
         isSolid = false;
         isTransparent = true;
         hasGravity = false;
         hardness = 10;
         harvestLevel = 1;
         optimalToolType = "pickaxe"; // Herramienta ideal: pico
         dropItem = new SeedsItem();
         maxStack = 64;
         isOre = false;
         isCraftable = false;
         buildTime = 1000;
     }
}
