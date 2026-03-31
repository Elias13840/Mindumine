import { Structure } from './Structure.js';
 import { DirtBlock, StoneBlock, WoodBlock, GlassBlock } from '../../Blocks/Block.js'; // Agrega bloques que uses
 export class SmallHouseStructure extends Structure {
     constructor() {
         // Tamaño: 5x3x5, probabilidad de spawn 10%
         super("small-house", "Casa Pequeña", 5, 3, 5, 0.1);
     }
     // Sobrescribe método para crear el diseño de la casa
     createLayout() {
         const layout = super.createLayout();
         // Paredes de piedra
         for (let i = 0; i < this.sizeX; i++) {
             for (let k = 0; k < this.sizeZ; k++) {
                 if (i === 0 || i === this.sizeX - 1 || k === 0 || k === this.sizeZ - 1) {
                     layout[i][0][k] = new StoneBlock(); // Piso
                     layout[i][1][k] = new StoneBlock(); // Paredes
                 } else {
                     layout[i][0][k] = new DirtBlock(); // Piso interior
                 }
             }
         }
         // Techo de madera
         for (let i = 0; i < this.sizeX; i++) {
             for (let k = 0; k < this.sizeZ; k++) {
                 layout[i][2][k] = new WoodBlock();
             }
         }
         // Ventana de cristal
         layout[2][1][0] = new GlassBlock();
         return layout;
     }
              }
