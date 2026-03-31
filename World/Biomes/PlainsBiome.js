import { Biome } from './Biome.js';
 import { GrassBlock, DirtBlock, StoneBlock } from '../../Blocks/Block.js';
 import { SmallHouseStructure } from '../Structures/SmallHouseStructure.js';
 export class PlainsBiome extends Biome {
     constructor() {
         // Define propiedades del bioma de llanuras
         super(
             "plains",
             "Llanuras",
             0.6, // Temperatura media
             0.5, // Humedad media
             {
                 top: new GrassBlock(),
                 middle: new DirtBlock(),
                 bottom: new StoneBlock()
             },
             [new GrassBlock(), new DirtBlock()] // Vegetación básica
         );
         // Agrega una estructura al bioma
         this.addStructure(new SmallHouseStructure());
     }
     // Sobrescribe método para agregar flores aleatorias
     generateRandomVegetation() {
         const vegetation = super.generateRandomVegetation();
         // 30% de probabilidad de generar una flor (si la agregas como bloque)
         if (Math.random() < 0.3) {
             return { type: "flower", color: ["red", "yellow"][Math.floor(Math.random() * 2)] };
         }
         return vegetation;
     }
           }
