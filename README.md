This is experimental project which store user private data (usernames, passwords and sites). 
Backend implemented with Spring and other project from Spring family. Frontend is made of Angular 4 with bootstrap Angular Material 4.
All private data are encrypted with AES. Decrypt this data takes place inside frontend, so all data from server goes encrypted.

Clone repository and
For backend:
Run - main
For frontend:
cd frontend/src/main/frontend
ng serve --proxy-config proxy.conf.json
(//localhost:4200)