package observer;

public class WeatherStation {
    public static void main(String[] args) {
        // 创建主题类
        WeatherData weatherData = new WeatherData();
        // 创建观察者
        CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay(weatherData);
        // 主题更新状态
        weatherData.setMeasurements(20, 45, 30.4f);
        weatherData.setMeasurements(20, 45, 30.4f);
        weatherData.setMeasurements(20, 45, 30.4f);
    }
}
