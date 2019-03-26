echo "Deploy for tag: $TRAVIS_TAG - branch: $TRAVIS_BRANCH"
echo "Deploy for tag: $TRAVIS_TAG - branch: ${TRAVIS_BRANCH}"

if [ ${TRAVIS_PULL_REQUEST} = 'true' ] && [ ${TRAVIS_BRANCH} = 'snapshot' ]; then
    echo 'Deploying snapshot to OSS 1'
fi
if [ ${TRAVIS_PULL_REQUEST} = 'true' ] && [ ${TRAVIS_BRANCH} != 'snapshot' ]; then
    echo 'Deploying snapshot to OSS 2'
fi
if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [ ${TRAVIS_BRANCH} = 'snapshot' ]; then
    echo 'Deploying snapshot to OSS 3'
fi
if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [ ${TRAVIS_BRANCH} != 'snapshot' ]; then
    echo 'Deploying snapshot to OSS 4'
fi
if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [ ${TRAVIS_BRANCH} = 'snapshot' ]; then
    echo 'Deploying snapshot to OSS'
    mvn clean deploy --settings ./maven.xml -DskipTests=true -Pdeploy;
    exit $?;
fi