package com.mindumine.player;
 import android.os.Parcel;
 import android.os.Parcelable;
 import java.util.ArrayList;
 import java.util.List;
 public class Player implements Parcelable {
     private String nombre;
     private int puntuacionActual;
     private int nivelActual;
     private int vidasRestantes;
     private List<PowerUp> powerUpsDisponibles; // Lista de power-ups del jugador
     // Constructor por defecto
     public Player() {
         this.nombre = "Jugador";
         this.puntuacionActual = 0;
         this.nivelActual = 1;
         this.vidasRestantes = 3;
         this.powerUpsDisponibles = new ArrayList<>();
         inicializarPowerUps(); // Carga los power-ups base
     }
     // Constructor con nombre personalizado
     public Player(String nombre) {
         this.nombre = nombre;
         this.puntuacionActual = 0;
         this.nivelActual = 1;
         this.vidasRestantes = 3;
         this.powerUpsDisponibles = new ArrayList<>();
         inicializarPowerUps();
     }
     // Inicializa los power-ups básicos del jugador
     private void inicializarPowerUps() {
         powerUpsDisponibles.add(new PowerUp(TipoPowerUp.MULTIPLICADOR_PUNTOS, 10000, 30000)); // 10s activo, 30s cooldown
         powerUpsDisponibles.add(new PowerUp(TipoPowerUp.INVULNERABILIDAD, 8000, 25000)); // 8s activo, 25s cooldown
         powerUpsDisponibles.add(new PowerUp(TipoPowerUp.EXTRA_VIDA)); // Efecto permanente
         powerUpsDisponibles.add(new PowerUp(TipoPowerUp.DESBLOQUEO_RAPIDO, 12000, 35000)); // 12s activo, 35s cooldown
     }
     // Método para aplicar el efecto de un power-up
     public void aplicarEfectoPowerUp(TipoPowerUp tipo) {
         for (PowerUp pu : powerUpsDisponibles) {
             if (pu.getTipo() == tipo && pu.activar()) {
                 switch (tipo) {
                     case MULTIPLICADOR_PUNTOS:
                         // Efecto: duplica puntos
                         break;
                     case INVULNERABILIDAD:
                         // Efecto: evita pérdida de vidas
                         break;
                     case EXTRA_VIDA:
                         ganarVida();
                         break;
                     case DESBLOQUEO_RAPIDO:
                         // Efecto: acelera acciones
                         break;
                 }
                 break;
             }
         }
     }
     // Método para actualizar estados de todos los power-ups
     public void actualizarPowerUps() {
         for (PowerUp pu : powerUpsDisponibles) {
             pu.actualizarEstado();
         }
     }
     // Método para verificar si un power-up está activo
     public boolean isPowerUpActivo(TipoPowerUp tipo) {
         for (PowerUp pu : powerUpsDisponibles) {
             if (pu.getTipo() == tipo && pu.getEstado() == EstadoPowerUp.ACTIVO) {
                 return true;
             }
         }
         return false;
     }
     // Métodos existentes (sumarPuntos, restarPuntos, etc.) se mantienen igual
     // ... (código existente sin cambios) ...
     // Actualización de Parcelable para incluir power-ups
     protected Player(Parcel in) {
         nombre = in.readString();
         puntuacionActual = in.readInt();
         nivelActual = in.readInt();
         vidasRestantes = in.readInt();
         powerUpsDisponibles = in.createTypedArrayList(PowerUp.CREATOR);
     }
     @Override
     public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(nombre);
         dest.writeInt(puntuacionActual);
         dest.writeInt(nivelActual);
         dest.writeInt(vidasRestantes);
         dest.writeTypedList(powerUpsDisponibles);
     }
     // ... (resto del código existente) ...
     // Getter para la lista de power-ups
     public List<PowerUp> getPowerUpsDisponibles() { return powerUpsDisponibles; }
 }
