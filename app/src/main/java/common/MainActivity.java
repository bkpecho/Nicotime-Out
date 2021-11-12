package common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.nicotimeout.app.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupOnboardingItems();
        ViewPager2 onboardingViewPager = findViewById(R.id.onBoardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem slideOne = new OnboardingItem();
        slideOne.setTitle("WELCOME!");
        slideOne.setDescription("You are about to start the most fruitful journey of your life");
        slideOne.setImage(R.drawable.intro_img_1);

        OnboardingItem slideTwo = new OnboardingItem();

        slideTwo.setDescription("Quit Smoking with the Nicotime-Out! App");
        slideTwo.setImage(R.drawable.intro_img_2);

        OnboardingItem slideThree = new OnboardingItem();

        slideThree.setDescription("Cross the Smoke-Free Finish Line");
        slideThree.setImage(R.drawable.intro_img_3);

        onboardingItems.add(slideOne);
        onboardingItems.add(slideTwo);
        onboardingItems.add(slideThree);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

}