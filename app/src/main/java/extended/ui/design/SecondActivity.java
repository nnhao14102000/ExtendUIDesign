package extended.ui.design;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView second_back_arrow, second_arrow_up;
    TextView second_title, second_subtitle, second_rating_number, second_rating_number2, more_details;
    RatingBar second_ratingbar;

    Animation from_left, from_right, from_bottom;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        second_back_arrow = findViewById(R.id.second_back_arrow);
        second_arrow_up = findViewById(R.id.second_arrow_up);
        second_title = findViewById(R.id.second_title);
        second_subtitle = findViewById(R.id.second_subtitle);
        second_rating_number = findViewById(R.id.second_rating_number);
        second_rating_number2 = findViewById(R.id.second_rating_number2);
        more_details = findViewById(R.id.more_details);
        second_ratingbar = findViewById(R.id.second_ratingbar);

        second_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //hide status bar and navigation bar at the bottom
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController controller = getWindow().getInsetsController();
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            //noinspection deprecation
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        }

        //load animation
        from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);

        //set animation
        second_back_arrow.setAnimation(from_left);
        second_title.setAnimation(from_right);
        second_subtitle.setAnimation(from_right);
        second_ratingbar.setAnimation(from_left);
        second_rating_number.setAnimation(from_right);
        second_rating_number2.setAnimation(from_right);
        second_arrow_up.setAnimation(from_bottom);
        more_details.setAnimation(from_bottom);

        //set onClick for arrow_up
        second_arrow_up.setOnClickListener((view) -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(second_arrow_up, "background_image_transition");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this, pairs);
            startActivity(intent, options.toBundle());
        });
    }
}