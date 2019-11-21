# Car-Dealership-Inventory-Website
This full stack web application used a Postgres SQL server and Spring Boot framework as an API back end with an Angular front end to host a simple car dealership inventory website. The website requires login to authenticate users, with a sign-up option. Once authenticated, the user is given authorization based on a JSON Web Tokens (JWT). Normal users can see all the cars in the inventory, with the ability to order by and search cars based on a list of preset attributes. The admin is given access to all CRUD operations, allowing the admin to create cars, read all cars same as normal users, update existing cars, and delete cars. All API calls are wrapped in error handling to deal with API errors. The application is configured to run in a docker container.

/* Not finished

How to use: (Commands assume ubuntu)

Step 1: Clone Repository
	git clone https://github.com/EthanWare/Car-Dealership-Inventory-Website.git

Step 2: Install Docker
	sudo apt-get update
	sudo apt-get install docker.io

Step 3: CD to repository folder

Step 4: Start docker damean
	sudo dockerd

Step 5: Run docker compose file
	sudo docker-compose up

Step 6: Open website in browser at http://localhost:4200

*/
