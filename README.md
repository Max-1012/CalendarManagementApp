# 🗓️ 일정 관리 앱

## 📌 프로젝트 정보
| 항목      | 내용           |
|---------|--------------|
| 이름      | 박소영          |
| 프로젝트명   | 일정 관리 앱      |
| 버전      | v1.0.0       |
| base URL | `http://localhost:8080/` |

## ✏️ 프로그램 소개
일정 관리앱은 나만의 일정을 등록하여 사용하는 앱 입니다.
댓글 기능을 통해 자유롭게 의견을 나눌 수 있습니다.

## 주요 기능
### 👥 사용자
- 일정 등록 - 제목, 내용, 작성자명, 비밀번호 등록 필요
- 일정 수정/삭제 - 비밀번호 일치 필요

### 📘 일정 조회
- 작성자별 일정 전체 조회 - 작성자명 입력 필요
  - 일정 정보 및 댓글 조회
- 모든 일정 전체 조회

### 🗣️댓글
- 댓글 등록
- 일정 단건 조회시 댓글 조회 가능

### ⚠️ 제한
- 일정 제목
    - 최대 30자 이내
    - 필수 입력 값
- 일정 내용
    - 최대 200자 이내
    - 필수 입력 값
- 댓글 내용
    - 최대 100자 이내
    - 필수 입력 값
- 비밀번호, 작성자명
    - 필수 입력 값

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

# ERD
<img alt="Image" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FbuPlLP%2FdJMcacorJjN%2FAAAAAAAAAAAAAAAAAAAAAKSeAZir3T61HkjWIMAAPBG39z_JqlXKBEho4jWpx3_J%2Fimg.png%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1772290799%26allow_ip%3D%26allow_referer%3D%26signature%3DzmPEBQBepRD7eQDxxRgf8u282pc%253D" />

# 일정 관리 API 명세서
## 1. 일정 생성
**POST** `/schedules`

### Example
#### Request Body
```json
{
  "title" : "나의 졸업식",
  "content" : "졸업식",
  "author" : "박소영",
  "password" : "soyeong"
}
```
#### Response (201 Created)
```json
{
  "id": 1,
  "title": "나의 졸업식",
  "content": "졸업식",
  "author": "박소영",
  "createdDate": "2026-02-05T13:29:20.2080736",
  "modifiedDate": "2026-02-05T13:29:20.2080736"
}
```

## 2. 일정 전체 조회
**GET** `/schedules`

### Example 1. 작성자를 쿼리 파라미터로 넘겨주지 않은 경우
#### Request
**GET** `/schedules`
#### Response (200 OK)
```json
[
  {
    "id": 1,
    "title": "나의 졸업식",
    "content": "졸업식",
    "author": "박소영",
    "createdDate": "2026-02-05T13:29:20.208074",
    "modifiedDate": "2026-02-05T13:29:20.208074"
  },
  {
    "id": 2,
    "title": "홍길동의 졸업식",
    "content": "졸업식",
    "author": "홍길동",
    "createdDate": "2026-02-05T13:30:32.330002",
    "modifiedDate": "2026-02-05T13:30:32.330002"
  }
]
```
### Example 2. 작성자를 쿼리 파라미터로 넘겨준 경우
#### Request
**GET** `/schedules/author=박소영`
#### Response (200 OK)

```json
[
    {
        "id": 1,
        "title": "나의 졸업식",
        "content": "졸업식",
        "author": "박소영",
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
  "author": "박소영",
  "createdDate": "2026-02-05T13:29:20.208074",
  "modifiedDate": "2026-02-05T13:29:20.208074",
  "commentList": []
}
```
### Example 1. 댓글이 있는 경우
#### Request
**GET** `/schedules/1`
#### Response (200 OK)
```json
{
  "id": 1,
  "title": "나의 졸업식",
  "content": "졸업식",
  "author": "박소영",
  "createdDate": "2026-02-05T13:29:20.208074",
  "modifiedDate": "2026-02-05T13:29:20.208074",
  "commentList": [
    {
      "id": 1,
      "content": "댓글내용",
      "author": "박소영",
      "modifiedDate": "2026-02-05T13:32:30.618754",
      "CreatedDate": "2026-02-05T13:32:30.618754"
    }
  ]
}
```

## 4. 일정 수정
**PUT** `/schedules/{scheduleId}?author={newAuthor}&title={newTitle}&password={password}`
- scheduleId, password : 필수값
- author, title : 필수값은 아니지만 둘 다 없는 경우 수정할 항목이 없으므로 수정 처리 X

### Example
#### Request
**GET** `/schedules/1?password=soyeong&author=newAuthor&title=newTitle`
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
**DELETE** `/schedules/{scheduleId}?password={password}`
- scheduleId, password : 필수값

### Example
#### Request
**DELETE** `/schedules/2?password=12345678`

#### Response (204 No Content)

## 6.댓글 생성 기능
**POST** `/schedules/{{scheduleId}}/comments`

### Example
**POST** `/schedules/1/comments`
#### Request Body
```json
{
  "content":"댓글내용",
  "author" : "박소영",
  "password" : "soyeong"
}
```

#### Response (201 Created)
```json
{
  "id": 1,
  "scheduleId": 1,
  "content": "댓글내용",
  "author": "박소영",
  "createdDate": "2026-02-05T13:32:30.6187536",
  "modifiedDate": "2026-02-05T13:32:30.6187536"
}
```
