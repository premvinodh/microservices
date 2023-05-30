<!-- 
	https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#hiding-content-with-comments 
	https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#tables
-->
# microservices
The concept/idea was borrowed from https://github.com/koushikkothagal/spring-boot-microservices-workshop

Contains the following projects
- discovery-server where the microservices are registered with.
- The movie-info-service has information about the movies
- The ratings-data-service has ratings given by user(s)
- The movie-catalog-servie takes the information from the above 2 services and merges the data and shows the movie information along with the user ratings.

Commits and their associated topics 
-----------------------------------
Refer the section below on how to [get a specific commit(https://github.com/premvinodh/microservices#how-to-get-certain-commit-from-github-project)].

| Sl.No		| Topic                                       				| Commit Hash           								|
|:---------:|-----------------------------------------------------------|:-----------------------------------------------------:|
| 	1.		| Load Balancer Client      								| 675a66cca83cdab6bfa4d713c6445674854b9238				|
|	2.		| @LoadBalanced without retry      							| e85708cfbdf7b7bb594cce3ec0c123d320c80655				|
|	3.		| @LoadBalanced with retry (without retry properties)     	| 9c258733a619d16712b8ea6e90e2518233c76eb8				|
|	4.		| @LoadBalanced with retry (with retry properties)     		| 78128daa3eff377df0303bd5e75168b0d5d83620				|
|	5.		| Circuit Breaker with fallback funcationality			    | 7db33ca7246dba84b948dcc517051395f2c5bd85				|
|	6.		| Granular fallback for invididual rest api calls		    | 40578f1f90f1d4bfaf6fae0073eb7105b3ef2b79				|
|	7.		| Hystrix Dashabord for MovieCatalogService microservice    | 6ddcac40b9233896ed3c4d1c1e4a409c158cf716				|

### How to get certain commit from GitHub project
------------------------------------------------
1. First, clone the repository using git as shown below
git clone https://github.com/premvinodh/microservices.git
That downloads the complete history of the repository, so you can switch to any version. Next, change into the newly cloned repository:

2. cd <complete_path_on_your_system>\microservices

3. Use git checkout <COMMIT> to change to the right commit of a particular topic
git checkout 675a66cca83cdab6bfa4d713c6445674854b9238
