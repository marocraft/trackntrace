echo "Deploy for tag: $TRAVIS_TAG - branch: $TRAVIS_BRANCH"

if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [ ${TRAVIS_BRANCH} = 'snapshot' ]; then
    echo 'Deploying snapshot to OSS in branch ${TRAVIS_BRANCH}'
    mvn clean deploy --settings ./.maven.xml -DskipTests=true -Possrh;
    exit $?;
fi