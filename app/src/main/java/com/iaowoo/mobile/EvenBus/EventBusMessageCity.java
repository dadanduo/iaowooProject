package com.iaowoo.mobile.EvenBus;

/**
 * 自定义城市选择
 */
public class EventBusMessageCity {

    private String CityName;

    public EventBusMessageCity(String cityName) {
        this.CityName = cityName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}


