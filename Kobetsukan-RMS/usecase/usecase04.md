# ユースケース 3： 閉校日を登録する

## 概要
管理者が，塾の閉校日をシステムに登録する

## アクター
- 管理者

## 事前条件
- 管理者がシステムにログインしている
- その日が開校日に指定されている

## 事後条件
- 指定した日が閉校日となっている

## トリガ―
- 管理者が，画面のカレンダーの日付を選択する

## 基本フロー
1. 管理者が，画面のカレンダーの日付を選択する
1. システムは，選択された日の情報を画面に表示する
1. 管理者は，画面の「閉校日にする」ボタンを押す
1. システムは，選択された日を閉校日に変更する
1. システムは，閉校日に変更したことを伝える画面を表示する

## 代替フロー
### 代替フロー1
- 2a.1 選択された日がすでに閉校日である場合は，「閉校日にする」ボタンが表示されず，代わりに，「開校日にする」ボタンが表示される．

## GUI紙芝居
### ○○画面
<img src="gamen1.png">