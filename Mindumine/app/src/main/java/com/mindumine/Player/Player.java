package com.mindumine.player;
 import android.os.Parcel;
 import android.os.Parcelable;
 // Implementa Parcelable para poder pasar objetos Player entre componentes Android
 public class Player implements Parcelable {
     // Propiedades del jugador
     private String nombre;
     private int puntuacionActual;
     private int nivelActual;
     private int vidasRestantes;
     private boolean tienePowerUp;
     // Constructor por defecto
     public Player() {
         this.nombre = "Jugador";
         this.puntuacionActual = 0;
         this.nivelActual = 1;
         this.vidasRestantes = 3;
         this.tienePowerUp = false;
     }
     // Constructor con nombre personalizado
     public Player(String nombre) {
         this.nombre = nombre;
         this.puntuacionActual = 0;
         this.nivelActual = 1;
         this.vidasRestantes = 3;
         this.tienePowerUp = false;
     }
     // Métodos para gestionar puntuación
     public void sumarPuntos(int puntos) {
         if (puntos > 0) {
             puntuacionActual += puntos;
             verificarSubidaNivel();
         }
     }
     public void restarPuntos(int puntos) {
         if (puntos > 0 && puntuacionActual >= puntos) {
             puntuacionActual -= puntos;
         }
     }
     // Método para verificar si se sube de nivel
     private void verificarSubidaNivel() {
         int puntosNecesarios = nivelActual * 100; // Ejemplo: 100 pts para nivel 1, 200 para nivel 2, etc.
         if (puntuacionActual >= puntosNecesarios) {
             nivelActual++;
             vidasRestantes++; // Recompensa por subir de nivel
         }
     }
     // Métodos para gestionar vidas
     public void perderVida() {
         if (vidasRestantes > 0) {
             vidasRestantes--;
         }
     }
     public void ganarVida() {
         vidasRestantes++;
     }
     // Métodos para power-ups
     public void activarPowerUp() {
         tienePowerUp = true;
     }
     public void desactivarPowerUp() {
         tienePowerUp = false;
     }
     // Getters y Setters
     public String getNombre() { return nombre; }
     public void setNombre(String nombre) { this.nombre = nombre; }
     public int getPuntuacionActual() { return puntuacionActual; }
     public int getNivelActual() { return nivelActual; }
     public int getVidasRestantes() { return vidasRestantes; }
     public boolean tienePowerUp() { return tienePowerUp; }
     // Métodos de Parcelable (para persistencia o paso entre componentes)
     protected Player(Parcel in) {
         nombre = in.readString();
         puntuacionActual = in.readInt();
         nivelActual = in.readInt();
         vidasRestantes = in.readInt();
         tienePowerUp = in.readByte() != 0;
     }
     public static final Creator<Player> CREATOR = new Creator<Player>() {
         @Override
         public Player createFromParcel(Parcel in) {
             return new Player(in);
         }
         @Override
         public Player[] newArray(int size) {
             return new Player[size];
         }
     };
     @Override
     public int describeContents() {
         return 0;
     }
     @Override
     public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(nombre);
         dest.writeInt(puntuacionActual);
         dest.writeInt(nivelActual);
         dest.writeInt(vidasRestantes);
         dest.writeByte((byte) (tienePowerUp ? 1 : 0));
     }
             }
