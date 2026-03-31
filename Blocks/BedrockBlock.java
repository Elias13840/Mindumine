public class BedrockBlock extends Block {
     public BedrockBlock() {
         id = "bedrock-block";
         name = "Bedrock";
         width = 32;
         height = 32;
         textureId = 1;
         backgroundColor = 0xFFA9A9A9;
         borderColor = 0xFF000000;
         weight = 1.5f;
         isSolid = true;
         isTransparent = false;
         hasGravity = false;
         hardness = 1000000;
         harvestLevel = 1000;
         dropItem = new BedrockItem();
         maxStack = 64;
         isOre = false;
         isCraftable = false;
         buildTime = 1000;
     }
}
