GUHU
==============
GUHU共有リポジトリ  

## チーム紹介 
--------------  
>チーム名:GUHU  
>チーム名の由来:メンバーの頭文字をとった形,
GAFAににてるから  
>チームの目標:みんなで協力して単位をもぎ取ろう
  
## 個別館予約システムの説明書
------------
>デプロイはしておらず、ローカルで閲覧することを前提としています。
### 管理者機能
http://localhost:2290/admin/register
にてユーザの登録・削除、また登録したユーザー一覧を閲覧することができる。ユーザはユーザーID,名前,先生かどうかの3つの要素を持っている。
 - ユーザーIDには、4文字以上16文字未満のアルファベット小文字，数字，ハイフン，アンダーバーのみ使用できる
 - 氏名は最大32文字で、半角・全角が使用できる
 - チェックボックスにチェックを入れ登録すると、講師として登録され、チェックを入れずに登録すると、生徒として登録される

### ユーザ機能
1.http://localhost:2290/
にて登録済みのユーザIDを入力してログインすると、現在のそのユーザーの講習会予約リストが表示される。  
2.生徒が予約ボタンを押すと講師一覧画面が表示される。  
3.その講師一覧から受講したい講師名をクリックすることでその講師のシフト一覧が表示される。**(ここで1コマに対し生徒は2人まで受講可能なので、2人を超えたシフトは表示されないようになっている)**  
4.受講したいコマを選択することで生徒と講師間で予約が成立する。
 - 今回、講師のシフト登録に関してはダミーの講師とそのシフトをデータベースに登録し、実現している。

## サーバポート番号
```
server.port=2290
```

## MySQLデータベース接続設定
```
spring.datasource.url=jdbc:mysql://localhost:3306/kobetsukan?serverTimezone=Asia/Tokyo
spring.datasource.username=kobetsukan
spring.datasource.password=tokuronI
```
