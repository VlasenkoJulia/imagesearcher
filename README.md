To run application:
1. Run Postgres docker image (docker run -it --name img_db -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=img_db postgres:11.7)
2. Run application (entry point ImagesearcherApplication.java)