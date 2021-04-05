# curl cookie 测试
curl http://localhost:9527/payment/lb --cookie "username=zzyy"

# curl X-Request-Id 测试
curl http://localhost:9527/payment/lb -H "X-Request-Id:123"