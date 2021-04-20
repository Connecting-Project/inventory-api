# inventory api

Project made by Hawaiian Pizza.

Spring Boot, 리엑트로 개발된 프론트로 구성된 웹 - 서버 기반 애플리케이션입니다. URL은 다음과 같습니다.

- https://inventory.hawaiian-pizza.gq/

기본 환경에서는 테스트 데이터베이스가 포함되어 있습니다. 



## 요구사항

- 제품의 정보는 다음과 같다.
  - 카테고리
    - 컴퓨터, 서버, 네트워크, 주변기기, 센서, 전기부품, 엑추에이터, 오픈소스H/W,  기타
  - 구입처
  - 제조사
  - 제품명
  - 가격
  - 수량
- 제품의 정보는 S/N 번호로 조회, 삭제, 수정이 가능하다.
  - 모든 제품은 고유한 S/N을 가진다.
  - S/N은 숫자 11자리로 이루어진다.
- 제품의 조회는 다음과 같이 구성된다.
  - 전체조회
  - 제품명 조회
  - 카테고리 조회
- 프론트 엔드에서는 S/N, QR코드 생성을 통해서 출력 기능으로 상품에 부착할 수 있어야 한다.
- 처음 접속 홈페이지에는 로그인 화면이 나타나지만, 상당 우측에는 관리자 로그인 버튼이 존재해야 한다.



### 관리자 유스케이스

- 애플리케이션 설치후 관리자 계정을 생성한다.
  - ID, PW, Email, 이름, 연락처
  - 관리자 홈페이지는 따로 존재해야 한다.
- 관리자는 사용자를 관리할 수 있다.
  - 사용자 정보 확인
    - 이름, 연락처, Email
  - 사용자 허가, 삭제 가능
  - 만약 관리자가 사용자를 삭제할 경우 기존의 대여품목은 모두 반납 처리가 되어야 한다. (경고 표시 필수)
- 관리자는 제품의 정보를 등록할 수 있다.
  - 등록절차의 경우 제품의 카테고리, 구입처, 제조사, 제품명, 가격, 수량 정보를 필수로 입력해야 한다.
    - 사진은 옵션이나, 업로드 혹은 이미지 URL 링크를 첨부하여 표시할 수 있음
  - SN정보의 경우 제품 등록시 고유한 넘버로 생성해야 하며, 프론트에서는 이를 QR코드로 표현할 수 있다.
- 관리자는 모든 물품의 정보 리스트형식으로 조회가 가능하다.
  - 물품별 조회가 가능하다 (제품의 조회는 요구사항과 같다)
  - 물품별 상세 페이지로 모든 정보를 자세히 확인할 수 있다.
- 관리자는 각 물품별 사용자가 대여한 리스트를 확인할 수 있다.
- 관리자는 특정 사용자에게 대여를 지정할 수 있다.
- 사용자 중에 관리자를 추가적으로 지정할 수 있다.
- 기 등록된 제품명이 존재하는 경우 다음과 같은 방법을 따른다.
  - 사용자가 제품명을 검색하여, 동일상품있는 경우 수량정보 업데이트
  - 사용자가 제품등록시 제품명이 존재하는경우 기 등록된 상품이 존재한다는 화면이 나오고, 수량 추가할지, 아님 제품명 변경할지 묻는 화면으로 표시



## Installation

Follow these installation instructions:

본 프로그램을 온프레미스 환경에서 사용하기 위해서는 접속정보를 수정해야 합니다.

- **src/main/resources/application.properties**

  ```java
  spring.datasource.driverClassName=org.mariadb.jdbc.Driver
  spring.datasource.url=jdbc:mariadb://$(IP-ADDRESS)
  spring.datasource.username=
  spring.datasource.password=
  spring.mail.host=smtp.gmail.com
  spring.mail.password=
  spring.mail.username=
  spring.mail.port=587
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
  ```

  

### Manually

```bash
$ git clone https://github.com/Connecting-Project/inventory-api.git
$ cd inventory-api
$ gradle clean && gradle build 
$ java -jar ./build/lib/login-*-SNAPSHOT.jar
```



### Docker

```bash
$ docker run -p 8080:8080 -d jusk2/hawaiian-inventory-api:tagname
```

#### Build 

```bash
$ docker buildx build --platform linux/amd64 -t hawaiian-inventory-api .
```

#### docker-compose.yml 

```yaml
version: '3.7'
services:
  db:
    image: mariadb
    ports:
      - 50000:3306
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: "xSv4jtkPpMF5Tm"
      MYSQL_DATABASE : "inventory"
      MYSQL_ALLOW_EMPTY_PASSWORD: "yes"
    volumes:
      - ./inventory.sql/:/docker-entrypoint-initdb.d/
      - ./mysql/:/var/lib/mysql
      
  inventory:
    image: hawaiian-inventory-api
    ports:
      - 8080:8080
    restart: always
```



## Tech Stack

- Spring Boot
- MariaDB
- Docker

## Developer

- 김수현 - 벡엔드
- 조용우 - 프론트 엔드
- 이성원 - 인프라

## Contact

토이 프로젝트에 있어서 궁금한 점이 있거나 개선하고 싶은 점 등 여러분들의 다양한 의견을 기다립니다.

- [seongwon@edu.hanbat.ac.kr](mailto:seongwon@edu.hanbat.ac.kr)
- [dpfmxlfls95@naver.com](mailto:dpfmxlfls95@naver.com)
- [kimsoo5133@gmail.com](mailto:kimsoo5133@gmail.com)

## License

Distributed under the MIT License. See `LICENSE` for more information.