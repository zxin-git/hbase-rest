#!/bin/sh
exec java -Xms512M -Xmx512M -XX:NewRatio=1 -Xss256k -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:"./log/gc.log" -cp ".:resources:./*:lib/*:umflib/*" $MAIN_CLASS
