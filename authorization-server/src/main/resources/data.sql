/* bob / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('bob', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, '39');
insert into authorities (username, authority) values ('bob', 'ADHERENT');


/* bob / mot de passe : 234 */
insert into users (username, password, enabled, idAdherent) values ('sara', '$2a$10$WPDbKLCRnV0UrkEs2IEtUejsZiicxt0/GhUcOkg2.UscjBi8tOmxa', true, '40');
insert into authorities (username, authority) values ('sara', 'CONSEIL');


/* gege / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('gege', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, '41');
insert into authorities (username, authority) values ('gege', 'BUREAU');


/* tete / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('tete', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, '42');
insert into authorities (username, authority) values ('tete', 'ADMIN');


/* adherent@gmail.com / mot de passe : 123 */
insert into users (username, password, enabled, idAdherent) values ('adherent@gmail.com', '$2a$10$/ns.CwZ9sdhQaVjw/bwBQeelnmTZTI19trLtyY/bjbIVUokAckX8y', true, '39');
insert into authorities (username, authority) values ('adherent@gmail.com', 'ADHERENT');