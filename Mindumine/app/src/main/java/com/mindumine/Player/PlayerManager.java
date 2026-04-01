package com.mindumine.player;
 import android.content.Context;
 import android.content.SharedPreferences;
 import java.util.List;
 public class PlayerManager {
     private static final String PREFS_NOMBRE = "MinduminePlayerPrefs";
     private static final String KEY_NOMBRE = "nombreJugador";
     private static final String KEY_PUNTUACION = "puntuacionJugador";
     private static final String KEY_NIVEL = "nivelJugador";
     private static final String KEY_VIDAS = "vidasJugador";
     // Claves para power-ups
     private static final String KEY_PU_ESTADO = "pu_estado_";
     private static final String KEY_PU_TIEMPO_ACT = "pu_tiempo_act_";
     private static final String KEY_PU_TIEMPO_DESACT = "pu_tiempo_desact_";
     private SharedPreferences sharedPreferences;
     public PlayerManager(Context contexto) {
         sharedPreferences = contexto.getSharedPreferences(PREFS_NOMBRE, Context.MODE_PRIVATE);
     }
     // Actualización del método guardarJugador
     public void guardarJugador(Player jugador) {
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putString(KEY_NOMBRE, jugador.getNombre());
         editor.putInt(KEY_PUNTUACION, jugador.getPuntuacionActual());
         editor.putInt(KEY_NIVEL, jugador.getNivelActual());
         editor.putInt(KEY_VIDAS, jugador.getVidasRestantes());
         // Guardar estado de cada power-up
         List<PowerUp> powerUps = jugador.getPowerUpsDisponibles();
         for (PowerUp pu : powerUps) {
             String tipo = pu.getTipo().name();
             editor.putString(KEY_PU_ESTADO + tipo, pu.getEstado().name());
             editor.putLong(KEY_PU_TIEMPO_ACT + tipo, pu.getTiempoActivacion());
             editor.putLong(KEY_PU_TIEMPO_DESACT + tipo, pu.getTiempoDesactivacion());
         }
         editor.apply();
     }
     // Actualización del método cargarJugador
     public Player cargarJugador() {
         Player jugador = new Player();
         jugador.setNombre(sharedPreferences.getString(KEY_NOMBRE, "Jugador"));
         jugador.sumarPuntos(sharedPreferences.getInt(KEY_PUNTUACION, 0));
         
         int nivelGuardado = sharedPreferences.getInt(KEY_NIVEL, 1);
         for (int i = 1; i < nivelGuardado; i++) {
             jugador.sumarPuntos(100);
         }
         jugador.setVidasRestantes(sharedPreferences.getInt(KEY_VIDAS, 3));
         // Cargar estado de cada power-up
         List<PowerUp> powerUps = jugador.getPowerUpsDisponibles();
         for (PowerUp pu : powerUps) {
             String tipo = pu.getTipo().name();
             EstadoPowerUp estado = EstadoPowerUp.valueOf(
                 sharedPreferences.getString(KEY_PU_ESTADO + tipo, EstadoPowerUp.INACTIVO.name())
             );
             long tiempoAct = sharedPreferences.getLong(KEY_PU_TIEMPO_ACT + tipo, 0);
             long tiempoDesact = sharedPreferences.getLong(KEY_PU_TIEMPO_DESACT + tipo, 0);
             pu.setEstado(estado);
             pu.setTiempoActivacion(tiempoAct);
             pu.setTiempoDesactivacion(tiempoDesact);
             pu.actualizarEstado(); // Ajustar estado según tiempo actual
         }
         return jugador;
     }
     // ... (métodos reiniciarJugador y demás sin cambios) ...
     // Métodos auxiliares para setear valores de power-ups (añadidos a PowerUp.java)
     // Se deben agregar los setters correspondientes en PowerUp.java:
     // public void setEstado(EstadoPowerUp estado) { this.estado = estado; }
     // public void setTiempoActivacion(long tiempo) { this.tiempoActivacion = tiempo; }
     // public void setTiempoDesactivacion(long tiempo) { this.tiempoDesactivacion = tiempo; }
 }
