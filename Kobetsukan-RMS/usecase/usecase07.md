# ユースケース 7:  シフトの登録

## 概要
講師がシフトを登録する

## アクター
- 講師

## 事前条件
- 講師がシフト登録画面を表示している

## 事後条件
- その講師に新たなシフトが登録される

## トリガ―
- 講師がシフト登録画面で「シフトを登録」ボタンを押す

## 基本フロー
1. 講師が「シフト登録」ボタンを押す
2. システムは登録フォーム画面を表示する
3. 講師は登録したい日にちと時刻を入力し「シフトを登録」ボタンを押す
4. システムは入力された日時が既に登録されていないかチェックする
5. チェックOKならシステムは入力情報の確認画面を表示する
6. 講師はOKボタンを押す
7. システムは，入力された情報に基づいて，シフト情報を作成・保存し，登録完了画面を表示する

## 代替フロー
### 代替フロー1
- 4a.1  基本フロー4において，他のシフトと重なっている場合は，エラーを表示し，2に戻る
### 代替フロー2
- 6a.1  基本フロー6において，講師がキャンセルした場合はその旨を表示し，2に戻る
