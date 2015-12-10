package com.androidadvance.ultimateandroidtemplaterx;


import android.app.Application;
import android.content.Context;

import com.androidadvance.ultimateandroidtemplaterx.injection.component.ApplicationComponent;
import com.androidadvance.ultimateandroidtemplaterx.injection.module.ApplicationModule;


public class AndroidBoilerplateApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //Timber.plant(new Timber.DebugTree());
        }

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AndroidBoilerplateApplication get(Context context) {
        return (AndroidBoilerplateApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
