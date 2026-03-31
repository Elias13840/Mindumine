public class PlayerView extends View {
     private PlayerCharacter player;
     private PlayerControl playerControl;
     private Bitmap playerTexture;
     private Paint healthPaint;
     private Paint backgroundPaint;
     public PlayerView(Context context, PlayerCharacter player) {
         super(context);
         this.player = player;
         this.playerControl = new PlayerControl(player);
         playerTexture = BitmapFactory.decodeResource(getResources(), player.textureResId);
         
         healthPaint = new Paint();
         healthPaint.setColor(Color.RED);
         
         backgroundPaint = new Paint();
         backgroundPaint.setColor(Color.GREEN);
     }
     @Override
     protected void onDraw(Canvas canvas) {
         super.onDraw(canvas);
         
         canvas.drawColor(Color.parseColor("#87CEEB"));
         
         canvas.drawBitmap(playerTexture, player.positionX, player.positionY, null);
         
         canvas.drawRect(
             player.positionX, 
             player.positionY - 10, 
             player.positionX + player.width, 
             player.positionY - 5, 
             backgroundPaint
         );
         
         float healthBarWidth = (player.currentHealth * 1.0f / player.maxHealth) * player.width;
         canvas.drawRect(
             player.positionX, 
             player.positionY - 10, 
             player.positionX + healthBarWidth, 
             player.positionY - 5, 
             healthPaint
         );
         
         invalidate();
     }
     public void handleMovement(String direction) {
         switch (direction) {
             case "LEFT":
                 playerControl.moveLeft();
                 break;
             case "RIGHT":
                 playerControl.moveRight();
                 break;
             case "UP":
                 playerControl.moveUp();
                 break;
             case "DOWN":
                 playerControl.moveDown();
                 break;
             case "STOP":
                 playerControl.stopMoving();
                 break;
         }
     }
     public void equipSelectedTool() {
         playerControl.equipTool(player.selectedSlot);
     }
           }
