# sk_miniproject1
sk_miniproject1-backend

Spring Boot Project
1. 컨트롤러 계층 (Controller)
   사용자의 요청을 받고 응답을 반환하는 역할.
1.	QuestionController.java
      o	랜덤 문제 데이터를 반환.
2.	ResponseController.java
      o	사용자의 응답 데이터를 저장.
      o	사용자별 또는 문제별 응답 데이터를 조회.
3.	RankingController.java
      o	사용자 점수를 업데이트.
      o	상위 10명의 랭킹을 반환.
________________________________________
2. 서비스 계층 (Service)
   비즈니스 로직을 처리하고 레포지토리와 상호작용.
1.	QuestionService.java
      o	랜덤 문제 데이터를 제공.
      o	레포지토리를 호출하여 문제 데이터를 가져옴.
2.	ResponseService.java
      o	사용자 응답 데이터를 저장하고 정답 여부를 확인.
      o	특정 사용자의 모든 응답 또는 특정 문제에 대한 응답 조회.
3.	RankingService.java
      o	사용자 점수를 업데이트.
      o	상위 10명의 랭킹 데이터를 반환.

________________________________________
3. 레포지토리 계층 (Repository)
   데이터베이스와 직접 상호작용하며 데이터를 저장, 조회, 수정, 삭제.
1.	QuestionRepository.java
      o	랜덤 문제 데이터를 가져오는 메서드 포함 (findRandomQuestions).
2.	ResponseRepository.java
      o	사용자별 응답 데이터를 조회.
      o	특정 문제에 대한 사용자 응답 데이터를 조회.
3.	RankingRepository.java
      o	상위 10명의 랭킹 데이터를 조회.
________________________________________

4. 모델 계층 (Model)
1.	Question.java
      o	Questions 테이블과 매핑.
      o	문제 ID, 사자성어, 설명, 정답, 선택지 데이터 포함.
2.	Response.java
      o	Responses 테이블과 매핑.
      o	사용자 ID, 문제 ID, 정답 여부, 응답 시간 포함.
3.	Ranking.java
      o	Rankings 테이블과 매핑.
      o	사용자 ID, 총 점수, 마지막 업데이트 시간 포함.


________________________________________
5. 설정 계층 (Config)
   프로젝트 전반의 설정을 관리.
1.	WebConfig.java
      o	CORS 정책을 설정하여 React 같은 프론트엔드와 통신 가능하게 함.
2.	GlobalExceptionHandler.java
      o	전역 예외를 처리하여 사용자에게 일관된 오류 메시지를 반환.
________________________________________
6. 메인 애플리케이션 클래스
   QuizAppApplication.java
   •	Spring Boot 애플리케이션의 진입점.
   •	main 메서드에서 애플리케이션 실행.
________________________________________
요약
1.	컨트롤러 계층:
      o	사용자 요청을 받고 응답 반환.
2.	서비스 계층:
      o	비즈니스 로직 처리.
3.	리포지토리 계층:
      o	데이터베이스와 상호작용.
4.	모델 계층:
      o	데이터베이스와 매핑되는 클래스 정의.
5.	설정 계층:
      o	프로젝트의 설정과 정책 관리.

________________________________________________________________________________

DataBase
테이블 구조
1. Questions 테이블
   사자성어 퀴즈의 문제 데이터를 저장하는 테이블입니다.
   테이블 구조:
   컬럼 이름	데이터 타입	설명
   question_id	BIGINT	문제 ID (Primary Key).
   phrase	VARCHAR(100)	사자성어.
   description	TEXT	사자성어에 대한 설명.
   correct_answer	VARCHAR(100)	정답.
   choices	JSON	보기 목록 (JSON 형식).
   created_at	TIMESTAMP	문제 생성 시간.
________________________________________
2. Responses 테이블
   사용자가 퀴즈에 응답한 데이터를 저장하는 테이블입니다.
   테이블 구조:
   컬럼 이름	데이터 타입	설명
   response_id	BIGINT	응답 ID (Primary Key).
   user_id	VARCHAR(50)	사용자 ID.
   question_id	BIGINT	문제 ID (Questions 테이블 참조).
   user_answer	VARCHAR(100)	사용자가 제출한 답변.
   is_correct	BOOLEAN	정답 여부 (TRUE: 정답, FALSE: 오답).
   answered_at	TIMESTAMP	응답 제출 시간.
3. Rankings 테이블
   사용자의 점수와 랭킹 정보를 저장하는 테이블입니다.
   테이블 구조:
   컬럼 이름	데이터 타입	설명
   user_id	VARCHAR(50)	사용자 ID (Primary Key).
   total_score	INT	사용자 총 점수.
   last_updated	TIMESTAMP	점수 마지막 업데이트 시간.
   예제 데이터:
   user_id	total_score	last_updated
   user123	85	2025-01-03 10:30:00
   user456	45	2025-01-03 10:35:00
________________________________________
테이블 간 관계
1. Questions ↔ Responses
   •	Responses.question_id는 Questions.question_id를 참조합니다.
   •	문제와 응답 간의 관계를 나타냅니다.
2. Rankings ↔ Responses
   •	Rankings.user_id는 Responses.user_id와 연결됩니다.
   •	사용자의 응답 데이터를 기반으로 점수를 계산하여 랭킹에 반영합니다.
________________________________________
데이터 흐름
1.	문제 데이터:
      o	Questions 테이블에 사자성어 문제 데이터가 저장.
      o	JSON 형태의 보기(choices) 포함.
2.	사용자 응답:
      o	사용자가 퀴즈에 응답하면 Responses 테이블에 저장.
      o	정답 여부(is_correct)는 응답 제출 시 자동으로 계산.
3.	점수 업데이트:
      o	사용자가 응답을 제출하면 점수가 계산되어 Rankings 테이블에 저장 또는 업데이트.
      o	기존 사용자는 점수 누적, 새로운 사용자는 점수 생성.
4.	랭킹 조회:
      o	Rankings 테이블에서 총 점수를 기준으로 상위 10명을 반환.

