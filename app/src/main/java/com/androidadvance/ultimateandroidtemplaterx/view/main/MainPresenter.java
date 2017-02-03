package com.androidadvance.ultimateandroidtemplaterx.view.main;

import android.content.Context;
import com.androidadvance.ultimateandroidtemplaterx.BaseApplication;
import com.androidadvance.ultimateandroidtemplaterx.data.remote.APIService;
import com.androidadvance.ultimateandroidtemplaterx.model.weather.WeatherPojo;
import com.androidadvance.ultimateandroidtemplaterx.presenter.Presenter;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import retrofit2.adapter.rxjava.HttpException;


public class MainPresenter implements Presenter<MainMvpView> {

  @Inject public MainPresenter(Context ctx) {
    ((BaseApplication) ctx.getApplicationContext()).getApplicationComponent().inject(this);
  }

  @Inject APIService apiService;
  @Inject EventBus mEventBus;

  private MainMvpView mainMvpView;
  private WeatherPojo weatherPojo;

  @Override public void attachView(MainMvpView view) {
    this.mainMvpView = view;
  }

  @Override public void detachView() {
    this.mainMvpView = null;
  }

  public void loadWeather(String from_where) {



    String weather_from_where = from_where.trim();
    if (weather_from_where.isEmpty()) return;

    mainMvpView.showProgress();

    BaseApplication baseApplication = BaseApplication.get(mainMvpView.getContext());


  }



  private static boolean isHttp404(Throwable error) {
    return error instanceof HttpException && ((HttpException) error).code() == 404;
  }
}