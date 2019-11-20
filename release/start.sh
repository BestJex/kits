#!/bin/bash
tpid=`cat tpid|awk '{print $1}'`
tpid=`ps -aef|grep $tpid|awk '{print $2}'|grep $tpid`
if [ ${tpid} ];
then
  echo kits is already running!
else
  rm -f tpid
  nohup java -jar kits.jar --Dspring.config.location=application.yml &
  echo 'kits is started.'
  echo $! > tpid
fi