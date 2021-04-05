# 触发配置中心服务端刷新配置
curl -X POST "http://localhost:3344/actuator/bus-refresh"

# 触发配置中心服务端刷新配置——定点通知
curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"