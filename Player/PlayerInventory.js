export class PlayerInventory {
     constructor() {
         this.SLOT_COUNT = 36;
         this.inventorySlots = new Array(this.SLOT_COUNT).fill(null);
         this.selectedSlotIndex = 0;
         this.maxStackSize = 64;
     }
     // Agregar ítem al inventario
     addItem(item) {
         // Busca slots con el mismo ítem y espacio
         for (let i = 0; i < this.SLOT_COUNT; i++) {
             const slotItem = this.inventorySlots[i];
             if (slotItem && slotItem.id === item.id && slotItem.stackSize < this.maxStackSize) {
                 const spaceLeft = this.maxStackSize - slotItem.stackSize;
                 const addAmount = Math.min(item.stackSize, spaceLeft);
                 
                 slotItem.stackSize += addAmount;
                 item.stackSize -= addAmount;
                 if (item.stackSize <= 0) return true;
             }
         }
         // Busca slots vacíos
         for (let i = 0; i < this.SLOT_COUNT; i++) {
             if (!this.inventorySlots[i]) {
                 this.inventorySlots[i] = { ...item }; // Clona el ítem
                 return true;
             }
         }
         return false; // No hay espacio
     }
     // Remover ítem
     removeItem(slotIndex, amount = 1) {
         if (slotIndex < 0 || slotIndex >= this.SLOT_COUNT || !this.inventorySlots[slotIndex]) {
             return false;
         }
         const slotItem = this.inventorySlots[slotIndex];
         if (slotItem.stackSize <= amount) {
             this.inventorySlots[slotIndex] = null;
         } else {
             slotItem.stackSize -= amount;
         }
         return true;
     }
     // Seleccionar slot
     selectSlot(index) {
         if (index >= 0 && index < this.SLOT_COUNT) {
             this.selectedSlotIndex = index;
         }
     }
     // Obtener ítem seleccionado
     getSelectedItem() {
         return this.inventorySlots[this.selectedSlotIndex];
     }
              }
