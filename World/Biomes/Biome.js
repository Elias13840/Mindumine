// Importa bloques existentes
 import { GrassBlock, DirtBlock, StoneBlock, AirBlock } from '../../Blocks/Block.js';
 export class Biome {
     constructor(id, name, temperature, humidity, groundBlocks, vegetationBlocks) {
         this.id = id; // Identificador único (ej: "plains")
         this.name = name; // Nombre visible (ej: "Llanuras")
         this.temperature = temperature; // 0 = frío, 1 = cálido
         this.humidity = humidity; // 0 = seco, 1 = húmedo
         this.groundBlocks = groundBlocks; // Bloques del suelo (capas)
         this.vegetationBlocks = vegetationBlocks; // Vegetación que genera
         this.structures = []; // Estructuras que aparecen en el bioma
     }
     // Método para agregar estructuras al bioma
     addStructure(structure) {
         this.structures.push(structure);
     }
     // Método para generar una capa de suelo en coordenadas X,Z
     generateGroundLayer(yLevel) {
         const layer = [];
         // Define capas: suelo superior, tierra, piedra
         for (let y = 0; y < yLevel; y++) {
             if (y === yLevel - 1) layer.push(this.groundBlocks.top);
             else if (y >= yLevel - 3) layer.push(this.groundBlocks.middle);
             else layer.push(this.groundBlocks.bottom);
         }
         return layer;
     }
     // Método para generar vegetación aleatoria
     generateRandomVegetation() {
         const randomIndex = Math.floor(Math.random() * this.vegetationBlocks.length);
         return this.vegetationBlocks[randomIndex];
     }
                             }
