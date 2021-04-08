# 依照必要格式生成密钥对
ssh-keygen -m PEM -t rsa -b 4096 -f ~/config_server_deploy_key.rsa

# 触发配置中心服务端刷新配置
curl -X POST "http://localhost:3344/actuator/bus-refresh"

# 触发配置中心服务端刷新配置——定点通知
curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"

# docker 启动 rabbitmq 命令
docker run -d --hostname my-rabbit --name some-rabbit -p 4369:4369 -p 15671:15671 -p 25672:25672 -p 15672:15672 -p 5671:5671 -p 5672:5672 rabbitmq:3-management