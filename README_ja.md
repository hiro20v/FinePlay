[English](./README.md) | 日本語

<img src="./public/images/ja-JP/logo.png" alt="fine✿play" height="54"/>

Play(Java) 2.8 & Bootstrap 4.5  
多言語、レスポンシブな、プロジェクトのひな形(何かのたたき台に)。

| 携帯 | タブレット |
|-------|-----------|
| <img src="./public/images/iPhone.png" height="300"/>  | <img src="./public/images/iPad.png" height="400"/> |

遊び方
----------

##### 環境 #####

	macOS Catalina バージョン 10.15.6（19G73）
	時間帯 UTC
	openjdk バージョン "11.0.8" 2020-07-14 / OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.8+10)
	Safari バージョン 13.1.2 (15609.3.5.1.3)

##### OpenJDK #####

インストール

[AdoptOpenJDK](https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.8%2B10/OpenJDK11U-jdk_x64_mac_hotspot_11.0.8_10.pkg)

##### コンソール #####

	MacBook:~ user$ cd github/FinePlay

	MacBook:FinePlay user$ chmod +x ./sbt-dist/bin/sbt
	MacBook:FinePlay user$ chmod +x ./sbt
	MacBook:FinePlay user$ chmod +x ./start.sh

	MacBook:FinePlay user$ ./start.sh

##### Safari #####

URL を開く

[http://localhost:9000](http://localhost:9000)

緑色のユーザーアイコンをクリックすると、開発用のユーザーを選択できます。

書類
-------

### 公式書類 ###

[JDK](https://docs.oracle.com/javase/jp/11/docs/api/)

[Play Framework](https://www.playframework.com/documentation/2.8.x)

[Bootstrap](https://getbootstrap.com/docs/4.5)

[Font Awesome](https://fontawesome.com/how-to-use)

この **ひな形** は、非公式です。

サポート / 追加コンテンツ
---------------

##### Play Framework #####
+ ライトベンド社で、[商用サポート](https://www.lightbend.com/subscription)が提供されているようです。
+ [フォーラム](https://discuss.lightbend.com/c/play)が開催されているようです。
+ アプリケーションの[実装例](https://github.com/playframework/play-samples)があるようです。

##### Bootstrap #####
+ [ドキュメント](https://getbootstrap.com)を閲覧すると、広告収入になると思います。
+ [テーマ](https://themes.getbootstrap.com)を販売しているようです。

##### Font Awesome #####
+ [Pro版](https://fontawesome.com/pro)を購入すると、より多くのアイコンが使用できるようです。

注意
---------------

+ このプロジェクトは、継続性を考慮していません。（しかし、このひな形は、オープンソースソフトウェアのための非常に薄いラッパーなので、心配しないでください。）
+ このプロジェクトは、汎用的に使用する事は、考えられていません。（このプロジェクトは、ひな形なので、フォークもしくは、修正して使用します。）
+ クライアントライブラリは、ユーザー環境の変化によるリスクを想定して下さい。
+ このプロジェクトで使用している、ライブラリのライセンスは、利用者において改めて、確認してください。
+ このプロジェクトは、個人の趣味として開発しており、ここでのみ配布しています。

やる事(やるとは、言ってない)
---------------

###### このプロジェクトが、将来更新されるかどうかは、未定です。 ######

+ system_menuのリファクタリング

リリース履歴
---------------

+ **2.8.2-b1** - 2020-05-30
+ **2.7.3-b2** - 2019-07-06
+ **2.6.13-β7** - 2018-06-18
+ **2.6.3-α1** - 2017-08-14
   + 最初のコミット

###### バージョン番号なんて飾りです。偉い人にはそれがわからんのですよ。

寄付
-------
+ **ペイパル** - https://www.paypal.me/hiro20v/JPY

ライセンス
-------
著作権 &copy; [hiro20v](https://github.com/hiro20v)  
[MIT License][mit] の下で配布されます。

[MIT]: http://opensource.org/licenses/MIT
