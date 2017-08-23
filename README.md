# speedtest-auto
Automated to test your internet connection on speedtest.net and save the results on a Excel file.

Built with Maven

Dependencies:
  Selenium
  TestNG
  Log4J2
  Apache POI

Jenkins: https://jenkins.io/download/
  Install: Maven Integration plugin
  Configure global variables
  Schedule: H/15 * * * *
  mvn test -PSpeedTestReport
