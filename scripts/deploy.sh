#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot-posts-service

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl springboot-posts-service | grep jar | awk '{print $1}')
# springboot-posts-service를 이름으로 하는 다른 프로그램이 있을 수도 있으니 jar 프로세스를 찾고 id를 찾는다

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME # nohup이 실행할 수 있도록 실행 권한을 부여

echo "> $JAR_NAME 실행"

nohup java -jar -Dspring.config.location=classpath:/application.yml,classpath:/application-real.yml,/home/ec2-user/app/application-oauth.yml,/home/ec2-user/app/application-real-db.yml -Dspring.profiles.active=real $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

#기존의 deploy.sh와 다른점: git pull을 통해 직접 빌드하는 부분을 제거