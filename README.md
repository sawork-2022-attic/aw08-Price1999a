# aw08

Run the project with `mvn spring-boot:run` and send request to `http://localhost:8080/check`. You should see an reponses in json format like the following.

```json
{
    "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    "id": "kswv7NIaTCaIIErlBzODaA",
    "url": "https://api.chucknorris.io/jokes/kswv7NIaTCaIIErlBzODaA",
    "value": "Chuck Norris's shadow weighs 250 pounds and can kick your ass ."
}
```

Try to understand the provided code which demonstrates spring integration between a spring boot application with an externel http service (https://api.chucknorris.io/jokes/random).

Please implement delivery as an standalone service (just like the random joke service). Refer the sample code to integrate your Micropos system with delivery service so that user can check delivery status on Miropos which actually forwards user request to delivery service on demand.

![](Micropos.svg)

Consider the advantage by doing so and write it down in your readme file.

## 作业报告

同样的实际作业将被处理到aw05微服务那边对应的分支。
~~链接待更新 TODO~~

在仓库中的代码主要探索了用SI进行代理的实现，对`http://localhost:8080/check/{var}` 的请求目前被转发到 `https://api.chucknorris.io/jokes/{var}` 上

也就是说现在对`http://localhost:8080/check/random`的调用目前就像一个网关一样。

对我们目前aw05这个应用而言，SI并没有什么特别的必要，因为对于收到的信息并不需要进行额外的处理，不像在aw08一样存在对于外部调用返回信息的一层处理。aw05中的是原样转发，这导致SI这个场景下并没有体现出什么优势。