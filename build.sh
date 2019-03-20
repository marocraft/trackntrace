if [ ${TRAVIS_PULL_REQUEST} = 'false' ] && [[ ${TRAVIS_BRANCH} = 'master'  ||  ${TRAVIS_BRANCH} = 'develop' ]]; then
      echo 'Checking Quality Gates'
      mvn -B clean verify sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_LOGIN} -Dsonar.projectKey=${SONAR_PROJECT} -Dsonar.organization=${SONAR_ORGANIZATION}
	  
	## export GPG details
		echo 'export GPG'
		echo ${GPG_SECRET_KEYS}  | ${GPG_EXECUTABLE --import}
		echo ${GPG_OWNERTRUST}   | ${GPG_EXECUTABLE --import-ownertrust}
	
	## Build and release to maven central  
		echo 'Build and release to maven central'
		mvn clean deploy --settings .maven.xml -DskipTests=true -B -U -Prelease
		
elif [ ${TRAVIS_PULL_REQUEST} != 'false' ]; then 
      echo 'Build and analyze pull request'
      mvn -B clean verify sonar:sonar -Dsonar.host.url=${SONAR_URL} -Dsonar.projectKey=${SONAR_PROJECT} -Dsonar.organization=${SONAR_ORGANIZATION} -Dsonar.login=${SONAR_LOGIN} -Dsonar.github.oauth=${SONAR_GITHUB_OAUTH} -Dsonar.pullrequest.github.repository=${TRAVIS_REPO_SLUG} -Dsonar.pullrequest.provider=GitHub  -Dsonar.pullrequest.branch=${TRAVIS_BRANCH}  -Dsonar.pullrequest.key=${TRAVIS_PULL_REQUEST};
fi