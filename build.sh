if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [[ $TRAVIS_BRANCH = 'master'  ||  ${TRAVIS_BRANCH} = 'develop' ]]; then
      echo 'Checking Quality Gates'
      mvn -B clean verify sonar:sonar \
      -Dsonar.host.url=${SONAR_URL} \
      -Dsonar.login=${SONAR_LOGIN} \
      -Dsonar.projectKey=${SONAR_PROJECT} \
      -Dsonar.organization=${SONAR_ORGANIZATION}
fi