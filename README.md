# Fuel Calculator with Localization (JavaFX, MariaDB & Docker)

This project is a JavaFX application that calculates fuel consumption and trip cost.  
It supports four languages: English (EN), French (FR), Japanese (JP), and Farsi (IR).  
All UI text is loaded dynamically from a MariaDB database.


## 1. Project Structure
- **src/main/java/**: Java source files  
- **src/main/resources/**: FXML, CSS  
- **database.sql**: Database schema + sample data  
- **Dockerfile**: Builds the application image  
- **docker-compose.yml**: Runs app + MariaDB  
- **Jenkinsfile**: CI/CD pipeline (optional)  


## 2. Requirements

- Java 21  
- Docker Desktop  
- Xming (for running JavaFX UI from inside Docker on Windows)  
- No additional database tools required  


## 3. Database Setup Instructions

The database is created automatically by Docker using `database.sql`.

### Database details
**Database**: fuel_calculator_localization  
**User**: test  
**Password**: Test123  
**Port**: 3306  
**Host inside Docker**: db  
**Host on local machine**: localhost  

## 4. Accessing the database

Enter the MariaDB container:  
`docker exec -it calculator-db sh`

Log in:  
`mariadb -u test -p`

Enter password:  
`Test123`

Select database:  
`USE fuel_calculator_localization;`

View localization data:  
`SELECT * FROM localization_strings;`

View calculation results:  
`SELECT * FROM calculation_records;`


## 5. Running the application

Start the system:  
`docker compose up --build`

Stop the system:  
`docker compose down`

Reset database completely:  
`docker compose down -v`


## 6. Sample Data
The database.sql file includes sample localization strings for:

- English
- French
- Japanese
- Farsi

These are loaded automatically when the database is created.
