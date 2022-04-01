package observer;

/**
 * 布告板接口，有三种标准的布告板，同时允许第三方实现自己的布告板，用来展示天气信息
 */
public interface DisplayElement {
    /**
     * 布告板需要显示时调用此方法
     */
    void display();
}
