# FCMTestApp
<div>
  <h3><label>* FCM 참조 사이트</label></h3>
  <br><br>
  <li>FCM 프로젝트 콘솔 : https://console.firebase.google.com/</li>
  <li>FCM 문서 : https://firebase.google.com/docs/guides/?authuser=0</li>
  <li>FCM & Spring Boot : http://javasampleapproach.com/spring-framework/spring-boot/firebase-cloud-messaging-server-spring-to-push-notification-example-spring-boot </li>
</div>
<br><br>
<div>
  <h3><label>* FCM Token 저장</label></h3>
  <br><br>
  <li>1. 처음 App을 설치 시 토큰값 부여(처음 실행 시 에만 부여)</li>
  <li>2. 부여된 토큰값을 서버로 전송</li>
  <li>3. 전송된 토큰값을 DB에 저장</li>
  <li>4. App 재접속 시 토큰값 유효성 비교 후 변경 필요 시 DB 업데이트</li>
</div>
