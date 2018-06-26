package org.sfitengg.heart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //GUI COMPONENTS :
    private ImageView[] hearts;
    private Button resetAnimatedButton;

    //Variables:
    private int animationDuration = 500;
    private int translateDistance = 20;

    private Animation getTranslateAnimation(){
        Animation translate = new TranslateAnimation(
                0, 0,
                0,translateDistance
        );
        translate.setDuration(animationDuration);
        return translate;
    }
    private Animation getFadeOutAnimation(final ImageView imageView){
        Animation fadeOutAnimation = new AlphaAnimation(1, 0);
        fadeOutAnimation.setInterpolator(new AccelerateInterpolator());
        fadeOutAnimation.setDuration(animationDuration);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        return fadeOutAnimation;
    }
    private void mStartAnimation(ImageView imageView){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(getFadeOutAnimation(imageView));
        animationSet.addAnimation(getTranslateAnimation());
        imageView.startAnimation(animationSet);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialising gui components:
        resetAnimatedButton = findViewById(R.id.reset_animation_button);
        hearts = new ImageView[4];
        hearts[0] = findViewById(R.id.heart_1);
        hearts[1] = findViewById(R.id.heart_2);
        hearts[2] = findViewById(R.id.heart_3);
        hearts[3] = findViewById(R.id.heart_4);

        //set Animation to all imageViews:
        for(int i = 0; i<hearts.length; i++){
            final int finalI = i;
            hearts[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mStartAnimation(hearts[finalI]);
                }
            });
        }

        //reset animation by making all views visible:
        resetAnimatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<hearts.length; i++){
                    hearts[i].clearAnimation();
                    hearts[i].setVisibility(View.VISIBLE);
                }
            }
        });
    }
}