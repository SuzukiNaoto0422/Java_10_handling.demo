## APIの概要  

idとnameの情報によりユーザーを管理する機能を持ったAPIを作成しました。  
以下API仕様書になります。  
https://suzukinaoto0422.github.io/Java_10_handling.demo_API-specification-document/api.html  

## 起動手順(操作手順)  
```  
docker compose up  
```  
で起動  
```  
docker compose down  
``` 
で停止  

各curlコマンド
```  
GET : curl -X GET http://localhost:8080/users/{id}  

POST : curl -X POST -H "Content-Type: application/json" -d '{"name":"{name}", "age":{age}}' http://localhost:8080/users  

PATCH : curl -X PATCH -H "Content-Type: application/json" -d '{"name":"{更新後のname}"}' http://localhost:8080/users/{id}  

DELETE : curl -X DELETE http://localhost:8080/users/{id}  
```  

## データベース定義

|カラム名（論理名）|カラム名（物理名）|型・桁|Nullable|その他コメント|
|---|---|---|---|---|
|ID|id|int|NO|primary key, auto_increment|
|名前|name|varchar(20)|NO|  
|年齢|age|int|NO|
