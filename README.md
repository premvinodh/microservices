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
