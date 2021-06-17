# SIA_Backend_Project

먼저 docker-compose을 실행시킵니다.

## 1. docker-compose.yml git clone
```cmd
$ git clone https://github.com/wisoftMY/SIA_Docker.git
$ cd SIA_Docker
$ docker-compose up
```

## 2. API 실행방법
### 행정지역의 이름, 전체 지리 정보를 저장합니다.

**[POST] http://{ip address}:8082/regions<br>**
**Content-Type: application/json<br>**
**Accept-Charset: utf-8**

* 요청 형식
```json
{
  "name": "서울시",
  "area": [
    {
      "x": 126.835,
      "y": 37.688
    },
    {
      "x": 127.155,
      "y": 37.702
    },
    {
      "x": 127.184,
      "y": 37.474
    },
    {
      "x": 126.821,
      "y": 37.454
    },
    {
      "x": 126.835,
      "y": 37.688
    }
  ]
}
```
* 응답 형식
```json
{
   "id": 1
}
```

### 관심 지역의 이름, 전체 지리 정보를 저장합니다.
**[POST] http://{ip address}:8082/aois<br>**
**Content-Type: application/json<br>**
**Accept-Charset: utf-8**

* 요청 형식
```json
{
  "name": "북한산",
  "area": [
    {
      "x": 127.02,
      "y": 37.742
    },
    {
      "x": 127.023,
      "y": 37.664
    },
    {
      "x": 126.945,
      "y": 37.605
    },
    {
      "x": 126.962,
      "y": 37.692
    },
    {
      "x": 127.02,
      "y": 37.742
    }
  ]
}
```
* 응답 형식
```json
{
   "id": 1
}
```
### 행정지역에 지리적으로 포함되는 관심지역을 조회합니다.
**GET http://{ip address}:8082/regions/1/aois/intersects<br>**
**Accept: application/json**

* 응답 형식
```json 
{
  "aois": [
    {
      "id": 1,
      "name": "북한산",
      "area": [
        {
          "x": 127.02,
          "y": 37.742
        },
        {
          "x": 127.023,
          "y": 37.664
        },
        {
          "x": 126.945,
          "y": 37.605
        },
        {
          "x": 126.962,
          "y": 37.692
        },
        {
          "x": 127.02,
          "y": 37.742
        }
      ]
    },
    {
      "id": 2,
      "name": "관악산",
      "area": [
        {
          "x": 126.985,
          "y": 37.464
        },
        {
          "x": 126.98,
          "y": 37.429
        },
        {
          "x": 126.933,
          "y": 37.406
        },
        {
          "x": 126.91,
          "y": 37.432
        },
        {
          "x": 126.931,
          "y": 37.456
        },
        {
          "x": 126.985,
          "y": 37.464
        }
      ]
    }
  ]
}
```
### 특정 좌표에 가장 가까운 관심지역을 조회합니다.
**[GET] http://{ip address}/aois?lat={lat}&long={long}<br>**
**Accept: application/json**

* 응답 형식
```json
{
  "aois": {
    "id": 1,
    "name": "북한산"
  }
}
```

테스트 코드는 미완성 상태입니다. 죄송합니다.
