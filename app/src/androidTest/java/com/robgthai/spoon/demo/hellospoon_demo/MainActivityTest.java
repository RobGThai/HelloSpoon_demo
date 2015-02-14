package com.robgthai.spoon.demo.hellospoon_demo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityIsAvailable() {
        assertNotNull("Activity is not available", getActivity());
    }

    public void testScreenshotIsWorking() {
        assertNotNull("Activity is not available", getActivity());
        Spoon.screenshot(getActivity(), "start");
    }

    public void testHelloWorldVisibleByDefault() {
        ViewGroup root = (ViewGroup) getActivity()
                .findViewById(android.R.id.content);
        int total = root.getChildCount();

        for(int i = 0; i < total; i++) {
            View v = root.getChildAt(i);
            if(v instanceof TextView) {
                assertEquals("TextView contain Hello World!"
                        , ((TextView) v).getText().toString()
                        , "Hello World!");
            }
        }
    }
}
