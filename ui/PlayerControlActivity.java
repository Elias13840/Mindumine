public class PlayerControlActivity extends AppCompatActivity {
     private PlayerCharacter player;
     private PlayerView gameView;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_player_control);
         
         player = new PlayerCharacter();
         gameView = new PlayerView(this, player);
         
         FrameLayout gameContainer = findViewById(R.id.game_container);
         gameContainer.addView(gameView);
         
         Button btnLeft = findViewById(R.id.btn_left);
         Button btnRight = findViewById(R.id.btn_right);
         Button btnUp = findViewById(R.id.btn_up);
         Button btnDown = findViewById(R.id.btn_down);
         Button btnStop = findViewById(R.id.btn_stop);
         Button btnEquip = findViewById(R.id.btn_equip);
         
         btnLeft.setOnClickListener(v -> gameView.handleMovement("LEFT"));
         btnRight.setOnClickListener(v -> gameView.handleMovement("RIGHT"));
         btnUp.setOnClickListener(v -> gameView.handleMovement("UP"));
         btnDown.setOnClickListener(v -> gameView.handleMovement("DOWN"));
         btnStop.setOnClickListener(v -> gameView.handleMovement("STOP"));
         btnEquip.setOnClickListener(v -> gameView.equipSelectedTool());
     }
                                       }
