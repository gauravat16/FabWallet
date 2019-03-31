
INSERT INTO USER (id,first_name,last_name,password) VALUES ("han@starwars.com", "han", "solo", "$2a$10$Xjisp/cesT2E6tPj5vm3BuqkdaQi4QXeSdwdItFT3hHEbc4HdmEUy" );
INSERT INTO USER (id,first_name,last_name,password) VALUES ("luke@starwars.com", "luke", "skywalker", "$2a$10$P7GBax3pb0XrA.5.POQ/KuSBtvckFzz3XZcAIMFtAkDXX3QXVr0Ne" );
INSERT INTO USER (id,first_name,last_name,password) VALUES ("leia@starwars.com", "leia", "organa", "$2a$10$P7GBax3pb0XrA.5.POQ/KuSBtvckFzz3XZcAIMFtAkDXX3QXVr0Ne" );


INSERT INTO WALLET(user_id,balance) VALUES ("han@starwars.com",800.0);
INSERT INTO WALLET(user_id,balance) VALUES ("luke@starwars.com",90.0);
INSERT INTO WALLET(user_id,balance) VALUES ("leia@starwars.com",9999.0);