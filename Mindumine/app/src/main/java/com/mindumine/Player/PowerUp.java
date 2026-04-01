package com.mindumine.player;
 import android.os.Parcel;
 import android.os.Parcelable;
 // Tipos de power-ups disponibles
 public enum TipoPowerUp {
     MULTIPLICADOR_PUNTOS,  // Duplica puntos obtenidos
     INVULNERABILIDAD,      // Evita pérdida de vidas
     EXTRA_VIDA,            // Añade una vida de forma permanente
     DESBLOQUEO_RAPIDO      // Acelera acciones del jugador
 }
 // Estados del power-up
 public enum EstadoPowerUp {
     INACTIVO,    // No disponible o no adquirido
     ACTIVO,      // En funcionamiento
     EN_COOLDOWN  // No se puede usar hasta que termine el tiempo de espera
 }
 public class PowerUp implements Parcelable {
     private TipoPowerUp tipo;
     private EstadoPowerUp estado;
     private long duracionMs;       // Duración del efecto (en milisegundos)
     private long cooldownMs;       // Tiempo de espera para volver a usar
     private long tiempoActivacion; // Marca temporal de activación
     private long tiempoDesactivacion; // Marca temporal de fin
     // Constructor base para power-ups con duración y cooldown
     public PowerUp(TipoPowerUp tipo, long duracionMs, long cooldownMs) {
         this.tipo = tipo;
         this.estado = EstadoPowerUp.INACTIVO;
         this.duracionMs = duracionMs;
         this.cooldownMs = cooldownMs;
         this.tiempoActivacion = 0;
         this.tiempoDesactivacion = 0;
     }
     // Constructor para power-up de efecto permanente (ej: Extra Vida)
     public PowerUp(TipoPowerUp tipo) {
         this.tipo = tipo;
         this.estado = EstadoPowerUp.INACTIVO;
         this.duracionMs = 0;
         this.cooldownMs = 0;
         this.tiempoActivacion = 0;
         this.tiempoDesactivacion = 0;
     }
     // Método para activar el power-up
     public boolean activar() {
         if (estado == EstadoPowerUp.INACTIVO) {
             estado = EstadoPowerUp.ACTIVO;
             tiempoActivacion = System.currentTimeMillis();
             
             if (duracionMs > 0) {
                 tiempoDesactivacion = tiempoActivacion + duracionMs;
             } else {
                 // Efecto permanente: se marca como inactivo después de aplicar
                 estado = EstadoPowerUp.INACTIVO;
             }
             return true;
         }
         return false;
     }
     // Método para verificar y actualizar el estado del power-up
     public void actualizarEstado() {
         if (estado == EstadoPowerUp.ACTIVO && duracionMs > 0) {
             long tiempoActual = System.currentTimeMillis();
             if (tiempoActual >= tiempoDesactivacion) {
                 estado = EstadoPowerUp.EN_COOLDOWN;
                 tiempoActivacion = System.currentTimeMillis();
             }
         } else if (estado == EstadoPowerUp.EN_COOLDOWN) {
             long tiempoActual = System.currentTimeMillis();
             if (tiempoActual >= tiempoActivacion + cooldownMs) {
                 estado = EstadoPowerUp.INACTIVO;
             }
         }
     }
     // Getters y Setters
     public TipoPowerUp getTipo() { return tipo; }
     public EstadoPowerUp getEstado() { return estado; }
     public long getTiempoRestante() {
         if (estado == EstadoPowerUp.ACTIVO) {
             return Math.max(0, tiempoDesactivacion - System.currentTimeMillis());
         } else if (estado == EstadoPowerUp.EN_COOLDOWN) {
             return Math.max(0, (tiempoActivacion + cooldownMs) - System.currentTimeMillis());
         }
         return 0;
     }
     // Métodos de Parcelable
     protected PowerUp(Parcel in) {
         tipo = TipoPowerUp.valueOf(in.readString());
         estado = EstadoPowerUp.valueOf(in.readString());
         duracionMs = in.readLong();
         cooldownMs = in.readLong();
         tiempoActivacion = in.readLong();
         tiempoDesactivacion = in.readLong();
     }
     public static final Creator<PowerUp> CREATOR = new Creator<PowerUp>() {
         @Override
         public PowerUp createFromParcel(Parcel in) {
             return new PowerUp(in);
         }
         @Override
         public PowerUp[] newArray(int size) {
             return new PowerUp[size];
         }
     };
     @Override
     public int describeContents() {
         return 0;
     }
     @Override
     public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(tipo.name());
         dest.writeString(estado.name());
         dest.writeLong(duracionMs);
         dest.writeLong(cooldownMs);
         dest.writeLong(tiempoActivacion);
         dest.writeLong(tiempoDesactivacion);
     }
           }
