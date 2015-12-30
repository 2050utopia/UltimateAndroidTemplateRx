package com.androidadvance.ultimateandroidtemplaterx;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.androidadvance.ultimateandroidtemplaterx.data.local.DbModel;
import com.androidadvance.ultimateandroidtemplaterx.data.remote.ApiService;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BaseApplication extends Application {

  private ApiService apiService;
  private Scheduler defaultSubscribeScheduler;

  @Override public void onCreate() {
    super.onCreate();

    boolean isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

    if (isDebuggable) {
      Timber.plant(new Timber.DebugTree());
    }

    FlowManager.init(this);

    Delete.table(DbModel.class);
  }

  public ApiService getApiService() {
    if (apiService == null) {
      apiService = ApiService.Factory.getApi();
    }
    return apiService;
  }

  public Scheduler defaultSubscribeScheduler() {
    if (defaultSubscribeScheduler == null) {
      defaultSubscribeScheduler = Schedulers.io();
    }
    return defaultSubscribeScheduler;
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    Timber.e("##### onLowMemory #####");
  }

  public static BaseApplication get(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }
}
