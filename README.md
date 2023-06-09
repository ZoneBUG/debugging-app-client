# 디버깅 : Client
## 1. Environment
- Android Studio
- Kotlin
- Retroift
- Firebase Storage

<br>

## 2. 주요 기능
#### (1) 회원가입 및 로그인
- Preference를 이용한 JWT access, refresh 토큰 저장

#### (2) 우리 가게 보고서
- 원하는 주기의 매장 보고서 생성 기능
- 해충 이동 경로 이미지, 해충명, 해충 특징, 방역 체크리스트, 추천 약품 제공
- RecyclerView, ViewPager2를 이용한 화면 구성

#### (3) 이 벌레 뭐지?
- 사진 첨부 후, 해충을 검색하는 기능
- Fragment를 이용한 화면 전환
- Multipart를 통한 이미지 파일 HTTP Request 전송

#### (4) Zone-Talk
- '오늘의 Issue', '궁금해요', '공유해요' 게시판 기능
- 댓글 및 대댓글 기능
- RecyclerView를 이용한 게시글 목록 제공

<br>


## 3. Project Structure
```bash
app
└── src/main
    ├── java/com/zonebug/debugging
    │   ├── DTO
    │   ├── activity
    │   ├── retrofit
    │   │   ├── model
    │   │   └── web
    │   ├── PreferenceUtil.ko
    │   └── App.kt
    └── res
        ├── drawable
        ├── font
        └── layout
``` 
