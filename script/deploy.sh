REPOSITORY=/home/ubuntu/mm-auto-care

cd $REPOSITORY/mm-auto-care-website-server


echo "> Git Pull"

git pull

echo "> Permission Denied 방지"

chmod +x gradlew

echo "> build 시작"

./gradlew build

echo "> Build 파일 복사"

cp ./build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f mm-auto-care)

echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> 구동중인 애플리케이션 종료 kill -2 $CURRENT_PID"
    kill -9 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ |grep 'mm-auto-care' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar -DSpring.profiles.active=prod $REPOSITORY/$JAR_NAME 1> /dev/null 2>&1 &