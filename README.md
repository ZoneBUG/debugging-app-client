<img src="https://github.com/ZoneBUG/debugging-app-server/assets/97878992/909a448d-7314-442c-8cd6-332a7cdb5e86" width=100%>

# 디버깅 ; DEBUGGING <img src="https://github.com/ZoneBUG/debugging-app-server/assets/97878992/67ec499f-7947-4499-a65a-dff9d81b8036" align=left width=100>

> 자영업자들을 위한 맞춤형 방역 솔루션 서비스, DEBUG Your Place! 

<br>

## Skills
<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=andriod&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<br>

## Main Feature
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


## Project Structure
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
