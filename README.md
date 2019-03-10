
# Nexters-Amatda

## 아맞다 : https://medium.com/amatda
## 아맞다 IOS : https://github.com/Nexters/Amatda-iOS
## 아맞다 Android : https://github.com/sojeongw/amatda-android

## 디비 테이블 명세서

>### 1. 캐리어 테이블
>>#### cId (캐리어 아이디) - Primary Key
>>#### cName (캐리어 이름)
>>#### cCountry (여행국가 이름)
>>#### startDate (여행 시작날짜)
>>#### cCreated (캐리어 생성 날짜)

>### 2. 옵션 테이블
>>#### oId (옵션 아이디) - Primary Key
>>#### ocId (캐리어 아이디) - Foreign Key
>>#### oCategory (카테고리 종류)

>### 3. 준비물 테이블
>>#### pId (준비물 아이디) - Primary Key
>>#### pcId (캐리어 아이디) - Foreign Key
>>#### pColor (준비물 라벨 색상)
>>#### pCheck (준비물을 챙겼는지 확인하는 변수)
>>#### pCreated (준비물 생성시간)

>### 4. 추천 준비물 테이블
>>#### rId (추천 준비물 아이디) - Primary Key
>>#### rCategory (추천 준비물 옵션) - Foreign Key
>>#### rName (추천 준비물 이름)

>### 5. 도시 테이블
>>#### city_id (도시 아이디) - Primary Key
>>#### city_name (도시 이름)

>#### 6. 날씨 테이블
>>#### weather_id (날씨 아이디) - Primary Key
>>#### temperature_avg (평균 온도)
>>#### weather_status (날씨 상태)
>>#### city_id (도시 아이디) - Foreign Key
>>#### month (월)

## Amatda API 문서

>### 1. 캐리어 상세정보 보기 (특정 캐리어에 대한 상세정보)
>>#### http method : GET
>>#### url -> /carrier?cId = 2 (파라미터 : cId - 캐리어 아이디)

>### 2. 캐리어 생성하기
>>#### http method : POST
>>#### url -> /carrier
>>#### body에 들어갈 데이터 : cName(varchar), cCountry(varchar), startDate(datetime), category_list(array)
>>#### return 값 : cId (캐리어 아이디)

>### 3. 캐리어 수정하기
>>#### http metho : PUT
>>#### url -> /carrier
>>#### body에 들어갈 데이터 : cId(int), cName(varchar), cCountry(varchar), startDate(datetime)
>>#### return 값 : 수정된 캐리어 객체의 컬럼값

>### 4. 캐리어 삭제하기
>>#### http method : DELETE
>>#### url -> /carrier?cId=2 (파라미터 : cId - 캐리어 아이디)

>### 5. 특정 캐리어의 모든 준비물 출력
>>#### http method : GET
>>#### url -> /carrier/pack?cId=2 & sort=1 (파라미터 : cId - 캐리어 아이디, sort = 0 등록순, 1 라벨순 )

>### 6. 준비물 등록하기
>>#### http method : POST
>>#### body에 넣을 데이터 : pcId(int), pName(varchar), pColor(varchar), pCheck(varchar)
>>#### url -> /carrier/pack
>>#### return 값 : pId (준비물 아이디)

>### 7. 준비물 수정하기
>>#### http method : PUT
>>#### url -> /carrier/pack
>>#### return 값  : pId(준비물 아이디)

>### 8. 준비물 삭제하기
>>#### http method : DELETE
>>#### url -> /carrier/pack?pId=2 (파라미터 : pId - 준비물 아이디)

>### 9. 준비물 체크하기
>>#### http method : PUT
>>#### body에 넣을 데이터  : pId(int), pCheck(varchar)
>>#### url -> /carrier/pack/check
>>#### return 값 : 수정된 준비물 객체의 컬럼

>### 10.날씨 조회하기
>>#### http method : GET
>>#### url -> /weather?city_id=2&month=12 (파라미터 : city_id - 도시 아이디, month - 월)





