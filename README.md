# 대전 2반 2조 - 웹 디자인

## 프로젝트 소개


> 프로젝트 명

- 내 안의 트롯

> 프로젝트 주소

- https://i3b202.p.ssafy.io

> 개요

    - 5060 세대의 애환을 품은 고품격 트로트 통합 정보 SNS '내 안의 트롯'
    - 5060 세대의 sns 사용 증가로 인한 정보 요약 큐레이션 서비스 제공  



> 기술 스택

    - Front-end
      - Vue.js Framework
      - Vuetify
    
    - Back-end
      - JAVA 
      - Spring Boot
      - Maria DB
    
    - Server
      - AWS EC2
      - Docker



## 팀원 정보 및 개발 분담 내역

> 팀명

    - 덕명동 파워레인저

<img src="./S(owo)Z/dmp.png" alt="덕명동 파워레인저의 힘을 보여ju겠다" style="zoom:15%;" />

> Front-end

    - 김도연 (핑크) : Front-end 개발 팀장
    - 김소영 (옐로) : Front-end 개발, UX/UI Design



>  Back-end

    - 장한별 (레드, 팀장) : DBA, Server 배포, Back-end 개발
    - 이진용 (블랙) : Server 배포, Back-end 개발
    - 임지민 (블루) : Back-end 개발 팀장



## 기능 구현


#### 가수 별 영상 및 기사 실시간 확인 기능

    - 가수별 동영상 및 뉴스 기사 NAVER TV Crawling, Youtube API 사용하여 DB에 저장
    - 가수별 스케줄 Crawling 후 DB 저장 



#### 트로트 프로그램 일별 편성표

    - 일별로 트로트 프로그램 편성표 Crawling하여 DB에 저장
    - 편성표에 나오는 프로그램을 시간, 프로그램순으로 필터 기능
    - Kakao API 알람 기능


​      

#### 가수 및 프로그램 검색 기능

    - 편성표에 나온 프로그램 실시간 검색 기능
    - 가수 별 검색 기능, 검색 후 디테일 페이지로 이동 기능
    - Paging 처리(Infinite Scrolling)



#### 가수 디테일 페이지

    - 가수 디테일에서 해당 가수의 영상 및 뉴스 확인 기능
    - Following
    - Paging 처리(Infinite Scrolling)



#### 투표 기능

    - 이번주 Top3, 지난주 Top3
    - 전국 순위, 전체 순위 Map Data Visualization
    - 투표하기 기능



#### 커뮤니티 페이지

    - 커뮤니티 게시글 리스트 , 게시글 입력 가능
    - 좋아요, 댓글, Following 기능
    - Paging 처리(Infinite Scrolling)



#### 소셜 로그인

    - Kakao API

