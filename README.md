# Чат-бот с ИИ-ассистентом (OpenAI)

## Демо
http://45.9.41.17:8080

## Использование
1. клонируйте код.
2. установите свой chatgpt.openaiapikey в «application.yml».
3. запустите его, а затем вы сможете общаться с chatgpt с помощью этого API.

+ запрос
```shell
curl --location --request GET '127.0.0.1:8080/send?message=привет'
```

+ ответ
```json
{
  "code": 200,
  "message": "success",
  "data": "\n\nЗдравствуйте! Чем я могу вам помочь?"
}
```
