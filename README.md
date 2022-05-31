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
[aw05对应分支](https://github.com/sawork-2022/aw05-Price1999a/tree/aw08)

在仓库中的代码主要探索了用SI进行代理的实现，对`http://localhost:8080/check/{var}` 的请求目前被转发到 `https://api.chucknorris.io/jokes/{var}` 上

也就是说现在对 `http://localhost:8088/check/random` 的调用目前就像一个网关一样。

对我们目前aw05这个应用而言，SI并没有什么特别的必要，因为对于收到的信息并不需要进行额外的处理，不像在aw08一样存在对于外部调用返回信息的一层处理。aw05中的是原样转发，这导致SI这个场景下并没有体现出什么优势。

### 对SI的反思

Spring Integration 的主要目标是为构建企业集成解决方案提供一个简单的模型。

在我们当前的运用中，这一点其实并没有一个很明确的展示，因为当前转发请求的需求更适合网关进行操作。

但是假如我们注意到这个样例请求——也就是那个对 `https://api.chucknorris.io/jokes/random` 网址的参考样例，那么我们就可以发现这里所做的不仅仅是简单的转发信息——实际上也简单的做了一些对信息的过滤。那么在这种情况下，相较于网关实现（可能需要复杂的自定义filter），这里的实现显然是更加简单的。

同时更重要的是，spring集成可以简单的适配多种奇奇怪怪的外部组件，虽然这个特性在当前项目中没有体现，但是这使得它比网关更具通用性——若要使用网关达成类似效果，可能需要在各个系统上做一些适配，而SI可以直接完成类似任务。
