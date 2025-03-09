# Web API 勉強用プロジェクト

## 概要
このプロジェクトは、JAX-RS を使用して Restful API を構築することを目的とした学習用 Web API です。

## 使用技術
- **言語:** Java
- **フレームワーク:** JAX-RS
- **ビルドツール:** Gradle
- **データベース:** MySQL
- **アプリケーションサーバ:** Tomcat

## 機能
- RESTful API のエンドポイントを構築
- `users` テーブルと `tasks` テーブルを使用したデータ管理
- MySQL との連携

## 環境構築
### 1. MySQL のセットアップ
#### データベースの作成
```sql
CREATE DATABASE webapi_db;
USE webapi_db;
```

#### `users` テーブルの作成
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email) VALUES
('太郎', 'taro@example.com'),
('花子', 'hanako@example.com'),
('次郎', 'jiro@example.com');
```

#### `tasks` テーブルの作成
```sql
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    status ENUM('pending', 'done') NOT NULL DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO tasks (user_id, title, status) VALUES
(1, '書類作成', 'pending'),
(1, 'ミーティング', 'done'),
(2, '資料レビュー', 'pending'),
(2, 'クライアント対応', 'done'),
(3, 'プレゼン準備', 'pending'),
(3, 'コードレビュー', 'pending'),
(1, 'メール返信', 'done'),
(2, '企画書作成', 'pending'),
(3, '社内ミーティング', 'done');
```

### 2. Tomcat のセットアップ
1. Tomcat をインストール
2. `web.xml` を適切に設定
3. `war` ファイルをデプロイ

### 3. Gradle のセットアップ
```sh
gradle build
```

## API エンドポイント

### 1. ユーザー登録
```sh
curl -X POST http://localhost:8080/api/users \
     -H "Content-Type: application/json" \
     -d '{"name": "John Doe", "email": "john.doe@example.com", "createdAt": "2025-03-10"}'
```

### 2. ユーザー一覧取得
```sh
curl -X GET http://localhost:8080/api/users
```
