# Contributing to the TracknTrace Framework

First off, thank you for taking the time to contribute! :+1: :tada:

## Code of Conduct
This project is governed by the Marocraft code of Conduct. By participating you are expected to uphold this code. Please report unacceptable behavior to marocraft.community@gmail.com.

## How to contribute
### Discuss
If you have a question, send an email to marocraft.community@gmail.com to start a new discussion if necessary.

If you suspect an issue, perform a search in the [GitHub issue tracker](https://github.com/marocraft/trackntrace/issues), using a few different keywords. When you find related issues and discussions, prior or current, it helps you to learn and it helps us to make a decision.

### Submit a Pull Request
You can contribute a source code change by submitting a pull request.

1. For all but the most trivial of contributions, please create a ticket. The purpose of the ticket is to understand and discuss the underlying issue or feature. We use the GitHub issue tracker as the preferred place of record for conversations and conclusions. In that sense discussions directly under a PR are more implementation detail oriented and transient in nature.

2. Always check out the master branch and submit pull requests against it (for target version see pom.xml). Backports to prior versions will be considered on a case-by-case basis and reflected as the fix version in the issue tracker.

3. Use short branch names, preferably based on the GitHub issue (e.g. 22276), or otherwise using succinct, lower-case, dash (-) delimited names, such as fix-warnings.

4. Choose the granularity of your commits consciously and squash commits that represent multiple edits or corrections of the same logical change. See Rewriting History section of Pro Git for an overview of streamlining commit history.

5. Format commit messages using 55 characters for the subject line, 72 lines for the description, followed by the issue fixed, e.g. Fixes #22276. See the Commit Guidelines section of Pro Git for best practices around commit messages and use git log to see some examples.

6. List the GitHub issue number in the PR description.

If accepted, your contribution may be heavily modified as needed prior to merging. You will likely retain author attribution for your Git commits granted that the bulk of your changes remain intact. You may also be asked to rework the submission.

If asked to make corrections, simply push the changes against the same branch, and your pull request will be updated. In other words, you do not need to create a new pull request when asked to make changes.

### Requirements

For development, you will need:

- Spring 5.x.x
- [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.2+](https://maven.apache.org/download.cgi)

### Get Started

1. Clone the repo.  

``` shell
$ git clone https://github.com/marocraft/trackntrace.git
```

2. Checkout the "develop" branch .  
   
``` shell
$ git checkout develop
```

3. Pull, build and execute.

``` shell
$ git pull
$ mvn clean install
```
