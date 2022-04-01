package observer;

/**
 * 天气当前状况布告板
 */
public class CurrentConditionDisplay implements DisplayElement, Observer {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    /**
     * 使用weatherData对象创建布告板，并将该布告板注册到该主题
     *
     * @param weatherData
     */
    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * 当天气数据发生变化时，该方法被调用
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println(String.format("current condition: temperature %f, humidity %f."));
    }
}
