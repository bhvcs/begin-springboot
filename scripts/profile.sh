#!/usr/bin/env bash

# 쉬고 있는 profile ckwrl: real1 <-> real2

function find_idle_profile()
{
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile) #현재 엔진엑스가 바라보고 있는 스프링 부트가 정상 수행중인지 호가인하는 코드

  if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면 에러
  then
    CURRNET_PROFILE=real2
  else
    CURRNET_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRNET_PROFILE} == real1 ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi #IDLE_PROFILE은 엔진엑스와 연결되지 않은 profile

  echo "${IDLE_PROFILE}" # my: 마지막! 줄의 에코는 반환의 의미..
}
  #쉬고 있는 profile의 port 찾기
function find_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi

}
