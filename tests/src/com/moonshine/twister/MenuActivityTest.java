package com.moonshine.twister;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.moonshine.twister.MenuActivityTest \
 * com.moonshine.twister.tests/android.test.InstrumentationTestRunner
 */
public class MenuActivityTest extends ActivityInstrumentationTestCase2<MenuActivity> {

    public MenuActivityTest() {
        super("com.moonshine.twister", MenuActivity.class);
    }

}
