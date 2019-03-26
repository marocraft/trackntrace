echo "Deploy for tag: $TRAVIS_TAG - branch: $TRAVIS_BRANCH pr: $TRAVIS_PULL_REQUEST_BRANCH"
echo "Deploy for tag: $TRAVIS_TAG - branch: ${TRAVIS_BRANCH}"
export BRANCH=$TRAVIS_BRANCH
echo "the travis branch is: $BRANCH"
if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [ ${TRAVIS_BRANCH} = 'snapshot' ]; then
    echo 'Deploying snapshot to OSS'
    mvn clean deploy --settings ./maven.xml -DskipTests=true -Pdeploy;
    exit $?;
fi