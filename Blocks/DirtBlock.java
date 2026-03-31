public class DirtBlock extends Block {
     public DirtBlock() {
         id = "Dirt-Block";
         name = "Tierra";
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
