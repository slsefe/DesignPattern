package metrics;

/**
 * 提供API，采集接口请求的原始数据
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * 保存某次请求
     */
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || requestInfo.getApiName().isEmpty()) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
