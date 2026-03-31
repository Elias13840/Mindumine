public class AndesiteBlock extends Block {
     public AndesiteBlock() {
         id = "andesite-block";
         name = "Andesita";
         width = 32;
         height = 32;
         textureId = 1;
         backgroundColor = 0xFFA9A9A9;
         borderColor = 0xFF000000;
         weight = 1.5f;
         isSolid = true;
         isTransparent = false;
         hasGravity = false;
         hardness = 10;
         harvestLevel = 1;
         optimalToolType = "pickaxe"; // Herramienta ideal: pico
         dropItem = new DioriteItem();
         maxStack = 64;
         isOre = false;
         isCraftable = false;
         buildTime = 1000;
     }
}
