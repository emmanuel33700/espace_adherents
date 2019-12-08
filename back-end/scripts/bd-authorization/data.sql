/* bob / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('bob', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, 39);
insert into authorities (username, authority) values ('bob', 'ADHERENT');


/* adherent@gmail.com / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('adherent@gmail.com', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, 39);
insert into authorities (username, authority) values ('adherent@gmail.com', 'ADHERENT');

/* bureau@gmail.com / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('bureau@gmail.com', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, 41);
insert into authorities (username, authority) values ('bureau@gmail.com', 'BUREAU');
insert into authorities (username, authority) values ('bureau@gmail.com', 'ADHERENT');

/* conseil@gmail.com / mot de passe : 234 */
insert into users (username, password, enabled, idAdherent) values ('conseil@gmail.com', '$2a$10$WPDbKLCRnV0UrkEs2IEtUejsZiicxt0/GhUcOkg2.UscjBi8tOmxa', true, 40);
insert into authorities (username, authority) values ('conseil@gmail.com', 'CONSEIL');
insert into authorities (username, authority) values ('conseil@gmail.com', 'BUREAU');
insert into authorities (username, authority) values ('conseil@gmail.com', 'ADHERENT');


/* admin@gmail.com / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('admin@gmail.com', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, 42);
insert into authorities (username, authority) values ('admin@gmail.com', 'ADMIN');
insert into authorities (username, authority) values ('admin@gmail.com', 'CONSEIL');
insert into authorities (username, authority) values ('admin@gmail.com', 'BUREAU');
insert into authorities (username, authority) values ('admin@gmail.com', 'ADHERENT');

