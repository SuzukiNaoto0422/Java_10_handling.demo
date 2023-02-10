FORMAT: 1A
# ユーザー管理API仕様書

# Group Users

## POST /users
ユーザーを登録。
以下のパラメータをJSON形式で送信。

+ name (string, required) - ユーザー名。アルファベットでの入力のみ可。
+ ※idはオートインクリメントされ自動で入力。

+ Request (application/json)

        {
          "name": "suzuki"
        }

+ Response 200 (text/plain;charset=UTF-8)
    + Body
      name successfully created

+ Response 404
    + Body
      {
        "message":"Name must not be empty or null or alphabet."
      }

## GET /users/{id}

idに指定したユーザーの情報を取得。

+ Parameters
    + id: 1 (number) - ユーザーのID


+ Response 200 (application/json)
  {
  "id": 1,
  "name": "suzuki"
  }

+ Response 404 (application/json)
  {
  "message": "resource not found"
  }

## DELETE /users/{id}

idに指定したユーザーの情報を削除。

+ Parameters

    + id: 1 (number) 

+ Response 200 (application/json)
  {
  "message": "user successfully deleted"
  }

+ Response 404 (application/json)
  {
  "message": "user not found"
  }

## PATCH /users/{id}

idに指定したユーザーの情報を更新。

+ Parameters

  + id: 1 (number)

+ Response 200 (application/json)
  {
  "message": "user successfully updated:{更新前のname}"
  }

+ Response 404 (application/json)
  {
  "message": "user not found"
  }
