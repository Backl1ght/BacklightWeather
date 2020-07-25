# SimpleWeatherApp
简易安卓天气应用，借助和风天气API获取天气数据。

### 使用

1. 注册和风天气的key，替换掉FetchItemsTask.java中的key值。
2. 注册高德地图的key，替换掉AndroidManifest.xml中的key值。 

### 效果

<img src="./images/main.png" alt="main" style="zoom:25%;" />

### TODO

- [x] 使用okhttp替换HttpURLConnection
- [ ] 完善支持华氏度单位的功能
- [ ] 保存离线数据，当无网络连接时就显示离线数据
- [ ] 多标签页显示多个城市的天气
  - [x] 城市列表
  - [ ] 城市选择(没找到能用的CityPicker)
  - [ ] 多标签页(VIewPager + CircleIndicator)
- [ ] 美化界面
