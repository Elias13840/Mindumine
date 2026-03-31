// Importa bloques necesarios
 import { AirBlock } from '../../Blocks/Block.js';
 export class Structure {
     constructor(id, name, sizeX, sizeY, sizeZ, spawnChance) {
         this.id = id; // Identificador único (ej: "small-house")
         this.name = name; // Nombre visible
         this.sizeX = sizeX; // Ancho en X
         this.sizeY = sizeY; // Alto en Y
         this.sizeZ = sizeZ; // Profundidad en Z
         this.spawnChance = spawnChance; // Probabilidad de aparecer (0-1)
         this.layout = this.createLayout(); // Matriz 3D con el diseño de la estructura
     }
     // Método abstracto para crear el diseño de la estructura
     createLayout() {
         // Se debe sobrescribir en cada estructura específica
         const emptyLayout = new Array(this.sizeX)
             .fill(null)
             .map(() => new Array(this.sizeY)
                 .fill(null)
                 .map(() => new Array(this.sizeZ).fill(new AirBlock()))
             );
         return emptyLayout;
     }
     // Método para verificar si la estructura puede generarse en una posición
     canSpawn(world, x, y, z) {
         // Verifica que haya espacio suficiente y el suelo sea adecuado
         for (let i = 0; i < this.sizeX; i++) {
             for (let k = 0; k < this.sizeZ; k++) {
                 const groundBlock = world.getBlock(x + i, y - 1, z + k);
                 if (!groundBlock || groundBlock.id !== "grass-block") return false;
             }
         }
         return true;
     }
     // Método para generar la estructura en el mundo
     spawn(world, x, y, z) {
         if (!this.canSpawn(world, x, y, z) || Math.random() > this.spawnChance) return;
         
         for (let i = 0; i < this.sizeX; i++) {
             for (let j = 0; j < this.sizeY; j++) {
                 for (let k = 0; k < this.sizeZ; k++) {
                     const block = this.layout[i][j][k];
                     world.setBlock(x + i, y + j, z + k, block);
                 }
             }
         }
         console.log(`Estructura ${this.name} generada en (${x}, ${y}, ${z})`);
     }
}
