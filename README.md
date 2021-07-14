# MagicTower v1.13 (复刻者:Vip、疯子)

## 前言
本项目是基于Java开发的魔塔游戏，复刻的是胖老鼠的flash版魔塔v1.12；同时，我在此基础上添加了彩蛋、分数上传等功能，最终的boss也可以多次击杀以获得更高的分数。

整个魔塔项目包含了一个客户端和一个服务端，其中，本项目为客户端。游戏的主窗体基于JPanel实现，主时间轴为8帧，游戏刷新率默认为每秒50帧；游戏的服务端主要用于玩家分数的上传和展示，主要基于SpringBoot实现。

游戏的主体框架已经搭建完成，其中，提供了比较多的扩展接口，大家可以轻松的在本项目的基础上完成自己的魔塔。

## 参考资料
- 游戏主题的时间轴变化参考了[Tower-of-the-Deathmaster](https://github.com/jiminma50/Tower-of-the-Deathmaster)的设计
- 游戏主体中的窗体设计参考了[MagicTower](https://github.com/gdut-yy/MagicTower)的设计

## 资源列表
- 图片资源
  - Flash魔塔（原版、胖老鼠版、cos105hk版）
  - [Tower-of-the-Deathmaster](https://github.com/jiminma50/Tower-of-the-Deathmaster)的静态资源（通过编写的C#工具类截取）
- 音频资源
  - 游戏音效
    - [Tower-of-the-Deathmaster](https://github.com/jiminma50/Tower-of-the-Deathmaster)的静态资源
    - Flash魔塔（原版、胖老鼠版、cos105hk版）
  - BGM
    - [Tower-of-the-Deathmaster](https://github.com/jiminma50/Tower-of-the-Deathmaster)的静态资源
    - 氷とサンゴのダンジョン 　　　　　　　　　　　  [[風来のシレン外伝 女剣士アスカ見参! (DC) ]](https://music.163.com/#/song?id=1397495802)
    - 廃坑 （断崖の風穴） 　　　　　　　　　　　　　[[風来のシレン2 鬼襲来!シレン城! (N64) ]](https://music.163.com/#/song?id=1397495784)
    - オーバーチュア〜オープニングタイトル　　　　&nbsp;&nbsp;[[Romancing SaGa Original Soundtrack -REMASTER- ]](https://y.qq.com/n/ryqq/albumDetail/000u4Skk1lvxqI)
    - ラストダンジョン　　　　　　　　　　　　　　 &nbsp;[[Romancing SaGa Original Soundtrack -REMASTER- ]](https://y.qq.com/n/ryqq/albumDetail/000u4Skk1lvxqI)
    - 邪神復活 　　　　　　　　　　　　　　　　　　&nbsp;[[Romancing SaGa Original Soundtrack -REMASTER- ]](https://y.qq.com/n/ryqq/albumDetail/000u4Skk1lvxqI)
    - ダンジョン2 　　　　　　　　　　　　　　　　 &nbsp;&nbsp;[[Romancing SaGa Original Soundtrack -REMASTER- ]](https://y.qq.com/n/ryqq/albumDetail/000u4Skk1lvxqI)
    - Dance with wind 　　　　　　　　　　　　　　 &nbsp;&nbsp;&nbsp;[[阿玛迪斯战记 游戏原声带]](https://music.163.com/#/album?id=3233573)

**注：以上的链接仅为能够搜索到的版本，并不代表资源的出处，若有侵权，请联系我删除。**

## 关于作者
不瞒你们说，本人特别喜欢打游戏，也喜欢做一些奇奇怪怪的东西，例如脚本、**等。在校期间也希望完成一个比较有特色的毕业设计，也就有了本项目。限于本人当时水平，代码中存在部分的问题，但基本的游玩完全没有问题，现在开源出来供大家参考、借鉴，同时也欢迎大家向我反馈问题。

[GitHub](https://github.com/xhy1999)　　　[个人主页](http://www.xuehaoyang.com)

****
## 一、开发环境
- 开发工具
  - IntelliJ IDEA 　　2020.1.2
  - JDK 　　　　　 &nbsp;1.8.0_202
- IDEA插件
  - JavaFX Helper 　1.0.4
  - LomBok 　　　 &nbsp;0.32 - 2020.1
- 额外依赖
  - cn.hutool　　　　　　　　　　(工具包)　　　　5.5.1
  - org.projectlombok　　　　　　(LomBok插件)　 1.18.4
  - com.googlecode.soundlibs　　 (音乐播放)　　　1.9.5.4
  - com.alibaba.fastjson　　　　　 (Json解析)　　　1.2.58
  - org.jogamp.jogl　　　　　　　 (Java OpenGL)　 2.3.2
  - org.jogamp.gluegen　　　　　 (Java OpenGL)　 2.3.2

## 二、打包与运行
**注：若想要在开发环境运行，则运行main.Main即可。**

### 1.打包（以IDEA 2021.1为例）

#### (1).点击左上角`File`，选择`Project Structure`
<img src="readme-images/packaging-1.jpg" style="zoom:80%">

#### (2).点击左侧`Artifacts`，点击中间上方的`+`号，选择`JAR` - `From modules with dependencies`
<img src="readme-images/packaging-2.jpg" style="zoom:80%">

#### (3).选择主函数：`Main（main）`
<img src="readme-images/packaging-3.jpg" style="zoom:80%">

#### (4).配置如下，注意红框中的路径，配置完成后点击`OK`
<img src="readme-images/packaging-4.jpg" style="zoom:80%">

#### (5).点击右下角`Apply`
<img src="readme-images/packaging-5.jpg" style="zoom:80%">

#### (6).点击IDEA导航栏的`Build`，点击`Build Artifacts`
<img src="readme-images/packaging-6.jpg" style="zoom:100%">

#### (7).点击弹出小框中的`Rebuild`
<img src="readme-images/packaging-7.jpg" style="zoom:100%">

#### (8).打包完成
<img src="readme-images/packaging-8.jpg" style="zoom:100%">

### 2.运行（Windows环境）
直接`双击jar`包，或者在jar包的路径运行指令`java -jar MagicTower.jar`

### 3.运行（Linux环境）
Linux需要有图形化桌面，否则无法运行，运行时在jar包的路径执行指令`java -jar MagicTower.jar`

**注意：运行请使用Java 1.8以上环境，Linux请使用Java 1.8_271以上版本，否则可能导致分数结算界面无法正常弹出。**

<br><br>

未完待续...