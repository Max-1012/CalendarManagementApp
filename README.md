# 🗓️ 일정 관리 앱
# ERD
<img alt="Image" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FFJNAc%2FdJMcaaRMQRY%2FAAAAAAAAAAAAAAAAAAAAACecGIjuxfy3tU0gOdI00AcaLBMaZqGWgNXKnXtDFpE3%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1772290799%26allow_ip%3D%26allow_referer%3D%26signature%3DXfSO1EkhrbJ27kbkjg8Q%252B4x%252FvVU%253D" />

## 📌 프로젝트 정보
| 항목      | 내용                       |
|---------|--------------------------|
| 이름      | 박소영                      |
| 프로젝트명   | 일정 관리 앱                  |
| 버전      | v1.0.1                   |
| base URL | `http://localhost:8080/` |

## ✏️ 프로그램 소개
일정 관리앱은 나만의 일정을 등록하여 사용하는 앱 입니다.
댓글 기능을 통해 자유롭게 의견을 나눌 수 있습니다.

## 주요 기능
- 회원 서비스
  - 회원 가입, 회원 탈퇴, 로그인, 로그아웃, 회원 전체 조회, 회원 단건 조회, 회원 이름 수정
- 일정 서비스
  - 일정 생성, 일정 전체 조회, 일정 단건 조회, 일정 수정(제목, 내용), 일정 삭제
- 댓글 서비스
  - 댓글 생성, 댓글 단건 조회, 일정 조회 시 해당 일정 댓글 전제 조회, 댓글 업데이트(댓글 내용), 댓글 삭제

### ⚠️ 제한
- 회원
  - 이름 : 필수, 중복 불가
  - email : 필수, 중복 불가, 최대 100자 이내
  - 비밀번호 : 필수, 최대 100자 이내(암호화)
  
- 일정 
  - 일정 생성, 수정, 삭제 시 로그인 필수
    - 제목 : 필수, 최대 30자 이내
    - 내용 : 필수, 최대 100자 이내
- 댓글
  - 댓글 생성, 수정, 삭제 시 로그인 필수
    - 내용 : 필수, 최대 100자 이내

## 🔧 기술 스택
| 구분 | 기술 | 버전 |
|-----|-----|-----|
| Language | Java | 17 |
| Framework | Spring Boot | 4.0.2 |
| Build Tool | Gradle | 9.3.0 |
| Database | MySQL | 8.4.8 |

## ⚙️ 설치 및 실행
### 1. 프로젝트 클론

```bash
git clone https://github.com/Max-1012/CalendarManagementApp
```
### 2.데이터베이스 설정
```bash
// MySQL에서 데이터베이스 생성
// 애플리케이션 실행 시 JPA(Hibernate)가 테이블을 자동 생성합니다.
CREATE DATABASE calendarManagement;
```
### 3.애플리케이션 설정
```bash
src/main/resources/application.properties 파일에서 데이터베이스 정보를 설정.

spring.datasource.url=jdbc:mysql://localhost:3306/calendar
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```


# 일정 관리 API 명세서
# Schedule 서비스
## 1. 일정 생성
- 로그인 필수

**POST** `/schedules`

### Example
#### Request Body
```json
{
  "title" : "박소영의 졸업식",
  "content" : "졸업식"
}
```
#### Response (201 Created)
```json
{
  "id": 1,
  "title": "박소영의 졸업식",
  "content": "졸업식",
  "createdDate": "2026-02-05T13:29:20.2080736",
  "modifiedDate": "2026-02-05T13:29:20.2080736"
}
```

## 2. 일정 전체 조회
**GET** `/schedules`

### Example 1. 유저명을 쿼리 파라미터로 넘겨주지 않은 경우
#### Request
**GET** `/schedules`
#### Response (200 OK)
```json
[
  {
    "id": 1,
    "title": "나의 졸업식",
    "content": "졸업식",
    "userName": "박소영",
    "createdDate": "2026-02-05T13:29:20.208074",
    "modifiedDate": "2026-02-05T13:29:20.208074"
  },
  {
    "id": 2,
    "title": "홍길동의 졸업식",
    "content": "졸업식",
    "userName": "홍길동",
    "createdDate": "2026-02-05T13:30:32.330002",
    "modifiedDate": "2026-02-05T13:30:32.330002"
  }
]
```
### Example 2. 작성자를 쿼리 파라미터로 넘겨준 경우
#### Request
**GET** `/schedules/userName=박소영`
#### Response (200 OK)

```json
[
    {
        "id": 1,
        "title": "나의 졸업식",
        "content": "졸업식",
        "userName": "박소영",
        "createdDate": "2026-02-05T13:29:20.208074",
        "modifiedDate": "2026-02-05T13:29:20.208074"
    }
]
```


## 3. 일정 단건 조회
**GET** `/schedules/{scheduleId}`

### Example 1. 댓글이 없는 경우
#### Request
**GET** `/schedules/1`
#### Response (200 OK)
```json
{
  "id": 1,
  "title": "나의 졸업식",
  "content": "졸업식",
  "userName": "박소영",
  "createdDate": "2026-02-05T13:29:20.208074",
  "modifiedDate": "2026-02-05T13:29:20.208074",
  "commentList": []
}
```
### Example 2. 댓글이 있는 경우
#### Request
**GET** `/schedules/1`
#### Response (200 OK)
```json
{
  "id": 1,
  "title": "나의 졸업식",
  "content": "졸업식",
  "userName": "박소영",
  "createdDate": "2026-02-05T13:29:20.208074",
  "modifiedDate": "2026-02-05T13:29:20.208074",
  "commentList": [
    {
      "id": 1,
      "content": "댓글내용",
      "userName": "박소영",
      "modifiedDate": "2026-02-05T13:32:30.618754",
      "CreatedDate": "2026-02-05T13:32:30.618754"
    }
  ]
}
```

## 4. 일정 수정
- 로그인 필수

**PATCH** `/schedules/{scheduleId}`

### Example
#### Request
**PATCH** `/schedules/1`
#### Response (200 OK)
```json
{
  "id": 1,
  "title": "newTitle",
  "content": "졸업식",
  "author": "newAuthor",
  "createdDate": "2026-02-05T13:29:20.208074",
  "modifiedDate": "2026-02-05T13:33:47.6943046"
}
```

## 5. 일정 삭제
- 로그인 필수

**DELETE** `/schedules/{scheduleId}`

### Example
#### Request
**DELETE** `/schedules/2`

#### Response (204 No Content)

# CommentService

## 1.댓글 생성 기능
- 로그인 필수

**POST** `/schedules/{scheduleId}/comments`

### Example
**POST** `/schedules/1/comments`
#### Request Body
```json
{
  "content":"댓글내용"
}
```

#### Response (201 Created)
```json
{
  "id": 1,
  "scheduleId": 1,
  "content": "댓글내용",
  "userName": "박소영",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```
## 2.댓글 단건 조회 기능
**GET** `/schedules/{scheduleId}/comments/{commentId}`

### Example
#### Request
**GET** `/schedules/1/comments/1`

#### Response (200 OK)
```json
{
  "id": 1,
  "scheduleId": 1,
  "content": "댓글내용",
  "userName": "박소영",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```
## 3.댓글 업데이트
- 로그인 필수

**PATCH** `/schedules/{scheduleId}/comments/{commentId}`

### Example
#### Request
**PATCH** `/schedules/1/comments/1`

```json
{
  "content":"댓글 수정"
}
```
#### Response (200 OK)
```json
{
  "id": 1,
  "scheduleId": 1,
  "content": "댓글 수정",
  "userName": "박소영",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```
## 4. 댓글 삭제
- 로그인 필수
  **DELETE** `/schedules/{scheduleId}/comments/{commentId}`

### Example
#### Request
**DELETE** `/schedules/1/comments/1`

#### Response (204 No Content)


# UserService

## 1.유저 회원가입 기능

**POST** `/users/signUp`

### Example
**POST** `/users/signUp`
#### Request Body
```json
{
  "userName": "박소영",
  "email": "thdud001012@gmail.com",
  "password": "soyoung"
}
```
#### Response (201 Created)
```json
{
  "id": 1,
  "userName": "박소영",
  "email": "thdud001012@gmail.com",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```

## 2.유저 로그인 기능

**POST** `/users/login`

### Example
**POST** `/users/login`

#### Request Body
```json
{
  "email": "thdud001012@gmail.com",
  "password": "soyoung"
}
```

#### Response (200 OK)
```json
{
  "id": 1,
  "userName": "박소영",
  "email": "thdud001012@gmail.com"
}
```

## 3. 유저 로그아웃 기능
- 로그인 필수

**POST** `/users/logout`

### Example
#### Request
**POST** `/users/logout`

#### Response (204 No Content)


## 4. 유저 전체 조회
**GET** `/users`
### Example
#### Request
**GET** `/users`

#### Response (200 OK)
```json
[
  {
    "id": 1,
    "userName": "박소영",
    "email" : "thdud001012@gmail.com",
    "createdDate": "2026-02-05T13:32:30.6187536",
    "modifiedDate": "2026-02-05T13:32:30.6187536"
  },
  {
    "id": 2,
    "userName": "홍길동",
    "email" : "gildong@gmail.com",
    "createdDate": "2026-02-05T13:32:30.6187536",
    "modifiedDate": "2026-02-05T13:32:30.6187536"
  }
]

```

## 5. 유저 단건 조회
**GET** `/users/{userId}`
### Example
#### Request
**GET** `/users/{userId}`

#### Response (200 OK)
```json
{
  "id": 1,
  "userName": "박소영",
  "email" : "thdud001012@gmail.com",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```


## 6. 유저 탈퇴
- 로그인 필수

**DELETE** `/users/withdraw`

### Example
#### Request
**DELETE** `/users/withdraw`

#### Response (204 No Content)
