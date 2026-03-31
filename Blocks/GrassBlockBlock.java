public class GrassBlock extends Block {
     public GrassBlock() {
         id = "grass-block";
         name = "Tierra con pasto";
         width = 32;
         height = 32;
         textureId = 1;
         backgroundColor = 0xFFA9A9A9;
         borderColor = 0xFF000000;
         weight = 1.5f;
         isSolid = true;
         isTransparent = false;
         hasGravity = false;
         hardness = 3;
         harvestLevel = 1;
         optimalToolType = "shovel"; // Herramienta ideal: pico
         dropItem = new DirtItem();
         maxStack = 64;
         isOre = false;
         isCraftable = false;
         buildTime = 1000;
     }
}
