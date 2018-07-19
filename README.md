# FCMTestApp
<div>
  <h3><label>* FCM 참조 사이트</label></h3>
  <li>FCM 프로젝트 콘솔 : https://console.firebase.google.com/</li>
  <li>FCM 문서 : https://firebase.google.com/docs/guides/?authuser=0</li>
  <li>FCM & Spring Boot : http://javasampleapproach.com/spring-framework/spring-boot/firebase-cloud-messaging-server-spring-to-push-notification-example-spring-boot </li>
</div>
<br>
<div>
  <h3><label>* FCM Token 저장</label></h3>
  <li>1. 처음 App을 설치 시 토큰값 부여(처음 실행 시 에만 부여)</li>
  <li>2. 부여된 토큰값을 서버로 전송</li>
  <li>3. 전송된 토큰값을 DB에 저장</li>
  <li>4. App 재접속 시 토큰값 유효성 비교 후 변경 필요 시 DB 업데이트</li>
</div>
<br>
<div>
  <h3><label>* Push Message 구조(Android)</label></h3>
  <img src="https://raw.githubusercontent.com/seochangwook/FCMtest/master/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202018-07-07%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%2011.55.44.png" width="600" height="200">
</div>
<br>
<div>
  <h3><label>* Android Oreo Version Update</label></h3>
  <li>Alarm Channel</li>
  <img src="https://raw.githubusercontent.com/seochangwook/FCMtest/master/oreoimage.png" width="170" height="150">
</div>
